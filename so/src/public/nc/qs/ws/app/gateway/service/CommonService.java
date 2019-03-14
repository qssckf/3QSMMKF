package nc.qs.ws.app.gateway.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nc.bs.framework.common.NCLocator;
import nc.qs.ws.app.exception.AppRuntimeException;
import nc.qs.ws.app.gateway.service.xml.ServiceNode;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.RowSet;

public class CommonService {
	
	public CommonService(){
		
	}
	
	public static JSONArray invokeNCService(ServiceNode sn, Class<?>[] paraTypes, Object[] paras,IInvokeCallback callback) throws Exception{
		
		 Method method = null;
		 Object ncObj = null;
		 String parastring=null;
		 String paramsStr = sn.getPropertiesMap().get("params").toString().trim();
		 
		 for(Object obj:paras){
			 
			 parastring=parastring+"|"+obj;
			 
		 }
		
		 try{
			 Object obj=NCLocator.getInstance().lookup(sn.getPropertiesMap().get("interface").toString().trim());
			 
			 if ((paraTypes.length == 1) && (null == paraTypes[0])) { 
				 method = obj.getClass().getMethod(sn.getMethod().trim(), new Class[0]);
				 ncObj=method.invoke(obj, new Object[0]);
			 }else{
				 method = obj.getClass().getMethod(sn.getMethod().trim(), paraTypes);
				 ncObj=method.invoke(obj, paras);
			 }
			 
		 }catch(Exception e){
			 			 
			 if(e instanceof InvocationTargetException){  
				 throw new AppRuntimeException("0",e,"NC Servic exec process error!error msg:"+((InvocationTargetException)e).getTargetException().getMessage());
			 }else{
				 throw new AppRuntimeException("0",e,"NC Servic exec process error!error msg:"+e.getMessage());
			 }
			 
		 }
		
		return callback.handleResult(ncObj, sn);
		
	}
	
	public static JSONArray ConvertListMap2Json(List<Map<String,Object>> retObj){
		
		JSONArray jsons = new JSONArray();
		
		if(retObj==null || retObj.size()==0){
			throw new AppRuntimeException("没有找到符合条件的记录");
		}
		
		try{
			
			for(Map<String,Object> map:retObj){
				
				JSONObject json=new JSONObject();
				
				for(String key:map.keySet()){
					
					json.put(key, map.get(key));
					
				}
				
				jsons.put(json);

			}
	
		}catch(Exception e){
			throw new AppRuntimeException("返回结果解析错误，请检查！");
		}
		
		return jsons;
		
	}
	
	
	public static JSONArray ConvertRowset2Json(IRowSet rowset,Map<String, String[]> fileds){
		
		JSONArray jsons = new JSONArray();
		
		try{
			
			while(rowset.next()){
				
				JSONObject json=new JSONObject();
				
				if(fileds.get("filed").length>0&&fileds.get("index").length>0){
					for(int i=0;i<fileds.get("filed").length;i++){
							json.put(fileds.get("filed")[i], rowset.getString(Integer.valueOf(fileds.get("index")[i]))==null?"":rowset.getString(Integer.valueOf(fileds.get("index")[i])));
					}
				}else{
					throw new AppRuntimeException("0","Not define Return Data Field Info!");
				}
				
				jsons.put(json);
			}
			
		}catch(Exception e){
			throw new AppRuntimeException("0",e,e.getMessage());
		}
		
		
		return jsons;
	}

	public static JSONArray ConvertString2Json(String retObj) {
		// TODO 自动生成的方法存根
		JSONArray jsons = new JSONArray();
		
		JSONObject json=new JSONObject();
		
		if (retObj.startsWith("|")) {
			retObj = retObj.subSequence(1, retObj.length()).toString();
		}
		if (retObj.endsWith("|")) {
			retObj = retObj.substring(0, retObj.length() - 1);
		}
		
		String[] paramsStrs = retObj.split("\\|");
		
		if("0".equals(paramsStrs[0])){
			throw new AppRuntimeException(paramsStrs[1]);
		}else if("1".equals(paramsStrs[0])){

			try {
				
				json.put("SuccessMsg", paramsStrs[1]);
				jsons.put(json);
				
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				throw new AppRuntimeException("json转换错误");
			}
		}
		
		
		
		
		return jsons;
	}
	
	
	public static JSONArray ConvertMap2Json(Map retObj) {
		
		JSONArray jsons = new JSONArray();
		
		if(retObj == null || retObj.size()==0){
			
			throw new AppRuntimeException("没有找到符合条件的记录");
			
		}else{
			
			try{
				
				Set<String> keys=retObj.keySet();
				
				for(String key:keys){
					
					JSONObject json=new JSONObject();
					
					json.put(key,retObj.get(key));
					
					jsons.put(json);
					
				}
				
			}catch(Exception e){
				
				throw new AppRuntimeException("审批结果类型转换错误！");
				
			}
			
			
		}
		
		
		return jsons;
		
	}

	public static JSONArray ConvertBillMap2Json(Map<String, Object> map) throws JSONException {
		// TODO 自动生成的方法存根
		
		JSONArray jsons = new JSONArray();
		
		if(map == null || map.size()==0){
			
			throw new AppRuntimeException("没有找到符合条件的记录");
			
		}else{
		
		for(String key:map.keySet()){
			
			JSONObject json=new JSONObject();
			
			if("taskbill".equals(key) || "tranbill".equals(key)){
				
				JSONObject bill=new JSONObject();
				
				LinkedHashMap headmap=(LinkedHashMap) map.get(key);
				
				List<Map<String, Object>> headinfos=(List<Map<String, Object>>) headmap.get("head");
				
				JSONArray heanjsons=new JSONArray();
				
				for(Map<String, Object> headinfo:headinfos){
					
					LinkedHashMap headinfo1=(LinkedHashMap) headinfo.get("tabContent");
					
					List<Map<String, Object>> headinfo2s=(List<Map<String, Object>>) headinfo1.get("billItemData");
					   
					JSONArray billItemjsons=new JSONArray();
					   
					for(Map<String, Object> headinfo2:headinfo2s){
						   
						  JSONObject billitem=new JSONObject();
						   
						  for(String headitemkey:headinfo2.keySet()){

							  billitem.put(headitemkey, headinfo2.get(headitemkey));
							   
						   }
						   
						  billItemjsons.put(billitem);
					}
					
					JSONObject jsonitem=new JSONObject();
					jsonitem.put("billItemData", billItemjsons);
					jsonitem.put("tabCode",headinfo.get("tabCode"));
					jsonitem.put("tabName",headinfo.get("tabName"));
					
					heanjsons.put(jsonitem);
					

				}
				   
				
				
				List<Map<String, Object>> bodyinfos=(List<Map<String, Object>>) headmap.get("body");
				
				JSONArray bodyjsons=new JSONArray();
				
				for(Map<String, Object> bodyinfo:bodyinfos){
					
					JSONArray tabconts=new JSONArray();
					
					List<Map<String, Object>> tabs = (List<Map<String, Object>>) bodyinfo.get("tabContent");

					for(Map<String, Object> tab:tabs){
						
						List<Map<String, Object>> bodyinfo2s=(List<Map<String, Object>>) tab.get("billItemData");
						
						JSONArray billItemjsons=new JSONArray();
						
						for(Map<String, Object> bodyinfo2:bodyinfo2s){
							   
							  JSONObject billitem=new JSONObject();
							   
							  for(String bodyitemkey:bodyinfo2.keySet()){

								  billitem.put(bodyitemkey, bodyinfo2.get(bodyitemkey));
								   
							  }
							   
							  billItemjsons.put(billitem);
						}
						
						JSONObject tabcont=new JSONObject();
						
						tabcont.put("billItemData", billItemjsons);
						
						tabconts.put(tabcont);
					}
				
					
					JSONObject jsonitem=new JSONObject();
					jsonitem.put("billItemData", tabconts);
					jsonitem.put("tabCode",bodyinfo.get("tabCode"));
					jsonitem.put("tabName",bodyinfo.get("tabName"));
					
					bodyjsons.put(jsonitem);

				}
				
				bill.put("body", bodyjsons);
				bill.put("head", heanjsons);
				   
				json.put("taskbill", bill);
				
			}else{
				json.put(key, map.get(key));
			}
			
			
			jsons.put(json);
		}
		
		
		return jsons;
	}}
		

}
