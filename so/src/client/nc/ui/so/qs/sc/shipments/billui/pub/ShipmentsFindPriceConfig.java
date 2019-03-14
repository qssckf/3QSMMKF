package nc.ui.so.qs.sc.shipments.billui.pub;
/*     */ 
/*     */ import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.FQ01trantype.IFQ01TranTypeService;
///*     */ import nc.itf.so.m38trantype.IM38TranTypeService;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.so.pub.findprice.IFindPriceConfig;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.calculator.CalculatorUtil;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
///*     */ import nc.vo.so.m38.entity.PreOrderBVO;
///*     */ import nc.vo.so.m38.entity.PreOrderHVO;
///*     */ import nc.vo.so.m38.entity.PreOrderVO;
///*     */ import nc.vo.so.m38trantype.entity.M38TranTypeVO;
/*     */ import nc.vo.so.pub.enumeration.AskPriceRule;
/*     */ import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShipmentsFindPriceConfig
/*     */   implements IFindPriceConfig
/*     */ {
/*     */   private FQ01TranTypeVO fq01trantypevo;
/*     */   private BillCardPanel cardPanel;
/*     */   
/*     */   public ShipmentsFindPriceConfig(BillCardPanel cardPanel)
/*     */   {
/*  38 */     this.cardPanel = cardPanel;
/*     */   }
/*     */   
/*     */   private FQ01TranTypeVO getTranTypeVO() {
/*  42 */     if (null == this.fq01trantypevo) {
/*  43 */       IKeyValue keyValue = new CardKeyValue(this.cardPanel);
/*  44 */       String trantypeid = keyValue.getHeadStringValue("transtypepk");
/*  45 */       if (PubAppTool.isNull(trantypeid)) {
/*  46 */         return null;
/*     */       }
/*  48 */       IFQ01TranTypeService srv = (IFQ01TranTypeService)NCLocator.getInstance().lookup(IFQ01TranTypeService.class);
/*     */       try
/*     */       {
/*  51 */         this.fq01trantypevo = srv.queryTranTypeVO(trantypeid);
/*     */       }
/*     */       catch (BusinessException e) {
/*  54 */         ExceptionUtils.wrappException(e);
/*     */       }
/*     */     }
/*     */     
/*  58 */     return this.fq01trantypevo;
/*     */   }
/*     */   
/*     */   public boolean isModifyAskSucess()
/*     */   {
/*  63 */     FQ01TranTypeVO trantype = getTranTypeVO();
/*  64 */     if (null == trantype) {
/*  65 */       return false;
/*     */     }
/*  67 */     UFBoolean bmodify = trantype.getBmodifyaskedqt();
/*  68 */     return (null != bmodify) && (bmodify.booleanValue());
/*     */   }
/*     */   
/*     */   public boolean isModifyAskFail()
/*     */   {
/*  73 */     FQ01TranTypeVO trantype = getTranTypeVO();
/*  74 */     if (null == trantype) {
/*  75 */       return false;
/*     */     }
/*  77 */     UFBoolean bmodify = trantype.getBmodifyunaskedqt();
/*  78 */     return (null != bmodify) && (bmodify.booleanValue());
/*     */   }
/*     */   
/*     */   public boolean isShowMsgAskFail()
/*     */   {
/*  83 */     FQ01TranTypeVO trantype = getTranTypeVO();
/*  84 */     if (null == trantype) {
/*  85 */       return false;
/*     */     }
/*  87 */     UFBoolean bhint = trantype.getBnofindpricehit();
/*  88 */     return (null != bhint) && (bhint.booleanValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isLargessAskPrice()
/*     */   {
/*  95 */     FQ01TranTypeVO trantype = getTranTypeVO();
/*  96 */     if (null == trantype) {
/*  97 */       return false;
/*     */     }
/*  99 */     return LargessGetqtRule.ASK_SALEQT.equalsValue(trantype.getFlargessgetqtrule());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Integer getAskPriceRule()
/*     */   {
/* 107 */     FQ01TranTypeVO trantype = getTranTypeVO();
/* 108 */     if (null == trantype) {
/* 109 */       return AskPriceRule.ASKPRICE_NO.getIntegerValue();
/*     */     }
/* 111 */     return trantype.getFaskqtrule();
/*     */   }
/*     */   
/*     */   public void processAskSucessRows(int[] sucessrows, String chgkey)
/*     */   {
/* 116 */     ShipmentsCalculator calcultor = new ShipmentsCalculator(this.cardPanel);
/* 117 */     calcultor.setChangePrice(UFBoolean.FALSE);
/* 118 */     calcultor.calculate(sucessrows, chgkey);
/*     */     
/* 120 */     IKeyValue keyValue = new CardKeyValue(this.cardPanel);
/* 121 */     for (int i : sucessrows) {
/* 122 */       UFDouble nitemdiscountrate = keyValue.getBodyUFDoubleValue(i, "nitemdiscountrate");
/*     */       
/* 124 */       nitemdiscountrate = CalculatorUtil.div(nitemdiscountrate, new UFDouble(100.0D));
/*     */       
/*     */ 
/* 127 */       UFDouble ndiscountrate = keyValue.getBodyUFDoubleValue(i, "ndiscountrate");
/*     */       
/* 129 */       ndiscountrate = CalculatorUtil.div(ndiscountrate, new UFDouble(100.0D));
/*     */       
/* 131 */       UFDouble discount = CalculatorUtil.multiply(nitemdiscountrate, ndiscountrate);
/*     */       
/* 133 */       int intZkl = MathTool.compareTo(discount, UFDouble.ZERO_DBL);
/*     */       
/* 135 */       if (intZkl != 0)
/*     */       {
/*     */ 
/* 138 */         UFDouble norignetprice = keyValue.getBodyUFDoubleValue(i, "norignetprice");
/*     */         
/* 140 */         UFDouble norigprice = CalculatorUtil.div(norignetprice, discount);
/* 141 */         keyValue.setBodyValue(i, "norigprice", norigprice);
/*     */         
/*     */ 
/* 144 */         UFDouble norigtaxnetprice = keyValue.getBodyUFDoubleValue(i, "norigtaxnetprice");
/*     */         
/* 146 */         UFDouble norigtaxprice = CalculatorUtil.div(norigtaxnetprice, discount);
/* 147 */         keyValue.setBodyValue(i, "norigtaxprice", norigtaxprice);
/*     */         
/*     */ 
/* 150 */         UFDouble nnetprice = keyValue.getBodyUFDoubleValue(i, "nnetprice");
/*     */         
/* 152 */         UFDouble nprice = CalculatorUtil.div(nnetprice, discount);
/* 153 */         keyValue.setBodyValue(i, "nprice", nprice);
/*     */         
/*     */ 
/* 156 */         UFDouble ntaxnetprice = keyValue.getBodyUFDoubleValue(i, "ntaxnetprice");
/*     */         
/* 158 */         UFDouble ntaxprice = CalculatorUtil.div(ntaxnetprice, discount);
/* 159 */         keyValue.setBodyValue(i, "ntaxprice", ntaxprice);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void processAskFailRows(int[] failrows) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public AggregatedValueObject getBillVO()
/*     */   {
/* 172 */     AggregatedValueObject bill = this.cardPanel.getBillValueVO(AggShipmentsVO.class.getName(), ShipmentsVO.class.getName(), ShipmentsBVO.class.getName());
/*     */     
/*     */ 
/* 175 */     return bill;
/*     */   }
/*     */ }