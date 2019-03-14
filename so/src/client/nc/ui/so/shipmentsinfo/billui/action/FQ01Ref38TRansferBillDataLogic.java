package nc.ui.so.shipmentsinfo.billui.action;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
///*    */ import nc.ui.so.m30.billui.rule.AssociateConstractRule;
///*    */ import nc.ui.so.m30.billui.rule.MatchLargessRule;
///*    */ import nc.ui.so.m30.billui.view.SaleOrderBillForm;
		 import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.vo.pubapp.AppContext;
///*    */ import nc.vo.so.m30.rule.HeadTotalCalculateRule;
///*    */ import nc.vo.so.m30.util.SaleOrderClientContext;
///*    */ import nc.vo.so.m30trantype.entity.M30TranTypeVO;
//import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.qs.sc.FQ01.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
import nc.vo.so.shipmens.util.ShipmentsClientContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FQ01Ref38TRansferBillDataLogic extends DefaultBillDataLogic {
/*    */	
/*    */   public FQ01Ref38TRansferBillDataLogic() {}
/*    */   
/*    */   public void doTransferAddLogic(Object selectedData)
/*    */   {
/* 31 */     super.doTransferAddLogic(selectedData);
/*    */     
/*    */ 
/* 34 */     ShowUpableBillForm billform = (ShowUpableBillForm)getBillForm();
/* 35 */     BillCardPanel cardPanel = billform.getBillCardPanel();
/* 36 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 38 */     BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
/* 39 */     int[] rows = bodycouuitl.getMarNotNullRows();
/*    */     
/* 41 */     String tranTypeCode = keyValue.getHeadStringValue("transtype");
/*    */     
/* 43 */     String pk_group = AppContext.getInstance().getPkGroup();		 
///* 44 */     ShipmentsClientContext cache = billform.;
///* 45 */     FQ01TranTypeVO fq01transvo = cache.getTransType(tranTypeCode, pk_group);
///* 46 */     AssociateConstractRule ctrule = new AssociateConstractRule(cardPanel, fq01transvo);
///*    */     
///* 48 */     ctrule.associateCT(rows);
///*    */     
			MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel);
			matchlarrule.matchLargess(rows);
/*    */     
/*    */ 
/* 54 */     HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
/* 55 */     totalrule.calculateHeadTotal();
/*    */   }
/*    */ }
