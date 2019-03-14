package nc.ui.so.qs.sc.maschine.scale;
/*     */ 
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillListPanel;
/*     */ import nc.ui.pubapp.scale.CardPaneScaleProcessor;
/*     */ import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
/*     */ import nc.ui.uif2.model.AbstractAppModel;
/*     */ import nc.vo.pubapp.scale.BillScaleProcessor;
/*     */ import nc.vo.pubapp.scale.PosEnum;
/*     */ import nc.vo.uif2.LoginContext;
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
/*     */ public class MaschineBodyScaleUtil
/*     */ {
/*     */   public MaschineBodyScaleUtil() {}
/*     */   
/*     */   public void orgChgScale(ShowUpableBillForm billform)
/*     */   {
/*  28 */     String pk_group = billform.getModel().getContext().getPk_group();
/*  29 */     BillCardPanel bcp = billform.getBillCardPanel();
/*  30 */     setScale(new CardPaneScaleProcessor(pk_group, bcp));
/*  31 */     setTimeScale(billform);
/*     */   }
/*     */   
/*     */   public void setListScale(BillListPanel blp, String pk_group, String pk_org)
/*     */   {
/*  36 */     setListTimeScale(blp, pk_org);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
///*  42 */   public static final String[] bodyNASTNumKeys = { "nastnum" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*  49 */   public static final String[] bodyNNumKeys = { "nnum" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */     // ªªÀ„¬ 
///*  55 */   public static final String[] hslkeys = { "vchangerate" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private MaschineTimeScale maschineTimeScale;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setScale(BillScaleProcessor scale)
/*     */   {
///*  67 */     scale.setHslCtlInfo(hslkeys, PosEnum.body, null);
/*     */     
/*  69 */     scale.process();
/*     */   }
/*     */   
/*     */   public void setTimeScale(ShowUpableBillForm billform) {
/*  73 */     int timeUnitScale = getMaschineTimeScale().getTimeUnitSCaleByOrg(billform.getModel().getContext().getPk_org()).intValue();
/*     */     
///*  75 */     billform.getBillCardPanel().getBodyItem("prodinv_items", "ntime").setDecimalDigits(timeUnitScale);
/*     */     
/*     */ 
///*  78 */     billform.getBillCardPanel().getBodyItem("replace_items", "nreplacetime").setDecimalDigits(timeUnitScale);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setListTimeScale(BillListPanel blp, String pk_org)
/*     */   {
/*  87 */     int timeUnitScale = getMaschineTimeScale().getTimeUnitSCaleByOrg(pk_org).intValue();
///*  88 */     blp.getBodyItem("prodinv_items", "ntime").setDecimalDigits(timeUnitScale);
///*  89 */     blp.getBodyItem("replace_items", "nreplacetime").setDecimalDigits(timeUnitScale);
///*  90 */     blp.getHeadItem("ntime").setDecimalDigits(timeUnitScale);
///*  91 */     blp.getHeadItem("npreparetime").setDecimalDigits(timeUnitScale);
///*  92 */     blp.getHeadItem("ndisassemtime").setDecimalDigits(timeUnitScale);
/*     */   }
/*     */   
/*     */ 
/*     */   public MaschineTimeScale getMaschineTimeScale()
/*     */   {
/*  98 */     return this.maschineTimeScale;
/*     */   }
/*     */   
/*     */   public void setMaschineTimeScale(MaschineTimeScale maschineTimeScale) {
/* 102 */     this.maschineTimeScale = maschineTimeScale;
/*     */   }
/*     */ }
