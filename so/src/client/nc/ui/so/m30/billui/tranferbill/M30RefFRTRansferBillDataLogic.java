package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class M30RefFRTRansferBillDataLogic extends DefaultBillDataLogic{
	
	public M30RefFRTRansferBillDataLogic(){}

	@Override
	public void doTransferAddLogic(Object selectedData) {
		// TODO 自动生成的方法存根
		super.doTransferAddLogic(selectedData);
		
		SaleOrderBillForm billform = (SaleOrderBillForm)getBillForm();
		BillCardPanel cardPanel = billform.getBillCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		
		BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
		int[] rows = bodycouuitl.getMarNotNullRows();
		
		String tranTypeCode = keyValue.getHeadStringValue("vtrantypecode");
		
		String pk_group = AppContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = billform.getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		AssociateConstractRule ctrule = new AssociateConstractRule(cardPanel, m30transvo);
		
		ctrule.associateCT(rows);
		
		MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel);
		matchlarrule.matchLargess(rows);
		
		HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
		totalrule.calculateHeadTotal();
	};
	
	
	
	

}
