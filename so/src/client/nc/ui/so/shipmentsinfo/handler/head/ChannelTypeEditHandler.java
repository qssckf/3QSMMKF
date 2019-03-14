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
/*    */ public class ChannelTypeEditHandler
/*    */ {
/*    */   public ChannelTypeEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardHeadTailAfterEditEvent e)
/*    */   {
/* 22 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 23 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 24 */     BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
/* 25 */     int[] rows = countutil.getMarNotNullRows();
/* 26 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 27 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/* 28 */     findPrice.findPriceAfterEdit(rows, "cchanneltypeid");
/*    */   }
/*    */ }
