package nc.bs.so.qs.sc.maschine.rule;
/*    */ 
/*    */ import nc.bs.bd.baseservice.DefaultGetBizInfoByMDUtil;
/*    */ import nc.bs.bd.baseservice.IGetBizInfoUtil;
/*    */ import nc.bs.bd.cache.CacheProxy;
/*    */ import nc.impl.pubapp.pattern.rule.IRule;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*    */ import nc.vo.pd.pd0404.entity.WkProdinvVO;
///*    */ import nc.vo.pd.pd0404.entity.WkRepltimeVO;
///*    */ import nc.vo.pd.pd0404.entity.WkSubstVO;
///*    */ import nc.vo.pd.pd0404.entity.WkVO;
///*    */ import nc.vo.pd.pd0404.entity.WkWipVO;
/*    */ import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MachineCustomer;
import nc.vo.so.qs.sc.MachineDept;
import nc.vo.so.qs.sc.MachineMater_Mater;
import nc.vo.so.qs.sc.MaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineInsertCacheMaintainRule
/*    */   implements IRule<AggMaschineVO>
/*    */ {
/*    */   public MaschineInsertCacheMaintainRule() {}
/*    */   
/*    */   public void process(AggMaschineVO[] vos)
/*    */   {
/*    */     try
/*    */     {
/* 28 */       IGetBizInfoUtil bizInfoUtil = new DefaultGetBizInfoByMDUtil();
/*    */       
/* 30 */       CacheProxy.fireDataInserted(bizInfoUtil.getTableName(new MaschineVO()), null);
/*    */       
/* 32 */       CacheProxy.fireDataInserted(bizInfoUtil.getTableName(new MachineCustomer()), null);
/*    */       
/* 34 */       CacheProxy.fireDataInserted(bizInfoUtil.getTableName(new MachineDept()), null);
/*    */       
/* 36 */       CacheProxy.fireDataInserted(bizInfoUtil.getTableName(new MachineMater_Mater()), null);
/*    */       
/*    */     }
/*    */     catch (BusinessException e) {
/* 43 */       ExceptionUtils.wrappException(e);
/*    */     }
/*    */   }
/*    */ }