package nc.ui.so.fq01.billref.m30.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.IShipmentsInfoMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.qs.sc.AggShipmentsVO;

public class FQOrderQueryServiceFor30 implements nc.ui.pubapp.uif2app.query2.model.IRefQueryService{

	@Override
	public Object[] queryByWhereSql(String paramString) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme Scheme) throws Exception {
		// TODO 自动生成的方法存根
		
		AggShipmentsVO[] retbills=null;
		
		IShipmentsInfoMaintain service=(IShipmentsInfoMaintain)NCLocator.getInstance().lookup(IShipmentsInfoMaintain.class);
		
		retbills=service.queryShipmentsFor30(Scheme);
		
		return retbills;
	}

}
