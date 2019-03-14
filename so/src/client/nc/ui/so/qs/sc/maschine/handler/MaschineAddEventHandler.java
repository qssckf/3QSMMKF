package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
/*    */ import nc.ui.mmf.framework.util.BillCardPanelUtil;
///*    */ import nc.ui.pd.pd0404.model.PDWkBillManageModel;
///*    */ import nc.ui.pd.pd0404.view.PDWkBillForm;
/*    */ import nc.ui.pub.bill.BillCardPanel;
/*    */ import nc.ui.pub.bill.BillItem;
/*    */ import nc.ui.pubapp.AppUiContext;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*    */ import nc.ui.pubapp.uif2app.event.billform.AddEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
/*    */ import nc.ui.pubapp.uif2app.event.card.CardPanelEvent;
/*    */ import nc.ui.pubapp.uif2app.view.BillForm;
		import nc.ui.so.qs.sc.maschine.model.MaschineBillManageModel;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillForm;
/*    */ import nc.util.mmf.busi.service.OrgUnitPubService;
/*    */ import nc.vo.pd.pd0404.message.MMPDLangConstPd0404;
/*    */ import nc.vo.pubapp.AppContext;
		import nc.vo.uif2.LoginContext;
/*    */ 
/*    */ public class MaschineAddEventHandler extends nc.ui.mmf.framework.handler.MMBaseHandler implements IAppEventHandler<AddEvent>
/*    */ {
/*    */   private MaschineBillForm billform;
/*    */   private MaschineBillManageModel model;
/*    */   
/*    */   public MaschineAddEventHandler() {}
/*    */   
/*    */   public MaschineBillForm getBillform()
/*    */   {
/* 28 */     return this.billform;
/*    */   }
/*    */   
/*    */   public void setBillform(MaschineBillForm billform) {
/* 32 */     this.billform = billform;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public MaschineBillManageModel getModel()
/*    */   {
/* 40 */     return this.model;
/*    */   }
/*    */   
/*    */   public void setModel(MaschineBillManageModel model) {
/* 44 */     this.model = model;
/*    */   }
/*    */   
/*    */ 
/*    */   public void handleAppEvent(AddEvent e)
/*    */   {
/* 50 */     String pk_group = e.getContext().getPk_group();
/*    */     
/* 52 */     String pk_org = e.getContext().getPk_org();
/*    */     
/* 54 */     BillCardPanel cardPanel = e.getBillForm().getBillCardPanel();
/*    */     
/*    */ 
/* 57 */     cardPanel.setHeadItem("pk_group", pk_group);
/* 58 */     cardPanel.setHeadItem("pk_org", pk_org);
/* 59 */     cardPanel.setHeadItem("pk_org_v", OrgUnitPubService.getNewVIDByOrgID(pk_org));
/* 60 */     nc.ui.pubapp.uif2app.view.util.BillPanelUtils.setOrgForAllRef(getBillform().getBillCardPanel(), getModel().getContext());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void beforeEdit(CardPanelEvent e) {}
/*    */   
/*    */ 
/*    */ 
/*    */   public void afterEdit(CardPanelEvent e)
/*    */   {
/* 72 */     CardBodyAfterRowEditEvent event = (CardBodyAfterRowEditEvent)e;
/* 73 */     String pk_group = e.getContext().getPk_group();
/*    */     
/* 75 */     String pk_org = e.getContext().getPk_org();
/* 76 */     int[] rows = event.getRows();
/* 77 */     int[] arr$ = rows;
			 int len$ = arr$.length; for (int i$ = 0; i$ < len$; i$++) { 
			   Integer row = Integer.valueOf(arr$[i$]);
/* 78 */       String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
/* 79 */       BillCardPanelUtil utils = new BillCardPanelUtil(e.getBillCardPanel());
/*    */       
/* 81 */       utils.setBodyValue(AppUiContext.getInstance().getBusiDate(), row.intValue(), "dbegindate", tabCode);
/* 82 */       utils.setBodyValue(MMPDLangConstPd0404.MAXTIME, row.intValue(), "denddate", tabCode);
/*    */       
///* 84 */       utils.setBodyValue(e.getBillCardPanel().getHeadItem("ftimeunit").getValueObject(), row.intValue(), "ftimeunit", tabCode);
/*    */       
/* 86 */       utils.setBodyValue(pk_org, row.intValue(), "pk_org", tabCode);
/*    */       
/* 88 */       utils.setBodyValue(pk_group, row.intValue(), "pk_group", tabCode);
/*    */       
/* 90 */       utils.setBodyValue(OrgUnitPubService.getNewVIDByOrgID(pk_org), row.intValue(), "pk_org_v", tabCode);
/*    */     }
/*    */   }
/*    */ }

