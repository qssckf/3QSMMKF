package nc.bs.so.qs.sc.planbill.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.BizLockRule;
import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.bd.bp.template.UpdateBPTemplate;
import nc.bs.so.qs.sc.plugin.PBPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillUpdateBP {
	
	public PlanBillUpdateBP(){};
	
	public MmPlanBillVO[] update(MmPlanBillVO[] updateVo,MmPlanBillVO[] oldVo){
		
		UpdateBPTemplate<MmPlanBillVO> bpt=new UpdateBPTemplate(PBPluginPoint.UPDATE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.update(updateVo, oldVo);
		

		
	}

	private void addAfterRule(CompareAroundProcesser<MmPlanBillVO> compareAroundProcesser) {
		// TODO 自动生成的方法存根
		
		
	}

	private void addBeforeRule(CompareAroundProcesser<MmPlanBillVO> compareAroundProcesser) {
		// TODO 自动生成的方法存根
		
		compareAroundProcesser.addBeforeRule(new StringFieldTrimRule());
		
//		compareAroundProcesser.addBeforeRule(new BDPKLockSuperVORule());
		
		compareAroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "plandate", "bomid","vbomversion" })));
		
		compareAroundProcesser.addBeforeRule(new UpdateAuditInfoRule());
		
	}

}
