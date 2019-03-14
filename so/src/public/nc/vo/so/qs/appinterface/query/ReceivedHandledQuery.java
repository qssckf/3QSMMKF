package nc.vo.so.qs.appinterface.query;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import nc.bs.ml.NCLangResOnserver;
/*    */ import nc.vo.pf.mobileapp.MobileAppUtil;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.wfengine.definition.WorkflowTypeEnum;
/*    */ 
/*    */ public class ReceivedHandledQuery extends AbstractReceivedQuery
/*    */ {
/*    */   public ReceivedHandledQuery() {}
/*    */   
/*    */   public String getPksSql()
/*    */   {
/* 16 */     String checkman = getCuserid();
/* 17 */     String workflowTypeIn = "(" + WorkflowTypeEnum.Approveflow.getIntValue() + ", " + WorkflowTypeEnum.SubApproveflow.getIntValue() + ", " + WorkflowTypeEnum.SubWorkApproveflow.getIntValue() + ")";
/* 18 */     String procStatus = String.valueOf(nc.vo.wfengine.pub.WfTaskOrInstanceStatus.Finished.getIntValue());
/*    */     
/* 20 */     String sql = getBaseSql().replace("#checkman#", checkman).replace("#workflowtype#", workflowTypeIn).replace("#approvestatus#", procStatus).replace("#pk_group#", getPk_group()).replace("#senddate#", getDateAsEnd());
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 27 */     return sql;
/*    */   }
/*    */   
/*    */   public Map<String, Object> queryTaskActions(String pk)
/*    */     throws BusinessException
/*    */   {
/* 33 */     String mainTag = "ReceivedHandledQuery.queryTaskActions";
/*    */     try {
/* 35 */       List<Map<String, Object>> list = MobileAppUtil.createArrayList();
/*    */       
/* 37 */       Map<String, Object> doBackMap = MobileAppUtil.createHashMap();
/* 38 */       doBackMap.put("code", "doBack");
/* 39 */       String btnName = NCLangResOnserver.getInstance().getStrByID("mobileapp", "PFMobileAppServiceImpl-000007");
/* 40 */       doBackMap.put("name", btnName);
/* 41 */       list.add(doBackMap);
/* 42 */       Map<String, Object> resultMap = MobileAppUtil.createHashMap();
/* 43 */       resultMap.put("actionstructlist", list);
/* 44 */       return resultMap;
/*    */     }
/*    */     finally {
/* 47 */       nc.vo.pf.mobileapp.log.PerformanceLoggerUtil.end(mainTag);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public Map<String, Object> queryTaskActionDefaultValue(String pk, String actionCode)
/*    */     throws BusinessException
/*    */   {
/* 55 */     Map<String, Object> map = MobileAppUtil.createHashMap();
/* 56 */     map.put("isassign", "N");
/* 57 */     map.put("isassignsigle", "N");
/* 58 */     map.put("assigntype", "1");
/* 59 */     map.put("isupload", "Y");
/* 60 */     map.put("isbassign", "N");
/* 61 */     map.put("isaassign", "N");
/* 62 */     map.put("issend", "N");
/* 63 */     map.put("ishasmemo", "0");
/* 64 */     map.put("memo", "N");
/* 65 */     map.put("ishasnote", "3");
/* 66 */     return map;
/*    */   }
/*    */ }
