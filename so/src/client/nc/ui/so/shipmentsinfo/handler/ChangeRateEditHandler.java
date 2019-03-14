package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*    */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.SOUnitChangeRateRule;
/*    */ 
/*    */ public class ChangeRateEditHandler
/*    */ {
/*    */   public ChangeRateEditHandler() {}
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 14 */     nc.ui.pub.bill.BillCardPanel cardPanel = e.getBillCardPanel();
/* 15 */     IKeyValue keyvalue = new nc.ui.so.pub.keyvalue.CardKeyValue(cardPanel);
/* 16 */     int row = e.getRow();
/* 17 */     String cunitid = keyvalue.getBodyStringValue(row, "cunitid");
/* 18 */     String castunitid = keyvalue.getBodyStringValue(row, "castunitid");
/*    */     
/* 20 */     if ((PubAppTool.isNull(cunitid)) || (PubAppTool.isNull(castunitid))) {
/* 21 */       e.setReturnValue(Boolean.valueOf(false));
/* 22 */       return;
/*    */     }
/* 24 */     SOUnitChangeRateRule changeraterule = new SOUnitChangeRateRule(keyvalue);
/* 25 */     boolean isfix = changeraterule.isAstFixedRate(e.getRow());
/* 26 */     e.setReturnValue(Boolean.valueOf(!isfix));
/*    */   }
/*    */ }