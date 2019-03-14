package nc.ui.so.qs.mmplanbill.readyplan.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class RdMmModel extends BillManageModel{
	
	private BillManageModel parentModel;
	private ShowUpableBillListView parentForm;
	private BillManageModel chModel;
	private String operatype;
	private boolean edit;

	
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

	public BillManageModel getChModel() {
		return chModel;
	}

	public void setChModel(BillManageModel chModel) {
		this.chModel = chModel;
	}

	public void initPara(PlanDetailPara para){
		
		this.parentModel=(BillManageModel) para.getModel();
		this.chModel=(BillManageModel) para.getCmodel();
		this.parentForm=para.getList();
		this.operatype=para.getOperatype();
		this.edit=para.getEdit();
		
	}

	public void setPlanService(IPlanBillSerive planService) {
		PlanService = planService;
	}

	private IPlanBillSerive PlanService;
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
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
	
	

}
