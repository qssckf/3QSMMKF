package nc.vo.so.qs.appinterface.query;
/*    */ 
/*    */ import nc.vo.ml.AbstractNCLangRes;
/*    */ import nc.vo.ml.NCLangRes4VoTransl;


/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReceivedHandled
/*    */   implements ITaskType
/*    */ {
/*    */   public ReceivedHandled() {}
/*    */   
/*    */   public String getCategory()
/*    */   {
/* 17 */     return "ishandled";
/*    */   }
/*    */   
/*    */   public String getCode()
/*    */   {
/* 22 */     return "handled";
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 27 */     return NCLangRes4VoTransl.getNCLangRes().getStrByID("mobileapp", "SubmittedHandled-000000");
/*    */   }
/*    */   
/*    */   public TaskQuery createNewTaskQuery()
/*    */   {
/* 32 */     return new ReceivedHandledQuery();
/*    */   }
/*    */ }

