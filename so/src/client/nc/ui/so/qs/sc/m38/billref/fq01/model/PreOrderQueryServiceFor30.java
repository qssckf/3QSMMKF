package nc.ui.so.qs.sc.m38.billref.fq01.model;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.IShipmentsInfoMaintain;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.so.m38.entity.PreOrderVO;

public class PreOrderQueryServiceFor30 implements nc.ui.pubapp.uif2app.query2.model.IRefQueryService{

	@Override
	public Object[] queryByWhereSql(String arg0) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
		// TODO 自动生成的方法存根
		
		PreOrderVO[] retbills = null;
		IShipmentsInfoMaintain service = (IShipmentsInfoMaintain)NCLocator.getInstance().lookup(IShipmentsInfoMaintain.class);
		
		try{
			
			retbills = service.queryPreOrderFor30(queryScheme);
			
		}catch (BusinessException e) {
			
			nc.vo.pubapp.pattern.exception.ExceptionUtils.wrappException(e);
		}
		
		return retbills;
	}



}
