package nc.bs.so.shipmentsinfo.ace.bp;

import nc.bs.so.fq.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;

/**
 * 修改保存的BP
 * 
 */
public class AceShipmentsInfoUpdateBP {

	public AggShipmentsVO[] update(AggShipmentsVO[] bills, AggShipmentsVO[] originBills) {

    	// 调用修改模板
		UpdateBPTemplate<AggShipmentsVO> bp = new UpdateBPTemplate<AggShipmentsVO>(ShipmentsInfoPluginPoint.UPDATE);

        // 执行前规则
        this.addBeforeRule(bp.getAroundProcesser());
        // 执行后规则
        this.addAfterRule(bp.getAroundProcesser());
        return bp.update(bills, originBills);
	}
	private void addAfterRule(CompareAroundProcesser<AggShipmentsVO> processer) {
		IRule<AggShipmentsVO> rule=null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setCbilltype("FQ01");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setOrgItem("pk_org");
				     				    				   				   
		processer.addAfterRule(rule);
		processer.addAfterRule(new RewriteBillUpdateRule());
				   				     
    }
	private void addBeforeRule(CompareAroundProcesser<AggShipmentsVO> processer) {
		IRule<AggShipmentsVO> rule=null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
				    				   				   				    				     				    				   				    				      				       processer.addBeforeRule(rule);
		ICompareRule<AggShipmentsVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule)ruleCom).setCbilltype("FQ01");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule)ruleCom).setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule)ruleCom).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule)ruleCom).setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
		rule = new nc.bs.pubapp.pub.rule.FieldLengthCheckRule();
				    				   				   				    				     				    				   				    				      				       processer.addBeforeRule(rule);
		rule = new nc.impl.pubapp.bd.material.assistant.MarAssistantSaveRule();
		((nc.impl.pubapp.bd.material.assistant.MarAssistantSaveRule)rule).setNotNullValidate("cmaterialvid");
		processer.addBeforeRule(rule);
	}

}
