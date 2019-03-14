package nc.qs.so.app.pubapp.pub.common.context;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import nc.desktop.ui.WorkbenchEnvironment;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
/*     */ import nc.vo.pubapp.pattern.pub.Constructor;
/*     */ import nc.vo.pubapp.pflow.PfUserObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PFlowContext
/*     */ {
/*     */   private String actionName;
/*     */   public PFlowContext() {}
/*     */   
/*     */   public static enum PFActionType
/*     */   {
/*  43 */     APPROVE, 
/*     */     
/*  45 */     DISCARD, 
/*     */     
/*  47 */     NOSET, 
/*     */     
/*  49 */     SAVE, 
/*     */     
/*  51 */     SENDAPPOVE, 
/*     */     
/*  53 */     UNAPPORVE;
/*     */     
/*     */ 
/*     */     private PFActionType() {}
/*     */   }
/*     */   
/*  59 */   private PFActionType actionType = PFActionType.NOSET;
/*     */   
/*     */   private String billType;
/*     */   
/*     */   private AbstractBill[] billVos;
/*     */   
/*     */   private AbstractBill checkVo;
/*     */   
/*     */   private Map<Object, Object> eParam;
/*     */   
/*  69 */   private Map<Integer, String> exceptionResults = new HashMap();
/*     */   
/*     */ 
/*     */   private boolean isBatch;
/*     */   
/*     */   
/*     */   private String strBeforeUIClass;
/*     */   
/*     */   private String trantype;
/*     */   
/*  80 */   private String userId = "";
/*     */   private PfUserObject[] userObj;
/*     */   
/*     */   public void clear()
/*     */   {
/*  85 */     this.billVos = null;
/*  86 */     this.userObj = null;
/*  87 */     this.trantype = null;
/*  88 */     this.userId = "";
/*  89 */     this.eParam = null;
/*  90 */     this.exceptionResults.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void combinExceptionResults(Map<Integer, String> newExceptionResults)
/*     */   {
/*  99 */     Set<Map.Entry<Integer, String>> entrySet = newExceptionResults.entrySet();
/* 100 */     for (Map.Entry<Integer, String> entry : entrySet) {
/* 101 */       if (!alreadyContainResult((Integer)entry.getKey())) {
/* 102 */         this.exceptionResults.put(entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getActionName()
/*     */   {
/* 111 */     return this.actionName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PFActionType getActionType()
/*     */   {
/* 118 */     return this.actionType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getBillType()
/*     */   {
/* 125 */     return this.billType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AggregatedValueObject getBillVo()
/*     */   {
/* 132 */     if ((null != this.billVos) && (this.billVos.length > 0)) {
/* 133 */       return this.billVos[0];
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AggregatedValueObject[] getBillVos()
/*     */   {
/* 142 */     return this.billVos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AggregatedValueObject getCheckVo()
/*     */   {
/* 149 */     return this.checkVo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<Object, Object> getEParam()
/*     */   {
/* 156 */     if (this.eParam == null) {
/* 157 */       this.eParam = new HashMap();
/*     */     }
/* 159 */     return this.eParam;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getExceptionResults() {
/* 163 */     return this.exceptionResults;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   
/*     */ 
/*     */ 
/*     */   public String getStrBeforeUIClass()
/*     */   {
/* 177 */     return this.strBeforeUIClass;
/*     */   }
/*     */   
/*     */   public String getTrantype() {
/* 181 */     return this.trantype;
/*     */   }
/*     */   
/*     */   public String getUserId() {
/* 185 */     return this.userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PfUserObject getUserObj()
/*     */   {
/* 192 */     if ((null != this.userObj) && (this.userObj.length > 0)) {
/* 193 */       return this.userObj[0];
/*     */     }
/* 195 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public PfUserObject[] getUserObjs()
/*     */   {
/* 202 */     return this.userObj;
/*     */   }
/*     */   
/*     */   public boolean isBatch() {
/* 206 */     return this.isBatch;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActionName(String actionName)
/*     */   {
/* 214 */     this.actionName = actionName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setActionType(PFActionType actionType)
/*     */   {
/* 222 */     this.actionType = actionType;
/*     */   }
/*     */   
/*     */   public void setBatch(boolean batch) {
/* 226 */     this.isBatch = batch;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBillType(String billType)
/*     */   {
/* 234 */     this.billType = billType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBillVo(AbstractBill billVo)
/*     */   {
/* 242 */     if (billVo != null) {
/* 243 */       this.billVos = ((AbstractBill[])Constructor.construct(billVo.getClass(), 1));
/*     */     }
/* 245 */     this.billVos[0] = billVo;
/* 246 */     setBatch(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBillVos(AbstractBill[] billVos)
/*     */   {
/* 254 */     this.billVos = billVos;
/* 255 */     if ((billVos != null) && (billVos.length > 1)) {
/* 256 */       setBatch(true);
/*     */     }
/*     */     else {
/* 259 */       setBatch(false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCheckVo(AbstractBill checkVo)
/*     */   {
/* 268 */     this.checkVo = checkVo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEParam(Map<Object, Object> param)
/*     */   {
/* 276 */     this.eParam = param;
/*     */   }
/*     */   
/*     */   public void setExceptionResults(Map<Integer, String> exceptionResults) {
/* 280 */     this.exceptionResults = exceptionResults;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStrBeforeUIClass(String strBeforeUIClass)
/*     */   {
/* 296 */     this.strBeforeUIClass = strBeforeUIClass;
/*     */   }
/*     */   
/*     */   public void setTrantype(String trantype) {
/* 300 */     this.trantype = trantype;
/*     */   }
/*     */   
/*     */   public void setUserId(String userId) {
/* 304 */     this.userId = userId;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserObj(PfUserObject userObj)
/*     */   {
/* 312 */     if (null != userObj) {
/* 313 */       this.userObj = new PfUserObject[] { userObj };
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUserObjs(PfUserObject[] userObjs)
/*     */   {
/* 324 */     this.userObj = userObjs;
/*     */   }
/*     */   
/*     */   private boolean alreadyContainResult(Integer index) {
/* 328 */     return (this.exceptionResults.containsKey(index)) && (this.exceptionResults.get(index) != null);
/*     */   }
/*     */ }
