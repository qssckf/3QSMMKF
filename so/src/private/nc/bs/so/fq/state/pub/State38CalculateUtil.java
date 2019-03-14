package nc.bs.so.fq.state.pub;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.impl.pubapp.env.BSContext;
/*     */ import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.pubitf.so.fq.so.m30.Rewrite30Para;
import nc.pubitf.so.fq.so.m30.RewritefqPara;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
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
/*     */ public class State38CalculateUtil
/*     */ {
/*  30 */   Map<String, UFBoolean> maparronce = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Map<String, RewritefqPara> mParas;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public State38CalculateUtil() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitBillClose(PreOrderVO vo)
/*     */   {
/*  49 */     PreOrderBVO[] items = (PreOrderBVO[]) vo.getChildrenVO();
/*  50 */     for (PreOrderBVO item : items) {
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
/*     */   public boolean isAutoTransitBillOpen(PreOrderVO vo)
/*     */   {
/*  67 */     return !isAutoTransitBillClose(vo);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitRowClose(PreOrderViewVO view)
/*     */   {
/*  77 */     return BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isAutoTransitRowOpen(PreOrderViewVO view)
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
/*     */   public boolean isRowClose(PreOrderViewVO view)
/*     */   {
			
/* 103 */     PreOrderBVO item = view.getItem();
/* 104 */     RewritefqPara para = (RewritefqPara)getRewrite38Paras().get(item.getCpreorderbid());
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
/*     */   public boolean isRowOpen(PreOrderViewVO view)
/*     */   {
/* 121 */     PreOrderBVO item = view.getItem();
/* 122 */     RewritefqPara para = (RewritefqPara)getRewrite38Paras().get(item.getCpreorderbid());
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
/*     */   private Map<String, RewritefqPara> getRewrite38Paras()
/*     */   {
/* 142 */     if (this.mParas == null) {
/* 143 */       this.mParas = ((Map)BSContext.getInstance().getSession(RewritefqPara.class.getName()));
/*     */     }
/*     */     
/*     */ 
/* 147 */     return this.mParas;
/*     */   }
/*     */   
			
/*     */ }

