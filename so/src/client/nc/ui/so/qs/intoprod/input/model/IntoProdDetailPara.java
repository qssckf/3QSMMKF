package nc.ui.so.qs.intoprod.input.model;

import java.io.Serializable;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.so.qs.sc.IntoProdDetailVO;

public class IntoProdDetailPara implements Serializable{

	private static final long serialVersionUID = 4029748719310521650L;
	
	private IntoProdDetailVO ItVo;
	private BillManageModel model;
	private ShowUpableBillListView list;
	private BillManageModel cmodel;
	private String operatype;
	private Boolean edit;
	
	public IntoProdDetailVO getItVo() {
		return ItVo;
	}
	public void setItVo(IntoProdDetailVO itVo) {
		ItVo = itVo;
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
	public BillManageModel getCmodel() {
		return cmodel;
	}
	public void setCmodel(BillManageModel cmodel) {
		this.cmodel = cmodel;
	}
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
	
	

}
