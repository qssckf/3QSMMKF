package nc.vo.so.qs.appinterface.query;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import nc.bs.logging.Logger;
/*     */ import nc.vo.pf.mobileapp.MobileAppUtil;
/*     */ import nc.vo.pf.mobileapp.TaskMetaData;
/*     */ import nc.vo.pf.mobileapp.log.PerformanceLoggerUtil;
/*     */ import nc.vo.pf.term.ApproveTermConfig;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.pf.AssignableInfo;
/*     */ import nc.vo.pub.pf.PFClientBizRetVO;
/*     */ import nc.vo.pub.pf.WfTaskInfo;
/*     */ import nc.vo.pub.workflownote.WorkflownoteVO;
/*     */ import nc.vo.wfengine.definition.WorkflowTypeEnum;
/*     */ import nc.vo.wfengine.pub.WfTaskOrInstanceStatus;
/*     */ 
/*     */ public class ReceivedUnhandledQuery extends AbstractReceivedQuery
/*     */ {
/*     */   public ReceivedUnhandledQuery() {}
/*     */   
/*     */   public String getPksSql()
/*     */   {
/*  27 */     String checkman = getCuserid();
/*  28 */     String pk_group = getPk_group();
/*  29 */     String workflowTypeIn = "(" + WorkflowTypeEnum.Approveflow.getIntValue() + ", " + WorkflowTypeEnum.SubApproveflow.getIntValue() + ", " + WorkflowTypeEnum.SubWorkApproveflow.getIntValue() + ")";
/*     */     
/*     */ 
/*  32 */     String procStatus = String.valueOf(WfTaskOrInstanceStatus.Started.getIntValue());
/*     */     
/*  34 */     String sql = getBaseSql().replace("#checkman#", checkman).replace("#workflowtype#", workflowTypeIn).replace("#approvestatus#", procStatus).replace("#pk_group#", pk_group).replace("#senddate#", getDateAsEnd());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */     return sql;
/*     */   }
/*     */   
/*     */   public Map<String, Object> queryTaskActions(String pk)
/*     */     throws BusinessException
/*     */   {
/*  46 */     String mainTag = "ReceivedUnhandledQuery.queryTaskActions";
/*  47 */     PerformanceLoggerUtil.begin(mainTag);
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  52 */       PerformanceLoggerUtil.begin("query task metadata");
/*  53 */       TaskMetaData tmd = MobileAppUtil.queryTaskMetaData("ishandled", "unhandled", pk);
/*     */       
/*  55 */       PerformanceLoggerUtil.end("query task metadata");
/*     */       
/*     */ 
/*  58 */       PerformanceLoggerUtil.begin("query bill entity");
/*  59 */       AggregatedValueObject billvo = MobileAppUtil.queryBillEntity(tmd.getBillType(), tmd.getBillId());
/*  60 */       PerformanceLoggerUtil.end("query bill entity");
/*     */       
/*     */ 
/*  63 */       PerformanceLoggerUtil.begin("checkworkflow");
/*  64 */       WorkflownoteVO note = MobileAppUtil.checkWorkflow(tmd);
/*  65 */       PerformanceLoggerUtil.end("checkworkflow");
/*     */       
/*     */ 
/*  68 */       PFClientBizRetVO bizret = null;
/*     */       try
/*     */       {
/*  71 */         PerformanceLoggerUtil.begin("execute client biz process");
/*     */         
/*  73 */         bizret = MobileAppUtil.executeClientBiz(billvo, note);
/*     */       } catch (Exception e) {
/*  75 */         Logger.error(e.getMessage(), e);
/*     */       } finally {
/*  77 */         PerformanceLoggerUtil.end("execute client biz process");
/*     */       }
/*     */       
/*  80 */       List<Map<String, Object>> list = MobileAppUtil.createArrayList();
/*     */       
/*     */ 
/*  83 */       if (MobileAppUtil.canAgree(bizret, note)) {
/*  84 */         Map<String, Object> passingMap = MobileAppUtil.createHashMap();
/*     */         
/*  86 */         ((Map)passingMap).put("code", "doAgree");
/*  87 */         ((Map)passingMap).put("name", ApproveTermConfig.getInstance().getText("pass"));
/*     */         
/*  89 */         if (MobileAppUtil.canAssignWhenPassing(note))
/*     */         {
/*  91 */           ((Map)passingMap).put("actflag", "0");
/*     */         } else {
/*  93 */           ((Map)passingMap).put("actflag", "1");
/*     */         }
/*     */         
/*  96 */         list.add(passingMap);
/*     */       }
/*     */       
/*     */ 
/* 100 */       if (MobileAppUtil.canDisAgree(bizret, note)) {
/* 101 */         Map<String, Object> noPassMap = MobileAppUtil.createHashMap();
/*     */         
/* 103 */         ((Map)noPassMap).put("code", "doDisAgree");
/* 104 */         ((Map)noPassMap).put("name", ApproveTermConfig.getInstance().getText("nopass"));
/*     */         
/* 106 */         if (MobileAppUtil.canAssignWhenNoPass(note))
/*     */         {
/* 108 */           ((Map)noPassMap).put("actflag", "2");
/*     */         } else {
/* 110 */           ((Map)noPassMap).put("actflag", "3");
/*     */         }
/*     */         
/* 113 */         list.add(noPassMap);
/*     */       }
/*     */       
/*     */ 
/* 117 */       if (MobileAppUtil.canReject(bizret, note)) {
/* 118 */         Map<String, Object> rejectMap = MobileAppUtil.createHashMap();
/*     */         
/* 120 */         ((Map)rejectMap).put("code", "doReject");
/* 121 */         ((Map)rejectMap).put("name", ApproveTermConfig.getInstance().getText("reject"));
/* 122 */         ((Map)rejectMap).put("actflag", "4");
/*     */         
/* 124 */         list.add(rejectMap);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 129 */       if (MobileAppUtil.canReassign(note)) {
/* 130 */         Map<String, Object> reassignMap = MobileAppUtil.createHashMap();
/*     */         
/* 132 */         ((Map)reassignMap).put("code", "doReassign");
/* 133 */         ((Map)reassignMap).put("name", ApproveTermConfig.getInstance().getText("transfer"));
/* 134 */         ((Map)reassignMap).put("actflag", "5");
/*     */         
/* 136 */         list.add(reassignMap);
/*     */       }
/*     */       
/*     */ 
/* 140 */       if (MobileAppUtil.canAddApprover(note)) {
/* 141 */         Map<String, Object> addApproverMap = MobileAppUtil.createHashMap();
/*     */         
/* 143 */         ((Map)addApproverMap).put("code", "doAddApprove");
/* 144 */         ((Map)addApproverMap).put("name", ApproveTermConfig.getInstance().getText("addapprover"));
/* 145 */         ((Map)addApproverMap).put("actflag", "6");
/*     */         
/* 147 */         list.add(addApproverMap);
/*     */       }
/*     */       
/* 150 */       Map<String, Object> resultMap = MobileAppUtil.createHashMap();
/*     */       
/* 152 */       if ((bizret != null) && (bizret.getHintMessage() != null)) {
/* 153 */         ((Map)resultMap).put("hint", bizret.getHintMessage());
/*     */       } else {
/* 155 */         ((Map)resultMap).put("hint", "");
/*     */       }
/*     */       
/* 158 */       if ((bizret != null) && (bizret.isStopFlow())) {
/* 159 */         ((Map)resultMap).put("actionstructlist", MobileAppUtil.createArrayList());
/*     */       } else {
/* 161 */         ((Map)resultMap).put("actionstructlist", list);
/*     */       }
/*     */       
/* 164 */       return resultMap;
/*     */     } finally {
/* 166 */       PerformanceLoggerUtil.end(mainTag);
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<String, Object> queryTaskActionDefaultValue(String pk, String actionCode)
/*     */     throws BusinessException
/*     */   {
/* 173 */     WorkflownoteVO worknote = MobileAppUtil.checkWorkflow(pk);
/*     */     
/* 175 */     Map<String, Object> map = MobileAppUtil.createHashMap();
/*     */     
/* 177 */     boolean canAssign = false;
/*     */     
/* 179 */     if ("doAgree".equals(actionCode)) {
/* 180 */       Vector<AssignableInfo> assignInfos = worknote.getTaskInfo().getAssignableInfos();
/*     */       
/* 182 */       if ((assignInfos != null) && (assignInfos.size() > 0)) {
/* 183 */         for (AssignableInfo ai : assignInfos) {
/* 184 */           if ((AssignableInfo.CRITERION_PASS.equals(ai.getCheckResultCriterion())) || (AssignableInfo.CRITERION_NOTGIVEN.equals(ai.getCheckResultCriterion())))
/*     */           {
/* 186 */             canAssign = true;
/* 187 */             break;
/*     */           }
/*     */         }
/*     */       }
/* 191 */     } else if ("doDisAgree".equals(actionCode)) {
/* 192 */       Vector<AssignableInfo> assignInfos = worknote.getTaskInfo().getAssignableInfos();
/*     */       
/* 194 */       if ((assignInfos != null) && (assignInfos.size() > 0)) {
/* 195 */         for (AssignableInfo ai : assignInfos) {
/* 196 */           if ((AssignableInfo.CRITERION_NOPASS.equals(ai.getCheckResultCriterion())) || (AssignableInfo.CRITERION_NOTGIVEN.equals(ai.getCheckResultCriterion())))
/*     */           {
/* 198 */             canAssign = true;
/* 199 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 204 */     map.put("isassign", UFBoolean.valueOf(canAssign).toString());
/* 205 */     map.put("isassignsigle", "N");
/* 206 */     map.put("assigntype", "1");
/* 207 */     map.put("isupload", "Y");
/* 208 */     map.put("isbassign", "N");
/* 209 */     map.put("isaassign", "N");
/* 210 */     map.put("issend", "N");
/* 211 */     map.put("ishasmemo", "0");
/* 212 */     map.put("memo", "N");
/* 213 */     map.put("ishasnote", "3");
/* 214 */     return map;
/*     */   }
/*     */ }

