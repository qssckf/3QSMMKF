package nc.bs.so.shipmentsinfo.ace.bp;

import nc.bs.so.fq.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.shipmentsinfo.plugin.bpplugin.ShipmentsInfoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
/**
 * 标准单据新增BP
 */
public class AceShipmentsInfoInsertBP {

	public AggShipmentsVO[] insert(AggShipmentsVO[] bills) {

		InsertBPTemplate<AggShipmentsVO> bp = new InsertBPTemplate<AggShipmentsVO>(ShipmentsInfoPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);
 	}
  /**
   * 新增后规则
   * 
   * @param processor
   */
	private void addAfterRule(AroundProcesser<AggShipmentsVO> processor) {
		IRule<AggShipmentsVO> rule=null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setCbilltype("FQ01");
	    ((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setCodeItem("vbillcode");
	    ((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setGroupItem("pk_group");
	    ((nc.bs.pubapp.pub.rule.BillCodeCheckRule)rule).setOrgItem("pk_org");
	    
		 processor.addAfterRule(rule);
		 processor.addAfterRule(new RewriteBillInsertRule());
				   				   
    }
  /**
   * 新增前规则
   * 
   * @param processor
   */
	private void addBeforeRule(AroundProcesser<AggShipmentsVO> processer) {
		IRule<AggShipmentsVO> rule=null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
  		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule)rule).setCbilltype("FQ01");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule)rule).setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule)rule).setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule)rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
  		rule = new nc.bs.pubapp.pub.rule.FieldLengthCheckRule();
		processer.addBeforeRule(rule);
  		rule = new nc.bs.pubapp.pub.rule.CheckNotNullRule();
		processer.addBeforeRule(rule);
  		rule = new nc.impl.pubapp.bd.material.assistant.MarAssistantSaveRule();
		((nc.impl.pubapp.bd.material.assistant.MarAssistantSaveRule)rule).setNotNullValidate("cmaterialvid");
		processer.addBeforeRule(rule);
    }
}
