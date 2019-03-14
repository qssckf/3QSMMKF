package nc.ui.so.qs.intoprod.input.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class ItAppModel extends BillManageModel{

	private BillManageModel parentModel;
	private ShowUpableBillListView parentForm;
	private BillManageModel chModel;
	private String operatype;
	private boolean edit;
	
	private IRdMmService rdservice;

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		return rdservice;
	}
	
	public void initPara(IntoProdDetailPara para){
		
		this.parentModel=(BillManageModel) para.getModel();
		this.chModel=(BillManageModel) para.getCmodel();
		this.parentForm=para.getList();
		this.operatype=para.getOperatype();
		this.edit=para.getEdit();
		
		
	}
	
	public BillManageModel getParentModel() {
		return parentModel;
	}
	public void setParentModel(BillManageModel parentModel) {
		this.parentModel = parentModel;
	}
	public ShowUpableBillListView getParentForm() {
		return parentForm;
	}
	public void setParentForm(ShowUpableBillListView parentForm) {
		this.parentForm = parentForm;
	}
	public BillManageModel getChModel() {
		return chModel;
	}
	public void setChModel(BillManageModel chModel) {
		this.chModel = chModel;
	}
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public boolean getEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	
	
}
