package nc.bs.so.qs.sc.readyplan.bp;

import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.so.qs.readyplan.bp.rule.MakePMOStatusChkRule;
import nc.bs.so.qs.readyplan.bp.rule.SetBillStatusByPMORule;
import nc.bs.so.qs.readyplan.bp.rule.StockOrgChkRule;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.bs.so.qs.sc.readyplan.bp.opera.RdReleaseOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.template.CommonOperatorTemplate;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdReleaseBP {
	
	public RdReleaseBP(){
		
	}
	
	public RdPorductDetailVO[] doRelease(RdPorductDetailVO[] vo) throws BusinessException{
		
		
		RdReleaseOperator op=new RdReleaseOperator();
		
		CommonOperatorTemplate<RdPorductDetailVO> bp = new CommonOperatorTemplate(RdPluginPoint.RELEASE, op);
		
		addBeforeRule(bp.getAroundProcesser());
		addAfterRule(bp.getAroundProcesser());
		
		return bp.operate(vo);
		
		
		
		
	}

	private void addAfterRule(AroundProcesser<RdPorductDetailVO> ard) {
		// TODO 自动生成的方法存根
		
		
		
	}

	private void addBeforeRule(AroundProcesser<RdPorductDetailVO> ard) {
		// TODO 自动生成的方法存根
		
		ard.addBeforeRule(new StockOrgChkRule());
		
		ard.addBeforeRule(new MakePMOStatusChkRule());
		
		ard.addBeforeRule(new SetBillStatusByPMORule());
		
		ard.addBeforeRule(new UpdateAuditInfoRule());
		
		
	}
	
	

}
