package nc.ui.so.qs.intoprod.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.bill.service.IAppModelServiceEx;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.pub.BusinessException;

public class RdAppModelService implements IAppModelServiceEx{
	
	private IRdMmService rdservice;

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		
		return rdservice;
		
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	@Override
	public Object insert(Object paramObject) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object update(Object paramObject) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void delete(Object paramObject) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Object[] queryVOsByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		return this.getRdservice().queryVOsByPksByIp(pks);

	}

	@Override
	public String[] queryVosPksBySqlWhere(String[] orgIDs, String condition) throws BusinessException {
		// TODO 自动生成的方法存根
		
		StringBuilder builder = new StringBuilder();
		 
		builder.append("(" + condition + ")");
		
		String inSql = BDSqlInUtil.getInSql(orgIDs, false);
		 
		builder.append(" and (pk_org in ").append(inSql + ")");
		
		
		
		
		 
		return this.getRdservice().queryVOPksByIp(builder.toString());
	}

}
