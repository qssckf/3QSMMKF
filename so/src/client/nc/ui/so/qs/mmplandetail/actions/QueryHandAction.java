package nc.ui.so.qs.mmplandetail.actions;

import java.awt.event.ActionEvent;

import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.so.qs.mmplanbill.view.HandQueryInfoDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.uitheme.ui.ThemeResourceCenter;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

public class QueryHandAction extends NCAction {
	
	private final String iconurl="themeres/ui/toolbaricons/analysis_press.png";
	private BillManageModel model = null;
	private CompositeBillDataPrepare userdefitemPreparator;
	private LoginContext logincontext;
	private OnhandPanelSrc list;
	private HandQueryInfoDialog dialog;
	

	public OnhandPanelSrc getList() {
		return list;
	}

	public void setList(OnhandPanelSrc list) {
		this.list = list;
	}

	public CompositeBillDataPrepare getUserdefitemPreparator() {
		return userdefitemPreparator;
	}

	public void setUserdefitemPreparator(
			CompositeBillDataPrepare userdefitemPreparator) {
		this.userdefitemPreparator = userdefitemPreparator;
	}

	public LoginContext getLogincontext() {
		return logincontext;
	}

	public void setLogincontext(LoginContext logincontext) {
		this.logincontext = logincontext;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public QueryHandAction(){
		
		this.setBtnName("查询现存量");
		this.setCode("QueryHand");
		this.putValue("ShortDescription", "根据选择物料查询目前仓库现存量");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
		
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {
		// TODO 自动生成的方法存根
		
		int row=this.getModel().getSelectedRow();
		
		if(row>=0){
			
			if(this.dialog==null){
				this.dialog=new HandQueryInfoDialog(this.getModel().getContext().getEntranceUI(),this.getList());
			}
			
			this.dialog.getAdaptor().setUserdefitemPreparator(this.getUserdefitemPreparator());
			this.dialog.getAdaptor().setLogincontext(this.getLogincontext());
			
			this.dialog.showModal();
			
			this.dialog.getAdaptor().freshData(row);
			
			
			
			
		}else{
			
			this.dialog.getAdaptor().clearData();
			
			throw new BusinessException("没有选择记录，不能联查");
		}
		


	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		Object obj=this.getModel().getSelectedData();
		
		if(obj!=null){
			return true;
		}else{
			return false;
		}

	}
	
	
	

}
