/*     */ package nc.vo.so.m30.vochange;
/*     */ 
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.scmpub.res.billtype.SOBillType;
/*     */ import nc.vo.so.m30.entity.SaleOrderVO;
/*     */ import nc.vo.so.m30.pub.SaleOrderVOCalculator;
/*     */ import nc.vo.so.m30.rule.DirectStoreRule;
/*     */ import nc.vo.so.m30.rule.PayTermRule;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.so.pub.keyvalue.VOKeyValue;
/*     */ import nc.vo.so.pub.rule.BodyValueRowRule;
/*     */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*     */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*     */ import nc.vo.so.pub.rule.SOCountryInfoRule;
/*     */ import nc.vo.so.pub.rule.SOCurrencyRule;
/*     */ import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
/*     */ import nc.vo.so.pub.rule.SOExchangeRateRule;
/*     */ import nc.vo.so.pub.rule.SOGlobalExchangeRate;
/*     */ import nc.vo.so.pub.rule.SOGroupExchangeRate;
/*     */ import nc.vo.so.pub.rule.SOTaxInfoRule;
/*     */ import nc.vo.so.pub.rule.SaleOrgRelationRule;
/*     */ import nc.vo.so.pub.util.ArrayUtil;
/*     */ 
/*     */ public class FQ01ToM30ChangeVOAdjust
/*     */   extends AbstractSaleOrderChangeVOAdjust
/*     */ {
/*     */   public FQ01ToM30ChangeVOAdjust() {}
/*     */   
/*     */   protected void fillRefAddValue(SaleOrderVO[] vos)
/*     */   {
/*  31 */     super.fillRefAddValue(vos);
/*     */     
/*  33 */     for (SaleOrderVO billvo : vos)
/*     */     {
/*  35 */       IKeyValue keyValue = new VOKeyValue(billvo);
/*     */       
/*     */ 
/*  38 */       BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
/*     */       
/*  40 */       SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
/*  41 */       int[] sendstockrows = bodycouuitl.getValueNullRows("csendstockorgvid");
/*     */       
/*  43 */       orgrelrule.setSendStockOrg(sendstockrows);
/*     */       
/*  45 */       int[] trafficrows = bodycouuitl.getValueNullRows("ctrafficorgvid");
/*     */       
/*  47 */       orgrelrule.setTrafficOrg(trafficrows);
/*     */       
/*  49 */       int[] finacerows = bodycouuitl.getValueNullRows("csettleorgvid");
/*     */       
/*  51 */       orgrelrule.setFinanceOrg(finacerows);
/*     */       
/*     */ 
/*  54 */       SOCurrencyRule currule = new SOCurrencyRule(keyValue);
/*  55 */       currule.setCurrency(finacerows);
/*     */       
/*  57 */       SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
/*  58 */       exrule.calcBodyExchangeRates(finacerows);
/*     */       
/*     */ 
/*  61 */       SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
/*  62 */       globalraterule.calcGlobalExchangeRate(finacerows);
/*     */       
/*  64 */       SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
/*  65 */       groupraterule.calcGroupExchangeRate(finacerows);
/*     */       
/*  67 */       SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
/*  68 */       vocalcultor.calculate(finacerows, "nexchangerate");
/*     */       
/*     */ 
/*  71 */       String invoicecust = keyValue.getHeadStringValue("cinvoicecustid");
/*     */       
/*  73 */       if (PubAppTool.isNull(invoicecust)) {
/*  74 */         SOCustRelaDefValueRule custrelarule = new SOCustRelaDefValueRule(keyValue);
/*     */         
/*  76 */         custrelarule.setCustRelaInvoiceCust();
/*     */       }
/*     */       
/*  79 */       PayTermRule payTermRule = new PayTermRule(keyValue);
/*  80 */       payTermRule.setPayTermInfo();
/*     */       
/*  82 */       int[] needchangerows = ArrayUtil.combinArrays(new int[][] { sendstockrows, finacerows });
/*  83 */       SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
/*  84 */       countryrule.setCountryInfo(needchangerows);
/*     */       
/*  86 */       SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/*  87 */       buyflgrule.setBuysellAndTriaFlag(needchangerows);
/*     */       
/*  89 */       int[] buychgrows = buyflgrule.getBuysellChgRow();
/*  90 */       vocalcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
/*     */       
/*  92 */       SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/*  93 */       taxInfo.setTaxInfoByBodyPos(needchangerows);
/*  94 */       int[] taxchgrows = taxInfo.getTaxChangeRows();
/*  95 */       vocalcultor.calculate(taxchgrows, "ntaxrate");
/*     */       
/*  97 */       int[] rows = bodycouuitl.getMarNotNullRows();
/*     */       
/*  99 */       DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
/* 100 */       dirstorerule.setDirectStore(rows);
/*     */     }
/*     */   }
/*     */   
/*     */   protected String getSrcBillTypeCode()
/*     */   {
/* 106 */     return SOBillType.PreOrder.getCode();
/*     */   }
/*     */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\lib\pubso_salesorder.jar
 * Qualified Name:     nc.vo.so.m30.vochange.M38ToM30ChangeVOAdjust
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */