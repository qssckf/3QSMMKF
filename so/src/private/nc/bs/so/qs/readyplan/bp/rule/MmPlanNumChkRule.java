package nc.bs.so.qs.readyplan.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.util.IQLock;

public class MmPlanNumChkRule<E extends SuperVO> implements IRule<E> {
	
	public static IMDPersistenceQueryService getMDQueryService()
	{
		return MDPersistenceService.lookupPersistenceQueryService();
	}

	@Override
	public void process(E[] objs) {
		// TODO 自动生成的方法存根
		
		try{
			
			for(SuperVO obj:objs){
				
				String srcbid=(String) obj.getAttributeValue("vsrcplanbid");
				
				PlanProduceDetailVO detailvo=this.getMDQueryService().queryBillOfVOByPK(PlanProduceDetailVO.class, srcbid, false);
				
				if(detailvo!=null){
					
					if(detailvo.getTotalnum().toDouble()>=detailvo.getPlannum().toDouble()){
						throw new BusinessException("排产数量已经超过计划数量，不能保存！");
					}
					
				}else{
					throw new BusinessException("没有找到来源单据，排产保存异常，请重试！");
				}
				
				
			}
			
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		
		
		
	}

}
