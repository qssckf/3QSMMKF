package nc.ui.so.qs.sc.maschine.actions;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillData;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
/*    */ import nc.ui.uif2.editor.BillForm;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineRefreshCardAction extends RefreshSingleAction
/*    */ {
/*    */   private static final long serialVersionUID = 2754124313032603403L;
/* 21 */   private BillForm editor = null;
/*    */   
/*    */   public MaschineRefreshCardAction() {}
/*    */   
/* 25 */   public void doAction(ActionEvent e) throws Exception { 
			 super.doAction(e);
/* 26 */     setDefaultNprodfactorValue();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void setDefaultNprodfactorValue()
/*    */   {
///* 36 */     UFDouble timeValue = (UFDouble)this.editor.getBillCardPanel().getBillData().getHeadItem("ntime").getValueObject();
///*    */     
///* 38 */     UFDouble ntime = timeValue == null ? UFDouble.ZERO_DBL : timeValue;
///* 39 */     UFDouble astNum = (UFDouble)this.editor.getBillCardPanel().getBillData().getHeadItem("nastnum").getValueObject();
///*    */     
///* 41 */     UFDouble nastnum = astNum == null ? UFDouble.ZERO_DBL : astNum;
///* 42 */     UFDouble nprodfactor = ntime == UFDouble.ZERO_DBL ? null : nastnum.div(ntime);
///* 43 */     UFDouble nprodbeat = nastnum == UFDouble.ZERO_DBL ? null : ntime.div(nastnum);
///* 44 */     this.editor.getBillCardPanel().getBillData().getHeadItem("nprodfactor").setValue(nprodfactor);
///* 45 */     this.editor.getBillCardPanel().getBillData().getHeadItem("nprodbeat").setValue(nprodbeat);
/*    */   }
/*    */   
/*    */   public BillForm getEditor() {
/* 49 */     return this.editor;
/*    */   }
/*    */   
/*    */   public void setEditor(BillForm editor) {
/* 53 */     this.editor = editor;
/*    */   }
/*    */ }