package nc.bs.so.qs.sc.intoprod.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.bd.bp.template.UpdateBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.sc.intoprod.bp.rule.RdNumChRule;
import nc.bs.so.qs.sc.intoprod.bp.rule.ReWriteByUpdateRdRule;
import nc.bs.so.qs.sc.plugin.IpPluginPoint;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.IntoProdDetailVO;

public class IntoProdUpdateBP {
	
	public IntoProdUpdateBP(){
		
	}
	
	public IntoProdDetailVO[] update(IntoProdDetailVO[] updateVo,IntoProdDetailVO[] oldVo) throws Exception{
		
		
		UpdateBPTemplate<IntoProdDetailVO> bpt=new UpdateBPTemplate(IpPluginPoint.UPDATE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.update(updateVo, oldVo);
		

		
	}

	private void addAfterRule(CompareAroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addAfterRule(new ReWriteByUpdateRdRule());
	}

	private void addBeforeRule(CompareAroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new BDPKLockFatherVORule());
		
		aroundProcesser.addBeforeRule(new RdNumChRule());
		
		aroundProcesser.addBeforeRule(new StringFieldTrimRule());
		
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] {"actuaproddate","expandline","expandunit","prodnum","prodnastnum"})));
		
		aroundProcesser.addBeforeRule(new UpdateAuditInfoRule());
		
		
	}

}
