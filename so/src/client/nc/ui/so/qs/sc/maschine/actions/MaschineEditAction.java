package nc.ui.so.qs.sc.maschine.actions;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import nc.ui.pubapp.uif2app.actions.EditAction;
/*    */ import nc.ui.uif2.model.BillManageModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineEditAction
/*    */   extends EditAction
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public MaschineEditAction() {}
/*    */   
/*    */   public void doAction(ActionEvent e)
/*    */     throws Exception
/*    */   {
/* 20 */     super.doAction(e);
/*    */   }
/*    */   
/*    */   protected boolean isActionEnable()
/*    */   {
/* 25 */     if (!super.isActionEnable()) {
/* 26 */       return false;
/*    */     }
/* 28 */     if ((getModel() instanceof BillManageModel)) {
/* 29 */       BillManageModel mtModel = (BillManageModel)getModel();
/* 30 */       if ((mtModel.getSelectedOperaDatas() != null) && (mtModel.getSelectedOperaDatas().length > 1))
/*    */       {
/* 32 */         return false;
/*    */       }
/*    */     }
/* 35 */     return true;
/*    */   }
/*    */ }
