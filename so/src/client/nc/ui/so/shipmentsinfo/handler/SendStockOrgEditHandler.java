package nc.ui.so.shipmentsinfo.handler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
/*     */ import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
/*     */ import nc.ui.bd.ref.AbstractRefModel;
/*     */ import nc.ui.pub.beans.UIRefPane;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*     */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*     */ import nc.vo.so.pub.rule.SOCountryInfoRule;
/*     */ import nc.vo.so.pub.rule.SOCurrencyRule;
/*     */ import nc.vo.so.pub.rule.SOExchangeRateRule;
/*     */ import nc.vo.so.pub.rule.SOGlobalExchangeRate;
/*     */ import nc.vo.so.pub.rule.SOGroupExchangeRate;
///*     */ import nc.vo.so.pub.rule.SOTaxInfoRule;
/*     */ import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.trade.checkrule.VOChecker;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SendStockOrgEditHandler
/*     */ {
/*     */   public SendStockOrgEditHandler() {}
/*     */   
/*     */   public void afterEdit(CardBodyAfterEditEvent e)
/*     */   {
/*  40 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/*  41 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  42 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*     */     
/*  44 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/*     */     
/*     */ 
/*  47 */     for (int row : rows) {
/*  48 */       keyValue.setBodyValue(row, "csendstordocid", null);
/*     */     }
/*     */     
/*  51 */     SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
/*     */     
/*  53 */     int ilength = rows.length;
/*  54 */     String[] oldcurrs = new String[ilength];
/*  55 */     for (int i = 0; i < ilength; i++) {
/*  56 */       oldcurrs[i] = keyValue.getBodyStringValue(rows[i], "ccurrencyid");
/*     */     }
/*     */     
/*     */ 
/*  60 */     relarule.setFinanceOrg(rows);
/*     */     
/*     */ 
/*  63 */     SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
/*  64 */     currencyrule.setCurrency(rows);
/*     */     
/*     */ 
/*  67 */     relarule.setTrafficOrg(rows);
/*     */     
/*  69 */     SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
/*     */     
/*  71 */     conutryinforule.setSendCountry(rows);
/*  72 */     conutryinforule.setTaxCountry(rows);
/*     */     
/*  74 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/*  75 */     buyflgrule.setBuysellAndTriaFlag(rows);
/*     */     
/*  77 */     int[] buysellrows = buyflgrule.getBuysellChgRow();
/*  78 */     calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
/*     */     
/*     */ 
///*  81 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
///*  82 */     taxInfo.setTaxInfoByBodyPos(rows);
/*     */     
///*  84 */     int[] taxchgrows = taxInfo.getTaxChangeRows();
///*  85 */     calculator.calculate(taxchgrows, "ntaxrate");
/*     */     
/*  87 */     List<Integer> listchgrow = new ArrayList();
/*  88 */     for (int i = 0; i < ilength; i++) {
/*  89 */       String newcurr = keyValue.getBodyStringValue(rows[i], "ccurrencyid");
/*     */       
/*  91 */       if (isCurrChange(oldcurrs[i], newcurr)) {
/*  92 */         listchgrow.add(Integer.valueOf(rows[i]));
/*     */       }
/*     */     }
/*  95 */     int chgsize = listchgrow.size();
/*  96 */     if (chgsize > 0) {
/*  97 */       int[] chgrows = new int[chgsize];
/*  98 */       for (int i = 0; i < chgsize; i++) {
/*  99 */         chgrows[i] = ((Integer)listchgrow.get(i)).intValue();
/*     */       }
/*     */       
/* 102 */       SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
/* 103 */       changeraterule.calcBodyExchangeRates(chgrows);
/*     */       
/* 105 */       SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
/* 106 */       if (groupraterule.isGroupExchgRateChange("ccurrencyid")) {
/* 107 */         groupraterule.calcGroupExchangeRate(chgrows);
/*     */       }
/*     */       
/* 110 */       SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
/* 111 */       if (globalerate.isGlobalExchgRateChange("ccurrencyid")) {
/* 112 */         globalerate.calcGlobalExchangeRate(chgrows);
/*     */       }
/* 114 */       calculator.calculate(chgrows, "nexchangerate");
/*     */     }
/*     */   }
/*     */   
/*     */   public void beforeEdit(CardBodyBeforeEditEvent e) {
/* 119 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 120 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 121 */     String cmaterialid = keyValue.getBodyStringValue(e.getRow(), "cmaterialid");
/*     */     
/*     */ 
/* 124 */     String[] csendstockorgvids = null;
/* 125 */     if (PubAppTool.isNull(cmaterialid)) {
/* 126 */       e.setReturnValue(Boolean.valueOf(false));
/* 127 */       return;
/*     */     }
/*     */     try {
/* 130 */       String pk_org = keyValue.getHeadStringValue("pk_org");
/* 131 */       String[] csendstockorgids = SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org, cmaterialid);
/*     */       
/*     */ 
/* 134 */       csendstockorgvids = getSendStockOrgVIDs(csendstockorgids);
/*     */     }
/*     */     catch (BusinessException e1) {
/* 137 */       ExceptionUtils.wrappException(e1);
/*     */     }
/*     */     
/* 140 */     if (!VOChecker.isEmpty(csendstockorgvids)) {
/* 141 */       BillItem sendStockOrgItem = cardPanel.getBodyItem("csendstockorgvid");
/*     */       
/* 143 */       UIRefPane sendStockOrgRefPane = (UIRefPane)sendStockOrgItem.getComponent();
/*     */       
/* 145 */       AbstractRefModel model = sendStockOrgRefPane.getRefModel();
/* 146 */       model.setFilterPks(csendstockorgvids);
/*     */     }
/*     */   }
/*     */   
/*     */   private String[] getSendStockOrgVIDs(String[] csendstockorgids)
/*     */     throws BusinessException
/*     */   {
/* 153 */     String[] csendstockorgvids = null;
/* 154 */     if ((null == csendstockorgids) || (csendstockorgids.length == 0)) {
/* 155 */       return csendstockorgvids;
/*     */     }
/*     */     
/*     */ 
/* 159 */     Map<String, String> hmSendStockOrgIDs = OrgUnitPubService.getNewVIDSByOrgIDS(csendstockorgids);
/*     */     
/*     */ 
/* 162 */     if (hmSendStockOrgIDs != null) {
/* 163 */       List<String> alSendStockOrgVIDs = new ArrayList();
/* 164 */       for (String value : hmSendStockOrgIDs.values()) {
/* 165 */         if ((value != null) && (value.length() > 0)) {
/* 166 */           alSendStockOrgVIDs.add(value);
/*     */         }
/*     */       }
/* 169 */       if (alSendStockOrgVIDs.size() > 0) {
/* 170 */         csendstockorgvids = (String[])alSendStockOrgVIDs.toArray(new String[0]);
/*     */       }
/*     */     }
/* 173 */     return csendstockorgvids;
/*     */   }
/*     */   
/*     */   private boolean isCurrChange(String oldcurr, String newcurr) {
/* 177 */     String oldvalue = null == oldcurr ? "" : oldcurr;
/* 178 */     String newvalue = null == newcurr ? "" : newcurr;
/*     */     
/* 180 */     return !oldvalue.equals(newvalue);
/*     */   }
/*     */ }