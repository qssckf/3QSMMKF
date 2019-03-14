package nc.ui.so.qs.sc.maschine.actions;
/*     */ 
/*     */ import nc.ui.mmf.framework.util.BillCardPanelUtil;
///*     */ import nc.ui.pd.pd0404.view.PDWkBillForm;
/*     */ import nc.ui.pub.bill.BillCardPanel;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
/*     */ import nc.ui.uif2.UIState;
/*     */ import nc.ui.uif2.model.AbstractAppModel;
///*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*     */ import nc.vo.pd.pd0404.entity.WkProdinvVO;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pubapp.pattern.model.entity.bill.IBill;
/*     */ import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
		import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
		import nc.vo.so.qs.sc.AggMaschineVO;
/*     */ 
/*     */ public class MaschineSaveAction extends nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction
/*     */ {
/*     */   private MaschineBillForm billform;
/*     */   
/*     */   public MaschineSaveAction() {}
/*     */   
/*     */   public MaschineBillForm getBillform()
/*     */   {
/*  23 */     return this.billform;
/*     */   }
/*     */   
/*     */   public void setBillform(MaschineBillForm billform) {
/*  27 */     this.billform = billform;
/*     */   }
/*     */   
/*     */   protected void doAddSave(Object value) throws Exception
/*     */   {
/*  32 */     BillCardPanel card = getBillform().getBillCardPanel();
/*     */     
/*  34 */     IBill[] clientVOs = { (IBill)value };
/*     */     
/*     */ 
/*     */ 
/*  38 */     ClientBillToServer<IBill> tool = new ClientBillToServer();
/*     */     
/*     */ 
/*  41 */     IBill[] lightVOs = tool.constructInsert(clientVOs);
/*  42 */     IBill[] afterUpdateVOs = null;
/*  43 */     if (getService() == null) {
/*  44 */       throw new BusinessException("service不能为空。");
/*     */     }
/*  46 */     AggMaschineVO[] aggvos = (AggMaschineVO[])lightVOs;
/*  47 */     afterUpdateVOs = getService().insert(aggvos);
/*     */     
/*     */ 
/*  50 */     new ClientBillCombinServer().combine(clientVOs, afterUpdateVOs);
/*     */     
/*  52 */     getModel().directlyAdd(clientVOs[0]);
/*  53 */     getModel().setUiState(UIState.NOT_EDIT);
/*     */   }
/*     */   
/*     */   protected void doEditSave(Object value) throws Exception {
/*  57 */     BillCardPanel card = getBillform().getBillCardPanel();
/*     */     
/*  59 */     IBill[] clientVOs = { (IBill)value };
/*     */     
/*     */ 
/*     */ 
/*  63 */     ClientBillToServer<IBill> tool = new ClientBillToServer();
/*     */     
/*  65 */     IBill[] oldVO = { (IBill)getModel().getSelectedData() };
/*     */     
/*     */ 
/*     */ 
/*  69 */     IBill[] lightVOs = tool.construct(oldVO, clientVOs);
/*     */     
/*  71 */     IBill[] afterUpdateVOs = null;
/*  72 */     if (getService() == null) {
/*  73 */       throw new BusinessException("service不能为空。");
/*     */     }
/*  75 */     AggMaschineVO[] aggvos = (AggMaschineVO[])lightVOs;
/*  76 */     afterUpdateVOs = getService().update(aggvos);
/*     */     
/*  78 */     new ClientBillCombinServer().combine(clientVOs, afterUpdateVOs);
/*     */     
/*  80 */     getModel().directlyUpdate(clientVOs[0]);
/*  81 */     getModel().setUiState(UIState.NOT_EDIT);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private AggMaschineVO[] setDataValue(BillCardPanel card, AggMaschineVO[] aggvos)
/*     */   {
/*  90 */     int[] rows = card.getBillTable().getSelectedRows();
/*  91 */     BillCardPanelUtil utils = new BillCardPanelUtil(card);
/*  92 */     AggMaschineVO aggvo = aggvos[0];
///*  93 */     WkProdinvVO[] items = (WkProdinvVO[])aggvo.getTableVO("prodinv_items");
///*  94 */     if (nc.util.mmf.framework.base.MMArrayUtil.isEmpty(items)) {
///*  95 */       return null;
///*     */     }
///*  97 */     for (int i = 0; i < items.length; i++) {
///*  98 */       String innercode = utils.getBodyStringValue(i, "cmaterialclassid.innercode");
///*     */       
///*     */ 
///* 101 */       items[i].setAttributeValue("innercode", innercode);
///*     */     }
/* 103 */     return new AggMaschineVO[] { aggvo };
/*     */   }
/*     */ }
