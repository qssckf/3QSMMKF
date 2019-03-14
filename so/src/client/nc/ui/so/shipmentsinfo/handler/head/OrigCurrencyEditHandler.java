package nc.ui.so.shipmentsinfo.handler.head;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.BodyUpdateByHead;
/*    */ import nc.vo.so.pub.rule.BodyValueRowRule;
/*    */ import nc.vo.so.pub.rule.SOExchangeRateRule;
/*    */ import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrigCurrencyEditHandler
/*    */ {
/*    */   public OrigCurrencyEditHandler() {}
/*    */   
/*    */   public void afteEdit(CardHeadTailAfterEditEvent e)
/*    */   {
/* 28 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 29 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 30 */     BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
/* 31 */     int[] rows = countutil.getMarNotNullRows();
/*    */     
/* 33 */     BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
/* 34 */     updaterule.updateAllBodyValueByHead("corigcurrencyid", "corigcurrencyid");
/*    */     
/*    */ 
/* 37 */     SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
/* 38 */     exraterule.calcAllBodyExchangeRate();
/*    */     
/* 40 */     SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
/* 41 */     if (globalraterule.isGlobalExchgRateChange("corigcurrencyid")) {
/* 42 */       globalraterule.calcGlobalExchangeRate(rows);
/*    */     }
/*    */     
/*    */ 
/* 46 */     SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
/* 47 */     if (groupraterule.isGroupExchgRateChange("corigcurrencyid")) {
/* 48 */       groupraterule.calcGroupExchangeRate(rows);
/*    */     }
/*    */     
/* 51 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/* 52 */     for (int row : rows) {
/* 53 */       calculator.calculate(row, "nexchangerate");
/*    */     }
/*    */     
/* 56 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 57 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/* 58 */     findPrice.findPriceAfterEdit(rows, "corigcurrencyid");
/*    */   }
/*    */ }