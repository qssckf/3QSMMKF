package nc.ui.so.qs.mmplanbill.readyplan.model;

import java.io.Serializable;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class PlanDetailPara implements Serializable{

	private static final long serialVersionUID = -2390104438589778586L;
	
	private RdPorductDetailVO planVo;
	private BillManageModel model;
	private ShowUpableBillListView list;
	private BillManageModel cmodel;
	private String operatype;
	private Boolean edit;
	
	
	public String getOperatype() {
		return operatype;
	}
	public void setOperatype(String operatype) {
		this.operatype = operatype;
	}
	public Boolean getEdit() {
		return edit;
	}
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}
	public BillManageModel getCmodel() {
		return cmodel;
	}
	public void setCmodel(BillManageModel cmodel) {
		this.cmodel = cmodel;
	}
	public RdPorductDetailVO getPlanVo() {
		return planVo;
	}
	public void setPlanVo(RdPorductDetailVO planVo) {
		this.planVo = planVo;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	public ShowUpableBillListView getList() {
		return list;
	}
	public void setList(ShowUpableBillListView list) {
		this.list = list;
	}
	
	
	
	
	

}
