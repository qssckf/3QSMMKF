package nc.bs.so.qs.sc.planbill.bp;

import java.util.Arrays;

import nc.bs.bd.bp.rule.BDPKLockSuperVORule;
import nc.bs.bd.bp.rule.BizLockRule;
import nc.bs.bd.bp.rule.NotNullValueRule;
import nc.bs.bd.bp.rule.StringFieldTrimRule;
import nc.bs.bd.bp.rule.insert.InsertAuditInfoRule;
import nc.bs.bd.bp.template.InsertBPTemplate;
import nc.bs.so.qs.planbill.bp.rule.CreateDataCodeRule;
import nc.bs.so.qs.planbill.bp.rule.DataCodeCheckRule;
import nc.bs.so.qs.sc.plugin.PBPluginPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillInsertBP {
	
	public PlanBillInsertBP(){};
	
	public MmPlanBillVO[] insert(MmPlanBillVO[] vos){
		
		InsertBPTemplate<MmPlanBillVO> bpt=new InsertBPTemplate(PBPluginPoint.INSERT);
		
		addBeforeRule(bpt.getAroundProcesser());
		
		addAfterRule(bpt.getAroundProcesser());
		
		MmPlanBillVO[] ret=(MmPlanBillVO[])bpt.insert(vos);
		
		return ret;
		
	}

	private void addAfterRule(AroundProcesser<MmPlanBillVO> processor) {
		// TODO 自动生成的方法存根
			
		processor.addAfterRule(new DataCodeCheckRule("PLAN01","vbillcode","pk_group","pk_org"));
		
	}

	private void addBeforeRule(AroundProcesser<MmPlanBillVO> aroundProcesser) {
		// TODO 自动生成的方法存根
		
		aroundProcesser.addBeforeRule(new StringFieldTrimRule());
		
		aroundProcesser.addBeforeRule(new BDPKLockSuperVORule());
				
		aroundProcesser.addBeforeRule(new CreateDataCodeRule("PLAN01","vbillcode","pk_group","pk_org"));
		
		aroundProcesser.addBeforeRule(new NotNullValueRule(Arrays.asList(new String[] { "plandate", "bomid","vbomversion" })));
		
		aroundProcesser.addBeforeRule(new InsertAuditInfoRule());
		
	
	}
	
	

}
