 package nc.vo.so.qs.appinterface.util;
 
 import java.io.CharArrayWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Vector;
 import nc.bs.framework.common.InvocationInfoProxy;
 import nc.bs.framework.common.NCLocator;
 import nc.bs.logging.Logger;
 import nc.bs.mapp.conf.MappConfAccessor;
 import nc.bs.pf.pub.PFRequestDataCacheProxy;
 import nc.bs.pf.pub.PfDataCache;
 import nc.bs.pf.pub.WorkflowProcessCache;
 import nc.bs.pf.pub.cache.CondStringKey;
 import nc.bs.pf.pub.cache.ICacheDataQueryCallback;
 import nc.bs.pf.pub.cache.IRequestDataCacheKey;
 import nc.itf.uap.IUAPQueryBS;
 import nc.itf.uap.billtemplate.IBillTemplateQry;
 import nc.itf.uap.pf.IPFConfig;
 import nc.itf.uap.pf.IPFTemplate;
 import nc.itf.uap.pf.IWorkflowMachine;
 import nc.jdbc.framework.SQLParameter;
 import nc.ui.pf.multilang.PfMultiLangUtil;
 import nc.vo.bd.psn.PsnjobVO;
 import nc.vo.jcom.lang.StringUtil;
 import nc.vo.ml.MultiLangUtil;
 import nc.vo.org.DeptVO;
 import nc.vo.org.JobVO;
import nc.vo.pf.mobileapp.TaskMetaData;
 import nc.vo.pf.mobileapp.query.BillVORowCountKey;
 import nc.vo.pf.pub.util.ArrayUtil;
 import nc.vo.pub.AggregatedValueObject;
 import nc.vo.pub.BusinessException;
 import nc.vo.pub.BusinessRuntimeException;
 import nc.vo.pub.bill.BillTempletVO;
 import nc.vo.pub.billtype.BilltypeVO;
 import nc.vo.pub.lang.UFDouble;
 import nc.vo.pub.pf.AssignableInfo;
 import nc.vo.pub.pf.PFClientBizRetVO;
 import nc.vo.pub.pf.WfTaskInfo;
 import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.so.qs.appinterface.factory.TaskTypeFactory;
import nc.vo.so.qs.appinterface.query.ITaskType;
import nc.vo.so.qs.appinterface.query.TaskQuery;
 import nc.vo.uap.pf.TemplateParaVO;
 import nc.vo.wfengine.core.workflow.BasicWorkflowProcess;
import nc.vo.wfengine.pub.WFTask;
 
 
 
 
 
 
 
 public class ExMobileAppUtil
 {
   public static final Integer TRIM_TO_COUNT = Integer.valueOf(50);
   
 
   public ExMobileAppUtil() {}
   
   public static ArrayList<Map<String, Object>> createArrayList()
   {
     return new ArrayList();
   }
   
 
   public static IBillType getBillType(String category, String code)
   {
	   IBillType taskType = QSMaBillTypeFactory.getInstance().get(category, code);
     
	   if (taskType == null) {
		   throw new IllegalArgumentException("invalid category or code: " + category + ", " + code);
	   }
     
	   return taskType;
   }
 
 
   public static HashMap<String, Object> createHashMap()
   {
     return new HashMap();
   }
   
   public static LinkedHashMap<String, Object> createLinkedHashMap() {
     return new LinkedHashMap();
   }
   
   public static Map<Object, Map<String, Object>> convertToMap(List<Map<String, Object>> list, String key) {
     Map<Object, Map<String, Object>> resultMap = new HashMap();
     
 
     for (Map<String, Object> map : list) {
       resultMap.put(map.get(key), map);
     }
     
     return resultMap;
   }
   
 
 
 
 
 
 
 
 
 
 
 
   public static UFDouble adjust2Scale(UFDouble value)
   {
     value.setTrimZero(true);
     String struf = value.toString();
     if ((struf.lastIndexOf(".") > 0) && (struf.length() - (struf.lastIndexOf(".") + 1) >= 2)) {
       return value;
     }
     value.setTrimZero(false);
     return value.setScale(-2, 4);
   }
   
 
 
 
   public static String getFileSize(int filesize)
   {
     if (filesize < 1024)
       return filesize + "B";
     if ((filesize >= 1024) && (filesize < 1048576)) {
       return filesize / 1024 + "KB";
     }
     return filesize / 1048576 + "MB";
   }
   
 
 
 
 
 
 
 
   public static ITaskType getTaskType(String category, String code)
   {
     ITaskType taskType = TaskTypeFactory.getInstance().get(category, code);
     
     if (taskType == null) {
       throw new IllegalArgumentException("invalid category or code: " + category + ", " + code);
     }
     
     return taskType;
   }
   
 
 
 
 
 
   public static String getStringFromObjects(Object[] objs, int idx)
   {
     if (objs == null) {
       return null;
     }
     
     if (idx >= objs.length) {
       return null;
     }
     
     return objs[idx] == null ? null : String.valueOf(objs[idx]);
   }
   
 
 
 
 
 
 
 
   public static TaskMetaData queryTaskMetaData(final String category, final String code, final String taskid)
     throws BusinessException
   {
     IRequestDataCacheKey key = new CondStringKey("mobileapp_query_taskmetadata", new String[] { category, code, taskid });
     
 
 
 
 
     ICacheDataQueryCallback<TaskMetaData> callback = new ICacheDataQueryCallback()
     {
       public TaskMetaData queryData() throws BusinessException
       {
         ITaskType taskType = ExMobileAppUtil.getTaskType(category, code);
         TaskQuery query = taskType.createNewTaskQuery();
         
         TaskMetaData tmd = query.queryTaskMetaData(taskid);
         return tmd;
       }
       
     };
     return (TaskMetaData)PFRequestDataCacheProxy.get(key, callback);
   }
   
 
 
 
 
 
   public static WorkflownoteVO checkWorkflow(String pk_checkflow)
     throws BusinessException
   {
     TaskMetaData tmd = queryTaskMetaData("ishandled", "unhandled", pk_checkflow);
     return checkWorkflow(tmd);
   }
   
 
 
 
 
 
 
   public static WorkflownoteVO checkWorkflow(TaskMetaData tmd)
     throws BusinessException
   {
     IWorkflowMachine srv = (IWorkflowMachine)NCLocator.getInstance().lookup(IWorkflowMachine.class);
     WorkflownoteVO note = srv.checkWorkflowActions(tmd.getBillType(), tmd.getBillId());
     
     return note;
   }
   
   public static PFClientBizRetVO executeClientBiz(AggregatedValueObject aggvo, WorkflownoteVO wfvo) throws BusinessException {
     IWorkflowMachine srv = (IWorkflowMachine)NCLocator.getInstance().lookup(IWorkflowMachine.class);
     return srv.executeClientBizProcess(aggvo, wfvo, false);
   }
   
 
   public static String queryBillTemplateId(String billtype,String userid) throws BusinessException{
	     
	   String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		     
	   BilltypeVO btvo = PfDataCache.getBillTypeInfo(billtype);
		     
	   String funnode = btvo.getNodecode();
		     
	   TemplateParaVO para = new TemplateParaVO();
		     
	   para.setFunNode(funnode);
	   para.setNodeKey("MobileApp");
	   para.setOperator(userid);
	   para.setPk_Corp(pk_group);
	   para.setTemplateType(0);
		     
	   IPFTemplate srv = (IPFTemplate)NCLocator.getInstance().lookup(IPFTemplate.class);
	   String templateid = srv.getTemplateId(para);
		     
	   return templateid;
   }
 
 
 
   public static String queryTemplateId(TaskMetaData tmd)
     throws BusinessException
   {
     String billtype = tmd.getBillType();
     String cuserid = tmd.getCuserid();
     String pk_group = InvocationInfoProxy.getInstance().getGroupId();
     
     BilltypeVO btvo = PfDataCache.getBillTypeInfo(billtype);
     
     String funnode = btvo.getNodecode();
     
     TemplateParaVO para = new TemplateParaVO();
     
     para.setFunNode(funnode);
     para.setOperator(cuserid);
     para.setPk_Corp(pk_group);
     para.setTemplateType(5);
     
     IPFTemplate srv = (IPFTemplate)NCLocator.getInstance().lookup(IPFTemplate.class);
     String templateid = srv.getTemplateId(para);
     
     return templateid;
   }
   
 
 
 
 
   public static BillTempletVO queryTemplate(String pk_template)
     throws BusinessException
   {
     IBillTemplateQry qry = (IBillTemplateQry)NCLocator.getInstance().lookup(IBillTemplateQry.class.getName());
     
     BillTempletVO vo = qry.findTempletData(pk_template);
     
     return vo;
   }
   
 
 
 
 
 
   public static AggregatedValueObject queryBillEntity(final String billtype, final String billid)
     throws BusinessException
   {
     IRequestDataCacheKey key = new CondStringKey("mobileapp_query_billentity", new String[] { billtype, billid });
     
 
 
 
     ICacheDataQueryCallback<AggregatedValueObject> callback = new ICacheDataQueryCallback()
     {
       public AggregatedValueObject queryData() throws BusinessException
       {
         AggregatedValueObject busiObj = ((IPFConfig)NCLocator.getInstance().lookup(IPFConfig.class)).queryBillDataVO(billtype, billid);
         return busiObj;
       }
       
     };
     return (AggregatedValueObject)PFRequestDataCacheProxy.get(key, callback);
   }
   
 
 
 
   public static boolean canAddApprover(WorkflownoteVO note)
   {
     Object value = note.getRelaProperties().get("CanAddApprover");
     
     if ((value != null) && ("true".equalsIgnoreCase(value.toString()))) {
       if (note.actiontype.endsWith("_A"))
       {
         return false;
       }
       return true;
     }
     return false;
   }
   
 
 
 
 
 
   public static boolean canReject(PFClientBizRetVO bizret, WorkflownoteVO note)
   {
     if ((bizret != null) && (!bizret.isShowReject())) {
       return false;
     }
     
     return !note.getActiontype().endsWith("_A");
   }
   
   public static boolean canAgree(PFClientBizRetVO bizret, WorkflownoteVO note) {
     if ((bizret != null) && (!bizret.isShowPass())) {
       return false;
     }
     
     return true;
   }
   
 
 
 
   public static boolean canDisAgree(PFClientBizRetVO bizret, WorkflownoteVO note)
   {
     if ((bizret != null) && (!bizret.isShowNoPass())) {
       return false;
     }
     try
     {
       String pk_wf_def = getPk_wf_def(note);
       BasicWorkflowProcess process = WorkflowProcessCache.getInstance().getWorkflowProcess(pk_wf_def);
       
       if (process != null) {
         return !process.isHideNoPassing();
       }
       return false;
     }
     catch (BusinessException e) {
       throw new BusinessRuntimeException(e.getMessage(), e);
     }
   }
   
   private static String getPk_wf_def(WorkflownoteVO note) throws BusinessException
   {
     if ((note.getTaskInfo() != null) && (note.getTaskInfo().getTask() != null))
     {
       return note.getTaskInfo().getTask().getWfProcessDefPK();
     }
     String cond = "pk_wf_task=?";
     SQLParameter param = new SQLParameter();
     param.addParam(note.getPk_wf_task());
     
     IUAPQueryBS qry = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
     Collection<WFTask> taskCol = qry.retrieveByClause(WFTask.class, WFTask.mappingMeta, cond, new String[] { "pk_wf_task", "processdefid" }, param);
     
 
 
 
 
 
 
 
 
 
     if (ArrayUtil.isNull(taskCol)) {
       return null;
     }
     return ((WFTask)taskCol.iterator().next()).getWfProcessDefPK();
   }
   
 
 
 
 
 
   public static boolean canReassign(WorkflownoteVO note)
   {
     Object value = note.getRelaProperties().get("CanTransfer");
     
     if ((value != null) && ("true".equalsIgnoreCase(value.toString()))) {
       if (note.actiontype.endsWith("_A"))
       {
         return false;
       }
       return true;
     }
     
     return false;
   }
   
 
 
 
 
 
   public static boolean canAssignWhenNoPass(WorkflownoteVO note)
   {
     if (note.getActiontype().endsWith("_A"))
     {
       return false;
     }
     Vector<AssignableInfo> assignInfos = note.getTaskInfo().getAssignableInfos();
     String strCriterion;
     if ((assignInfos != null) && (assignInfos.size() > 0)) {
       strCriterion = null;
       for (AssignableInfo ai : assignInfos) {
         strCriterion = ai.getCheckResultCriterion();
         if ((AssignableInfo.CRITERION_NOTGIVEN.equals(strCriterion)) || (AssignableInfo.CRITERION_NOPASS.equals(strCriterion)))
         {
           return true; }
       }
     }
     return false;
   }
   
 
 
 
 
   public static boolean canAssignWhenPassing(WorkflownoteVO note)
   {
     if (note.getActiontype().endsWith("_A"))
     {
       return false;
     }
     Vector<AssignableInfo> assignInfos = note.getTaskInfo().getAssignableInfos();
     String strCriterion;
     if ((assignInfos != null) && (assignInfos.size() > 0)) {
       strCriterion = null;
       for (AssignableInfo ai : assignInfos) {
         strCriterion = ai.getCheckResultCriterion();
         if ((AssignableInfo.CRITERION_NOTGIVEN.equals(strCriterion)) || (AssignableInfo.CRITERION_PASS.equals(strCriterion)))
         {
           return true; }
       }
     }
     return false;
   }
   
 
   public static <T> List<T> subList(List<T> list, int startIndex, int count)
   {
     List<T> resultList = new ArrayList();
     
     int size = list.size();
     int endIndex = startIndex + count;
     
     if (startIndex >= size) {
       return resultList;
     }
     
     if (endIndex > size) {
       endIndex = size;
     }
     
     List<T> subList = list.subList(startIndex, endIndex);
     
     resultList.addAll(subList);
     
     return resultList;
   }
   
   public static String getStack(Throwable e) {
     CharArrayWriter cw = null;
     PrintWriter pw = null;
     try
     {
       cw = new CharArrayWriter();
       pw = new PrintWriter(cw);
       
       e.printStackTrace(pw);
       
       String msg = cw.toString();
       return msg;
     } catch (Exception ex) { String str1;
       Logger.error(ex.getMessage(), ex);
       return null;
     } finally {
       if (pw != null) {
         pw.close();
       }
     }
   }
   
   public static String getMaPushServlet() {
     String servlet = MappConfAccessor.getInstance().getProperty("mapurl");
     String url = "/" + servlet;
     
     return url;
   }
   
   public static String getMaHost() {
     return MappConfAccessor.getInstance().getMappHost();
   }
   
   public static int getMaPort() {
     try {
       return Integer.parseInt(MappConfAccessor.getInstance().getMappPort());
     } catch (Throwable e) {
       Logger.error(e.getMessage(), e); }
     return 8090;
   }
   
 
 
   public static String getMaPushServiceCode() { return MappConfAccessor.getInstance().getProperty("mapmessageserviceid"); }
   
   public static String getMaPushServiceCode(String key) {
     if ((key != null) && (!key.trim().isEmpty())) {
       return MappConfAccessor.getInstance().getProperty(key);
     }
     return getMaPushServiceCode();
   }
   
   public static String getMaPushToken() {
     return MappConfAccessor.getInstance().getProperty("maservertoken");
   }
   
 
 
 
 
 
   public static String getPsnJobInfo(String pk_psndoc)
     throws BusinessException
   {
     IUAPQueryBS qry = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
     String cond = "pk_psndoc=?";
     
     SQLParameter param = new SQLParameter();
     param.addParam(pk_psndoc);
     
     String[] fields = { "pk_job", "pk_dept" };
     
     Collection<PsnjobVO> col = qry.retrieveByClause(PsnjobVO.class, cond, fields, param);
     
     StringBuffer sb = new StringBuffer();
     if ((col != null) && (col.size() > 0)) {
       PsnjobVO pjv = (PsnjobVO)col.iterator().next();
       
       if (!StringUtil.isEmptyWithTrim(pjv.getPk_dept())) {
         cond = "pk_dept=?";
         
         param = new SQLParameter();
         param.addParam(pjv.getPk_dept());
         
         fields = new String[] { "name", "name" + MultiLangUtil.getCurrentLangSeqSuffix() };
         
         Collection<DeptVO> deptCol = qry.retrieveByClause(DeptVO.class, cond, fields, param);
         
         if ((deptCol != null) && (deptCol.size() > 0)) {
           DeptVO dvo = (DeptVO)deptCol.iterator().next();
           
           String deptName = PfMultiLangUtil.getSuperVONameOfCurrentLang(dvo, "name");
           sb.append(deptName);
         }
       }
       
       if (!StringUtil.isEmptyWithTrim(pjv.getPk_job())) {
         cond = "pk_job=?";
         
         param = new SQLParameter();
         param.addParam(pjv.getPk_job());
         
         fields = new String[] { "jobname", "jobname" + MultiLangUtil.getCurrentLangSeqSuffix() };
         
         Collection<JobVO> jobCol = qry.retrieveByClause(JobVO.class, cond, fields, param);
         
         if ((jobCol != null) && (jobCol.size() > 0)) {
           JobVO jvo = (JobVO)jobCol.iterator().next();
           
           String jobName = PfMultiLangUtil.getSuperVONameOfCurrentLang(jvo, "jobname");
           
           if (sb.length() > 0) {
             sb.append(", ");
           }
           sb.append(jobName);
         }
       }
     }
     
     return sb.toString();
   }
   
 
 
 
   public static void setRowCount(Integer cnt)
   {
     IRequestDataCacheKey key = new BillVORowCountKey();
     PFRequestDataCacheProxy.put(key, cnt);
   }
   
 
 
 
   public static Integer getRowCount()
   {
     Object rowCount = PFRequestDataCacheProxy.get(new BillVORowCountKey());
     if ((rowCount != null) && ((rowCount instanceof Integer))) {
       return (Integer)rowCount;
     }
     return Integer.valueOf(0);
   }
   
   public static void handleException(Exception e) throws BusinessException
   {
     if ((e instanceof BusinessException)) {
       throw ((BusinessException)e);
     }
     throw new BusinessException(e.getMessage(), e);
   }
 }

