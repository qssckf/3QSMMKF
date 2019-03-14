package nc.bs.so.qs.sc.intoprod.bp;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.VersionValidateRule;
import nc.bs.bd.bp.rule.delete.BDReferenceCheckerRule;
import nc.bs.bd.bp.template.DeleteBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.readyplan.bp.rule.ReWriteByDeleMmPlanRule;
import nc.bs.so.qs.sc.intoprod.bp.rule.ReWriterByDeleteRdRule;
import nc.bs.so.qs.sc.plugin.IpPluginPoint;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IntoProdDeleteBP {
	
	public IntoProdDeleteBP(){
		
	}
	
	public void delete(IntoProdDetailVO[] vos) {
		
		DeleteBPTemplate<IntoProdDetailVO> bpt=new DeleteBPTemplate(IpPluginPoint.DELETE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		bpt.delete(vos);
		
	}

	private void addAfterRule(AroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addAfterRule(new ReWriterByDeleteRdRule());
	}

	private void addBeforeRule(AroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addBeforeRule(new BDPKLockFatherVORule());
		aroundProcesser.addBeforeRule(new BDPKLockSuperVORule());
		aroundProcesser.addBeforeRule(new VersionValidateRule());
		aroundProcesser.addBeforeRule(new BDReferenceCheckerRule());
	}

}
