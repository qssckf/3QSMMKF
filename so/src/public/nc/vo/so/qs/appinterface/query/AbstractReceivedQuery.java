package nc.vo.so.qs.appinterface.query;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nc.bs.pf.pub.PFRequestDataCacheProxy;
/*     */ import nc.bs.pf.pub.cache.CondStringKey;
/*     */ import nc.bs.pf.pub.cache.ICacheDataQueryCallback;
/*     */ import nc.bs.pf.pub.cache.IRequestDataCacheKey;
/*     */ import nc.itf.uap.IUAPQueryBS;
/*     */ import nc.vo.pf.mobileapp.TaskMetaData;
/*     */ import nc.vo.pf.mobileapp.exception.TaskNotValidException;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFDateTime;
/*     */ import nc.vo.pub.msg.MessageVO;
/*     */ import nc.vo.pub.workflownote.WorkflownoteVO;
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
/*     */ public abstract class AbstractReceivedQuery
/*     */   extends TaskQuery
/*     */ {
/*     */   protected static final String CHECKMAN = "#checkman#";
/*     */   protected static final String WORKFLOWTYPE_IN = "#workflowtype#";
/*     */   protected static final String APPROVRESTATUS = "#approvestatus#";
/*     */   protected static final String PK_GROUP = "#pk_group#";
/*     */   protected static final String SENDDATE = "#senddate#";
/*     */   private static final String SQL = "select pk_checkflow,senddate from pub_workflownote where actiontype like 'Z%' and checkman='#checkman#'  and workflow_type in #workflowtype# and approvestatus=#approvestatus# and pk_group ='#pk_group#'  and senddate<'#senddate#' order by senddate desc";
/*     */   
/*     */   public AbstractReceivedQuery() {}
/*     */   
/*     */   protected String getBaseSql()
/*     */   {
/*  42 */     return "select pk_checkflow,senddate from pub_workflownote where (actiontype like 'Z%' or actiontype='MAKEBILL') and checkman='#checkman#'  and workflow_type in #workflowtype# and approvestatus=#approvestatus# and pk_group ='#pk_group#'  and senddate<'#senddate#' order by senddate desc";
/*     */   }
/*     */   
/*     */ 
/*     */   public List<Map<String, Object>> queryByPks(String[] pks)
/*     */     throws BusinessException
/*     */   {
/*  49 */     List<Map<String, Object>> list = new ArrayList();
/*     */     
/*     */ 
/*  52 */     for (String pk : pks) {
/*  53 */       TaskMetaData tmd = queryTaskMetaData(pk);
/*  54 */       Map<String, Object> map = convertToTask(tmd);
/*     */       
/*  56 */       list.add(map);
/*     */     }
/*     */     
/*  59 */     return list;
/*     */   }
/*     */   
/*     */   private Map<String, Object> convertToTask(TaskMetaData tmd) {
/*  63 */     Map<String, Object> map = new HashMap();
/*     */     
/*  65 */     map.put("taskid", tmd.getPk_checkflow());
/*  66 */     map.put("date", tmd.getStartDate());
/*  67 */     map.put("title", tmd.getTitle());
/*     */     
/*  69 */     return map;
/*     */   }
/*     */   
/*     */   public TaskMetaData queryTaskMetaData(final String pk)
/*     */     throws BusinessException
/*     */   {
/*  75 */     IRequestDataCacheKey key = new CondStringKey("mobileapp_received_querytaskmetadata", new String[] { pk });
/*  76 */     ICacheDataQueryCallback<TaskMetaData> callback = new ICacheDataQueryCallback()
/*     */     {
/*     */       public TaskMetaData queryData()
/*     */         throws BusinessException
/*     */       {
/*  81 */         WorkflownoteVO note = (WorkflownoteVO)AbstractReceivedQuery.this.getQueryService().retrieveByPK(WorkflownoteVO.class, pk);
/*     */         
/*  83 */         if (note == null) {
/*  84 */           throw new TaskNotValidException();
/*     */         }
/*     */         
/*  87 */         return AbstractReceivedQuery.this.convertToMeta(note);
/*     */       }
/*     */       
/*  90 */     };
/*  91 */     return (TaskMetaData)PFRequestDataCacheProxy.get(key, callback);
/*     */   }
/*     */   
/*     */   private TaskMetaData convertToMeta(WorkflownoteVO note) {
/*  95 */     TaskMetaData tmd = new TaskMetaData();
/*     */     
/*  97 */     tmd.setBillType(note.getPk_billtype());
/*  98 */     tmd.setBillId(note.getBillVersionPK());
/*  99 */     tmd.setBillNo(note.getBillno());
/*     */     
/* 101 */     tmd.setCuserid(note.getCheckman());
/* 102 */     tmd.setPk_checkflow(note.getPrimaryKey());
/* 103 */     tmd.setTitle(MessageVO.getMessageNoteAfterI18N(note.getMessagenote()));
/* 104 */     tmd.setStartDate(note.getSenddate().toString());
/*     */     
/* 106 */     return tmd;
/*     */   }
/*     */ }

