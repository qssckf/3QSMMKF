package nc.qs.mobile.ws.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import nc.qs.mobile.ws.serv.IAppDispatcherService;
import nc.qs.ws.app.exception.AppRuntimeException;
import nc.qs.ws.app.gateway.service.CommonService;
import nc.qs.ws.app.gateway.service.IInvokeCallback;
import nc.qs.ws.app.gateway.service.NcServiceFactory;
import nc.qs.ws.app.gateway.service.xml.ServiceNode;
import nc.qs.ws.app.pub.ContextUtil;
import nc.qs.ws.app.pub.reqData;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.bs.logging.Logger;

public class AppDispatcherServerImpl implements IAppDispatcherService {

	@Override
	public String doService(String req) {
		// TODO 自动生成的方法存根
		
		try{
		
			Logger.debug(new Date()+":进入AppDispatcherService处理进程！");
			
			reqData data=ContextUtil.Parse(req);
			
			ServiceNode sn=NcServiceFactory.FindNCService(data.getAppid(), data.getServiceid());
			
			if(sn!=null){
				
				String paramsStr = sn.getPropertiesMap().get("params").toString().trim();
				
				Map<String, String[]> methodMap = ContextUtil.convertParams(paramsStr,"names","types");
				
				Class[] methodClasses = ContextUtil.getMethodClasses((String[])methodMap.get("types"));
				
				Object[] methodObjects =ContextUtil.getMethodObjects((String[])methodMap.get("names"), data.getParams());
				
				JSONArray obj=CommonService.invokeNCService(sn, methodClasses, methodObjects,new IInvokeCallback(){

					@Override
					public JSONArray handleResult(Object retObj, ServiceNode sn) throws JSONException {
						// TODO 自动生成的方法存根
						
						String convertType=sn.getPropertiesMap().get("converttype").toString().trim();
						
						if("sql".equalsIgnoreCase(convertType)){
							return CommonService.ConvertRowset2Json((IRowSet)retObj,ContextUtil.convertParams(sn.getPropertiesMap().get("field").toString(), "filed", "index"));
						}if("string".equalsIgnoreCase(convertType)){
							return CommonService.ConvertString2Json((String)retObj);
						}if("map".equalsIgnoreCase(convertType)){
							return CommonService.ConvertMap2Json((Map)retObj);
						}if("listmap".equals(convertType)){
							return CommonService.ConvertListMap2Json(((List)retObj));
						}if("billmap".equals(convertType)){
							return CommonService.ConvertBillMap2Json(((Map<String,Object>)retObj));
						}else{
							throw new AppRuntimeException("0","error convert type!");
						}
					}
					
				});
				
				return ContextUtil.processSuccessfuly(obj);
				
			}else{
				
				throw new AppRuntimeException("0","not find NC service,service id for:"+data.getServiceid()); 
				
			}

			
		}catch(Exception e){
			
			e.printStackTrace();
			
			String ret=ContextUtil.processErr(e);
			
			return ret;
			
		}
		

	}

}
