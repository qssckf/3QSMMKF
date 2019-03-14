package nc.ui.so.qs.sc.maschine.handler;
/*     */ 
/*     */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*     */ import nc.ui.mmf.framework.handler.MMEventHandler;
/*     */ import nc.ui.mmf.framework.util.BillCardPanelUtil;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillData;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillScrollPane;
/*     */ import nc.ui.pubapp.AppUiContext;
/*     */ import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
/*     */ import nc.vo.pub.lang.UFDate;
/*     */ import nc.vo.pubapp.AppContext;
/*     */ import nc.vo.uif2.LoginContext;
/*     */ 
/*     */ public class MaschineCardBodyAfterRowEditHandler extends MMEventHandler implements nc.ui.pubapp.uif2app.event.IAppEventHandler<CardBodyAfterRowEditEvent>
/*     */ {
/*     */   public MaschineCardBodyAfterRowEditHandler() {}
/*     */   
/*     */   public void handleAppEvent(CardBodyAfterRowEditEvent e)
/*     */   {
/*  23 */     MMBaseHandler handler = getHandler(e.getRowEditType().toString());
/*  24 */     if (handler != null) {
/*  25 */       handler.afterEdit(e);
/*     */     }
/*  27 */     if (e.getRowEditType().equals(BodyRowEditType.ADDLINE)) {
/*  28 */       processAddLine(e);
/*     */     }
/*  30 */     else if (e.getRowEditType().equals(BodyRowEditType.INSERTLINE)) {
/*  31 */       processInsertLine(e);
/*     */     }
/*  33 */     else if (e.getRowEditType().equals(BodyRowEditType.DELLINE)) {
			  }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void processInsertLine(CardBodyAfterRowEditEvent e)
/*     */   {
/*  41 */     setCommonDefault(e);
///*  42 */     if ("prodinv_items".equals(e.getBillCardPanel().getBodyPanel().getTableCode())) {
///*  43 */       setProdinvDefault2(e);
///*     */     }
///*  45 */     if ("replace_items".equals(e.getBillCardPanel().getBodyPanel().getTableCode())) {
///*  46 */       setRepltimeDefault2(e);
///*     */     }
/*     */   }
/*     */   
/*     */   private void processAddLine(CardBodyAfterRowEditEvent e)
/*     */   {
/*  52 */     String tableCode = e.getBillCardPanel().getBodyPanel().getTableCode();
/*     */     
/*  54 */     setCommonDefault(e);
/*     */     
///*  56 */     if (!"wip_items".equals(tableCode))
///*     */     {
///*  59 */       if ("subst_items".equals(tableCode)) {
///*  60 */         setSubstDefault(e);
///*     */       }
///*  62 */       else if ("prodinv_items".equals(tableCode)) {
///*  63 */         setProdinvDefault(e);
///*     */       }
///*  65 */       else if ("replace_items".equals(tableCode)) {
///*  66 */         setRepltimeDefault(e);
///*     */       }
///*     */     }

			       if ("cust".equals(tableCode)) {
			         setSubstDefault(e);
			       }
//			       else if ("prodinv_items".equals(tableCode)) {
//			         setProdinvDefault(e);
//			       }
//			       else if ("replace_items".equals(tableCode)) {
//			         setRepltimeDefault(e);
//			       }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void setProdinvDefault2(CardBodyAfterRowEditEvent e)
///*     */   {
///*  77 */     int row = e.getRows()[0];
///*  78 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///*  79 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
///*  80 */     utils.setBodyValue(AppUiContext.getInstance().getBusiDate(), row, "dbegindate", tabCode);
///*  81 */     utils.setBodyValue(nc.vo.pd.pd0404.message.MMPDLangConstPd0404.MAXTIME, row, "denddate", tabCode);
///*     */     
///*  83 */     utils.setBodyValue(e.getBillCardPanel().getHeadItem("ftimeunit").getValueObject(), row, "ftimeunit", tabCode);
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void setRepltimeDefault2(CardBodyAfterRowEditEvent e)
///*     */   {
///*  93 */     int row = e.getRows()[0];
///*  94 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///*  95 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
///*     */     
///*  97 */     utils.setBodyValue(e.getBillCardPanel().getHeadItem("ftimeunit").getValueObject(), row, "ftimeunit", tabCode);
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setCommonDefault(CardBodyAfterRowEditEvent e)
/*     */   {
/* 108 */     int row = e.getRows()[0];
/* 109 */     String pk_org = (String)e.getBillCardPanel().getBillData().getHeadItem("pk_org").getValueObject();
/* 110 */     String pk_org_v = (String)e.getBillCardPanel().getBillData().getHeadItem("pk_org_v").getValueObject();
/*     */     
/* 112 */     String pk_group = e.getContext().getPk_group();
/*     */     
/* 114 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
/*     */     
/* 116 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
/*     */     
/* 118 */     utils.setBodyValue(pk_group, row, "pk_group", tabCode);
/*     */     
/* 120 */     utils.setBodyValue(pk_org, row, "pk_org", tabCode);
/*     */     
/* 122 */     utils.setBodyValue(pk_org_v, row, "pk_org_v", tabCode);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setSubstDefault(CardBodyAfterRowEditEvent e)
/*     */   {
///* 133 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///* 134 */     int row = e.getRows()[0];
///* 135 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
///* 136 */     utils.setBodyValue(Integer.valueOf(row + 1), e.getRows()[0], "memo", tabCode);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setWIPDefault(CardBodyAfterRowEditEvent e)
/*     */   {
/* 148 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
/* 149 */     int row = e.getRows()[0];
/* 150 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
/* 151 */     utils.setBodyValue(String.valueOf(row + 1), row, "vrowno", tabCode);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void setProdinvDefault(CardBodyAfterRowEditEvent e)
///*     */   {
///* 161 */     int row = e.getRows()[0];
///* 162 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///* 163 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
///*     */     
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///* 170 */     utils.setBodyValue(e.getBillCardPanel().getHeadItem("ftimeunit").getValueObject(), row, "ftimeunit", tabCode);
///*     */     
///*     */ 
///*     */ 
///* 174 */     UFDate begindate = new UFDate(AppContext.getInstance().getBusiDate().toLocalString(), true);
///*     */     
///* 176 */     UFDate endDate = new UFDate("2999-12-31 23:59:59", false);
///*     */     
///* 178 */     utils.setBodyValue(begindate, row, "dbegindate", tabCode);
///*     */     
///* 180 */     utils.setBodyValue(endDate, row, "denddate", tabCode);
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void setRepltimeDefault(CardBodyAfterRowEditEvent e)
///*     */   {
///* 189 */     int row = e.getRows()[0];
///* 190 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///* 191 */     BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
///*     */     
///* 193 */     utils.setBodyValue(e.getBillCardPanel().getHeadItem("ftimeunit").getValueObject(), row, "ftimeunit", tabCode);
///*     */   }
/*     */   
/*     */ 
/*     */   public void initMap()
/*     */   {
/* 199 */     putHandler(BodyRowEditType.ADDLINE.toString(), MaschineAddEventHandler.class);
/*     */   }
/*     */ }
