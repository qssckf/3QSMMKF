package nc.ui.so.qs.sc.maschine.actions;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import nc.ui.mmf.framework.view.BillFormFacade;
/*    */ import nc.ui.pub.beans.UIComboBox;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillData;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.actions.CancelAction;
/*    */ import nc.util.mmf.framework.base.MMValueCheck;
///*    */ import nc.vo.pd.pd0404.enumeration.FcapacitycalcEnum;
		 import nc.vo.so.qs.en.MachStatus;
/*    */ import nc.vo.pub.lang.UFBoolean;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineCancelAction  extends CancelAction
/*    */ {
/*    */   private static final long serialVersionUID = 4399404636883822000L;
/*    */   private BillFormFacade billForm;
/*    */   
/*    */   public MaschineCancelAction() {}
/*    */   
/*    */   public void doAction(ActionEvent e)
/*    */     throws Exception
/*    */   {
/* 30 */     super.doAction(e);
/*    */     
///* 32 */     setDefaultTabCodeEdit();
/*    */   }
/*    */   
/*    */ 
/*    */ 
///*    */   private void setDefaultTabCodeEdit()
///*    */   {
///* 39 */     Object prodlineObject = getBillForm().getBillCardPanel().getHeadItem("bprodline").getValueObject();
///* 40 */     if (MMValueCheck.isEmpty(prodlineObject)) {
///* 41 */       return;
///*    */     }
///* 43 */     boolean prodline = UFBoolean.valueOf(prodlineObject.toString()).booleanValue();
///* 44 */     if (prodline) {
///* 45 */       getBillForm().getBillCardPanel().setTabEnabled(1, "prodinv_items", true);
///* 46 */       getBillForm().getBillCardPanel().setTabEnabled(1, "replace_items", true);
/////* 47 */       UIComboBox comboBox = (UIComboBox)getBillForm().getBillCardPanel().getBillData().getHeadItem("fcapacitycalc").getComponent();
///*    */       
///*    */ 
/////* 50 */       comboBox.removeAllItems();
/////* 51 */       comboBox.addItem(FcapacitycalcEnum.getStrOutputConstEnum());
/////* 52 */       comboBox.addItem(FcapacitycalcEnum.getStrMachinetimeConstEnum());
/////* 53 */       comboBox.addItem(FcapacitycalcEnum.getStrWorktimeConstEnum());
///*    */     }
///*    */     else {
///* 56 */       getBillForm().getBillCardPanel().setTabEnabled(1, "prodinv_items", false);
///* 57 */       getBillForm().getBillCardPanel().setTabEnabled(1, "replace_items", false);
///*    */     }
///*    */   }
/*    */   
/*    */   public void setBillForm(BillFormFacade billForm) {
/* 62 */     this.billForm = billForm;
/*    */   }
/*    */   
/*    */   public BillFormFacade getBillForm() {
/* 66 */     return this.billForm;
/*    */   }
/*    */ }
