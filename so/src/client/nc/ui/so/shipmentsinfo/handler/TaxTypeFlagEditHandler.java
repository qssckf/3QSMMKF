package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.so.m30.pub.SaleOrderCalculator;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaxTypeFlagEditHandler
/*    */ {
/*    */   public TaxTypeFlagEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 19 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 20 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*    */     
/* 22 */     SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
/* 23 */     calculator.calculate(rows, "ntaxrate");
/*    */   }
/*    */ }
