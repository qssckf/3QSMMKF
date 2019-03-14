package nc.ui.so.qs.sc.maschine.handler;
/*    */ 
///*    */ import nc.ui.pd.pd0404.scale.PDWkBodyScaleUtil;
///*    */ import nc.ui.pd.pd0404.scale.PDWkScaleUtil;
///*    */ import nc.ui.pd.pd0404.view.PDWkBillListView;
/*    */ import nc.ui.pub.bill.BillListPanel;
/*    */ import nc.ui.pub.bill.BillModel;
/*    */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*    */ import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
		import nc.ui.so.qs.sc.maschine.scale.MaschineBodyScaleUtil;
		import nc.ui.so.qs.sc.maschine.scale.MaschineScaleUtil;
		import nc.ui.so.qs.sc.maschine.view.MaschineBillListView;
/*    */ import nc.ui.uif2.model.BillManageModel;
		import nc.vo.uif2.LoginContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaschineRowChangeHandler
/*    */   implements IAppEventHandler<ListHeadRowChangedEvent>
/*    */ {
/*    */   private MaschineBillListView listView;
/*    */   private MaschineScaleUtil maschineScaleUtil;
/*    */   private MaschineBodyScaleUtil maschineBodyScaleUtil;
/*    */   
/*    */   public MaschineRowChangeHandler() {}
/*    */   
/*    */   public void handleAppEvent(ListHeadRowChangedEvent e)
/*    */   {
/* 27 */     BillListPanel blp = e.getBillListPanel();
/* 28 */     Object oldOrg = blp.getHeadBillModel().getValueAt(e.getOldRow(), "pk_org_ID");
/* 29 */     Object newOrg = blp.getHeadBillModel().getValueAt(e.getRow(), "pk_org_ID");
/* 30 */     if ((null == newOrg) || (newOrg.equals(oldOrg))) {
/* 31 */       return;
/*    */     }
/* 33 */     getMaschineScaleUtil().setListScale(e.getBillListPanel(), getListView().getModel().getContext().getPk_group(), newOrg.toString());
/*    */     
/* 35 */     getMaschineBodyScaleUtil().setListScale(getListView().getBillListPanel(), getListView().getModel().getContext().getPk_group(), newOrg.toString());
/*    */   }
/*    */   
/*    */   public void setListView(MaschineBillListView listView)
/*    */   {
/* 40 */     this.listView = listView;
/*    */   }
/*    */   
/*    */   public MaschineBillListView getListView() {
/* 44 */     return this.listView;
/*    */   }
/*    */   
/*    */ 
/*    */   public MaschineScaleUtil getMaschineScaleUtil()
/*    */   {
/* 50 */     return this.maschineScaleUtil;
/*    */   }
/*    */   
/*    */   public void setMaschineScaleUtil(MaschineScaleUtil maschineScaleUtil) {
/* 54 */     this.maschineScaleUtil = maschineScaleUtil;
/*    */   }
/*    */   
/*    */ 
/*    */   public MaschineBodyScaleUtil getMaschineBodyScaleUtil()
/*    */   {
/* 60 */     return this.maschineBodyScaleUtil;
/*    */   }
/*    */   
/*    */   public void setMaschineBodyScaleUtil(MaschineBodyScaleUtil maschineBodyScaleUtil) {
/* 64 */     this.maschineBodyScaleUtil = maschineBodyScaleUtil;
/*    */   }
/*    */ }
