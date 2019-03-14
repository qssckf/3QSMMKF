package nc.ui.so.m38.billui.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderSendApproveAction extends CommitScriptAction{
	
	public PreOrderSendApproveAction(){
		
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		if ((getModel().getAppUiState() == AppUiState.EDIT) || (getModel().getAppUiState() == AppUiState.ADD) || (getModel().getAppUiState() == AppUiState.COPY_ADD))
		{
			return true;
		}
		
		boolean isEnable = (getModel().getAppUiState() == AppUiState.NOT_EDIT) && (null != getModel().getSelectedData());
		
		
		if (isEnable) {
			
			Object[] selectedRows = getModel().getSelectedOperaDatas();
			PreOrderVO selectedData = (PreOrderVO)getModel().getSelectedData();
			
			Integer billstatus = selectedData.getParentVO().getFstatusflag();
			
			isEnable = ((null != selectedRows) && (selectedRows.length > 1)) || (BillStatus.FREE.equalsValue(billstatus));
		}
		
		return isEnable;
	}
	
	
	
	

}
