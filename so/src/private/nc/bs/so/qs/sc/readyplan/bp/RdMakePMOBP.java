package nc.bs.so.qs.sc.readyplan.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.bd.bp.template.UpdateBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.readyplan.bp.rule.MakePMORdStatusChkRule;
import nc.bs.so.qs.readyplan.bp.rule.MakePMOStatusChkRule;
import nc.bs.so.qs.readyplan.bp.rule.MmPlanNumChkRule;
import nc.bs.so.qs.readyplan.bp.rule.ReWriteByUpdateMmPlanRule;
import nc.bs.so.qs.readyplan.bp.rule.SetRdBillStatusByPMORule;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdMakePMOBP {
	
	public RdMakePMOBP(){};
	
	public RdPorductDetailVO[] MakeRdPMO(RdPorductDetailVO[] updateVo,RdPorductDetailVO[] oldVo){
		
		UpdateBPTemplate<RdPorductDetailVO> bpt=new UpdateBPTemplate(RdPluginPoint.UPDATE);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.update(updateVo, oldVo);
		
	}
	
	
	private void addAfterRule(CompareAroundProcesser aroundProcesser) {
		// TODO 自动生成的方法存根
		
		
	}

	private void addBeforeRule(CompareAroundProcesser aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new MakePMORdStatusChkRule());
		
		aroundProcesser.addBeforeRule(new StringFieldTrimRule());
		
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "prodnum", "prodnastnum","proddate","enddate","pk_material","pk_bomid","pk_dept","pk_machine","def1"})));
		
		aroundProcesser.addBeforeRule(new SetRdBillStatusByPMORule());
		
		aroundProcesser.addBeforeRule(new UpdateAuditInfoRule());
		
	}

}
