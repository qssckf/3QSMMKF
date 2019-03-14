package nc.ui.so.shipmentsinfo.handler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import nc.ui.ml.NCLangRes;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderCalculator;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*     */ import nc.ui.so.pub.findprice.FindSalePrice;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
/*     */ import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QtUnitNumEditHandler {
/*     */   public QtUnitNumEditHandler() {}
/*     */   
/*     */   public void afterEdit(CardBodyAfterEditEvent e)
/*     */   {
/*  33 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/*  34 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  35 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*  36 */     Integer fstatusflag = keyValue.getHeadIntegerValue("fstatusflag");
/*     */     
/*  38 */     UFDouble[] old_nums = new UFDouble[rows.length];
/*  39 */     for (int i = 0; i < rows.length; i++) {
/*  40 */       old_nums[i] = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */     }
/*     */     
/*  43 */     ShipmentsCalculator calcultor = new ShipmentsCalculator(cardPanel);
/*  44 */     calcultor.calculate(rows, "nqtunitnum");
/*     */     
/*  46 */     List<Integer> listchgrow = new ArrayList();
/*     */     
/*     */ 
/*  49 */     List<String> listerrorrow = new ArrayList();
/*  50 */     if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
/*  51 */       for (int i = 0; i < rows.length; i++) {
/*  52 */         UFDouble new_num = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */         
/*  54 */         UFDouble use_num = keyValue.getBodyUFDoubleValue(rows[i], "narrnum");
/*     */         
/*  56 */         if (MathTool.lessThan(new_num, use_num)) {
/*  57 */           keyValue.setBodyValue(rows[i], "nnum", old_nums[i]);
/*  58 */           calcultor.calculate(rows, "nnum");
/*  59 */           String rowno = keyValue.getBodyStringValue(rows[i], "crowno");
/*     */           
/*  61 */           listerrorrow.add(rowno);
/*     */ 
/*     */         }
/*  64 */         else if (!MathTool.equals(old_nums[i], new_num)) {
/*  65 */           listchgrow.add(Integer.valueOf(rows[i]));
/*     */         }
/*     */         
/*     */       }
/*     */     } else {
/*  70 */       for (int i = 0; i < rows.length; i++) {
/*  71 */         UFDouble new_num = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */         
/*  73 */         if (!MathTool.equals(old_nums[i], new_num)) {
/*  74 */           listchgrow.add(Integer.valueOf(rows[i]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  80 */     int chgsize = listchgrow.size();
/*  81 */     if ((chgsize == 0) && (listerrorrow.size() == 0)) {
/*  82 */       return;
/*     */     }
/*     */     
/*  85 */     int[] chgrows = new int[chgsize];
/*  86 */     for (int i = 0; i < chgsize; i++) {
/*  87 */       chgrows[i] = ((Integer)listchgrow.get(i)).intValue();
/*     */     }
/*  89 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/*  90 */     FindSalePrice findprice = new FindSalePrice(cardPanel, config);
/*  91 */     findprice.findPriceAfterEdit(rows, "nqtunitnum");
/*     */     
/*  93 */     if (listerrorrow.size() > 0) {
/*  94 */       StringBuilder errMsg = new StringBuilder(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000000"));
/*     */       
/*     */ 
/*     */ 
/*  98 */       for (String field : listerrorrow) {
/*  99 */         errMsg.append("[").append(field).append("]").append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000001"));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 108 */       errMsg.deleteCharAt(errMsg.length() - 1);
/* 109 */       errMsg.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000002"));
/*     */       
/* 111 */       ExceptionUtils.wrappBusinessException(errMsg.toString());
/*     */     }
/*     */   }
/*     */ }