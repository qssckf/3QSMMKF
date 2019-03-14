package nc.bs.so.qs.sc.planbill.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.template.UpdateBPTemplate;
import nc.bs.so.qs.planbill.bp.rule.PlanBillExpendByTMRule;
import nc.bs.so.qs.sc.plugin.PBPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillExendBPByTM {
	
	public PlanBillExendBPByTM(){}
	
	public MmPlanBillVO[] ExpendByTM(MmPlanBillVO[] updateVo,MmPlanBillVO[] oldVo){
		
		UpdateBPTemplate<MmPlanBillVO> bpt=new UpdateBPTemplate(PBPluginPoint.EXEC);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.update(updateVo, oldVo);
		
		
	}

	private void addAfterRule(CompareAroundProcesser<MmPlanBillVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addAfterRule(new PlanBillExpendByTMRule());
	}

	private void addBeforeRule(CompareAroundProcesser<MmPlanBillVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "sfexand"})));
	}

}
