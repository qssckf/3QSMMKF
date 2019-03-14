package nc.ui.so.qs.sc.maschine.actions;

import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.md.data.access.NCObject;
import nc.ui.pubapp.uif2app.actions.DeleteAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;

public class MaschineDeleteAction extends DeleteAction {
	
	
	
	protected boolean isActionEnable()
	{
	  if ((getModel() instanceof BillManageModel)) {
	    Object[] objs = ((BillManageModel)getModel()).getSelectedOperaDatas();
	    if ((objs != null) && (objs.length > 1)) {
	      return true;
	    }
	  }
	  
	  boolean isEnable = super.isActionEnable();
	  AggMaschineVO agg = (AggMaschineVO)getModel().getSelectedData();
	  if ((isEnable) && (agg != null)) {
	    NCObject obj = NCObject.newInstance(getModel().getSelectedData());
	    Integer mstatus = ((MaschineVO)agg.getParentVO()).getMstatus();

	
	    if (obj != null) {
	      Integer fstatusflag = ApproveFlowUtil.getBillStatus(obj);
	      if ((fstatusflag == null) || (BillStatusEnum.FREE.value().equals(fstatusflag)) || (BillStatusEnum.NOPASS.value().equals(fstatusflag)))
	      {
	  	    if( (getModel().getUiState() == UIState.NOT_EDIT) && ("0".toString().equals(mstatus.toString()))){
		    	return true;
		    }
	        return false;
	      }
	    }
	  }
	  return false;
	}
}
