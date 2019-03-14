package nc.ui.so.shipmentsinfo.handler;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import nc.ui.ml.NCLangRes;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
///*    */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*    */ import nc.ui.so.pub.findprice.FindSalePrice;
/*    */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*    */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*    */ import nc.vo.pubapp.pattern.pub.MathTool;
/*    */ import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ 
/*    */ public class NumEditHandler{
/*    */   public NumEditHandler() {}
/*    */   
/*    */   public void afterEdit(CardBodyAfterEditEvent e)
/*    */   {
/* 26 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/* 27 */     BillCardPanel cardPanel = e.getBillCardPanel();
/* 28 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/* 29 */     ShipmentsCalculator calcultor = new ShipmentsCalculator(cardPanel);
/* 30 */     Integer fstatusflag = keyValue.getHeadIntegerValue("fstatusflag");
/* 31 */     List<Integer> listchgrow = new ArrayList();
/*    */     
/* 33 */     List<String> listerrorrow = new ArrayList();
/* 34 */     if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
/* 35 */       for (int i = 0; i < rows.length; i++) {
/* 36 */         UFDouble new_num = keyValue.getBodyUFDoubleValue(rows[0], "nnum");
/*    */         
/* 38 */         UFDouble use_num = keyValue.getBodyUFDoubleValue(rows[0], "narrnum");
/*    */         
/* 40 */         if (MathTool.lessThan(new_num, use_num))
/*    */         {
/* 42 */           calcultor.calculate(rows, "nastnum");
/* 43 */           String rowno = keyValue.getBodyStringValue(rows[i], "crowno");
/*    */           
/* 45 */           listerrorrow.add(rowno);
/*    */         }
/*    */         else {
/* 48 */           listchgrow.add(Integer.valueOf(rows[i]));
/*    */         }
/*    */         
/*    */       }
/*    */     } else {
/* 53 */       for (int i = 0; i < rows.length; i++) {
/* 54 */         listchgrow.add(Integer.valueOf(rows[i]));
/*    */       }
/*    */     }
/*    */     
/* 58 */     int chgsize = listchgrow.size();
/* 59 */     if ((chgsize == 0) && (listerrorrow.size() == 0)) {
/* 60 */       return;
/*    */     }
/*    */     
/* 63 */     int[] chgrows = new int[chgsize];
/* 64 */     for (int i = 0; i < chgsize; i++) {
/* 65 */       chgrows[i] = ((Integer)listchgrow.get(i)).intValue();
/*    */     }
/* 67 */     calcultor.calculate(chgrows, "nnum");
/*    */     
/* 69 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/* 70 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/* 71 */     findPrice.findPriceAfterEdit(chgsize, "nqtunitnum");
/*    */     
/* 73 */     if (listerrorrow.size() > 0) {
/* 74 */       StringBuilder errMsg = new StringBuilder(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000000"));
/*    */       
/*    */ 
/*    */ 
/* 78 */       for (String field : listerrorrow) {
/* 79 */         errMsg.append("[").append(field).append("]").append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000001"));
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 88 */       errMsg.deleteCharAt(errMsg.length() - 1);
/* 89 */       errMsg.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000002"));
/*    */       
/* 91 */       ExceptionUtils.wrappBusinessException(errMsg.toString());
/*    */     }
/*    */   }
/*    */ }