/*     */ package nc.bs.so.m30.maintain.rule.insert;
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
/*     */ import nc.vo.scmpub.res.billtype.POBillType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RewriteBillInsertRule
/*     */   implements IRule<SaleOrderVO>
/*     */ {
/*     */   public RewriteBillInsertRule() {}
/*     */   
/*     */   public void process(SaleOrderVO[] vos)
/*     */   {
/*  46 */     RewriteBillUtil rewriteUtil = new RewriteBillUtil();
/*  47 */     BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
/*  48 */     Map<String, List<RewritePara>> srcParaIndex = srctool.splitForInsert(vos);
/*     */     
/*     */ 
/*  51 */     List<RewritePara> srcSaleOrder = (List)srcParaIndex.get(SOBillType.Order.getCode());
/*     */     
/*  53 */     if (!VOChecker.isEmpty(srcSaleOrder)) {
/*  54 */       rewriteUtil.reWriteSrc30(srcSaleOrder);
/*     */     }
/*     */     
/*  57 */     List<RewritePara> srcpara = (List)srcParaIndex.get(ICBillType.SaleOut.getCode());
/*  58 */     if (!VOChecker.isEmpty(srcpara)) {
/*  59 */       rewriteUtil.reWriteSrc4C(srcpara);
/*     */     }
/*     */     
/*  62 */     srcpara = (List)srcParaIndex.get(SOBillType.PreOrder.getCode());
/*  63 */     if (!VOChecker.isEmpty(srcpara)) {
/*  64 */       rewriteUtil.reWriteSrc38(srcpara);
/*     */     }

			  srcpara = (List)srcParaIndex.get("FQ01");
/*  63 */     if (!VOChecker.isEmpty(srcpara)) {
/*  64 */       rewriteUtil.reWriteSrcFQ(srcpara);
/*     */     }
/*     */     
/*  67 */     srcpara = (List)srcParaIndex.get(SOBillType.SaleQuotation.getCode());
/*  68 */     if (!VOChecker.isEmpty(srcpara))
/*     */     {
/*  70 */       rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(2));
/*     */     }
/*     */     
/*  73 */     srcpara = (List)srcParaIndex.get(OPCBillType.OPCORDER.getCode());
/*  74 */     if (!VOChecker.isEmpty(srcpara)) {
/*  75 */       rewriteUtil.reWriteSrcOPC(srcpara);
/*     */     }
/*     */     
/*  78 */     srcpara = (List)srcParaIndex.get(ICBillType.BorrowOut.getCode());
/*  79 */     if (!VOChecker.isEmpty(srcpara)) {
/*  80 */       rewriteUtil.reWriteSrc4H(srcpara);
/*     */     }
/*     */     
/*  83 */     rewriteUtil.reWrite21(vos);
/*     */     
/*  85 */     rewriteZ3(vos);
/*     */     
/*     */ 
/*     */ 
/*  89 */     BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
/*  90 */     Map<String, List<RewritePara>> firstParaIndex = firsttool.splitForInsert(vos);
/*     */     
/*  92 */     List<RewritePara> firstSaleOrder = (List)firstParaIndex.get(SOBillType.Order.getCode());
/*     */     
/*     */ 
/*  95 */     firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);
/*     */     
/*     */ 
/*  98 */     srcpara = (List)srcParaIndex.get(POBillType.Order.getCode());
/*  99 */     if ((!VOChecker.isEmpty(firstSaleOrder)) && (VOChecker.isEmpty(srcpara))) {
/* 100 */       rewriteUtil.reWriteFirst30(firstSaleOrder);
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
/*     */   private void rewriteZ3(SaleOrderVO[] vos)
/*     */   {
/* 113 */     SaleOrderVO[] newVOs = fillCtType(vos);
/* 114 */     if (newVOs.length == 0) {
/* 115 */       return;
/*     */     }
/*     */     
/* 118 */     SaleOrderVO[] firstVOs = fillFirstCtType(vos);
/*     */     
/*     */ 
/* 121 */     ItemKeyMapping mapping = new ItemKeyMapping();
/* 122 */     mapping.setVsrctypeKey("vcttype");
/* 123 */     mapping.setCsrcidKey("cctmanageid");
/* 124 */     mapping.setCsrcbidKey("cctmanagebid");
/* 125 */     mapping.setNnumKey("nnum");
/* 126 */     mapping.setNnum2Key("norigtaxmny");
/*     */     
/* 128 */     mapping.setCsrcbbidKey("crowno");
/* 129 */     if (firstVOs.length == 0) {
/* 130 */       mapping.setSrcTSKey("srcts");
/*     */     }
/*     */     
/*     */ 
/* 134 */     BillRewriter tool = new BillRewriter(mapping);
/*     */     
/* 136 */     tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
/* 137 */     tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);
/*     */     
/* 139 */     Map<String, UFDouble> bodymap = new HashMap();
/*     */     
/* 141 */     buildSaleOrderBVOData(bodymap, newVOs);
/*     */     
/* 143 */     Map<String, List<RewritePara>> paraIndex = tool.splitForInsert(newVOs);
/*     */     
/*     */ 
/* 146 */     List<RewritePara> paraList = (List)paraIndex.get(CTBillType.SaleDaily.getCode());
/* 147 */     if ((null != paraList) && (paraList.size() > 0)) {
/* 148 */       int size = paraList.size();
/* 149 */       CtWriteBackForOrderVO[] paras = new CtWriteBackForOrderVO[size];
/* 150 */       for (int i = 0; i < size; i++) {
/* 151 */         String id = ((RewritePara)paraList.get(i)).getCsrcid();
/* 152 */         String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 153 */         UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 154 */         UFDouble norigtaxmny = ((RewritePara)paraList.get(i)).getNnum2();
/* 155 */         String crowno = ((RewritePara)paraList.get(i)).getCsrcbbid();
/* 156 */         paras[i] = new CtWriteBackForOrderVO();
/* 157 */         paras[i].setPk_ctpu(id);
/* 158 */         paras[i].setPk_ctpu_b(bid);
/* 159 */         paras[i].setNum(nnum);
/* 160 */         paras[i].setPriceNum(norigtaxmny);
/* 161 */         paras[i].setcRowNum(crowno);
/* 162 */         if (bodymap.containsKey(((RewritePara)paraList.get(i)).getCbill_bid()))
/*     */         {
/* 164 */           UFDouble price = (UFDouble)bodymap.get(((RewritePara)paraList.get(i)).getCbill_bid());
/* 165 */           paras[i].setPrice(price);
/*     */         }
/*     */       }
/* 168 */       IReWriteZ3For30 api = (IReWriteZ3For30)NCLocator.getInstance().lookup(IReWriteZ3For30.class);
/*     */       try
/*     */       {
/* 171 */         api.rewriteBackZ3For30(paras);
/*     */       }
/*     */       catch (BusinessException ex) {
/* 174 */         ExceptionUtils.wrappException(ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private SaleOrderVO[] fillFirstCtType(SaleOrderVO[] vos) {
/* 180 */     List<SaleOrderVO> z3vos = new ArrayList();
/* 181 */     for (SaleOrderVO vo : vos) {
/* 182 */       SaleOrderBVO[] bodys = vo.getChildrenVO();
/* 183 */       List<SaleOrderBVO> z3bvos = new ArrayList();
/* 184 */       for (SaleOrderBVO body : bodys) {
/* 185 */         if (!CTBillType.SaleDaily.getCode().equals(body.getVsrctype())) {
/* 186 */           body.setVcttype(CTBillType.SaleDaily.getCode());
/* 187 */           z3bvos.add(body);
/*     */         }
/*     */       }
/* 190 */       if (z3bvos.size() > 0) {
/* 191 */         SaleOrderVO newvo = new SaleOrderVO();
/* 192 */         newvo.setParentVO(vo.getParentVO());
/* 193 */         newvo.setChildrenVO((SaleOrderBVO[])z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
/* 194 */         z3vos.add(newvo);
/*     */       }
/*     */     }
/* 197 */     return (SaleOrderVO[])z3vos.toArray(new SaleOrderVO[z3vos.size()]);
/*     */   }
/*     */   
/*     */   private SaleOrderVO[] fillCtType(SaleOrderVO[] vos) {
/* 201 */     List<SaleOrderVO> z3vos = new ArrayList();
/* 202 */     for (SaleOrderVO vo : vos) {
/* 203 */       SaleOrderBVO[] bodys = vo.getChildrenVO();
/* 204 */       List<SaleOrderBVO> z3bvos = new ArrayList();
/* 205 */       for (SaleOrderBVO body : bodys) {
/* 206 */         if ((body.getCctmanagebid() != null) && (body.getCctmanagebid().trim().length() > 0))
/*     */         {
/* 208 */           body.setVcttype(CTBillType.SaleDaily.getCode());
/* 209 */           z3bvos.add(body);
/*     */         }
/*     */       }
/* 212 */       if (z3bvos.size() > 0) {
/* 213 */         SaleOrderVO newvo = new SaleOrderVO();
/* 214 */         newvo.setParentVO(vo.getParentVO());
/* 215 */         newvo.setChildrenVO((SaleOrderBVO[])z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
/* 216 */         z3vos.add(newvo);
/*     */       }
/*     */     }
/* 219 */     return (SaleOrderVO[])z3vos.toArray(new SaleOrderVO[z3vos.size()]);
/*     */   }
/*     */   
/*     */   private void buildSaleOrderBVOData(Map<String, UFDouble> bodymap, SaleOrderVO[] billvos)
/*     */   {
/* 224 */     for (SaleOrderVO billvo : billvos) {
/* 225 */       SaleOrderBVO[] childvos = billvo.getChildrenVO();
/* 226 */       for (SaleOrderBVO bodyvo : childvos) {
/* 227 */         if (!bodymap.containsKey(bodyvo.getPrimaryKey())) {
/* 228 */           bodymap.put(bodyvo.getPrimaryKey(), bodyvo.getNorigtaxprice());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\classes
 * Qualified Name:     nc.bs.so.m30.maintain.rule.insert.RewriteBillInsertRule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */