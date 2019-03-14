package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*    */ import nc.ui.mmf.framework.handler.MMEventHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailBprodlineHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailFcapacitycalcHandler;
///*    */ import nc.ui.pd.pd0404.handler.head.PDWkHeadTailVWkgcodeHandler;
///*    */ import nc.ui.pd.pd0404.view.PDWkBillForm;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
		import nc.util.mmf.framework.base.MMStringUtil;
/*    */ 
/*    */ public class MaschineCardHeadTailBeforeEditHandler extends MMEventHandler implements nc.ui.pubapp.uif2app.event.IAppEventHandler<CardHeadTailBeforeEditEvent>
/*    */ {
/*    */   private MaschineBillForm billForm;
/*    */   
/*    */   public MaschineCardHeadTailBeforeEditHandler() {}
/*    */   
/*    */   public void handleAppEvent(CardHeadTailBeforeEditEvent e)
/*    */   {
/* 21 */     MMBaseHandler handler = getHandler(e.getKey());
/* 22 */     if (handler != null) {
///* 23 */       if (MMStringUtil.isEqual(e.getKey(), "fcapacitycalc")) {
///* 24 */         ((PDWkHeadTailFcapacitycalcHandler)handler).setBillForm(getBillForm());
///*    */       }
/* 26 */       handler.beforeEdit(e);
/*    */     }
/* 28 */     e.getBillCardPanel().execHeadTailLoadFormulas();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void initMap()
/*    */   {
///* 36 */     putHandler("vwkgcode", PDWkHeadTailVWkgcodeHandler.class);
///* 37 */     putHandler("fcapacitycalc", PDWkHeadTailFcapacitycalcHandler.class);
///* 38 */     putHandler("bprodline", PDWkHeadTailBprodlineHandler.class);
/*    */   }
/*    */   
/*    */   public MaschineBillForm getBillForm()
/*    */   {
/* 43 */     return this.billForm;
/*    */   }
/*    */   
/*    */   public void setBillForm(MaschineBillForm billForm) {
/* 47 */     this.billForm = billForm;
/*    */   }
/*    */ }
