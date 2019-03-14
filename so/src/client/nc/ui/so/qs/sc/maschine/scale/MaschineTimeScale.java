package nc.ui.so.qs.sc.maschine.scale;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import nc.pubitf.para.SysInitQuery;
/*    */ import nc.util.mmf.framework.base.MMMapUtil;
/*    */ import nc.util.mmf.framework.base.MMValueCheck;
/*    */ import nc.vo.pub.BusinessException;
/*    */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineTimeScale
/*    */ {
/*    */   private Map<String, Integer> timeUnitScaleMap;
/*    */   
/*    */   public MaschineTimeScale() {}
/*    */   
/*    */   public Integer getTimeUnitSCaleByOrg(String pk_org)
/*    */   {
/* 27 */     Integer commonScale = Integer.valueOf(8);
/* 28 */     if (MMValueCheck.isEmpty(pk_org)) {
/* 29 */       return commonScale;
/*    */     }
/* 31 */     if (MMMapUtil.isEmpty(this.timeUnitScaleMap)) {
/* 32 */       this.timeUnitScaleMap = new HashMap();
/*    */     }
/* 34 */     if (!this.timeUnitScaleMap.containsKey(pk_org)) {
/* 35 */       Map<String, Integer> orgScaleMap = new HashMap();
/*    */       try {
/* 37 */         Integer i = SysInitQuery.getParaInt(pk_org, "MMBD002");
/* 38 */         orgScaleMap.put(pk_org, i);
/*    */       }
/*    */       catch (BusinessException e) {
/* 41 */         ExceptionUtils.wrappException(e);
/*    */       }
/* 43 */       if (!MMMapUtil.isEmpty(orgScaleMap)) {
/* 44 */         this.timeUnitScaleMap.putAll(orgScaleMap);
/* 45 */         return (Integer)orgScaleMap.get(pk_org);
/*    */       }
/* 47 */       return commonScale;
/*    */     }
/* 49 */     return (Integer)this.timeUnitScaleMap.get(pk_org);
/*    */   }
/*    */   
/*    */   public Map<String, Integer> getTimeUnitSCaleByOrg(String[] pk_orgs) {
/* 53 */     if (MMValueCheck.isEmpty(pk_orgs)) {
/* 54 */       return null;
/*    */     }
/* 56 */     Map<String, Integer> initCodeByOrgMap = new HashMap();
/* 57 */     for (String pk_org : pk_orgs) {
/*    */       try {
/* 59 */         Integer initCodebyOrg = Integer.valueOf(SysInitQuery.getParaString(pk_org, "MMBD002"));
/*    */         
/* 61 */         initCodeByOrgMap.put(pk_org, initCodebyOrg);
/*    */       }
/*    */       catch (NumberFormatException e) {
/* 64 */         ExceptionUtils.wrappException(e);
/*    */       }
/*    */       catch (BusinessException e) {
/* 67 */         ExceptionUtils.wrappException(e);
/*    */       }
/*    */     }
/* 70 */     return initCodeByOrgMap;
/*    */   }
/*    */ }
