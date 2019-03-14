package nc.ui.so.qs.sc.maschine.actions;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
///*    */ import nc.ui.pd.pd0404.view.PDWkBillForm;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.actions.SaveAction;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
/*    */ import nc.ui.uif2.actions.AddAction;
/*    */ import nc.ui.uif2.actions.SaveAddAction;
/*    */ import nc.ui.uif2.editor.IEditor;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*    */ import nc.vo.pd.pd0404.entity.WkVO;
		import nc.vo.pub.lang.UFDouble;
		import nc.vo.so.qs.sc.AggMaschineVO;
		import nc.vo.so.qs.sc.MaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineSaveAddAction
/*    */   extends SaveAddAction
/*    */ {
/*    */   private static final long serialVersionUID = -4268689109445670958L;
/*    */   private BillCardPanel billcardpanel;
/*    */   
/*    */   public MaschineSaveAddAction() {}
/*    */   
/*    */   public BillCardPanel getBillcardpanel()
/*    */   {
/* 31 */     return this.billcardpanel;
/*    */   }
/*    */   
/*    */   public void setBillcardpanel(BillCardPanel billcardpanel) {
/* 35 */     this.billcardpanel = billcardpanel;
/*    */   }
/*    */   
/* 38 */   private SaveAction saveAction = null;
/*    */   
/*    */   public void doAction(ActionEvent e) throws Exception
/*    */   {
/* 42 */     AggMaschineVO value = (AggMaschineVO)getEditor().getValue();
/* 43 */     getSaveAction().doAction(e);
/* 44 */     initAddNewModel(value, e);
///* 45 */     ((MaschineBillForm)getEditor()).setDefaultEditProperty();
/*    */   }
/*    */   
/*    */   private void initAddNewModel(AggMaschineVO orgVO, ActionEvent e) throws Exception {
/* 49 */     AggMaschineVO addNew = new AggMaschineVO();
/* 50 */     MaschineVO addHead = (MaschineVO)orgVO.getParentVO();
/* 51 */     MaschineVO addNewHead = new MaschineVO();
/* 52 */     addNewHead.setPk_group(addHead.getPk_group());
/* 53 */     addNewHead.setPk_org(addHead.getPk_org());
/* 54 */     addNewHead.setPk_org_v(addHead.getPk_org_v());
/*    */     
///* 56 */     addNewHead.setFcapacitycalc(Integer.valueOf(1));
///*    */     
///* 58 */     addNewHead.setNparallelnum(UFDouble.ONE_DBL);
/* 59 */     addNew.setParentVO(addNewHead);
/* 60 */     getAddAction().doAction(e);
/* 61 */     getEditor().setValue(addNew);
/*    */   }
/*    */   
/*    */   public SaveAction getSaveAction() {
/* 65 */     return this.saveAction;
/*    */   }
/*    */   
/*    */   public void setSaveAction(SaveAction saveAction) {
/* 69 */     this.saveAction = saveAction;
/*    */   }
/*    */ }
