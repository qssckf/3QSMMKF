package nc.bs.so.qs.sc.readyplan.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.insert.InsertAuditInfoRule;
import nc.bs.bd.bp.template.InsertBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.readyplan.bp.rule.MmPlanNumChkRule;
import nc.bs.so.qs.readyplan.bp.rule.ReWriteMmPlanRule;
import nc.bs.so.qs.sc.plugin.PBPluginPoint;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdInsertBP {
	
	public RdInsertBP(){};
	
	public RdPorductDetailVO[] insert(RdPorductDetailVO[] vos) throws Exception{
		
		InsertBPTemplate<RdPorductDetailVO> bpt=new InsertBPTemplate(RdPluginPoint.INSERT);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.insert(vos);
		
		
	}

	private void addAfterRule(AroundProcesser<RdPorductDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		
		aroundProcesser.addAfterRule(new ReWriteMmPlanRule());
		
	}

	private void addBeforeRule(AroundProcesser<RdPorductDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new BDPKLockFatherVORule());
		
		aroundProcesser.addBeforeRule(new MmPlanNumChkRule());
		
		aroundProcesser.addBeforeRule(new StringFieldTrimRule());
		
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "processer","prodnum", "prodnastnum","proddate","enddate","pk_material","pk_bomid","pk_dept","pk_machine","def1"})));
		
		aroundProcesser.addBeforeRule(new InsertAuditInfoRule());
		
		
	}

}
