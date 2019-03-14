package nc.ui.so.qs.sc.maschine.actions;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
///*    */ import nc.ui.pd.pd0404.view.PDWkBillForm;
		import nc.ui.pubapp.uif2app.actions.AddAction;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineAddAction
/*    */   extends AddAction
/*    */ {
/*    */   private static final long serialVersionUID = -5284566997840987779L;
/* 17 */   private MaschineBillForm editor = null;
/*    */   
/*    */   public MaschineAddAction() {}
/*    */   
/* 21 */   public void doAction(ActionEvent e) throws Exception { 
			 super.doAction(e);
/*    */     
///* 23 */     getEditor().setDefaultEditProperty();
/*    */   }
/*    */   
/*    */   public MaschineBillForm getEditor() {
/* 27 */     return this.editor;
/*    */   }
/*    */   
/*    */   public void setEditor(MaschineBillForm editor) {
/* 31 */     this.editor = editor;
/*    */   }
/*    */ }
