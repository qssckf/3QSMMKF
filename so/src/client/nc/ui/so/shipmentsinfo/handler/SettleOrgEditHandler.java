package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*    */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*    */ import nc.vo.so.pub.rule.SOCountryInfoRule;
/*    */ import nc.vo.so.pub.rule.SOCurrencyRule;
/*    */ import nc.vo.so.pub.rule.SOExchangeRateRule;
/*    */ import nc.vo.so.pub.rule.SOGlobalExchangeRate;
/*    */ import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
/*    */ 
/*    */ 
/*    */ public class SettleOrgEditHandler
/*    */ {
/*    */   public SettleOrgEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 27 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/*    */     
/* 29 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 30 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 32 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/*    */     
/*    */ 
/* 35 */     SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
/* 36 */     conutryinforule.setTaxCountry(rows);
/*    */     
/* 38 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/* 39 */     buyflgrule.setBuysellAndTriaFlag(rows);
/*    */     
/* 41 */     int[] buysellrows = buyflgrule.getBuysellChgRow();
/* 42 */     calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
/*    */     
/* 44 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/* 45 */     taxInfo.setTaxInfoByBodyPos(rows);
/* 46 */     int[] taxchgrows = taxInfo.getTaxChangeRows();
/*    */     
/* 48 */     calculator.calculate(taxchgrows, "ntaxrate");
/*    */     
/* 50 */     int ilength = rows.length;
/* 51 */     String[] oldcurrs = new String[ilength];
/* 52 */     for (int i = 0; i < ilength; i++) {
/* 53 */       oldcurrs[i] = keyValue.getBodyStringValue(rows[i], "ccurrencyid");
/*    */     }
/*    */     
/*    */ 
/* 57 */     SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
/* 58 */     currencyrule.setCurrency(rows);
/*    */     
/* 60 */     List<Integer> listchgrow = new ArrayList();
/* 61 */     for (int i = 0; i < ilength; i++) {
/* 62 */       String newcurr = keyValue.getBodyStringValue(rows[i], "ccurrencyid");
/*    */       
/* 64 */       if (isCurrChange(oldcurrs[i], newcurr)) {
/* 65 */         listchgrow.add(Integer.valueOf(rows[i]));
/*    */       }
/*    */     }
/* 68 */     int chgsize = listchgrow.size();
/* 69 */     if (chgsize > 0) {
/* 70 */       int[] chgrows = new int[chgsize];
/* 71 */       for (int i = 0; i < chgsize; i++) {
/* 72 */         chgrows[i] = ((Integer)listchgrow.get(i)).intValue();
/*    */       }
/*    */       
/* 75 */       SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
/* 76 */       changeraterule.calcBodyExchangeRates(chgrows);
/*    */       
/* 78 */       SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
/* 79 */       if (groupraterule.isGroupExchgRateChange("ccurrencyid")) {
/* 80 */         groupraterule.calcGroupExchangeRate(chgrows);
/*    */       }
/*    */       
/* 83 */       SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
/* 84 */       if (globalerate.isGlobalExchgRateChange("ccurrencyid")) {
/* 85 */         globalerate.calcGlobalExchangeRate(chgrows);
/*    */       }
/* 87 */       calculator.calculate(chgrows, "nexchangerate");
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isCurrChange(String oldcurr, String newcurr) {
/* 92 */     String oldvalue = null == oldcurr ? "" : oldcurr;
/* 93 */     String newvalue = null == newcurr ? "" : newcurr;
/*    */     
/* 95 */     return !oldvalue.equals(newvalue);
/*    */   }
/*    */ }