package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderLarPriceConfig;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.rule.LargessPirceRule;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsLarPriceConfig;
/*    */ import nc.vo.pub.lang.UFBoolean;
/*    */ import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyValue;
 
/*    */ 
/*    */ public class LargessFlagEditHandler {
/*    */   public LargessFlagEditHandler() {}
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 19 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 20 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 21 */     int row = e.getRow();
/* 22 */     String cmaterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
/*    */     
/* 24 */     if (PubAppTool.isNull(cmaterialvid)) {
/* 25 */       e.setReturnValue(Boolean.valueOf(false));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 32 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 33 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 34 */     int row = e.getRow();
/*    */     
/* 36 */     UFBoolean largessflag = keyValue.getBodyUFBooleanValue(row, "blargessflag");
/*    */     
/*    */ 
/* 39 */     if ((null != largessflag) && (largessflag.booleanValue())) {
/* 40 */       ShipmentsLarPriceConfig config = new ShipmentsLarPriceConfig(cardPanel);
/* 41 */       LargessPirceRule larpricerule = new LargessPirceRule(cardPanel, config);
/* 42 */       int[] rows = { row };
/*    */       
/*    */ 
/* 45 */       larpricerule.setLargessPrice(rows);
/*    */     }
/*    */   }
/*    */ }