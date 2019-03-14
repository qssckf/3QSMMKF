package nc.bs.so.qs.sc.readyplan.bp;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.VersionValidateRule;
import nc.bs.bd.bp.rule.delete.BDReferenceCheckerRule;
import nc.bs.bd.bp.template.DeleteBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.readyplan.bp.rule.ReWriteByDeleMmPlanRule;
import nc.bs.so.qs.readyplan.bp.rule.ReWriteMmPlanRule;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdDeleteBP {
	
	public RdDeleteBP(){
		
	};
	
	public void delete(RdPorductDetailVO[] vos) {
		
		DeleteBPTemplate<RdPorductDetailVO> bpt=new DeleteBPTemplate(RdPluginPoint.DELETE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		bpt.delete(vos);
		
	}

	private void addAfterRule(AroundProcesser<RdPorductDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addAfterRule(new ReWriteByDeleMmPlanRule());
	}

	private void addBeforeRule(AroundProcesser<RdPorductDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new BDPKLockFatherVORule());
		aroundProcesser.addBeforeRule(new BDPKLockSuperVORule());
		aroundProcesser.addBeforeRule(new VersionValidateRule());
		aroundProcesser.addBeforeRule(new BDReferenceCheckerRule());
		
		
	}

}
