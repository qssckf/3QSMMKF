package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
/*    */ import nc.ui.pub.beans.UIRefPane;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*    */ import nc.ui.so.m30.pub.SaleOrderCalculator;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*    */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*    */ import nc.vo.so.pub.rule.SOCountryInfoRule;
/*    */ import nc.vo.so.pub.rule.SOTaxInfoRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReceiveAddressEditHandler
/*    */ {
/*    */   public ReceiveAddressEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 32 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 33 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 34 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 36 */     SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
/*    */     
/* 38 */     SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
/* 39 */     conutryinforule.setReceiveCountry(rows);
/*    */     
/* 41 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/* 42 */     buyflgrule.setBuysellAndTriaFlag(rows);
/* 43 */     int[] buysellrows = buyflgrule.getBuysellChgRow();
/* 44 */     calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
/*    */     
/* 46 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/* 47 */     taxInfo.setTaxInfoByBodyPos(rows);
/* 48 */     int[] taxchgrows = taxInfo.getTaxChangeRows();
/*    */     
/* 50 */     calculator.calculate(taxchgrows, "ntaxrate");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 59 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 60 */     BillItem item = cardPanel.getBodyItem("creceiveaddrid");
/* 61 */     UIRefPane uirefpane = (UIRefPane)item.getComponent();
/* 62 */     CustAddressDefaultRefModel model = (CustAddressDefaultRefModel)uirefpane.getRefModel();
/*    */     
/*    */ 
/*    */ 
/* 66 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 67 */     int editrow = e.getRow();
/* 68 */     String pk_org = keyValue.getHeadStringValue("pk_org");
/* 69 */     String customer = keyValue.getBodyStringValue(editrow, "creceivecustid");
/*    */     
/* 71 */     model.setPk_org(pk_org);
/* 72 */     model.setPk_customer(customer);
/*    */   }
/*    */ }