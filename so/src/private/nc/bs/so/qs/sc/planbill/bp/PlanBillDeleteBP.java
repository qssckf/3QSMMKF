package nc.bs.so.qs.sc.planbill.bp;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.FireEventRule;
import nc.bs.bd.bp.rule.VersionValidateRule;
import nc.bs.bd.bp.rule.WriteBusiLogRule;
import nc.bs.bd.bp.rule.delete.BDReferenceCheckerRule;
import nc.bs.bd.bp.rule.delete.NotifyVersionChangeWhenDataDeletedRule;
import nc.bs.bd.bp.template.DeleteBPTemplate;
import nc.bs.so.qs.sc.plugin.PBPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillDeleteBP {
	
	public PlanBillDeleteBP(){
		
	}
	
	public String[] delete(MmPlanBillVO[] vos){
		
		DeleteBPTemplate<MmPlanBillVO> bpt=new DeleteBPTemplate(PBPluginPoint.DELETE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		String[] teStrings = bpt.delete(vos);
		
		return teStrings;
		
	}
	
	private void addAfterRule(AroundProcesser<MmPlanBillVO> ard) {
		// TODO 自动生成的方法存根
		
		ard.addAfterRule(new FireEventRule("1006"));
		
		ard.addAfterRule(new WriteBusiLogRule("delete"));
		
	}

	private void addBeforeRule(AroundProcesser<MmPlanBillVO> ard) {
		// TODO 自动生成的方法存根
		
		ard.addBeforeRule(new BDPKLockSuperVORule());
		ard.addBeforeRule(new VersionValidateRule());
		ard.addBeforeRule(new BDReferenceCheckerRule());
		ard.addBeforeRule(new FireEventRule("1005"));
		ard.addBeforeRule(new NotifyVersionChangeWhenDataDeletedRule());
	}

}
