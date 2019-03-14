package nc.ui.so.qs.mmplanbill.service;

import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.bill.service.IAppModelServiceEx;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.uif2.LoginContext;

public class PlanBillAppModelService implements IAppModelServiceEx {

	private IPlanBillSerive planbillservice;
	
	public IPlanBillSerive getPlanbillservice() {
		
		if(this.planbillservice==null){
			this.planbillservice=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		
		return planbillservice;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	@Override
	public Object insert(Object objs) throws Exception {
		// TODO 自动生成的方法存根
	
		return null;
		
		
	}

	@Override
	public Object update(Object objs) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void delete(Object objs) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Object[] queryVOsByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		return this.getPlanbillservice().queryVObyPks(pks);
	
	}
	
	public String[] queryVosPksBySqlWhere(String[] orgIDs, String condition) throws BusinessException{
		
		
		StringBuilder builder = new StringBuilder();
		 
		builder.append("(" + condition + ")");
		
		String inSql = BDSqlInUtil.getInSql(orgIDs, false);
		 
		builder.append(" and (so_preorder_b.pk_org in ").append(inSql + ")");
		 
		return this.getPlanbillservice().queryVOPks(builder.toString());
		

		
	}

	

}
