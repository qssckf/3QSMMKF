package nc.vo.so.shipmens.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import nc.bs.framework.common.NCLocator;
///*     */ import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.itf.so.qs.sc.FQ01trantype.IFQ01TranTypeService;
/*     */ import nc.vo.ct.entity.CtBusinessVO;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
///*     */ import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
///*     */ import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.trade.checkrule.VOChecker;
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
/*     */ public class ShipmentsClientContext
/*     */ {
/*     */   private Map<String, CtBusinessVO> ctMap;
/*     */   private boolean isCashSale;
/*  33 */   private Map<String, FQ01TranTypeVO> m_transType = new HashMap();
/*     */   
/*     */   private OffsetTempVO offsettempvo;
/*     */   
///*     */   private SoBalanceVO sobalancevo;
/*     */   
/*     */   private UFDouble thisGatheringMny;
/*     */   
/*     */   private IFQ01TranTypeService tranTypeService;
/*     */   
/*     */ 
/*     */   public ShipmentsClientContext() {}
/*     */   
/*     */   public Map<String, CtBusinessVO> getCtMap()
/*     */   {
/*  48 */     return this.ctMap;
/*     */   }
/*     */   
/*     */   public boolean getIsCashSale() {
/*  52 */     return this.isCashSale;
/*     */   }
/*     */   
/*     */   public OffsetTempVO getOffsetTempVO() {
/*  56 */     return this.offsettempvo;
/*     */   }
/*     */   
///*     */   public SoBalanceVO getSobalancevo() {
///*  60 */     return this.sobalancevo;
///*     */   }
/*     */   
/*     */   public UFDouble getThisGatheringMny() {
/*  64 */     return this.thisGatheringMny;
/*     */   }
/*     */   
/*     */   public Map<String, FQ01TranTypeVO> getTransType() {
/*  68 */     return this.m_transType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FQ01TranTypeVO getTransType(String tranTypeCode, String pk_group)
/*     */   {
/*  78 */     FQ01TranTypeVO tranType = (FQ01TranTypeVO)this.m_transType.get(tranTypeCode);
/*     */     
/*  80 */     if (VOChecker.isEmpty(tranType)) {
/*     */       try {
/*  82 */         tranType = getTranTypeService().queryTranType(pk_group, tranTypeCode);
/*     */         
/*  84 */         setTransType(tranTypeCode, tranType);
/*     */       }
/*     */       catch (BusinessException e) {
/*  87 */         ExceptionUtils.wrappBusinessException(e.getMessage());
/*     */       }
/*     */     }
/*  90 */     return tranType;
/*     */   }
/*     */   
/*     */   public void setCtMap(Map<String, CtBusinessVO> ctMap) {
/*  94 */     this.ctMap = ctMap;
/*     */   }
/*     */   
/*     */   public void setIsCashSale(boolean isCashSale) {
/*  98 */     this.isCashSale = isCashSale;
/*     */   }
/*     */   
/*     */   public void setOffsetTempVO(OffsetTempVO offvo) {
/* 102 */     this.offsettempvo = offvo;
/*     */   }
/*     */   
///*     */   public void setSobalancevo(SoBalanceVO sobalancevo) {
///* 106 */     this.sobalancevo = sobalancevo;
///*     */   }
/*     */   
/*     */   public void setThisGatheringMny(UFDouble thisGatheringMny) {
/* 110 */     this.thisGatheringMny = thisGatheringMny;
/*     */   }
/*     */   
/*     */   public void setTransType(String transTypeCode, FQ01TranTypeVO mvo) {
/* 114 */     this.m_transType.put(transTypeCode, mvo);
/*     */   }
/*     */   
/*     */   private IFQ01TranTypeService getTranTypeService() {
/* 118 */     if (this.tranTypeService == null) {
/* 119 */       this.tranTypeService = ((IFQ01TranTypeService)NCLocator.getInstance().lookup(IFQ01TranTypeService.class));
/*     */     }
/*     */     
/* 122 */     return this.tranTypeService;
/*     */   }
/*     */ }