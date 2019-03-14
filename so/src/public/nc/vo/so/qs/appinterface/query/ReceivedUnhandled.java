package nc.vo.so.qs.appinterface.query;
/*    */ 
/*    */ import nc.vo.ml.AbstractNCLangRes;
/*    */ import nc.vo.ml.NCLangRes4VoTransl;

/*    */ 
/*    */ 
/*    */ public class ReceivedUnhandled
/*    */   implements ITaskType
/*    */ {
/*    */   public ReceivedUnhandled() {}
/*    */   
/*    */   public String getCategory()
/*    */   {
/* 16 */     return "ishandled";
/*    */   }
/*    */   
/*    */   public String getCode()
/*    */   {
/* 21 */     return "unhandled";
/*    */   }
/*    */   
/*    */   public String getName()
/*    */   {
/* 26 */     return NCLangRes4VoTransl.getNCLangRes().getStrByID("mobileapp", "SubmittedUnhandled-000000");
/*    */   }
/*    */   
/*    */   public TaskQuery createNewTaskQuery()
/*    */   {
/* 31 */     return new ReceivedUnhandledQuery();
/*    */   }
/*    */ }

