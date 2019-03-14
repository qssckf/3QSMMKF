package nc.bs.so.qs.sc.intoprod.bp;

import nc.bs.bd.bp.rule.update.UpdateAuditInfoRule;
import nc.bs.so.qs.sc.intoprod.bp.rule.MakePMOStatusChkRule;
import nc.bs.so.qs.sc.intoprod.bp.rule.SetBillStatusByPMORule;
import nc.bs.so.qs.sc.intoprod.bp.rule.StockOrgChkRule;
import nc.bs.so.qs.sc.plugin.IpPluginPoint;
import nc.bs.so.qs.sc.readyplan.bp.opera.IpReleaseOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.template.CommonOperatorTemplate;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.IntoProdDetailVO;

public class IntoProdReleaseBP {
	
	public IntoProdReleaseBP(){
		
	}
	
	public IntoProdDetailVO[] doRelease(IntoProdDetailVO[] vo) throws BusinessException{
		
		
		IpReleaseOperator op = new IpReleaseOperator();
		
		CommonOperatorTemplate<IntoProdDetailVO> bp=new CommonOperatorTemplate(IpPluginPoint.RELEASE,op);
		
		addBeforeRule(bp.getAroundProcesser());
		addAfterRule(bp.getAroundProcesser());
		
		return bp.operate(vo);
		
		
	}

	private void addAfterRule(AroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
	}

	private void addBeforeRule(AroundProcesser<IntoProdDetailVO> ard) {
		// TODO 自动生成的方法存根
		
		ard.addBeforeRule(new StockOrgChkRule());
		
		ard.addBeforeRule(new MakePMOStatusChkRule());
		
		ard.addBeforeRule(new SetBillStatusByPMORule());
		
		ard.addBeforeRule(new UpdateAuditInfoRule());
	}

}
