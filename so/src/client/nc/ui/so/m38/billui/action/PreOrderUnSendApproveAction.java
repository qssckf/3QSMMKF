package nc.ui.so.m38.billui.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderUnSendApproveAction extends UnCommitScriptAction{
	
	public PreOrderUnSendApproveAction(){
		
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		 boolean isEnable = (getModel().getAppUiState() == AppUiState.NOT_EDIT) && (null != getModel().getSelectedData());
		 
		 if (isEnable) {
			 Object[] selectedRows = getModel().getSelectedOperaDatas();
			 PreOrderVO selectedData = (PreOrderVO)getModel().getSelectedData();
		    
			 Integer billstatus = selectedData.getParentVO().getFstatusflag();
			 String approver = selectedData.getParentVO().getApprover();
		    
			 isEnable = ((null != selectedRows) && (selectedRows.length > 1)) || ((null == approver) && (BillStatus.AUDITING.equalsValue(billstatus)));
		 }
		  
		 
		  return isEnable;
		
	}
	
	

}
