package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.so.m30.pub.SaleOrderCalculator;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
///*    */ import nc.vo.so.m38.keyrela.PreOrderKeyrela;
/*    */ import nc.vo.so.pub.keyvalue.IKeyRela;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
/*    */ import nc.vo.so.pub.rule.SOBuysellTriaRule;
/*    */ import nc.vo.so.pub.rule.SOCalConditionRule;
/*    */ import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.qs.sc.FQ01.keyrela.ShipmentsKeyrela;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ //收货客户
/*    */ public class ReceiveCustEditHandler
/*    */ {
/*    */   public ReceiveCustEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 30 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 31 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 32 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 34 */     SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
/*    */     
/*    */ 
/* 37 */     IKeyRela keyRela = new ShipmentsKeyrela();
/* 38 */     ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue, keyRela);
/*    */     
/* 40 */     defaddrule.setCustDefaultAddress(rows);
/*    */     
/* 42 */     SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
/* 43 */     conutryinforule.setReceiveCountry(rows);
/*    */     
/* 45 */     SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
/* 46 */     buyflgrule.setBuysellAndTriaFlag(rows);
/* 47 */     int[] buysellrows = buyflgrule.getBuysellChgRow();
/* 48 */     calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
/*    */     
/* 50 */     SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
/* 51 */     taxInfo.setTaxInfoByBodyPos(rows);
/* 52 */     int[] taxchgrows = taxInfo.getTaxChangeRows();
/*    */     
/* 54 */     calculator.calculate(taxchgrows, "ntaxrate");
/*    */   }
/*    */ }