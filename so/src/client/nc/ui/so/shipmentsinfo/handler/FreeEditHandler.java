package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FreeEditHandler
/*    */ {
/*    */   public FreeEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 29 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 30 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 31 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 32 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/* 33 */     findprice.findPriceAfterEdit(rows, e.getKey());
/*    */     
/* 35 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 36 */     SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
/* 37 */     socustmar.setCustMaterial(rows);
/*    */   }
/*    */ }