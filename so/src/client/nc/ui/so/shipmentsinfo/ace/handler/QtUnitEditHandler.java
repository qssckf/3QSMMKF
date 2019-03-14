package nc.ui.so.shipmentsinfo.ace.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*    */ import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
/*    */ 
/*    */ public class QtUnitEditHandler {
/*    */   public QtUnitEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e) {
/* 22 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 23 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 24 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/* 26 */     SOUnitChangeRateRule raterule = new SOUnitChangeRateRule(keyValue);
/* 27 */     for (int row : rows) {
/* 28 */       raterule.calcQtChangeRate(row);
/*    */     }
/*    */     
/* 31 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/* 32 */     calculator.calculate(rows, "vqtunitrate");
/*    */     
/* 34 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 35 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/* 36 */     findprice.findPriceAfterEdit(rows, "cqtunitid");
/*    */   }
/*    */   
/*    */ 
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 42 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 43 */     IKeyValue keyvalue = new CardKeyValue(cardPanel);
/* 44 */     String material = keyvalue.getBodyStringValue(e.getRow(), "cmaterialvid");
/*    */     
/* 46 */     if (!nc.vo.pubapp.pattern.pub.PubAppTool.isNull(material))
/*    */     {
/* 48 */       BillItem qtunititem = cardPanel.getBodyItem("cqtunitid");
/* 49 */       FilterMeasdocRefUtils qtunitFilter = new FilterMeasdocRefUtils(qtunititem);
/*    */       
/* 51 */       qtunitFilter.setMaterialPk(material);
/* 52 */       e.setReturnValue(Boolean.TRUE);
/*    */     }
/*    */     else {
/* 55 */       e.setReturnValue(Boolean.FALSE);
/*    */     }
/*    */   }
/*    */ }