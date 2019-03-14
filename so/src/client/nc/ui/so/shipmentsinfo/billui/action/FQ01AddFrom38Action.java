package nc.ui.so.shipmentsinfo.billui.action;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import nc.itf.uap.pf.busiflow.PfButtonClickContext;
/*    */ import nc.ui.pub.pf.PfUtilClient;
/*    */ import nc.ui.pubapp.billref.dest.TransferViewProcessor;
/*    */ import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
/*    */ import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
/*    */ import nc.ui.pubapp.uif2app.view.BillForm;
/*    */ import nc.ui.uif2.UIState;
/*    */ import nc.ui.uif2.model.AbstractAppModel;
/*    */ import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
/*    */ import nc.vo.scmpub.res.billtype.SOBillType;
///*    */ import nc.vo.so.m30.entity.SaleOrderVO;
		 import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.uif2.LoginContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FQ01AddFrom38Action extends AbstractReferenceAction {
/*    */   private static final long serialVersionUID = 6280066245883849667L;
/*    */   private BillForm editor;
/*    */   private AbstractAppModel model;
/*    */   
/*    */   public FQ01AddFrom38Action() {}
/*    */   
/*    */   public void doAction(ActionEvent e)
/*    */     throws Exception
/*    */   {
/* 31 */     PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
/* 32 */     if (PfUtilClient.isCloseOK()) {
///* 33 */       SaleOrderVO[] vos = (SaleOrderVO[])PfUtilClient.getRetVos();
/* 34 */			AggShipmentsVO[] vos = (AggShipmentsVO[])PfUtilClient.getRetVos();
					for(AggShipmentsVO vo:vos){
						ShipmentsBVO[] bvos=(ShipmentsBVO[]) vo.getChildren(ShipmentsBVO.class);
						
						for(ShipmentsBVO bvo:bvos){
							
							String hsl=bvo.getVchangerate();
							bvo.setNastnum(HslParseUtil.hslDivUFDouble(hsl, bvo.getNnum()));

							
						}
					}
/*    */       
/* 36 */       getTransferViewProcessor().processBillTransfer(vos);
/*    */     }
/*    */   }
/*    */   
/*    */   private PfButtonClickContext createPfButtonClickContext() {
/* 41 */     PfButtonClickContext context = new PfButtonClickContext();
/* 42 */     context.setParent(getModel().getContext().getEntranceUI());
/* 43 */     context.setSrcBillType(getSourceBillType());
/* 44 */     context.setPk_group(getModel().getContext().getPk_group());
/* 45 */     context.setUserId(getModel().getContext().getPk_loginUser());
/*    */     
/* 47 */     String vtrantype = TrantypeFuncUtils.getTrantype(getModel().getContext());
/*    */     
/* 49 */     if (StringUtil.isEmptyWithTrim(vtrantype)) {
/* 50 */       context.setCurrBilltype("FQ01");
/*    */     }
/*    */     else {
/* 53 */       context.setCurrBilltype(vtrantype);
/*    */     }
/* 55 */     context.setUserObj(null);
/* 56 */     context.setSrcBillId(null);
/* 57 */     context.setBusiTypes(getBusitypes());
/*    */     
/*    */ 
/* 60 */     context.setTransTypes(getTranstypes());
/*    */     
/*    */ 
/* 63 */     context.setClassifyMode(1);
/* 64 */     return context;
/*    */   }
/*    */   
/*    */   public BillForm getEditor() {
/* 68 */     return this.editor;
/*    */   }
/*    */   
/*    */   public AbstractAppModel getModel() {
/* 72 */     return this.model;
/*    */   }
/*    */   
/*    */   public void setEditor(BillForm editor) {
/* 76 */     this.editor = editor;
/*    */   }
/*    */   
/*    */   public void setModel(AbstractAppModel model) {
/* 80 */     this.model = model;
/* 81 */     model.addAppEventListener(this);
/*    */   }
/*    */   
/*    */   protected boolean isActionEnable()
/*    */   {
/* 86 */     return this.model.getUiState() == UIState.NOT_EDIT;
/*    */   }
/*    */ }