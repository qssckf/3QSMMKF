package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

public class SaleOrderAddFromFQ01Action extends AbstractReferenceAction{
	
	private AbstractAppModel model;
	private BillForm editor;

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		
		if (PfUtilClient.isCloseOK()) {
			 SaleOrderVO[] vos = (SaleOrderVO[])PfUtilClient.getRetVos();
			 
			 getTransferViewProcessor().processBillTransfer(vos);
		}
		
		
		
	}
	
	private PfButtonClickContext createPfButtonClickContext() {
	   
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(getModel().getContext().getEntranceUI());
		context.setSrcBillType(getSourceBillType());
		context.setPk_group(getModel().getContext().getPk_group());
		context.setUserId(getModel().getContext().getPk_loginUser());
	   
		String vtrantype = TrantypeFuncUtils.getTrantype(getModel().getContext());
	   
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			context.setCurrBilltype(SOBillType.Order.getCode());
		}else {
			context.setCurrBilltype(vtrantype);
		}
		
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(getBusitypes());
	   
	 
		context.setTransTypes(getTranstypes());
	   
	 
		context.setClassifyMode(1);
		return context;
	
	}

	
	protected boolean isActionEnable(){
		return this.model.getUiState() == UIState.NOT_EDIT;
	}

}
