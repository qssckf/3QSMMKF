package nc.itf.so.qs.sc.FQ01trantype;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import nc.bs.framework.aop.Wrapper;
/*    */ import nc.pubimpl.bdlayer.cache.CacheVOQuery;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
///*    */ import nc.vo.so.m38trantype.entity.M38TranTypeVO;
/*    */ import nc.vo.so.m30trantype.entity.M30TranTypeVO;
/*    */ import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
/*    */ 
/*    */ 
/*    */ public class FQ01TranTypeServiceImpl implements IFQ01TranTypeService, Wrapper {
/*    */   public FQ01TranTypeServiceImpl() {}
/*    */   
/*    */   public void setWrapping(Object wrapping) {}
/*    */   
/*    */   public FQ01TranTypeVO queryTranTypeVO(String ctrantypeid)
/*    */     throws BusinessException
/*    */   {
/* 22 */     FQ01TranTypeVO[] retVOs = queryTranTypeVOs(new String[] { ctrantypeid });
/*    */     
/*    */ 
/* 25 */     if ((retVOs != null) && (retVOs.length > 0)) {
/* 26 */       return retVOs[0];
/*    */     }
/* 28 */     return null;
/*    */   }
/*    */   
/*    */   public FQ01TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids) throws BusinessException
/*    */   {
/* 33 */     FQ01TranTypeVO[] retVOs = null;
/*    */     try {
/* 35 */       String[] selectFds = getTranTypeSelectFds();
/* 36 */       String[] whrFields = { "ctrantypeid" };
/* 37 */       int length = ctrantypeids.length;
/* 38 */       String[][] whrFdValues = new String[length][];
/* 39 */       for (int i = 0; i < length; i++) {
/* 40 */         whrFdValues[i] = new String[1];
/* 41 */         whrFdValues[i][0] = ctrantypeids[i];
/*    */       }
/*    */       
/* 44 */       CacheVOQuery<FQ01TranTypeVO> cacheVOQuery = new CacheVOQuery(FQ01TranTypeVO.class, selectFds);
/*    */       
/* 46 */       retVOs = (FQ01TranTypeVO[])cacheVOQuery.query(whrFields, whrFdValues);
/*    */     }
/*    */     catch (Exception ex) {
/* 49 */       ExceptionUtils.marsh(ex);
/*    */     }
/* 51 */     return retVOs;
/*    */   }
/*    */   
/*    */   private String[] getTranTypeSelectFds()
/*    */   {
/* 56 */     List<String> attNames = new ArrayList();
/* 57 */     String[] allAtts = new FQ01TranTypeVO().getAttributeNames();
/* 58 */     for (String att : allAtts)
/*    */     {
/* 60 */       if (!"dr".equals(att))
/*    */       {
/*    */ 
/* 63 */         if (!"PSEUDOCOLUMN".equalsIgnoreCase(att))
/*    */         {
/*    */ 
/* 66 */           attNames.add(att); } }
/*    */     }
/* 68 */     return (String[])attNames.toArray(new String[attNames.size()]);
/*    */   }
/*    */
/*     */   public FQ01TranTypeVO queryTranType(String pk_group, String vtrantypecode)
/*     */     throws BusinessException
/*     */   {
/* 74 */     FQ01TranTypeVO[] retVOs = queryTranTypeVOs(pk_group, new String[] { vtrantypecode });
/*     */     
/*     */ 
/* 77 */     if ((retVOs != null) && (retVOs.length > 0)) {
/* 78 */       return retVOs[0];
/*     */     }
/* 80 */     return null;
/*     */   }
/*     */   public FQ01TranTypeVO[] queryTranTypeVOs(String pk_group, String[] vtrantypecodes)
/*     */     throws BusinessException
/*     */   {
/* 85 */     FQ01TranTypeVO[] retVOs = null;
/*     */     try {
/* 87 */       String[] selectFds = getTranTypeSelectFds();
/* 88 */       String[] whrFields = { "vtrantypecode", "pk_group" };
/*     */       
/*     */ 
/* 91 */       int length = vtrantypecodes.length;
/* 92 */       String[][] whrFdValues = new String[length][];
/* 93 */       for (int i = 0; i < length; i++) {
/* 94 */         whrFdValues[i] = new String[2];
/* 95 */         whrFdValues[i][0] = vtrantypecodes[i];
/* 96 */         whrFdValues[i][1] = pk_group;
/*     */       }
/*     */       
/* 99 */       CacheVOQuery<FQ01TranTypeVO> cacheVOQuery = new CacheVOQuery(FQ01TranTypeVO.class, selectFds);
/*     */       
/* 101 */       retVOs = (FQ01TranTypeVO[])cacheVOQuery.query(whrFields, whrFdValues);
/*     */     }
/*     */     catch (Exception ex) {
/* 104 */       ExceptionUtils.marsh(ex);
/*     */     }
/* 106 */     return retVOs;
/*     */   }
/*    */ }