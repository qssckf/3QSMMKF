/*     */ package nc.bs.so.m30.maintain.rule.update;
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
/*     */ import nc.impl.pubapp.pattern.rule.ICompareRule;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RewriteBillUpdateRule
/*     */   implements ICompareRule<SaleOrderVO>
/*     */ {
/*     */   public RewriteBillUpdateRule() {}
/*     */   
/*     */   public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs)
/*     */   {
/*  45 */     RewriteBillUtil rewriteUtil = new RewriteBillUtil();
/*  46 */     BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
/*  47 */     Map<String, List<RewritePara>> srcParaIndex = srctool.splitForUpdate(vos, originVOs);
/*     */     
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
/*  68 */     if (!VOChecker.isEmpty(srcpara)) {
/*  69 */       rewriteUtil.reWriteSrc4310(srcpara, Integer.valueOf(1));
/*     */     }
/*     */     
/*  72 */     srcpara = (List)srcParaIndex.get(OPCBillType.OPCORDER.getCode());
/*  73 */     if (!VOChecker.isEmpty(srcpara)) {
/*  74 */       rewriteUtil.reWriteSrcOPC(srcpara);
/*     */     }
/*     */     
/*  77 */     srcpara = (List)srcParaIndex.get(ICBillType.BorrowOut.getCode());
/*  78 */     if (!VOChecker.isEmpty(srcpara)) {
/*  79 */       rewriteUtil.reWriteSrc4H(srcpara);
/*     */     }
/*     */     
/*  82 */     rewriteZ3(vos, originVOs);
/*     */     
/*     */ 
/*     */ 
/*  86 */     BillRewriter firsttool = rewriteUtil.getFirstBillRewriter();
/*  87 */     Map<String, List<RewritePara>> firstParaIndex = firsttool.splitForUpdate(vos, originVOs);
/*     */     
/*  89 */     List<RewritePara> firstSaleOrder = (List)firstParaIndex.get(SOBillType.Order.getCode());
/*     */     
/*     */ 
/*  92 */     firstSaleOrder = rewriteUtil.filtrateSrc30(firstSaleOrder, srcSaleOrder);
/*  93 */     if (!VOChecker.isEmpty(firstSaleOrder)) {
/*  94 */       rewriteUtil.reWriteFirst30(firstSaleOrder);
/*     */     }
/*     */   }
/*     */   
/*     */   private void rewriteZ3(SaleOrderVO[] vos, SaleOrderVO[] originVOs)
/*     */   {
/* 100 */     SaleOrderVO[] newOriginVOs = fillCtType(originVOs);
/* 101 */     SaleOrderVO[] newVOs = fillCtType(vos);
/* 102 */     if ((newVOs.length == 0) && (newOriginVOs.length == 0)) {
/* 103 */       return;
/*     */     }
/*     */     
/* 106 */     ItemKeyMapping mapping = new ItemKeyMapping();
/* 107 */     mapping.setVsrctypeKey("vcttype");
/* 108 */     mapping.setCsrcidKey("cctmanageid");
/* 109 */     mapping.setCsrcbidKey("cctmanagebid");
/* 110 */     mapping.setNnumKey("nnum");
/* 111 */     mapping.setNnum2Key("norigtaxmny");
/* 112 */     mapping.setSrcTSKey("srcts");
/*     */     
/* 114 */     mapping.setCsrcbbidKey("crowno");
/*     */     
/* 116 */     BillRewriter tool = new BillRewriter(mapping);
/*     */     
/* 118 */     tool.addSRCHeadClazz(CTBillType.SaleDaily.getCode(), CtSaleVO.class);
/* 119 */     tool.addSRCItemClazz(CTBillType.SaleDaily.getCode(), CtSaleBVO.class);
/*     */     
/* 121 */     Map<String, UFDouble> bodymap = new HashMap();
/*     */     
/* 123 */     buildSaleOrderBVOData(bodymap, newVOs);
/* 124 */     buildSaleOrderBVOData(bodymap, newOriginVOs);
/* 125 */     SaleOrderVO[] destVOS = buildSplitVO(vos, originVOs);
/*     */     
/* 127 */     Map<String, List<RewritePara>> paraIndex = tool.splitForUpdate(destVOS, originVOs);
/*     */     
/*     */ 
/*     */ 
/* 131 */     List<RewritePara> paraList = (List)paraIndex.get(CTBillType.SaleDaily.getCode());
/* 132 */     if ((null != paraList) && (paraList.size() > 0)) {
/* 133 */       int size = paraList.size();
/* 134 */       CtWriteBackForOrderVO[] paras = new CtWriteBackForOrderVO[size];
/* 135 */       for (int i = 0; i < size; i++) {
/* 136 */         String id = ((RewritePara)paraList.get(i)).getCsrcid();
/* 137 */         String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
/* 138 */         UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
/* 139 */         UFDouble norigtaxmny = ((RewritePara)paraList.get(i)).getNnum2();
/* 140 */         String crowno = ((RewritePara)paraList.get(i)).getCsrcbbid();
/* 141 */         paras[i] = new CtWriteBackForOrderVO();
/* 142 */         paras[i].setPk_ctpu(id);
/* 143 */         paras[i].setPk_ctpu_b(bid);
/* 144 */         paras[i].setNum(nnum);
/* 145 */         paras[i].setPriceNum(norigtaxmny);
/* 146 */         paras[i].setcRowNum(crowno);
/* 147 */         if (bodymap.containsKey(((RewritePara)paraList.get(i)).getCbill_bid()))
/*     */         {
/* 149 */           UFDouble price = (UFDouble)bodymap.get(((RewritePara)paraList.get(i)).getCbill_bid());
/* 150 */           paras[i].setPrice(price);
/*     */         }
/*     */       }
/* 153 */       IReWriteZ3For30 api = (IReWriteZ3For30)NCLocator.getInstance().lookup(IReWriteZ3For30.class);
/*     */       try
/*     */       {
/* 156 */         api.rewriteBackZ3For30(paras);
/*     */       }
/*     */       catch (BusinessException ex) {
/* 159 */         ExceptionUtils.wrappException(ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private SaleOrderVO[] buildSplitVO(SaleOrderVO[] vos, SaleOrderVO[] originVOs)
/*     */   {
/* 172 */     Map<String, SaleOrderBVO> voIndex = new HashMap();
/* 173 */     for (SaleOrderVO originVO : originVOs) {
/* 174 */       for (SaleOrderBVO bvo : originVO.getChildrenVO()) {
/* 175 */         voIndex.put(bvo.getPrimaryKey(), bvo);
/*     */       }
/*     */     }
/* 178 */     List<SaleOrderVO> destVOList = new ArrayList();
/* 179 */     for (SaleOrderVO vo : vos) {
/* 180 */       SaleOrderVO destvo = new SaleOrderVO();
/* 181 */       List<SaleOrderBVO> bvoList = new ArrayList();
/* 182 */       destvo.setParentVO(vo.getParentVO());
/* 183 */       for (SaleOrderBVO bvo : vo.getChildrenVO()) {
/* 184 */         SaleOrderBVO destbvo = (SaleOrderBVO)bvo.clone();
/* 185 */         if (voIndex.containsKey(destbvo.getPrimaryKey())) {
/* 186 */           String originsrctype = ((SaleOrderBVO)voIndex.get(destbvo.getPrimaryKey())).getVcttype();
/*     */           
/* 188 */           String originctcode = ((SaleOrderBVO)voIndex.get(destbvo.getPrimaryKey())).getVctcode();
/*     */           
/* 190 */           if ((null != originctcode) && (CTBillType.SaleDaily.getCode().equals(originsrctype)))
/*     */           {
/* 192 */             String originctbid = ((SaleOrderBVO)voIndex.get(destbvo.getPrimaryKey())).getCctmanagebid();
/*     */             
/* 194 */             if (!originctbid.equals(destbvo.getCctmanagebid())) {
/* 195 */               destbvo.setPrimaryKey(null);
/*     */             }
/*     */           }
/* 198 */           if ((null == originctcode) && (null != destbvo.getVctcode())) {
/* 199 */             destbvo.setPrimaryKey(null);
/*     */           }
/*     */         }
/* 202 */         bvoList.add(destbvo);
/*     */       }
/* 204 */       destvo.setChildrenVO((SaleOrderBVO[])bvoList.toArray(new SaleOrderBVO[bvoList.size()]));
/* 205 */       destVOList.add(destvo);
/*     */     }
/* 207 */     return (SaleOrderVO[])destVOList.toArray(new SaleOrderVO[destVOList.size()]);
/*     */   }
/*     */   
/*     */   private SaleOrderVO[] fillCtType(SaleOrderVO[] vos) {
/* 211 */     List<SaleOrderVO> z3vos = new ArrayList();
/* 212 */     for (SaleOrderVO vo : vos) {
/* 213 */       SaleOrderBVO[] bodys = vo.getChildrenVO();
/* 214 */       List<SaleOrderBVO> z3bvos = new ArrayList();
/* 215 */       for (SaleOrderBVO body : bodys) {
/* 216 */         if ((body.getCctmanagebid() != null) && (body.getCctmanagebid().trim().length() > 0))
/*     */         {
/* 218 */           body.setVcttype(CTBillType.SaleDaily.getCode());
/* 219 */           z3bvos.add(body);
/*     */         }
/*     */       }
/*     */       
/* 223 */       if (z3bvos.size() > 0) {
/* 224 */         SaleOrderVO newvo = new SaleOrderVO();
/* 225 */         newvo.setParentVO(vo.getParentVO());
/* 226 */         newvo.setChildrenVO((SaleOrderBVO[])z3bvos.toArray(new SaleOrderBVO[z3bvos.size()]));
/* 227 */         z3vos.add(newvo);
/*     */       }
/*     */     }
/* 230 */     return (SaleOrderVO[])z3vos.toArray(new SaleOrderVO[z3vos.size()]);
/*     */   }
/*     */   
/*     */   private void buildSaleOrderBVOData(Map<String, UFDouble> bodymap, SaleOrderVO[] billvos)
/*     */   {
/* 235 */     for (SaleOrderVO billvo : billvos) {
/* 236 */       SaleOrderBVO[] childvos = billvo.getChildrenVO();
/* 237 */       for (SaleOrderBVO bodyvo : childvos) {
/* 238 */         if (!bodymap.containsKey(bodyvo.getPrimaryKey())) {
/* 239 */           bodymap.put(bodyvo.getPrimaryKey(), bodyvo.getNorigtaxprice());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\classes
 * Qualified Name:     nc.bs.so.m30.maintain.rule.update.RewriteBillUpdateRule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */