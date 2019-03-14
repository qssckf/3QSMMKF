package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.beans.UIRefPane;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*    */ import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*    */ import nc.vo.pubapp.AppContext;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.SOBatcheEditRule;
/*    */ 
/*    */ public class SendStordocEditHandler
/*    */ {
/*    */   public SendStordocEditHandler() {}
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 21 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 22 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 24 */     String csendstockorgid = keyValue.getBodyStringValue(e.getRow(), "csendstockorgid");
/*    */     
/* 26 */     if (nc.vo.pubapp.pattern.pub.PubAppTool.isNull(csendstockorgid)) {
/* 27 */       e.setReturnValue(Boolean.FALSE);
/*    */     }
/*    */     else
/*    */     {
/* 31 */       BillItem item = cardPanel.getBodyItem("csendstordocid");
/* 32 */       FilterWareHouseRefUtils utils = new FilterWareHouseRefUtils((UIRefPane)item.getComponent());
/*    */       
/* 34 */       utils.filterItemRefByOrg(csendstockorgid);
/*    */       
/* 36 */       String pk_group = AppContext.getInstance().getPkGroup();
/* 37 */       utils.filterItemRefByGroup(pk_group);
/* 38 */       utils.filterDirectStorc();
/* 39 */       utils.filterWasteStorc();
/* 40 */       utils.filterUnableState();
/* 41 */       e.setReturnValue(Boolean.TRUE);
/*    */     }
/*    */   }
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 47 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/*    */     
/* 49 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 50 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 52 */     SOBatcheEditRule batchedit = new SOBatcheEditRule(keyValue, new String[] { "csendstockorgid" });
/*    */     
/*    */ 
/* 55 */     batchedit.clearRows(rows, "csendstordocid");
/*    */   }
/*    */ }