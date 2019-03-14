package nc.ui.so.qs.sc.maschine.handler;
/*     */ 
/*     */ import nc.ui.mmf.framework.handler.MMBaseHandler;
/*     */ import nc.ui.mmf.framework.handler.MMEventHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCbomversionHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCmaterialvidHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCmatericlassidHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCunitidHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyNastnumHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_repltime.PDWkCardBodyCaftermatrelatshowHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_repltime.PDWkCardBodyCprematrelatshowHandler;
///*     */ import nc.ui.pd.pd0404.handler.tab_wip.PDWkCardBodyCstordocidHandler;
///*     */ import nc.ui.pd.pd0404.scale.PDWkDecimalCastunitidListener;
///*     */ import nc.ui.pd.pd0404.view.PDWkBillForm;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
		import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
/*     */ 
/*     */ public class MaschineCardBodyBeforeEditHandler extends MMEventHandler implements IAppEventHandler<CardBodyBeforeEditEvent>
/*     */ {
/*     */   private MaschineBillForm editor;
/*     */   
/*     */   public MaschineCardBodyBeforeEditHandler() {}
/*     */   
/*     */   public void initMap()
/*     */   {
///*  28 */     putHandler("wip_itemscstordocid", PDWkCardBodyCstordocidHandler.class);
///*     */     
///*  30 */     putHandler("prodinv_itemscmaterialvid", PDWkCardBodyCmaterialvidHandler.class);
///*     */     
///*  32 */     putHandler("prodinv_itemscastunitid", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCastunitidHandler.class);
///*     */     
///*  34 */     putHandler("prodinv_itemscunitid", PDWkCardBodyCunitidHandler.class);
///*     */     
///*  36 */     putHandler("prodinv_itemscbomversion", PDWkCardBodyCbomversionHandler.class);
///*     */     
///*  38 */     putHandler("replace_itemscprematrelatshow", PDWkCardBodyCprematrelatshowHandler.class);
///*     */     
///*  40 */     putHandler("replace_itemscaftermatrelatshow", PDWkCardBodyCaftermatrelatshowHandler.class);
///*     */     
///*  42 */     putHandler("prodinv_itemsvchangerate", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkProBodyVChangeRateHandler.class);
///*     */     
///*  44 */     putHandler("prodinv_itemsnastnum", PDWkCardBodyNastnumHandler.class);
///*     */     
///*  46 */     putHandler("prodinv_itemscmaterialclassid", PDWkCardBodyCmatericlassidHandler.class);
///*     */     
///*  48 */     putHandler("prodinv_itemsccustomerid", nc.ui.pd.pd0404.handler.tab_prodinv.PDWkCardBodyCcustomeridHandler.class);
/*     */   }
/*     */   
/*     */ 
/*     */   public void handleAppEvent(CardBodyBeforeEditEvent e)
/*     */   {
///*  54 */     String tabCode = e.getTableCode();
///*  55 */     String key = e.getKey();
///*  56 */     MMBaseHandler handler = getHandler(tabCode + key);
///*  57 */     if ((handler instanceof PDWkCardBodyCstordocidHandler)) {
///*  58 */       ((PDWkCardBodyCstordocidHandler)handler).setEditor(getEditor());
///*     */     }
///*  60 */     if ((handler instanceof PDWkCardBodyCmaterialvidHandler)) {
///*  61 */       ((PDWkCardBodyCmaterialvidHandler)handler).setEditor(getEditor());
///*     */     }
///*  63 */     if ((handler instanceof PDWkCardBodyCbomversionHandler)) {
///*  64 */       ((PDWkCardBodyCbomversionHandler)handler).setEditor(getEditor());
///*     */     }
///*  66 */     if ((handler instanceof PDWkCardBodyNastnumHandler)) {
///*  67 */       ((PDWkCardBodyNastnumHandler)handler).setEditor(getEditor());
///*     */     }
///*  69 */     if ((handler instanceof PDWkCardBodyCprematrelatshowHandler)) {
///*  70 */       ((PDWkCardBodyCprematrelatshowHandler)handler).setEditor(getEditor());
///*     */     }
///*  72 */     if ((handler instanceof PDWkCardBodyCaftermatrelatshowHandler)) {
///*  73 */       ((PDWkCardBodyCaftermatrelatshowHandler)handler).setEditor(getEditor());
///*     */     }
///*  75 */     if (handler != null) {
///*  76 */       handler.beforeEdit(e);
///*     */     }
///*  78 */     if (e.getKey().equals("nastnum")) {
///*  79 */       setDecimalDgit(e);
///*     */     }
///*  81 */     if (e.getKey().equals("nnum"))
///*  82 */       setDecimalDgit(e);
/*     */   }
/*     */   
///*     */   private void setDecimalDgit(CardBodyBeforeEditEvent e) {
///*  86 */     Object value = e.getBillCardPanel().getBodyValueAt(e.getRow(), "castunitid");
///*     */     
///*  88 */     Object value2 = e.getBillCardPanel().getBodyValueAt(e.getRow(), "cunitid");
///*     */     
///*  90 */     BillItem unitItem = e.getBillCardPanel().getBodyItem("nastnum");
///*     */     
///*  92 */     BillItem unitItem2 = e.getBillCardPanel().getBodyItem("nnum");
///*     */     
///*  94 */     if (value == null) {
///*  95 */       unitItem.setDecimalDigits(8);
///*  96 */       unitItem2.setDecimalDigits(8);
///*     */     } else {
///*  98 */       int dgit = PDWkDecimalCastunitidListener.qryDicimalDgit((String)value);
///*  99 */       int dgit2 = nc.ui.pd.pd0404.scale.PDWkDecimalCastidListener.qryDicimalDgit((String)value2);
///* 100 */       unitItem.setDecimalDigits(dgit);
///* 101 */       unitItem2.setDecimalDigits(dgit2);
///*     */     }
///*     */   }
/*     */   
/*     */   public MaschineBillForm getEditor()
/*     */   {
/* 107 */     return this.editor;
/*     */   }
/*     */   
/*     */   public void setEditor(MaschineBillForm editor) {
/* 111 */     this.editor = editor;
/*     */   }
/*     */ }

