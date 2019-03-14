package nc.ui.so.shipmentsinfo.handler.head;
/*     */ 
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*     */ import nc.ui.so.pub.findprice.FindSalePrice;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
///*     */ import nc.vo.so.m38.keyrela.PreOrderKeyrela;
/*     */ import nc.vo.so.pub.keyvalue.IKeyRela;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.so.pub.rule.BodyUpdateByHead;
/*     */ import nc.vo.so.pub.rule.BodyValueRowRule;
/*     */ import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
/*     */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*     */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*     */ import nc.vo.so.pub.rule.SOCountryInfoRule;
/*     */ import nc.vo.so.pub.rule.SOCurrencyRule;
/*     */ import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
/*     */ import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
/*     */ import nc.vo.so.pub.rule.SOExchangeRateRule;
/*     */ import nc.vo.so.pub.rule.SOGlobalExchangeRate;
/*     */ import nc.vo.so.pub.rule.SOGroupExchangeRate;
/*     */ import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.qs.sc.FQ01.keyrela.ShipmentsKeyrela;
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
/*     */ public class CustomerEditHandler
/*     */ {
/*     */   public CustomerEditHandler() {}
/*     */   
/*     */   public void afterEdit(CardHeadTailAfterEditEvent e)
/*     */   {
/*  45 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  46 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*  47 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/*  48 */     BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
/*  49 */     int[] rows = countutil.getMarNotNullRows();
/*     */     
/*  51 */     SOCustRelaDefValueRule defrule = new SOCustRelaDefValueRule(keyValue);
/*  52 */     defrule.setCustRelaDefValue();
/*     */     
/*  54 */     if (rows.length == 0) {
/*  55 */       return;
/*     */     }
/*  57 */     if (defrule.isDiscountRateChg()) {
/*  58 */       calculator.calculate(rows, "ndiscountrate");
/*     */     }
/*     */     
/*  61 */     SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
/*  62 */     relarule.setFinanceOrg(rows);
/*     */     
/*  64 */     SOCurrencyRule currule = new SOCurrencyRule(keyValue);
/*  65 */     currule.setCurrency(rows);
/*     */     
/*  67 */     SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
/*  68 */     exraterule.calcBodyExchangeRates(rows);
/*     */     
/*  70 */     SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
/*  71 */     globalraterule.calcGlobalExchangeRate(rows);
/*     */     
/*  73 */     SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
/*  74 */     groupraterule.calcGroupExchangeRate(rows);
/*     */     
/*  76 */     calculator.calculate(rows, "nexchangerate");
/*     */     
/*  78 */     BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
/*  79 */     updaterule.updateAllBodyValueByHead("creceivecustid", "ccustomerid");
/*     */     
/*     */ 
/*  82 */     IKeyRela keyRela = new ShipmentsKeyrela();
/*  83 */     ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue, keyRela);
/*     */     
/*  85 */     defaddrule.setCustDefaultAddress(rows);
/*     */     
/*  87 */     SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
/*  88 */     conutryinforule.setReceiveCountry(rows);
/*     */     
/*  90 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/*  91 */     buyflgrule.setBuysellAndTriaFlag(rows);
/*  92 */     int[] buychgrows = buyflgrule.getBuysellChgRow();
/*  93 */     calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
/*     */     
/*  95 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/*  96 */     taxInfo.setTaxInfoByBodyPos(rows);
/*  97 */     int[] ratechgrows = taxInfo.getTaxChangeRows();
/*  98 */     calculator.calculate(ratechgrows, "ntaxrate");
/*     */     
/* 100 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 101 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/* 102 */     findPrice.findPriceAfterEdit(rows, "ccustomerid");
/*     */     
/*     */ 
/* 105 */     SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
/* 106 */     socustmar.setCustMaterial(rows);
/*     */   }
/*     */ }