package nc.ui.so.qs.sc.maschine.handler.body;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import nc.ui.mmf.framework.handler.MMBaseHandler;
///*     */ import nc.ui.pd.pd0404.actions.PDWkEditAction;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.uif2app.actions.CancelAction;
/*     */ import nc.ui.pubapp.uif2app.actions.SaveAction;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeTabChangeEvent;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
import nc.ui.so.qs.sc.maschine.actions.MaschineEditAction;
/*     */ import nc.ui.uif2.UIState;
/*     */ import nc.ui.uif2.components.CommonConfirmDialogUtils;
/*     */ import nc.ui.uif2.model.AbstractUIAppModel;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineCardBodyBeforeTabChangeHandler
/*     */   extends MMBaseHandler
/*     */ {
/*     */   private AbstractUIAppModel model;
/*     */   private SaveAction saveAction;
/*     */   private CancelAction cancelAction;
/*     */   private MaschineEditAction editAction;
/*     */   
/*     */   public MaschineCardBodyBeforeTabChangeHandler() {}
/*     */   
/*     */   public void beforeEdit(CardPanelEvent event)
/*     */   {
/*  39 */     CardBodyBeforeTabChangeEvent e = (CardBodyBeforeTabChangeEvent)event;
/*  40 */     prodinvSaveAction(e);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void afterEdit(CardPanelEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void prodinvSaveAction(CardBodyBeforeTabChangeEvent e)
/*     */   {
/*  64 */     BillCardPanel panel = e.getBillCardPanel();
/*     */     
/*  66 */     UFBoolean editAbleFlag = UFBoolean.FALSE;
/*  67 */     if (((this.model.getUiState() == UIState.ADD) || (this.model.getUiState() == UIState.EDIT)) && (((Boolean)panel.getHeadItem("bprodline").getValueObject()).booleanValue()))
/*     */     {
/*     */ 
/*  70 */       editAbleFlag = saveConfirmdlg(e, editAbleFlag);
/*     */     } else {
/*  72 */       e.setReturnValue(Boolean.valueOf(true));
/*     */     }
/*  74 */     if ((this.model.getUiState() == UIState.NOT_EDIT) && (editAbleFlag == UFBoolean.TRUE))
/*     */     {
/*     */ 
/*  77 */       this.editAction.actionPerformed(new ActionEvent(this.editAction, 0, null));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UFBoolean saveConfirmdlg(CardBodyBeforeTabChangeEvent e, UFBoolean editAbleFlag)
/*     */   {
/*  91 */     int dlg = CommonConfirmDialogUtils.showConfirmSaveDialog(this.model.getContext().getEntranceUI());
/*     */     try
/*     */     {
/*  94 */       if (dlg == 4) {
/*  95 */         this.saveAction.actionPerformed(new ActionEvent(this.saveAction, 0, null));
/*     */         
/*  97 */         e.setReturnValue(Boolean.valueOf(true));
/*  98 */         editAbleFlag = UFBoolean.TRUE;
/*  99 */       } else if (dlg == 8) {
/* 100 */         this.cancelAction.actionPerformed(new ActionEvent(this.cancelAction, 0, null));
/*     */         
/* 102 */         e.setReturnValue(Boolean.valueOf(true));
/* 103 */       } else if (dlg == 2) {
/* 104 */         e.setReturnValue(Boolean.valueOf(true));
/*     */       }
/*     */     } catch (Exception ex) {
/* 107 */       ExceptionUtils.wrappException(ex);
/*     */     }
/* 109 */     return editAbleFlag;
/*     */   }
/*     */   
/*     */   public AbstractUIAppModel getModel() {
/* 113 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(AbstractUIAppModel model) {
/* 117 */     this.model = model;
/*     */   }
/*     */   
/*     */   public SaveAction getSaveAction() {
/* 121 */     return this.saveAction;
/*     */   }
/*     */   
/*     */   public void setSaveAction(SaveAction saveAction) {
/* 125 */     this.saveAction = saveAction;
/*     */   }
/*     */   
/*     */   public CancelAction getCancelAction() {
/* 129 */     return this.cancelAction;
/*     */   }
/*     */   
/*     */   public void setCancelAction(CancelAction cancelAction)
/*     */   {
/* 134 */     this.cancelAction = cancelAction;
/*     */   }
/*     */   
/*     */   public MaschineEditAction getEditAction() {
/* 138 */     return this.editAction;
/*     */   }
/*     */   
/*     */   public void setEditAction(MaschineEditAction editAction) {
/* 142 */     this.editAction = editAction;
/*     */   }
/*     */ }
