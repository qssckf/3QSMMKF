package nc.ui.so.shipmentsinfo.handler.head;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransportTypeEditHandler
/*    */ {
/*    */   public TransportTypeEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardHeadTailAfterEditEvent e)
/*    */   {
/* 23 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 24 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 25 */     BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
/* 26 */     int[] rows = countutil.getMarNotNullRows();
/* 27 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 28 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/* 29 */     findPrice.findPriceAfterEdit(rows, "ctransporttypeid");
/*    */   }
/*    */ }