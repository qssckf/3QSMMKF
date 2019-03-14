package nc.bs.so.qs.planbill.bp.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.Validator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.uif2.IExceptionHandler;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;

public class PlanBillUnExpendRule extends BatchDocBaseDAO implements IRule{

	private IPlanBillSerive PlanService;
	private IExceptionHandler exceptionHandler;
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	
	@Override
	public void process(Object[] vos) {
		// TODO 自动生成的方法存根
		
		try {
			
			if(vos instanceof MmPlanBillVO[]){
			
				MmPlanBillVO[] pbvos=(MmPlanBillVO[]) vos;
				
				for(MmPlanBillVO vo:pbvos){
					
					String pk_planbill=vo.getPk_planbill();
					
					Collection<PlanProduceDetailVO> col=this.getMDQueryService().queryBillOfVOByCond(PlanProduceDetailVO.class, "pk_planbill='"+pk_planbill+"' and dr=0", false);
					
					BomInfoUnExpend(col.toArray(new PlanProduceDetailVO[col.size()]));
					
				}
			
			}
		}catch(BusinessException e){
			
			ExceptionUtils.wrappException(e);
			
		}
		
		
		
		
	}

	private void BomInfoUnExpend(PlanProduceDetailVO[] obj) throws BusinessException {
		// TODO 自动生成的方法存根
		
		BatchOperateVO returnVO = new BatchOperateVO();
		
		List<Validator> deleteValList = new ArrayList();
		
		deleteValList.add(new UnExpendChkValidation());
		
		returnVO.setDelObjs(obj);
		
		Object[] retObj = null;
		if (returnVO != null) {
			retObj = batchSave(returnVO, null, null, deleteValList).getDelObjs();
		}

		
	}
	
	
	
	
	
	

}
