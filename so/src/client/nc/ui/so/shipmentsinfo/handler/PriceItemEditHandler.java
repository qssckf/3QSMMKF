package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PriceItemEditHandler
/*    */ {
/*    */   public PriceItemEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 23 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 24 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*    */     
/* 26 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 27 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/* 28 */     findprice.findPriceAfterEdit(rows, "cpriceitemid");
/*    */   }
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e) {
/* 32 */     BodyEditEventUtil.getInstance().filterPricetype(e, "cpriceitemtableid", "cpriceitemid");
/*    */   }
/*    */ }