package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*    */ import nc.ui.mmf.framework.handler.MMEventHandler;
///*    */ import nc.ui.pd.pd0404.actions.PDWkEditAction;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.actions.CancelAction;
/*    */ import nc.ui.pubapp.uif2app.actions.SaveAction;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeTabChangeEvent;
		import nc.ui.so.qs.sc.maschine.actions.MaschineEditAction;
		import nc.ui.uif2.model.AbstractUIAppModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineCardBodyBeforeTabChangeHandler extends MMEventHandler implements IAppEventHandler<CardBodyBeforeTabChangeEvent>
/*    */ {
/*    */   private AbstractUIAppModel model;
/*    */   private SaveAction saveAction;
/*    */   private CancelAction cancelAction;
/*    */   private MaschineEditAction editAction;
/*    */   
/*    */   public MaschineCardBodyBeforeTabChangeHandler() {}
/*    */   
/*    */   public void initMap()
/*    */   {
///* 35 */     putHandler("prodinv_items", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyBeforeTabChangeHandler.class);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void handleAppEvent(CardBodyBeforeTabChangeEvent e) {
/* 42 */     String tabCode = e.getBillCardPanel().getCurrentBodyTableCode();
/* 43 */     MMBaseHandler handler = getHandler(tabCode);
/* 44 */     if ((handler instanceof nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyBeforeTabChangeHandler)) {
/* 45 */       ((nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyBeforeTabChangeHandler)handler).setModel(getModel());
/*    */       
/* 47 */       ((nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyBeforeTabChangeHandler)handler).setSaveAction(getSaveAction());
/*    */       
/* 49 */       ((nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyBeforeTabChangeHandler)handler).setCancelAction(getCancelAction());
/*    */       
/* 51 */       ((nc.ui.so.qs.sc.maschine.handler.body.MaschineCardBodyBeforeTabChangeHandler)handler).setEditAction(getEditAction());
/*    */     }
/*    */     
/* 54 */     if (handler != null) {
/* 55 */       handler.beforeEdit(e);
/*    */     }
/*    */     
/* 58 */     e.setReturnValue(Boolean.valueOf(true));
/*    */   }
/*    */   
/*    */   public AbstractUIAppModel getModel()
/*    */   {
/* 63 */     return this.model;
/*    */   }
/*    */   
/*    */   public void setModel(AbstractUIAppModel model) {
/* 67 */     this.model = model;
/*    */   }
/*    */   
/*    */   public SaveAction getSaveAction() {
/* 71 */     return this.saveAction;
/*    */   }
/*    */   
/*    */   public void setSaveAction(SaveAction saveAction) {
/* 75 */     this.saveAction = saveAction;
/*    */   }
/*    */   
/*    */   public CancelAction getCancelAction() {
/* 79 */     return this.cancelAction;
/*    */   }
/*    */   
/*    */   public void setCancelAction(CancelAction cancelAction) {
/* 83 */     this.cancelAction = cancelAction;
/*    */   }
/*    */   
/*    */   public MaschineEditAction getEditAction() {
/* 87 */     return this.editAction;
/*    */   }
/*    */   
/*    */   public void setEditAction(MaschineEditAction editAction) {
/* 91 */     this.editAction = editAction;
/*    */   }
/*    */ }
