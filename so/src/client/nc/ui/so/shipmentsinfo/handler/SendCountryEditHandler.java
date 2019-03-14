package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.so.m30.pub.SaleOrderCalculator;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*    */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*    */ import nc.vo.so.pub.rule.SOTaxInfoRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SendCountryEditHandler
/*    */ {
/*    */   public SendCountryEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 24 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 25 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 26 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 28 */     SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
/*    */     
/*    */ 
/* 31 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/* 32 */     buyflgrule.setBuysellAndTriaFlag(rows);
/* 33 */     int[] buychgrows = buyflgrule.getBuysellChgRow();
/* 34 */     calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
/*    */     
/*    */ 
/* 37 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/* 38 */     taxInfo.setTaxInfoByBodyPos(rows);
/* 39 */     int[] taxchgrows = taxInfo.getTaxChangeRows();
/* 40 */     calculator.calculate(taxchgrows, "ntaxrate");
/*    */   }
/*    */ }
