package nc.ui.so.qs.sc.shipments.billui.pub;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.FQ01trantype.IFQ01TranTypeService;
///*     */ import nc.itf.so.m38trantype.IM38TranTypeService;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.AppUiContext;
/*     */ import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
/*     */ import nc.ui.pubapp.pub.scale.UIScaleUtils;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pubapp.AppContext;
/*     */ import nc.vo.pubapp.calculator.Calculator;
/*     */ import nc.vo.pubapp.calculator.Condition;
/*     */ import nc.vo.pubapp.calculator.data.IDataSetForCal;
/*     */ import nc.vo.pubapp.calculator.data.IRelationForItems;
/*     */ import nc.vo.pubapp.calculator.data.RelationItemForCal;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.pubapp.scale.ScaleUtils;
/*     */ import nc.vo.so.m38.rule.HeadTotalCalculateRule;
///*     */ import nc.vo.so.m38trantype.entity.M38TranTypeVO;
          import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;
/*     */ import nc.vo.so.pub.enumeration.PriceDiscountType;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*     */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*     */ import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShipmentsCalculator {
/*     */   private static class ShipmentsCarDataSet extends BillCardPanelDataSet {
/*     */     public ShipmentsCarDataSet(BillCardPanel cardPanel, int row, IRelationForItems item)
/*     */     {
/*  45 */       super(cardPanel,row, item);
/*     */     }
/*     */     public String getCorigcurrencyid()
/*     */     {
/*  54 */       String value = (String)getBillCardPanel().getHeadTailItem("corigcurrencyid").getValueObject();
/*     */       
/*     */ 
/*  57 */       return value;
/*     */     }
/*     */     
/*     */ 
/*     */     public String getPk_group()
/*     */     {
/*  63 */       String value = (String)getBillCardPanel().getHeadTailItem("pk_group").getValueObject();
/*     */       
/*     */ 
/*  66 */       return value;
/*     */     }
/*     */     
/*     */     public boolean hasItemKey(String key)
/*     */     {
/*  71 */       if (("corigcurrencyid".equals(key)) || ("pk_group".equals(key)))
/*     */       {
/*  73 */         return true;
/*     */       }
/*  75 */       return super.hasItemKey(key);
/*     */     }
/*     */   }
/*     */   
/*  79 */   private static final String[] STRNEEDCALKEY = { "nastnum", "nnum", "vchangerate", "castunitid", "nqtunitnum", "vqtunitrate", "ntaxrate", "ndiscountrate", "nitemdiscountrate", "norigtaxprice", "norigprice", "norigtaxnetprice", "norignetprice", "nqtorigtaxprice", "nqtorigprice", "nqtorigtaxnetprc", "nqtorignetprice", "ntax", "nmny", "ntaxmny", "ncaltaxmny", "norigmny", "norigtaxmny", "norigdiscount", "nexchangerate", "ngroupexchgrate", "nglobalexchgrate" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private BillCardPanel cardpanel;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UFBoolean forechgprice;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Set<String> hsNeedCalKey;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private IKeyValue keyValue;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private FQ01TranTypeVO trantypevo;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShipmentsCalculator(BillCardPanel cardpanel)
/*     */   {
/* 132 */     this.cardpanel = cardpanel;
/* 133 */     this.keyValue = new CardKeyValue(cardpanel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculate(int row, String editkey)
/*     */   {
/* 143 */     int[] rows = { row };
/*     */     
/*     */ 
/* 146 */     calcNumPriceMny(rows, editkey, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculate(int[] rows, String editkey)
/*     */   {
/* 156 */     calcNumPriceMny(rows, editkey, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculateOnlyNum(int row, String editkey)
/*     */   {
/* 166 */     int[] rows = { row };
/*     */     
/*     */ 
/* 169 */     calcNumPriceMny(rows, editkey, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculateOnlyNum(int[] rows, String editkey)
/*     */   {
/* 179 */     calcNumPriceMny(rows, editkey, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void calculatorBuysell(int[] rows)
/*     */   {
/* 188 */     if ((null != rows) && (rows.length > 0)) {
/* 189 */       if (isTaxPrior()) {
/* 190 */         calculate(rows, "nqtorigtaxprice");
/*     */       }
/*     */       else {
/* 193 */         calculate(rows, "nqtorigprice");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setChangePrice(UFBoolean forechgprice)
/*     */   {
/* 204 */     this.forechgprice = forechgprice;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTranTypeVO(FQ01TranTypeVO trantypevo)
/*     */   {
/* 213 */     this.trantypevo = trantypevo;
/*     */   }
/*     */   
/*     */   private void calcNumPriceMny(int[] rows, String editkey, boolean isnumonly)
/*     */   {
/* 218 */     if ((null == rows) || (rows.length == 0) || (PubAppTool.isNull(editkey))) {
/* 219 */       return;
/*     */     }
/*     */     
/* 222 */     if (!getNeedCalKey().contains(editkey)) {
/* 223 */       return;
/*     */     }
/*     */     
/* 226 */     IRelationForItems item = new RelationItemForCal();
/* 227 */     ScaleUtils scale = UIScaleUtils.getScaleUtils();
/*     */     
/*     */ 
/* 230 */     Condition cond = SOCalConditionRule.getCondition();
/*     */     
/*     */ 
/* 233 */     boolean isChgPriceOrDiscount = isChgPriceOrDiscount();
/* 234 */     cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
/*     */     
/* 236 */     SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
/* 237 */     SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
/* 238 */     for (int row : rows)
/*     */     {
/* 240 */       IDataSetForCal data = new ShipmentsCarDataSet(this.cardpanel, row, item);
/* 241 */       Calculator tool = new Calculator(data, scale);
/*     */       
/*     */ 
/* 244 */       cond.setInternational(buysellrule.isBuysellFlagOut(row));
/*     */       
/* 246 */       cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
/*     */       
/* 248 */       cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));
/* 249 */       if (isnumonly) {
/* 250 */         tool.calculateOnlyNumAssNumQtNum(cond, editkey);
/*     */       }
/*     */       else {
/* 253 */         tool.calculate(cond, editkey);
/*     */       }
/*     */     }
/*     */     
/* 257 */     HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(this.keyValue);
/*     */     
/* 259 */     totalrule.calculateHeadTotal();
/*     */   }
/*     */   
/*     */   private Set<String> getNeedCalKey() {
/* 263 */     if (null == this.hsNeedCalKey) {
/* 264 */       this.hsNeedCalKey = new HashSet();
/* 265 */       for (String key : STRNEEDCALKEY) {
/* 266 */         this.hsNeedCalKey.add(key);
/*     */       }
/*     */     }
/* 269 */     return this.hsNeedCalKey;
/*     */   }
/*     */   
/*     */   private FQ01TranTypeVO getTranTypeVO()
/*     */   {
/* 274 */     if (null == this.trantypevo) {
/* 275 */       String trantypeid = this.keyValue.getHeadStringValue("transtypepk");
/*     */       
/* 277 */       if (PubAppTool.isNull(trantypeid)) {
/* 278 */         return null;
/*     */       }
/* 280 */       IFQ01TranTypeService srv = (IFQ01TranTypeService)NCLocator.getInstance().lookup(IFQ01TranTypeService.class);
/*     */       try
/*     */       {
/* 283 */         this.trantypevo = srv.queryTranTypeVO(trantypeid);
/*     */       }
/*     */       catch (BusinessException e) {
/* 286 */         ExceptionUtils.wrappException(e);
/*     */       }
/*     */     }
/* 289 */     return this.trantypevo;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isChgPriceOrDiscount()
/*     */   {
/* 295 */     if (null != this.forechgprice) {
/* 296 */       return this.forechgprice.booleanValue();
/*     */     }
/* 298 */     FQ01TranTypeVO fq01tranvo = getTranTypeVO();
/*     */     
/* 300 */     if (null == fq01tranvo) {
/* 301 */       return false;
/*     */     }
/* 303 */     Integer fmodifymny = fq01tranvo.getFmodifymny();
/* 304 */     return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isTaxPrior()
/*     */   {
/* 311 */     String pk_group = AppUiContext.getInstance().getPkGroup();
/* 312 */     UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
/* 313 */     if (null == so23) {
/* 314 */       return false;
/*     */     }
/* 316 */     return so23.booleanValue();
/*     */   }
/*     */ }
