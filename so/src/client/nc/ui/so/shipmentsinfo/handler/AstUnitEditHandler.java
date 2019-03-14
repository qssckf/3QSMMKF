package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
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
/*    */ public class AstUnitEditHandler
/*    */ {
/*    */   public AstUnitEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 21 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 22 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 23 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*    */     
/*    */ 
/* 26 */     SOUnitChangeRateRule raterule = new SOUnitChangeRateRule(keyValue);
/* 27 */     for (int row : rows) {
/* 28 */       raterule.calcAstChangeRate(row);
/* 29 */       raterule.calcQtChangeRate(row);
/*    */     }
/*    */     
/* 32 */     ShipmentsCalculator calculator = new ShipmentsCalculator(cardPanel);
/* 33 */     calculator.calculate(rows, "vchangerate");
/*    */     
/* 35 */     calculator.calculate(rows, "vqtunitrate");
/*    */     
/* 37 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 38 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/* 39 */     findprice.findPriceAfterEdit(rows, "cqtunitid");
/*    */   }
/*    */   
/*    */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*    */   {
/* 44 */     int row = e.getRow();
/* 45 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 46 */     IKeyValue keyvalue = new CardKeyValue(cardPanel);
/* 47 */     String material = keyvalue.getBodyStringValue(row, "cmaterialvid");
/*    */     
/* 49 */     if (!nc.vo.pubapp.pattern.pub.PubAppTool.isNull(material))
/*    */     {
/* 51 */       nc.ui.pub.bill.BillItem astunititem = cardPanel.getBodyItem("castunitid");
/* 52 */       FilterMeasdocRefUtils astunitFilter = new FilterMeasdocRefUtils(astunititem);
/*    */       
/* 54 */       astunitFilter.setMaterialPk(material);
/* 55 */       e.setReturnValue(Boolean.TRUE);
/*    */     }
/*    */     else {
/* 58 */       e.setReturnValue(Boolean.FALSE);
/*    */     }
/*    */   }
/*    */ }