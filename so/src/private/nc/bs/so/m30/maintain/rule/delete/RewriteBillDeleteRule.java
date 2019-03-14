/*     */ package nc.bs.so.m30.maintain.rule.delete;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nc.bs.framework.common.NCLocator;
/*     */ import nc.bs.so.m30.maintain.util.RewriteBillUtil;
/*     */ import nc.impl.pubapp.bill.rewrite.BillRewriter;
/*     */ import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
/*     */ import nc.impl.pubapp.bill.rewrite.RewritePara;
/*     */ import nc.impl.pubapp.pattern.rule.IRule;
/*     */ import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
/*     */ import nc.vo.ct.entity.CtWriteBackForOrderVO;
/*     */ import nc.vo.ct.saledaily.entity.CtSaleBVO;
/*     */ import nc.vo.ct.saledaily.entity.CtSaleVO;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.scmpub.res.billtype.CTBillType;
/*     */ import nc.vo.scmpub.res.billtype.ICBillType;
/*     */ import nc.vo.scmpub.res.billtype.OPCBillType;
/*     */ import nc.vo.scmpub.res.billtype.SOBillType;
/*     */ import nc.vo.so.m30.entity.SaleOrderBVO;
/*     */ import nc.vo.so.m30.entity.SaleOrderVO;
/*     */ import nc.vo.trade.checkrule.VOChecker;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RewriteBillDeleteRule
/*     */   implements IRule<SaleOrderVO>
/*     */ {
/*     */   public RewriteBillDeleteRule() {}
/*     */   
/*     */   public void process(SaleOrderVO[] vos)
/*     */   {
/*  42 */     RewriteBillUtil rewriteUtil = new RewriteBillUtil();
/*  43 */     BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
/*  44 */     Map<String, List<RewritePara>> srcParaIndex = srctool.splitForDelete(vos);
/*     */     
/*     */ 
/*  47 */     List<RewritePara> srcSaleOrder = (List)srcParaIndex.get(SOBillType.Order.getCode());
/*     */     
/*  49 */     if (!VOChecker.isEmpty(srcSaleOrder)) {
/*  50 */       rewriteUtil.reWriteSrc30(srcSaleOrder);
/*     */     }
/*     */     
/*  53 */     List<RewritePara> srcpara = (List)srcParaIndex.get(ICBillType.SaleOut.getCode());
/*  54 */     if (!VOChecker.isEmpty(srcpara)) {
/*  55 */       rewriteUtil.reWriteSrc4C(srcpara);
/*     */     }
/*     */     
/*  58 */     srcpara = (List)srcParaIndex.get(SOBillType.PreOrder.getCode());
/*  59 */     if (!VOChecker.isEmpty(srcpara)) {
/*  60 */       rewriteUtil.reWriteSrc38(srcpara);
/*     */     }

			  srcpara = (List)srcParaIndex.get("FQ01");
/*  59 */     if (!VOChecker.isEmpty(srcpara)) {
/*  60 */       rewriteUtil.reWriteSrcFQ(srcpara);
/*     */     }
/*     */     
/*  63 */     srcpara = (List)srcParaIndex.get(SOBillType.SaleQuotation.getCode());
/*  64 */     if (!VOChecker.isEmpty(srcpara)) {
/*  65 */       rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(3));
/*     */     }
/*     */     
/*     */ 
/*  69 */     srcpara = (List)srcParaIndex.get(OPCBillType.OPCORDER.getCode());
/*  70 */     if (!VOChecker.isEmpty(srcpara)) {
/*  71 */       rewriteUtil.reWriteSrcOPC(srcpara);
/*     */     }
/*     */     
/*  74 */     srcpara = (List)srcParaIndex.get(ICBillType.BorrowOut.getCode());
/*  75 */     if (!VOChecker.isEmpty(srcpara)) {
/*  76 */       rewriteUtil.reWriteSrc4H(srcpara);
/*     */     }
/*     */     
/*  79 */     rewriteZ3(vos);
/*     */     
/*     */ 
/*     */ 
/*  83 */     BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
/*  84 */     Map<String, List<RewritePara>> firstParaIndex = firsttool.splitForDelete(vos);
/*     */     
/*  86 */     List<RewritePara> firstSaleOrder = (List)firstParaIndex.get(SOBillType.Order.getCode());
/*     */     
/*     */ 
/*  89 */     firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);
/*  90 */     if (!VOChecker.isEmpty(firstSaleOrder)) {
/*  91 */       rewriteUtil.reWriteFirst30(firstSaleOrder);
/*     */     }
/*     */   }
/*     */   
/*     */   private void rewriteZ3(SaleOrderVO[] vos)
/*     */   {
/*  97 */     SaleOrderVO[] newVOs = fillCtType(vos);
/*  98 */     if (newVOs.length == 0) {
/*  99 */       return;
/*     */     }
/*     */     
/* 102 */     ItemKeyMapping mapping = new ItemKeyMapping();
/* 103 */     mapping.setVsrctypeKey("vcttype");
/* 104 */     mapping.setCsrcidKey("cctmanageid");
/* 105 */     mapping.setCsrcbidKey("cctmanagebid");
/* 106 */     mapping.setNnumKey("nnum");
/* 107 */     mapping.setNnum2Key("norigtaxmny");
/* 108 */     mapping.setSrcTSKey("srcts");
/*     */     
/* 110 */     mapping.setCsrcbbidKey("crowno");
/*     */     
/* 112 */     BillRewriter tool = new BillRewriter(mapping);
/*     */     
/* 114 */     tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
/* 115 */     tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);
/*     */     
/* 117 */     Map<String, UFDouble> bodymap = new HashMap();
/*     */     
/* 119 */     buildSaleOrderBVOData(bodymap, newVOs);
/*     */     
/* 121 */     Map<String, List<RewritePara>> paraIndex = tool.splitForDelete(newVOs);
/*     */     
/*     */ 
/* 124 */     List<RewritePara> paraList = (List)paraIndex.get(CTBillType.SaleDaily.getCode());
/* 125 */     if ((null != paraList) && (paraList.size() > 0)) {
/* 126 */       int size = paraList.size();
/* 127 */       CtWriteBackForOrderVO[] paras = new CtWriteBackForOrderVO[size];
/* 128 */       for (int i = 0; i < size; i++) {
/* 129 */         String id = ((RewritePara)paraList.get(i)).getCsrcid();
/* 130 */         String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 131 */         UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 132 */         UFDouble norigtaxmny = ((RewritePara)paraList.get(i)).getNnum2();
/* 133 */         String crowno = ((RewritePara)paraList.get(i)).getCsrcbbid();
/* 134 */         paras[i] = new CtWriteBackForOrderVO();
/* 135 */         paras[i].setPk_ctpu(id);
/* 136 */         paras[i].setPk_ctpu_b(bid);
/* 137 */         paras[i].setNum(nnum);
/* 138 */         paras[i].setPriceNum(norigtaxmny);
/* 139 */         paras[i].setcRowNum(crowno);
/* 140 */         if (bodymap.containsKey(((RewritePara)paraList.get(i)).getCbill_bid()))
/*     */         {
/* 142 */           UFDouble price = (UFDouble)bodymap.get(((RewritePara)paraList.get(i)).getCbill_bid());
/* 143 */           paras[i].setPrice(price);
/*     */         }
/*     */       }
/* 146 */       IReWriteZ3For30 api = (IReWriteZ3For30)NCLocator.getInstance().lookup(IReWriteZ3For30.class);
/*     */       try
/*     */       {
/* 149 */         api.rewriteBackZ3For30(paras);
/*     */       }
/*     */       catch (BusinessException ex) {
/* 152 */         ExceptionUtils.wrappException(ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private SaleOrderVO[] fillCtType(SaleOrderVO[] vos) {
/* 158 */     List<SaleOrderVO> z3vos = new ArrayList();
/* 159 */     for (SaleOrderVO vo : vos) {
/* 160 */       SaleOrderBVO[] bodys = vo.getChildrenVO();
/* 161 */       List<SaleOrderBVO> z3bvos = new ArrayList();
/* 162 */       for (SaleOrderBVO body : bodys) {
/* 163 */         if ((body.getCctmanagebid() != null) && (body.getCctmanagebid().trim().length() > 0))
/*     */         {
/* 165 */           body.setVcttype(CTBillType.SaleDaily.getCode());
/* 166 */           z3bvos.add(body);
/*     */         }
/*     */       }
/* 169 */       if (z3bvos.size() > 0) {
/* 170 */         SaleOrderVO newvo = new SaleOrderVO();
/* 171 */         newvo.setParentVO(vo.getParentVO());
/* 172 */         newvo.setChildrenVO((SaleOrderBVO[])z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
/* 173 */         z3vos.add(newvo);
/*     */       }
/*     */     }
/* 176 */     return (SaleOrderVO[])z3vos.toArray(new SaleOrderVO[z3vos.size()]);
/*     */   }
/*     */   
/*     */   private void buildSaleOrderBVOData(Map<String, UFDouble> bodymap, SaleOrderVO[] billvos)
/*     */   {
/* 181 */     for (SaleOrderVO billvo : billvos) {
/* 182 */       SaleOrderBVO[] childvos = billvo.getChildrenVO();
/* 183 */       for (SaleOrderBVO bodyvo : childvos) {
/* 184 */         if (!bodymap.containsKey(bodyvo.getPrimaryKey())) {
/* 185 */           bodymap.put(bodyvo.getPrimaryKey(), bodyvo.getNorigtaxprice());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_salesorder.jar
 * Qualified Name:     nc.bs.so.m30.maintain.rule.delete.RewriteBillDeleteRule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */