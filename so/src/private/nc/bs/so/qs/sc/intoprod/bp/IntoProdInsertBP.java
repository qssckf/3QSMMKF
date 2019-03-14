package nc.bs.so.qs.sc.intoprod.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.insert.InsertAuditInfoRule;
import nc.bs.bd.bp.template.InsertBPTemplate;
import nc.bs.so.qs.readyplan.bp.rule.BDPKLockFatherVORule;
import nc.bs.so.qs.sc.intoprod.bp.rule.RdNumChRule;
import nc.bs.so.qs.sc.intoprod.bp.rule.ReWriterRdRule;
import nc.bs.so.qs.sc.plugin.IpPluginPoint;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.IntoProdDetailVO;

public class IntoProdInsertBP {
	
	public IntoProdInsertBP(){
		
	}
	
	public IntoProdDetailVO[] insert(IntoProdDetailVO[] vos) throws Exception{
		
		
		InsertBPTemplate<IntoProdDetailVO> bpt=new InsertBPTemplate(IpPluginPoint.INSERT);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		return bpt.insert(vos);
		
	}

	private void addAfterRule(AroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		aroundProcesser.addAfterRule(new ReWriterRdRule());
	}

	private void addBeforeRule(AroundProcesser<IntoProdDetailVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new BDPKLockFatherVORule());
		aroundProcesser.addBeforeRule(new RdNumChRule());
		aroundProcesser.addBeforeRule(new StringFieldTrimRule());
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] {"actuaproddate","expandline","expandunit","prodnum","prodnastnum"})));
		aroundProcesser.addBeforeRule(new InsertAuditInfoRule());
	}

}
