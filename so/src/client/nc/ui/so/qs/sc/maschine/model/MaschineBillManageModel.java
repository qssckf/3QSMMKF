package nc.ui.so.qs.sc.maschine.model;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import nc.itf.uap.busibean.ISysInitQry;
/*    */ import nc.mmbd.utils.factoryparam.MMBDFactoryParameter;
/*    */ import nc.ui.pubapp.uif2app.model.BillManageModel;
/*    */ import nc.util.mmf.framework.base.MMValueCheck;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
/*    */ import nc.vo.pd.pd0404.entity.PDTimeUnitUtil;
/*    */ import nc.vo.pub.BusinessException;
		import nc.vo.pubapp.pattern.exception.ExceptionUtils;
		import nc.vo.so.qs.sc.AggMaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineBillManageModel
/*    */   extends BillManageModel
/*    */ {
/*    */   public MaschineBillManageModel() {}
/*    */   
/* 28 */   private Map<String, String> tmpOrg2TimeUnit = new HashMap();
/*    */   
/*    */   public Integer getTimeUnitByOrg(String pk_org) {
/* 31 */     if (MMValueCheck.isEmpty(pk_org)) {
/* 32 */       return null;
/*    */     }
/* 34 */     String timeUnit = (String)this.tmpOrg2TimeUnit.get(pk_org);
/* 35 */     if (MMValueCheck.isEmpty(timeUnit)) {
/* 36 */       timeUnit = queryTimeUnit(pk_org);
/* 37 */       if (timeUnit != null) {
/* 38 */         this.tmpOrg2TimeUnit.put(pk_org, timeUnit);
/*    */       }
/*    */     }
/*    */     
/* 42 */     return PDTimeUnitUtil.timeUnitStringToInt(timeUnit);
/*    */   }
/*    */   
/*    */   private String queryTimeUnit(String pk_org) {
/*    */     //以下代码为调用参数语句
			 try {
/* 47 */       return MMBDFactoryParameter.sysInitQry().getParaString(pk_org, "MMBD008");
/*    */     }
/*    */     catch (BusinessException e) {
/* 50 */       ExceptionUtils.wrappException(e);
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */   
/*    */   public void initModel(Object data)
/*    */   {
/* 57 */     AggMaschineVO[] vos = (AggMaschineVO[])data;
/* 58 */     super.initModel(vos);
/*    */   }
/*    */ }
