package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
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
/*    */ public class ReceiveAreaEditHandler
/*    */ {
/*    */   public ReceiveAreaEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 21 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 22 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*    */     
/* 24 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 25 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/* 26 */     findprice.findPriceAfterEdit(rows, "creceiveareaid");
/*    */   }
/*    */ }