package nc.ui.so.shipmentsinfo.handler.head;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
/*    */ 
/*    */ public class DiscountRateEditHandler
/*    */ {
/*    */   public DiscountRateEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardHeadTailAfterEditEvent e)
/*    */   {
/* 16 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 17 */     IKeyValue keyValue = new nc.ui.so.pub.keyvalue.CardKeyValue(cardPanel);
/*    */     
/* 19 */     BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
/* 20 */     updaterule.updateAllBodyValueByHead("ndiscountrate", "ndiscountrate");
/*    */     
/* 22 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/* 23 */     int bodycount = keyValue.getBodyCount();
/* 24 */     for (int i = 0; i < bodycount; i++) {
/* 25 */       calculator.calculate(i, "ndiscountrate");
/*    */     }
/*    */   }
/*    */ }
