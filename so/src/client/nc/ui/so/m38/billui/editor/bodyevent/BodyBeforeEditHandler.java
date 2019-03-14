/*     */ package nc.ui.so.m38.billui.editor.bodyevent;
/*     */ 
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
/*     */ import nc.ui.so.m38.billui.view.PreOrderEditor;
/*     */ import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.m38.view.BomInfoPanel;
/*     */ import nc.vo.ml.AbstractNCLangRes;
/*     */ import nc.vo.ml.NCLangRes4VoTransl;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BodyBeforeEditHandler
/*     */   implements IAppEventHandler<CardBodyBeforeEditEvent>
/*     */ {
/*     */   private PreOrderEditor editor;
/*     */   
/*     */   public BodyBeforeEditHandler() {}
/*     */   
/*     */   public PreOrderEditor getEditor()
/*     */   {
/*  33 */     return this.editor;
/*     */   }
/*     */   
/*     */ 
/*     */   public void handleAppEvent(CardBodyBeforeEditEvent e)
/*     */   {
/*  39 */     e.setReturnValue(Boolean.valueOf(true));
/*  40 */     String editKey = e.getKey();
/*     */     
/*  42 */     BillCardPanel cardPanel = e.getBillCardPanel();
/*  43 */     IKeyValue keyValue = new CardKeyValue(cardPanel);
/*  44 */     String trantypeid = keyValue.getHeadStringValue("ctrantypeid");
/*  45 */     if (PubAppTool.isNull(trantypeid)) {
/*  46 */       ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0012"));
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*  51 */     else if ("cpriceitemid".equals(editKey)) {
/*  52 */       PriceItemEditHandler handler = new PriceItemEditHandler();
/*  53 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  56 */     else if ("ccustmaterialid".equals(editKey)) {
/*  57 */       CustMaterialEditHandler handler = new CustMaterialEditHandler();
/*  58 */       handler.beforeEdit(e);
/*     */     }
/*     */     
/*  61 */     if ("cmaterialvid".equals(editKey)) {
/*  62 */       MaterialEditHandler handler = new MaterialEditHandler();
/*  63 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  66 */     else if ("castunitid".equals(editKey)) {
/*  67 */       AstUnitEditHandler handler = new AstUnitEditHandler();
/*  68 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  71 */     else if ("vchangerate".equals(editKey)) {
/*  72 */       ChangeRateEditHandler handler = new ChangeRateEditHandler();
/*  73 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  76 */     else if ("cqtunitid".equals(editKey)) {
/*  77 */       QtUnitEditHandler handler = new QtUnitEditHandler();
/*  78 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  81 */     else if ("vqtunitrate".equals(editKey)) {
/*  82 */       QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
/*  83 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  86 */     else if ("blargessflag".equals(editKey)) {
/*  87 */       LargessFlagEditHandler handler = new LargessFlagEditHandler();
/*  88 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  91 */     else if ("nitemdiscountrate".equals(editKey)) {
/*  92 */       ItemDisRateEditHandler handler = new ItemDisRateEditHandler();
/*  93 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/*  96 */     else if ("csendstockorgvid".equals(editKey)) {
/*  97 */       SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
/*  98 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 101 */     else if ("csendstordocid".equals(editKey)) {
/* 102 */       SendStordocEditHandler handler = new SendStordocEditHandler();
/* 103 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 106 */     else if ("ctrafficorgvid".equals(editKey)) {
/* 107 */       TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
/* 108 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 111 */     else if ("nexchangerate".equals(editKey)) {
/* 112 */       ExchangerateEditHandler handler = new ExchangerateEditHandler();
/* 113 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 116 */     else if ("nglobalexchgrate".equals(editKey)) {
/* 117 */       GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
/* 118 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 121 */     else if ("ngroupexchgrate".equals(editKey)) {
/* 122 */       GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
/* 123 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 126 */     else if ("ncaltaxmny".equals(editKey)) {
/* 127 */       CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
/* 128 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 131 */     else if ("ctaxcodeid".equals(editKey)) {
/* 132 */       TaxCodeEditHandler handler = new TaxCodeEditHandler();
/* 133 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 136 */     else if ("vbatchcode".equals(editKey)) {
/* 137 */       BatchCodeEditHandler handler = new BatchCodeEditHandler();
/* 138 */       handler.setEditor(this.editor);
/* 139 */       handler.beforeEdit(e);
/*     */ 
/*     */     }
/* 142 */     else if ("creceiveaddrid".equals(editKey)) {
/* 143 */       ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
/* 144 */       handler.beforeEdit(e);
/*     */     }else if("vbdef11".equals(editKey)){

					int row = e.getRow();
					String pk_material=keyValue.getBodyStringValue(row, "cmaterialvid");
					
					if (PubAppTool.isNull(pk_material)) {
						
						ExceptionUtils.wrappBusinessException("请先选择物料！");

					}
					
					
					BomInfoPanel bompanel=(BomInfoPanel)cardPanel.getBodyItem("vbdef11").getComponent();
					bompanel.setSelectRow(row);
				
		
			  }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEditor(PreOrderEditor editor)
/*     */   {
/* 155 */     this.editor = editor;
/*     */   }
/*     */ }

