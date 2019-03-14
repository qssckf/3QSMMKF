/*     */ package nc.bs.so.m30.maintain.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.bs.so.m30.rule.rewrite.m4h.Rewrite4HPara;
/*     */ import nc.impl.pubapp.bill.rewrite.BillRewriter;
/*     */ import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
/*     */ import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.so.IShipmentsInfoMaintain;
/*     */ import nc.pubitf.ic.m4c.m30.IRewrite4CFor30;
/*     */ import nc.pubitf.ic.m4c.m30.Parameter4CFor30;
/*     */ import nc.pubitf.ic.m4h.m30.IBorrowOutRewriteFor30;
/*     */ import nc.pubitf.opc.opcapi.so.IRewriteExecuteInfo;
/*     */ import nc.pubitf.pu.m21.so.m30.IOrderUpdateCoopFor30;
/*     */ import nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw;
/*     */ import nc.pubitf.so.m38.so.m30.IRewrite38For30;
/*     */ import nc.pubitf.so.salequotation.so.ISaleOrderCallBack;
/*     */ import nc.vo.ct.saledaily.entity.CtSaleBVO;
/*     */ import nc.vo.ct.saledaily.entity.CtSaleVO;
/*     */ import nc.vo.ic.m4c.entity.SaleOutBodyVO;
/*     */ import nc.vo.ic.m4c.entity.SaleOutHeadVO;
/*     */ import nc.vo.ic.m4h.entity.BorrowOutBodyVO;
/*     */ import nc.vo.ic.m4h.entity.BorrowOutHeadVO;
/*     */ import nc.vo.opc.mc1.entity.CustomerPOBVO;
/*     */ import nc.vo.opc.mc1.entity.CustomerPOHVO;
/*     */ import nc.vo.opc.param.so.RewriteCustomerPOPara;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
/*     */ import nc.vo.scmpub.res.billtype.CTBillType;
/*     */ import nc.vo.scmpub.res.billtype.ICBillType;
/*     */ import nc.vo.scmpub.res.billtype.OPCBillType;
/*     */ import nc.vo.scmpub.res.billtype.SOBillType;
/*     */ import nc.vo.so.m30.entity.SaleOrderBVO;
/*     */ import nc.vo.so.m30.entity.SaleOrderHVO;
/*     */ import nc.vo.so.m30.entity.SaleOrderVO;
/*     */ import nc.vo.so.m38.entity.PreOrderBVO;
/*     */ import nc.vo.so.m38.entity.PreOrderHVO;
/*     */ import nc.vo.so.salequotation.entity.QuatationRewritePara;
/*     */ import nc.vo.so.salequotation.entity.SalequotationBVO;
/*     */ import nc.vo.so.salequotation.entity.SalequotationHVO;
/*     */ import nc.vo.trade.checkrule.VOChecker;
/*     */ import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
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
/*     */ public class RewriteBillUtil
/*     */ {
/*     */   public RewriteBillUtil() {}
/*     */   
/*     */   public List<RewritePara> filtrateSrc30(List<RewritePara> firstParaList, List<RewritePara> srcParaList)
/*     */   {
/*  66 */     if (VOChecker.isEmpty(srcParaList)) {
/*  67 */       return firstParaList;
/*     */     }
/*  69 */     if (VOChecker.isEmpty(firstParaList)) {
/*  70 */       return null;
/*     */     }
/*  72 */     Set<String> hsBid = new HashSet();
/*  73 */     for (RewritePara para : srcParaList) {
/*  74 */       hsBid.add(para.getCsrcbid());
/*     */     }
/*     */     
/*  77 */     List<RewritePara> filter = new ArrayList();
/*     */     
/*  79 */     for (RewritePara para : firstParaList) {
/*  80 */       if (!hsBid.contains(para.getCsrcbid())) {
/*  81 */         filter.add(para);
/*     */       }
/*     */     }
/*  84 */     return filter;
/*     */   }
/*     */   
/*     */   public BillRewriter getFirstBillRewriter()
/*     */   {
/*  89 */     ItemKeyMapping mapping = new ItemKeyMapping();
/*  90 */     mapping.setVsrctypeKey("vfirsttype");
/*  91 */     mapping.setCsrcidKey("cfirstid");
/*  92 */     mapping.setCsrcbidKey("cfirstbid");
/*  93 */     mapping.setNnumKey("nnum");
/*     */     
/*     */ 
/*  96 */     BillRewriter tool = new BillRewriter(mapping);
/*     */     
/*     */ 
/*  99 */     tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
/* 100 */     tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);
/*     */     
/* 102 */     return tool;
/*     */   }
/*     */   
/*     */ 
/*     */   public BillRewriter getSrcBillRewriter()
/*     */   {
/* 108 */     ItemKeyMapping mapping = new ItemKeyMapping();
/* 109 */     mapping.setVsrctypeKey("vsrctype");
/* 110 */     mapping.setCsrcidKey("csrcid");
/* 111 */     mapping.setCsrcbidKey("csrcbid");
/* 112 */     mapping.setNnumKey("nnum");
/* 113 */     mapping.setSrcTSKey("srcts");
/*     */     
/* 115 */     BillRewriter tool = new BillRewriter(mapping);
/*     */     
/* 117 */     tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
/* 118 */     tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);
/*     */     
/*     */ 
/* 121 */     tool.addSRCHeadClazz(ICBillType.SaleOut.getCode(), SaleOutHeadVO.class);
/* 122 */     tool.addSRCItemClazz(ICBillType.SaleOut.getCode(), SaleOutBodyVO.class);
/*     */     
/*     */ 
/* 125 */     tool.addSRCHeadClazz(SOBillType.PreOrder.getCode(), PreOrderHVO.class);
/* 126 */     tool.addSRCItemClazz(SOBillType.PreOrder.getCode(), PreOrderBVO.class);
/*     */     
/*     */ 
/* 129 */     tool.addSRCHeadClazz(SOBillType.SaleQuotation.getCode(), SalequotationHVO.class);
/*     */     
/* 131 */     tool.addSRCItemClazz(SOBillType.SaleQuotation.getCode(), SalequotationBVO.class);
/*     */     
/*     */ 
/*     */ 
/* 135 */     tool.addSRCHeadClazz(ICBillType.BorrowOut.getCode(), BorrowOutHeadVO.class);
/* 136 */     tool.addSRCItemClazz(ICBillType.BorrowOut.getCode(), BorrowOutBodyVO.class);
/*     */     
/* 138 */     tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
/* 139 */     tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);
/*     */     
/*     */ 
/* 142 */     tool.addSRCHeadClazz(OPCBillType.OPCORDER.getCode(), CustomerPOHVO.class);
/* 143 */     tool.addSRCItemClazz(OPCBillType.OPCORDER.getCode(), CustomerPOBVO.class);

			  tool.addSRCHeadClazz("FQ01", ShipmentsVO.class);
/* 143 */     tool.addSRCItemClazz("FQ01", ShipmentsBVO.class);
/*     */     
/* 145 */     return tool;
/*     */   }
/*     */   
/*     */ 
/*     */   public void reWriteFirst30(List<RewritePara> paraList)
/*     */   {
/* 151 */     int size = paraList.size();
/* 152 */     nc.pubitf.so.m30.so.withdraw.Rewrite30Para[] paras = new nc.pubitf.so.m30.so.withdraw.Rewrite30Para[size];
/* 153 */     for (int i = 0; i < size; i++) {
/* 154 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 155 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 156 */       paras[i] = new nc.pubitf.so.m30.so.withdraw.Rewrite30Para(bid, nnum);
/*     */     }
/* 158 */     IRewriteSaleOrderByWithdraw api = (IRewriteSaleOrderByWithdraw)NCLocator.getInstance().lookup(IRewriteSaleOrderByWithdraw.class);
/*     */     try
/*     */     {
/* 161 */       api.rewrite30NumForWithdraw(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 165 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWriteSrc30(List<RewritePara> paraList)
/*     */   {
/* 177 */     int size = paraList.size();
/* 178 */     nc.pubitf.so.m30.so.withdraw.Rewrite30Para[] paras = new nc.pubitf.so.m30.so.withdraw.Rewrite30Para[size];
/* 179 */     for (int i = 0; i < size; i++) {
/* 180 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 181 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 182 */       paras[i] = new nc.pubitf.so.m30.so.withdraw.Rewrite30Para(bid, nnum);
/*     */     }
/*     */     
/* 185 */     IRewriteSaleOrderByWithdraw api = (IRewriteSaleOrderByWithdraw)NCLocator.getInstance().lookup(IRewriteSaleOrderByWithdraw.class);
/*     */     try
/*     */     {
/* 188 */       api.rewrite30NumForWithdraw(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 192 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWrite21(SaleOrderVO[] vos)
/*     */   {
/* 202 */     Map<String, String> map = new HashMap();
/* 203 */     for (SaleOrderVO vo : vos) {
/* 204 */       SaleOrderHVO hvo = vo.getParentVO();
/* 205 */       UFBoolean iscoop = hvo.getBpocooptomeflag();
/* 206 */       if ((null != iscoop) && (iscoop.booleanValue())) {
/* 207 */         String srcHid = vo.getChildrenVO()[0].getCsrcid();
/* 208 */         String billcode = hvo.getVbillcode();
/* 209 */         map.put(srcHid, billcode);
/*     */       }
/*     */     }
/* 212 */     if (map.size() > 0) {
/* 213 */       IOrderUpdateCoopFor30 service = (IOrderUpdateCoopFor30)NCLocator.getInstance().lookup(IOrderUpdateCoopFor30.class);
/*     */       try
/*     */       {
/* 216 */         service.updateCoopFlag(true, map);
/*     */       }
/*     */       catch (BusinessException e) {
/* 219 */         ExceptionUtils.wrappException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWriteSrc38(List<RewritePara> paraList)
/*     */   {
/* 230 */     int size = paraList.size();
/* 231 */     nc.pubitf.so.m38.so.m30.Rewrite30Para[] paras = new nc.pubitf.so.m38.so.m30.Rewrite30Para[size];
/*     */     
/* 233 */     for (int i = 0; i < size; i++) {
/* 234 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 235 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 236 */       paras[i] = new nc.pubitf.so.m38.so.m30.Rewrite30Para(bid, nnum);
/*     */     }
/* 238 */     IRewrite38For30 api = (IRewrite38For30)NCLocator.getInstance().lookup(IRewrite38For30.class);
/*     */     try {
/* 240 */       api.rewrite38NarrnumFor30(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 244 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }

			public void reWriteSrcFQ(List<RewritePara> paraList){
				
/* 230 */     int size = paraList.size();
/* 231 */     nc.pubitf.so.fq.so.m30.Rewrite30Para[] paras = new nc.pubitf.so.fq.so.m30.Rewrite30Para[size];
/*     */     
/* 233 */     for (int i = 0; i < size; i++) {
/* 234 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 235 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 236 */       paras[i] = new nc.pubitf.so.fq.so.m30.Rewrite30Para(bid, nnum);
/*     */     }
/* 238 */     IShipmentsInfoMaintain api = (IShipmentsInfoMaintain)NCLocator.getInstance().lookup(IShipmentsInfoMaintain.class);
/*     */     try {
/* 240 */       api.rewriteFQNarrnumFor30(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 244 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */   public void reWriteSrcOPC(List<RewritePara> paraList) {
/* 249 */     int size = paraList.size();
/* 250 */     RewriteCustomerPOPara[] paras = new RewriteCustomerPOPara[size];
/* 251 */     for (int i = 0; i < size; i++) {
/* 252 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 253 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 254 */       paras[i] = new RewriteCustomerPOPara(bid, nnum);
/*     */     }
/*     */     try {
/* 257 */       IRewriteExecuteInfo rewriteinfo = (IRewriteExecuteInfo)NCLocator.getInstance().lookup(IRewriteExecuteInfo.class);
/*     */       
/* 259 */       rewriteinfo.rewriteTotalArrangeNum(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 263 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWriteSrc4310(List<RewritePara> paraList, Integer status)
/*     */   {
/* 273 */     int size = paraList.size();
/* 274 */     QuatationRewritePara[] paras = new QuatationRewritePara[size];
/* 275 */     for (int i = 0; i < size; i++) {
/* 276 */       String id = ((RewritePara)paraList.get(i)).getCsrcid();
/* 277 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 278 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 279 */       paras[i] = new QuatationRewritePara();
/* 280 */       paras[i].setPk_salequobill(id);
/* 281 */       paras[i].setPk_salequobill_b(bid);
/* 282 */       paras[i].setNnum(nnum);
/*     */       
/* 284 */       paras[i].setOperateFlag(status);
/*     */     }
/* 286 */     ISaleOrderCallBack api = (ISaleOrderCallBack)NCLocator.getInstance().lookup(ISaleOrderCallBack.class);
/*     */     try
/*     */     {
/* 289 */       api.saleOrderCallBack(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 293 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWriteSrc4H(List<RewritePara> paraList)
/*     */   {
/* 303 */     int size = paraList.size();
/* 304 */     Rewrite4HPara[] paras = new Rewrite4HPara[size];
/* 305 */     for (int i = 0; i < size; i++) {
/* 306 */       String hid = ((RewritePara)paraList.get(i)).getCsrcid();
/* 307 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 308 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 309 */       paras[i] = new Rewrite4HPara(hid, bid, nnum);
/*     */     }
/* 311 */     IBorrowOutRewriteFor30 api = (IBorrowOutRewriteFor30)NCLocator.getInstance().lookup(IBorrowOutRewriteFor30.class);
/*     */     try
/*     */     {
/* 314 */       api.rewriteTranoutNum(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 318 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reWriteSrc4C(List<RewritePara> paraList)
/*     */   {
/* 328 */     int size = paraList.size();
/* 329 */     Parameter4CFor30[] paras = new Parameter4CFor30[size];
/* 330 */     for (int i = 0; i < size; i++) {
/* 331 */       String hid = ((RewritePara)paraList.get(i)).getCsrcid();
/* 332 */       String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 333 */       UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 334 */       paras[i] = new Parameter4CFor30(hid, bid);
/*     */       
/* 336 */       paras[i].setNreplenishednum(MathTool.sub(UFDouble.ZERO_DBL, nnum));
/*     */     }
/*     */     
/* 339 */     IRewrite4CFor30 api = (IRewrite4CFor30)NCLocator.getInstance().lookup(IRewrite4CFor30.class);
/*     */     try {
/* 341 */       api.rewriteNreplenishednum(paras);
/*     */     }
/*     */     catch (BusinessException ex)
/*     */     {
/* 345 */       ExceptionUtils.wrappException(ex);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_salesorder.jar
 * Qualified Name:     nc.bs.so.m30.maintain.util.RewriteBillUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */