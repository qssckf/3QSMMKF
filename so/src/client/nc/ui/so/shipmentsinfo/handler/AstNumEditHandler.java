package nc.ui.so.shipmentsinfo.handler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import nc.ui.ml.NCLangRes;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*     */ import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsCalculator;
			import nc.ui.so.qs.sc.shipments.billui.pub.ShipmentsFindPriceConfig;
///*     */ import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
/*     */ import nc.ui.so.pub.findprice.FindSalePrice;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.ui.so.pub.util.BodyEditEventUtil;
/*     */ import nc.vo.pub.lang.UFDouble;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.MathTool;
/*     */ import nc.vo.so.pub.enumeration.BillStatus;
			import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ 
/*     */ 
/*     */ public class AstNumEditHandler {
/*     */   public AstNumEditHandler() {}
/*     */   
/*     */   public void afterEdit(CardBodyAfterEditEvent e)
/*     */   {
/*  26 */     int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
/*     */     
/*  28 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  29 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*  30 */     Integer fstatusflag = keyValue.getHeadIntegerValue("fstatusflag");
/*     */     
/*  32 */     UFDouble[] old_nums = new UFDouble[rows.length];
/*  33 */     for (int i = 0; i < rows.length; i++) {
/*  34 */       old_nums[i] = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */     }
/*     */     
/*  37 */     ShipmentsCalculator calcultor = new ShipmentsCalculator(cardPanel);
/*  38 */     calcultor.calculate(rows, "nastnum");
/*     */     
/*  40 */     List<Integer> listchgrow = new ArrayList();
/*     */     
/*  42 */     List<String> listerrorrow = new ArrayList();
/*  43 */     if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
/*  44 */       for (int i = 0; i < rows.length; i++) {
/*  45 */         UFDouble new_num = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */         
/*  47 */         UFDouble use_num = keyValue.getBodyUFDoubleValue(rows[i], "narrnum");
/*     */         
/*  49 */         if (MathTool.lessThan(new_num, use_num)) {
/*  50 */           keyValue.setBodyValue(rows[i], "nnum", old_nums[i]);
/*  51 */           calcultor.calculate(rows, "nnum");
/*  52 */           String rowno = keyValue.getBodyStringValue(rows[i], "crowno");
/*     */           
/*  54 */           listerrorrow.add(rowno);
/*     */ 
/*     */         }
/*  57 */         else if (!MathTool.equals(old_nums[i], new_num)) {
/*  58 */           listchgrow.add(Integer.valueOf(rows[i]));
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */     } else {
/*  64 */       for (int i = 0; i < rows.length; i++) {
/*  65 */         UFDouble new_num = keyValue.getBodyUFDoubleValue(rows[i], "nnum");
/*     */         
/*  67 */         if (!MathTool.equals(old_nums[i], new_num)) {
/*  68 */           listchgrow.add(Integer.valueOf(rows[i]));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  74 */     int chgsize = listchgrow.size();
/*  75 */     if ((chgsize == 0) && (listerrorrow.size() == 0)) {
/*  76 */       return;
/*     */     }
/*     */     
/*  79 */     int[] chgrows = new int[chgsize];
/*  80 */     for (int i = 0; i < chgsize; i++) {
/*  81 */       chgrows[i] = ((Integer)listchgrow.get(i)).intValue();
/*     */     }
/*  83 */     ShipmentsFindPriceConfig config = new ShipmentsFindPriceConfig(cardPanel);
/*  84 */     FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
/*  85 */     findPrice.findPriceAfterEdit(chgrows, "nqtunitnum");
/*  86 */     if (listerrorrow.size() > 0) {
/*  87 */       StringBuilder errMsg = new StringBuilder(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000000"));
/*     */       
/*     */ 
/*     */ 
/*  91 */       for (String field : listerrorrow) {
/*  92 */         errMsg.append("[").append(field).append("]").append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000001"));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 101 */       errMsg.deleteCharAt(errMsg.length() - 1);
/* 102 */       errMsg.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-000002"));
/*     */       
/* 104 */       ExceptionUtils.wrappBusinessException(errMsg.toString());
/*     */     }
/*     */   }
/*     */ }