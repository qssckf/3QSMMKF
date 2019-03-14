package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.ic.pub.view.PDOrgChangeMediator;
//		   import nc.ui.pd.pd0404.scale.PDWkBodyScaleUtil;
///*    */ import nc.ui.pd.pd0404.scale.PDWkScaleUtil;
/*    */ import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
		import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
		import nc.ui.so.qs.sc.maschine.scale.MaschineBodyScaleUtil;
		import nc.ui.so.qs.sc.maschine.scale.MaschineScaleUtil;
/*    */ 
/*    */ public class MaschineOrgChangedMediator
/*    */   extends PDOrgChangeMediator
/*    */ {
/*    */   private MaschineScaleUtil maschineScaleUtil;
/*    */   private MaschineBodyScaleUtil maschineBodyScaleUtil;
/*    */   
/*    */   public MaschineOrgChangedMediator() {}
/*    */   
/*    */   public void handleAppEvent(OrgChangedEvent e)
/*    */   {
/* 18 */     super.handleAppEvent(e);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 24 */     getMaschineScaleUtil().orgChgScale((ShowUpableBillForm)getBillFormEditor());
/*    */     
/*    */ 
/* 27 */     this.maschineBodyScaleUtil.setTimeScale((ShowUpableBillForm)getBillFormEditor());
/*    */   }
/*    */   
/*    */ 
/*    */   public MaschineScaleUtil getMaschineScaleUtil()
/*    */   {
/* 33 */     return this.maschineScaleUtil;
/*    */   }
/*    */   
/*    */   public void setMaschineScaleUtil(MaschineScaleUtil maschineScaleUtil) {
/* 37 */     this.maschineScaleUtil = maschineScaleUtil;
/*    */   }
/*    */   
/*    */ 
/*    */   public MaschineBodyScaleUtil getMaschineBodyScaleUtil()
/*    */   {
/* 43 */     return this.maschineBodyScaleUtil;
/*    */   }
/*    */   
/*    */   public void setMaschineBodyScaleUtil(MaschineBodyScaleUtil maschineBodyScaleUtil) {
/* 47 */     this.maschineBodyScaleUtil = maschineBodyScaleUtil;
/*    */   }
/*    */ }
