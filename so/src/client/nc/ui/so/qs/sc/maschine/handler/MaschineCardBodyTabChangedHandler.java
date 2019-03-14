package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*    */ import nc.ui.mmf.framework.handler.MMEventHandler;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyTabChangedEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineCardBodyTabChangedHandler extends MMEventHandler implements IAppEventHandler<CardBodyTabChangedEvent>
/*    */ {
/*    */   public MaschineCardBodyTabChangedHandler() {}
/*    */   
/*    */   public void initMap()
/*    */   {
///* 21 */     putHandler("replace_items", nc.ui.pd.pd0404.handler.tab_repltime.PDWkCardBodyTabChangedHandler.class);
/*    */   }
/*    */   
/*    */ 
/*    */   public void handleAppEvent(CardBodyTabChangedEvent e)
/*    */   {
/* 27 */     String tabCode = e.getBillCardPanel().getCurrentBodyTableCode();
/* 28 */     MMBaseHandler handler = getHandler(tabCode);
/* 29 */     if (handler != null) {
/* 30 */       handler.afterEdit(e);
/*    */     }
/*    */   }
/*    */ }
