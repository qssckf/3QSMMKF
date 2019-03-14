package nc.ui.so.qs.mmplanbill.process.model;

import java.io.Serializable;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.qs.mmplanbill.model.ManageAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillProcessPara implements Serializable{

	private static final long serialVersionUID = -1874501239910999888L;
	
	private MmPlanBillVO planVo;
	private BillManageModel model;
	private Boolean Edited;
	private int voStatus;
	private String MaterID;
	private ShowUpableBillListView list;
	
	
	public ShowUpableBillListView getList() {
		return list;
	}
	public void setList(ShowUpableBillListView list) {
		this.list = list;
	}
	
	public String getMaterID() {
		return MaterID;
	}
	public void setMaterID(String materID) {
		MaterID = materID;
	}
	public int getVoStatus() {
		return voStatus;
	}
	public void setVoStatus(int voStatus) {
		this.voStatus = voStatus;
	}
	public Boolean getEdited() {
		return Edited;
	}
	public void setEdited(Boolean edited) {
		Edited = edited;
	}
	public MmPlanBillVO getPlanVo() {
		return planVo;
	}
	public void setPlanVo(MmPlanBillVO planVo) {
		this.planVo = planVo;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
	}
	
}
