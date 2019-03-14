package nc.ui.so.shipmentsinfo.handler;
/*     */ 
/*     */ import java.util.Map;
/*     */ import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
/*     */ import nc.ui.ic.batchcode.ref.BatchRefDlg;
/*     */ import nc.ui.ic.batchcode.ref.BatchRefPane;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
///*     */ import nc.ui.so.m38.billui.view.PreOrderEditor;
		    import nc.ui.so.shipmentsinfo.billui.view.ShipmentsInfoEditor;
//		  import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ import nc.ui.uif2.model.AbstractAppModel;
/*     */ import nc.vo.bd.material.stock.MaterialStockVO;
/*     */ import nc.vo.ic.batch.BatchRefViewVO;
/*     */ import nc.vo.ic.batchcode.BatchDlgParam;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
/*     */ import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ import nc.vo.uif2.LoginContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BatchCodeEditHandler
/*     */ {
/*     */   private ShipmentsInfoEditor editor;
/*     */   
/*     */   public BatchCodeEditHandler() {}
/*     */   
/*     */   public void afterEdit(CardBodyAfterEditEvent e)
/*     */   {
/*  37 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  38 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*     */     
/*  40 */     String editvalue = (String)e.getValue();
/*  41 */     int row = e.getRow();
/*     */     
/*  43 */     BillItem batchcodeitem = cardPanel.getBodyItem("vbatchcode");
/*  44 */     BatchRefPane batchref = (BatchRefPane)batchcodeitem.getComponent();
/*  45 */     BatchRefDlg refdlg = batchref.getBatchRefDlg();
/*     */     
/*  47 */     String cmaterialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
/*     */     
/*  49 */     BatchRefViewVO batchvo = refdlg.getRefVO(cmaterialvid, editvalue);
/*  50 */     if ((PubAppTool.isNull(editvalue)) || (null == batchvo)) {
/*  51 */       keyValue.setBodyValue(row, "vbatchcode", null);
/*  52 */       keyValue.setBodyValue(row, "PK_BATCHCODE", null);
/*     */     }
/*     */     else {
/*  55 */       keyValue.setBodyValue(row, "vbatchcode", batchvo.getAttributeValue("vbatchcode"));
/*     */       
/*  57 */       keyValue.setBodyValue(row, "PK_BATCHCODE", batchvo.getAttributeValue("pk_batchcode"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void beforeEdit(CardBodyBeforeEditEvent e)
/*     */   {
/*  63 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  64 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*     */     
/*  66 */     int row = e.getRow();
/*  67 */     String materialvid = keyValue.getBodyStringValue(row, "cmaterialvid");
/*     */     
/*  69 */     String sendstock = keyValue.getBodyStringValue(row, "csendstockorgid");
/*     */     
/*  71 */     if ((PubAppTool.isNull(materialvid)) || (PubAppTool.isNull(sendstock))) {
/*  72 */       e.setReturnValue(Boolean.valueOf(false));
/*  73 */       return;
/*     */     }
/*  75 */     String[] wholeflag = { "wholemanaflag" };
/*     */     
/*     */ 
/*  78 */     Map<String, MaterialStockVO> map = MaterialPubService.queryMaterialStockInfo(new String[] { materialvid }, sendstock, wholeflag);
/*     */     
/*     */ 
/*     */ 
/*  82 */     MaterialStockVO marstockvo = (MaterialStockVO)map.get(materialvid);
/*  83 */     UFBoolean flag = null;
/*  84 */     if (null != marstockvo) {
/*  85 */       flag = marstockvo.getWholemanaflag();
/*     */     }
/*  87 */     if ((null == flag) || (!flag.booleanValue())) {
/*  88 */       e.setReturnValue(Boolean.valueOf(false));
/*  89 */       return;
/*     */     }
/*  91 */     String sendstordoc = keyValue.getBodyStringValue(row, "csendstordocid");
/*     */     
/*  93 */     String materialoid = keyValue.getBodyStringValue(row, "cmaterialid");
/*     */     
/*  95 */     String astunit = keyValue.getBodyStringValue(row, "castunitid");
/*  96 */     String vbatchcode = keyValue.getBodyStringValue(row, "vbatchcode");
/*     */     
/*     */ 
/*  99 */     BillItem batchcodeitem = cardPanel.getBodyItem("vbatchcode");
/* 100 */     BatchRefPane batchref = (BatchRefPane)batchcodeitem.getComponent();
/* 101 */     BatchDlgParam param = new BatchDlgParam();
/* 102 */     param.setPk_calbody(sendstock);
/* 103 */     param.setCwarehouseid(sendstordoc);
/* 104 */     param.setCmaterialvid(materialvid);
/* 105 */     param.setCmaterialoid(materialoid);
/* 106 */     param.setVbatchcode(vbatchcode);
/* 107 */     param.setCastUnitID(astunit);
/* 108 */     LoginContext context = getEditor().getModel().getContext();
/* 109 */     param.setLoginContext(context);
/* 110 */     batchref.setParam(param);
/* 111 */     batchref.setMultiSelectedEnabled(false);
/*     */   }
/*     */   
/*     */   public ShipmentsInfoEditor getEditor() {
/* 115 */     return this.editor;
/*     */   }
/*     */   
/*     */   public void setEditor(ShipmentsInfoEditor editor) {
/* 119 */     this.editor = editor;
/*     */   }
/*     */ }