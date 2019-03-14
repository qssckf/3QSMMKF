package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
/*    */ import nc.ui.pub.beans.UIRefPane;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*    */ import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
/*    */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaxCodeEditHandler
/*    */ {
/*    */   public TaxCodeEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 28 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 29 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 30 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 32 */     SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyValue);
/* 33 */     taxinfo.setTaxTypeAndRate(rows);
/*    */     
/* 35 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/* 36 */     calculator.calculate(rows, "ntaxrate");
/*    */   }
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e) {
/* 40 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 41 */     CardKeyValue keyValue = new CardKeyValue(cardPanel);
/* 42 */     int row = e.getRow();
/*    */     
/* 44 */     String ctaxcountryid = keyValue.getBodyStringValue(row, "ctaxcountryid");
/*    */     
/* 46 */     if (PubAppTool.isNull(ctaxcountryid)) {
/* 47 */       e.setReturnValue(Boolean.valueOf(false));
/* 48 */       return;
/*    */     }
/* 50 */     Integer fbuysellflag = keyValue.getBodyIntegerValue(row, "fbuysellflag");
/*    */     
/* 52 */     BillItem item = cardPanel.getBodyItem("ctaxcodeid");
/* 53 */     FilterTaxCodeRefUtils filter = new FilterTaxCodeRefUtils((UIRefPane)item.getComponent());
/*    */     
/* 55 */     filter.filterItemRefWithCompatible(ctaxcountryid, BuySellFlagEnum.valueOf(fbuysellflag));
/*    */   }
/*    */ }