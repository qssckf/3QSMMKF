package nc.bs.so.fq.state.pub;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.impl.pubapp.env.BSContext;
/*     */ import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.pubitf.so.fq.so.m30.Rewrite30Para;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StateCalculateUtil
/*     */ {
/*  30 */   Map<String, UFBoolean> maparronce = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<String, Rewrite30Para> mParas;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StateCalculateUtil() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitBillClose(AggShipmentsVO vo)
/*     */   {
/*  49 */     ShipmentsBVO[] items = (ShipmentsBVO[]) vo.getChildrenVO();
/*  50 */     for (ShipmentsBVO item : items) {
/*  51 */       UFBoolean blineclose = item.getBlineclose();
/*  52 */       if (!UFBoolean.TRUE.equals(blineclose)) {
/*  53 */         return false;
/*     */       }
/*     */     }
/*  56 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitBillOpen(AggShipmentsVO vo)
/*     */   {
/*  67 */     return !isAutoTransitBillClose(vo);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitRowClose(ShipmentsViewVO view)
/*     */   {
/*  77 */     return BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitRowOpen(ShipmentsViewVO view)
/*     */   {
/*  87 */     return BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag());
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
/*     */   public boolean isRowClose(ShipmentsViewVO view)
/*     */   {
			
/* 103 */     ShipmentsBVO item = view.getItem();
/* 104 */     Rewrite30Para para = (Rewrite30Para)getRewrite30Paras().get(item.getPk_shipments_b());
/*     */     
/* 106 */     if (MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) < 0) {
/* 107 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 111 */     return MathTool.compareTo(item.getNarrnum(), item.getNnum()) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRowOpen(ShipmentsViewVO view)
/*     */   {
/* 121 */     ShipmentsBVO item = view.getItem();
/* 122 */     Rewrite30Para para = (Rewrite30Para)getRewrite30Paras().get(item.getPk_shipments_b());
/*     */     
/* 124 */     if ((!item.getBlineclose().booleanValue()) || (MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) >= 0))
/*     */     {
/* 126 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 130 */     UFDouble origArrnum = MathTool.sub(item.getNarrnum(), para.getNnum());
/*     */     
/* 132 */     boolean bautoClose = MathTool.compareTo(origArrnum, item.getNnum()) >= 0;
/*     */     
/*     */ 
/*     */ 
/* 136 */     return (bautoClose) && (MathTool.compareTo(item.getNarrnum(), item.getNnum()) < 0);
/*     */   }
/*     */   
/*     */ 
/*     */   private Map<String, Rewrite30Para> getRewrite30Paras()
/*     */   {
/* 142 */     if (this.mParas == null) {
/* 143 */       this.mParas = ((Map)BSContext.getInstance().getSession(Rewrite30Para.class.getName()));
/*     */     }
/*     */     
/*     */ 
/* 147 */     return this.mParas;
/*     */   }
/*     */   
			
/*     */ }

