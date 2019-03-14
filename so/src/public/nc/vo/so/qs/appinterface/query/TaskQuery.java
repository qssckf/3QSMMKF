package nc.vo.so.qs.appinterface.query;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.bs.logging.Logger;
/*     */ import nc.bs.pf.pub.PfDataCache;
/*     */ import nc.bs.pub.pf.IMobileBillConstructListener;
/*     */ import nc.bs.pub.pf.PfUtilTools;
/*     */ import nc.itf.uap.IUAPQueryBS;
import nc.qs.sc.bill.data.access.BillAccess;
/*     */ import nc.vo.pf.mobileapp.MobileAppUtil;
/*     */ import nc.vo.pf.mobileapp.TaskMetaData;
/*     */ import nc.vo.pf.mobileapp.exception.LoopTerminateNotification;
/*     */ import nc.vo.pf.mobileapp.log.PerformanceLoggerUtil;
import nc.vo.pf.mobileapp.query.ITaskQuery;
/*     */ import nc.vo.pf.pub.util.ArrayUtil;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.CircularlyAccessibleValueObject;
/*     */ import nc.vo.pub.bill.BillTempletVO;
/*     */ import nc.vo.pub.billtype2.Billtype2VO;
/*     */ import nc.vo.pub.billtype2.ExtendedClassEnum;
/*     */ import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TaskQuery
/*     */   implements ITaskQuery
/*     */ {
/*     */   public TaskQuery() {}
/*     */   
/*  43 */   private ITaskType taskType = null;
/*  44 */   private String pk_group = null;
/*  45 */   private String cuserid = null;
/*  46 */   private String date = null;
/*     */   
/*  48 */   private IUAPQueryBS qry = null;
/*     */   
/*     */   protected IUAPQueryBS getQueryService() {
/*  51 */     if (this.qry == null) {
/*  52 */       this.qry = ((IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class));
/*     */     }
/*  54 */     return this.qry;
/*     */   }
/*     */   
/*     */   public ITaskType getTaskType() {
/*  58 */     return this.taskType;
/*     */   }
/*     */   
/*     */   public void setTaskType(ITaskType taskType) {
/*  62 */     this.taskType = taskType;
/*     */   }
/*     */   
/*     */   public String getPk_group() {
/*  66 */     return this.pk_group;
/*     */   }
/*     */   
/*     */   public void setPk_group(String pk_group) {
/*  70 */     this.pk_group = pk_group;
/*     */   }
/*     */   
/*     */   public String getCuserid() {
/*  74 */     return this.cuserid;
/*     */   }
/*     */   
/*     */   public void setCuserid(String cuserid) {
/*  78 */     this.cuserid = cuserid;
/*     */   }
/*     */   
/*     */   public String getDate() {
/*  82 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(String date) {
/*  86 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getDateAsEnd() {
/*  90 */     return new UFDate(this.date).asEnd().toString();
/*     */   }
/*     */   
/*     */   public String getIdentifier()
/*     */   {
/*  95 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  97 */     sb.append(getTaskType().getCategory());
/*  98 */     sb.append(getTaskType().getCode());
/*  99 */     sb.append(getPk_group());
/* 100 */     sb.append(getCuserid());
/* 101 */     sb.append(getDate());
/*     */     
/* 103 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public Map<String, Object> queryTaskBill(String pk) throws BusinessException
/*     */   {
/* 108 */     String mainTag = "TaskQuery.queryTaskBill";
/* 109 */     PerformanceLoggerUtil.begin(mainTag);
/*     */     
/*     */     try
/*     */     {
/* 113 */       PerformanceLoggerUtil.begin("queryTmd");
/* 114 */       TaskMetaData tmd = queryTaskMetaData(pk);
/* 115 */       PerformanceLoggerUtil.end("queryTmd");
/*     */       
/*     */ 
/*     */ 
/* 119 */       PerformanceLoggerUtil.begin("query template id");
/* 120 */       String templateid = MobileAppUtil.queryTemplateId(tmd);
/* 121 */       PerformanceLoggerUtil.end("query template id");
/*     */       
/* 123 */       PerformanceLoggerUtil.begin("query templetvo");
/* 124 */       BillTempletVO tvo = MobileAppUtil.queryTemplate(templateid);
/* 125 */       PerformanceLoggerUtil.end("query templetvo");
/*     */       
/*     */ 
/* 128 */       PerformanceLoggerUtil.begin("query bill entity");
/* 129 */       AggregatedValueObject bvo = MobileAppUtil.queryBillEntity(tmd.getBillType(), tmd.getBillId());
/* 130 */       PerformanceLoggerUtil.end("query bill entity");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 135 */       processEntity(bvo);
/*     */       
/*     */ 
/* 138 */       String billtype = tmd.getBillType();
/* 139 */       IMobileBillConstructListener listener = getConstructListener(billtype);
/*     */       
/* 141 */       if (listener != null)
/*     */       {
/* 143 */         listener.beforeConstruct(tvo, bvo);
/*     */       }
/*     */       
/* 146 */       PerformanceLoggerUtil.begin("construct by bill templet");
/*     */       
/*     */ 
/* 149 */       BillAccess ba = new BillAccess(tvo, bvo);
/* 150 */       Map<String, List<Map<String, Object>>> billVOMap = ba.billVO2Map(billtype);
/*     */       
/* 152 */       PerformanceLoggerUtil.end("construct by bill templet");
/*     */       
/* 154 */       if (listener != null)
/*     */       {
/* 156 */         listener.afterConstruct(billVOMap);
/*     */       }
/*     */       
/* 159 */       PerformanceLoggerUtil.begin("after construct, process row count");
/*     */       
/*     */ 
/* 162 */       int origin = MobileAppUtil.getRowCount().intValue();
/* 163 */       int current = ArrayUtil.getLength(bvo.getChildrenVO());
/* 164 */       Map<String, Object> result = processResultSetRowCount(billVOMap, origin, current);
/* 165 */       PerformanceLoggerUtil.end("after construct, process row count");
/*     */       
/*     */ 
/* 168 */       return result;
/*     */     } finally {
/* 170 */       PerformanceLoggerUtil.end(mainTag);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<String, Object> processResultSetRowCount(Map<String, List<Map<String, Object>>> billVOMap, int origin, int current)
/*     */   {
/* 184 */     Map<String, Object> resultMap = MobileAppUtil.createLinkedHashMap();
/*     */     
/* 186 */     for (Iterator<Map.Entry<String, List<Map<String, Object>>>> it = billVOMap.entrySet().iterator(); it.hasNext();) {
/* 187 */       Map.Entry<String, List<Map<String, Object>>> entry = (Map.Entry)it.next();
/*     */       
/* 189 */       String key = (String)entry.getKey();
/* 190 */       List<Map<String, Object>> value = (List)entry.getValue();
/*     */       
/* 192 */       resultMap.put(key, value);
/*     */       try
/*     */       {
/* 195 */         if (key.equals("body"))
/*     */         {
/*     */ 
/* 198 */           Map<String, Object> bodyMap = (Map)value.get(0);
/* 199 */           bodyMap.put("rowcnt", String.valueOf(origin));
/*     */         }
/*     */       } catch (Exception e) {
/* 202 */         Logger.error(e.getMessage(), e);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 207 */     if (origin > current)
/*     */     {
/*     */       try
/*     */       {
/* 211 */         processElement(resultMap);
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 220 */     return resultMap;
/*     */   }
/*     */   
/*     */   private void processElement(Object obj) throws LoopTerminateNotification {
/*     */     Map map;
/*     */     Iterator<String> it;
/* 226 */     if ((obj instanceof List)) {
/* 227 */       for (Object sub : (List)obj) {
/* 228 */         if (sub != null) {
/* 229 */           processElement(sub);
/*     */         }
/*     */       }
/* 232 */     } else if ((obj instanceof Map)) {
/* 233 */       map = (Map)obj;
/*     */       
/* 235 */       if (map.containsKey("ibodyrowcount")) {
/* 236 */         Integer rowcnt = MobileAppUtil.getRowCount();
/* 237 */         map.put("ibodyrowcount", String.valueOf(rowcnt));
/* 238 */         throw new LoopTerminateNotification();
/*     */       }
/* 240 */       for (it = map.keySet().iterator(); it.hasNext();) {
/* 241 */         String key = (String)it.next();
/* 242 */         Object value = map.get(key);
/*     */         
/* 244 */         processElement(value);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void processEntity(AggregatedValueObject aggVo)
/*     */   {
/* 252 */     processRowCount(aggVo);
/*     */     
/* 254 */     CircularlyAccessibleValueObject parent = aggVo.getParentVO();
/* 255 */     CircularlyAccessibleValueObject[] children = aggVo.getChildrenVO();
/*     */     
/*     */ 
/* 258 */     processDouble(parent);
/* 259 */     if (!ArrayUtil.isNull(children)) {
/* 260 */       for (CircularlyAccessibleValueObject c : children) {
/* 261 */         processDouble(c);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void processRowCount(AggregatedValueObject aggVo)
/*     */   {
/*     */     try
/*     */     {
/* 275 */       CircularlyAccessibleValueObject[] children = aggVo.getChildrenVO();
/* 276 */       Integer rowCount = Integer.valueOf(children == null ? 0 : children.length);
/*     */       
/* 278 */       Logger.debug("____row count: " + rowCount);
/*     */       
/* 280 */       MobileAppUtil.setRowCount(rowCount);
/*     */       
/* 282 */       if (rowCount.intValue() > MobileAppUtil.TRIM_TO_COUNT.intValue())
/*     */       {
/*     */ 
/* 285 */         List<CircularlyAccessibleValueObject> subList = Arrays.asList(children).subList(0, MobileAppUtil.TRIM_TO_COUNT.intValue());
/* 286 */         CircularlyAccessibleValueObject[] casted = (CircularlyAccessibleValueObject[])ArrayUtil.convertToArray(subList);
/* 287 */         aggVo.setChildrenVO((CircularlyAccessibleValueObject[])casted);
/*     */       }
/*     */     } catch (Exception e) {
/* 290 */       Logger.error(e.getMessage(), e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void processDouble(CircularlyAccessibleValueObject vo) {
/* 295 */     String[] attrs = vo.getAttributeNames();
/*     */     
/* 297 */     for (String att : attrs) {
/* 298 */       Object value = vo.getAttributeValue(att);
/*     */       
/* 300 */       if ((value instanceof UFDouble)) {
/* 301 */         UFDouble d = (UFDouble)value;
/* 302 */         d = MobileAppUtil.adjust2Scale(d);
/*     */         
/* 304 */         vo.setAttributeValue(att, d);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private IMobileBillConstructListener getConstructListener(String billtype) {
/* 310 */     List<Billtype2VO> list = PfDataCache.getBillType2Info(billtype, ExtendedClassEnum.MOBILEBILL_CONSTRUCT_LISTENER.getIntValue());
/*     */     
/* 312 */     if ((list != null) && (list.size() > 0)) {
/* 313 */       Billtype2VO b2vo = (Billtype2VO)list.get(0);
/*     */       
/* 315 */       String className = b2vo.getClassname();
/* 316 */       String realBillType = b2vo.getPk_billtype();
/*     */       try
/*     */       {
/* 319 */         return (IMobileBillConstructListener)PfUtilTools.instantizeObject(realBillType, className);
/*     */       }
/*     */       catch (Exception e) {
/* 322 */         Logger.error(e.getMessage(), e);
/*     */       }
/*     */     }
/*     */     
/* 326 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<String, Object> queryTaskActions(String pk)
/*     */     throws BusinessException
/*     */   {
/* 334 */     return new HashMap();
/*     */   }
/*     */   
/*     */   public Map<String, Object> queryTaskActionDefaultValue(String pk, String actionCode) throws BusinessException
/*     */   {
/* 339 */     return MobileAppUtil.createHashMap();
/*     */   }
/*     */ }

