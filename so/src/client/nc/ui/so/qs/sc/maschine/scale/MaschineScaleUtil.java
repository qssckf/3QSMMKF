package nc.ui.so.qs.sc.maschine.scale;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pub.bill.BillListPanel;
/*    */ import nc.ui.pubapp.scale.CardPaneScaleProcessor;
/*    */ import nc.ui.pubapp.scale.ListPaneScaleProcessor;
/*    */ import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
/*    */ import nc.ui.uif2.model.AbstractAppModel;
/*    */ import nc.vo.pubapp.scale.BillScaleProcessor;
/*    */ import nc.vo.pubapp.scale.PosEnum;
/*    */ import nc.vo.uif2.LoginContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineScaleUtil
/*    */ {
/*    */   public void MaschineScaleUtil() {}
/*    */   
/*    */   public void orgChgScale(ShowUpableBillForm billform)
/*    */   {
/* 23 */     String pk_group = billform.getModel().getContext().getPk_group();
/* 24 */     BillCardPanel bcp = billform.getBillCardPanel();
/* 25 */     setScale(new CardPaneScaleProcessor(pk_group, bcp));
/* 26 */     setTimeScale(billform);
/*    */   }
/*    */   
/*    */   public void setListScale(BillListPanel blp, String pk_group, String pk_org) {
/* 30 */     setScale(new ListPaneScaleProcessor(pk_group, blp));
/* 31 */     setListTimeScale(blp, pk_org);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
///* 37 */   public static final String[] headNumKeys = { "nastnum" };
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private MaschineTimeScale maschineTimeScale;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setScale(BillScaleProcessor scale)
/*    */   {
///* 50 */     scale.setNumCtlInfo(headNumKeys, PosEnum.head, null, "castunitid", PosEnum.head, null);
/* 51 */     scale.process();
/*    */   }
/*    */   
/*    */   private void setTimeScale(ShowUpableBillForm billform) {
/* 55 */     int timeUnitScale = getMaschineTimeScale().getTimeUnitSCaleByOrg(billform.getModel().getContext().getPk_org()).intValue();
///* 56 */     billform.getBillCardPanel().getHeadItem("npreparetime").setDecimalDigits(timeUnitScale);
///* 57 */     billform.getBillCardPanel().getHeadItem("ndisassemtime").setDecimalDigits(timeUnitScale);
///* 58 */     billform.getBillCardPanel().getHeadItem("ntime").setDecimalDigits(timeUnitScale);
/*    */   }
/*    */   
/*    */   private void setListTimeScale(BillListPanel blp, String pk_org)
/*    */   {
/* 63 */     int timeUnitScale = getMaschineTimeScale().getTimeUnitSCaleByOrg(pk_org).intValue();
///* 64 */     blp.getHeadItem("npreparetime").setDecimalDigits(timeUnitScale);
///* 65 */     blp.getHeadItem("ndisassemtime").setDecimalDigits(timeUnitScale);
///* 66 */     blp.getHeadItem("ntime").setDecimalDigits(timeUnitScale);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public MaschineTimeScale getMaschineTimeScale()
/*    */   {
/* 73 */     return this.maschineTimeScale;
/*    */   }
/*    */   
/*    */   public void setMaschineTimeScale(MaschineTimeScale maschineTimeScale) {
/* 77 */     this.maschineTimeScale = maschineTimeScale;
/*    */   }
/*    */ }
