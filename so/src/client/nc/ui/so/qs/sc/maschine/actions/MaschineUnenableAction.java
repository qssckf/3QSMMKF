package nc.ui.so.qs.sc.maschine.actions;

import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import nc.ui.so.xlx.tran.tranflow.ace.serviceproxy.AceTranFlowMaintainProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.maschine.service.IMaschineMaintain;
//import nc.itf.so.ITranFlowMaintain;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.IBatchOperateValidateService;
//import nc.ui.so.xlx.tran.base.action.SetBillValueAction;
//import nc.ui.so.xlx.tran.tranflow.model.TranFlowAppModel;
//import nc.ui.so.xlx.tran.tranflow.model.TranFlowAppModelDataManager;
import nc.ui.so.qs.sc.action.bill.SetBillValueAction;
import nc.ui.so.qs.sc.maschine.model.MaschineBillManageModel;
import nc.ui.so.qs.sc.maschine.model.MaschineModelDataManager;
import nc.ui.so.qs.sc.maschine.service.MaschineMaintainService;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
//import nc.vo.so.xlx.tranflow.AggTranFlowVO;
//import nc.vo.so.xlx.tranflow.TranFlowVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
import nc.vo.uap.busibean.exception.BusiBeanException;
import nc.ui.so.qs.sc.maschine.service.MaschineQueryService;

public class MaschineUnenableAction extends SetBillValueAction {
	
	private MaschineModelDataManager dataManager;
	
	private IMaschineMaintain service;
		
	public IMaschineMaintain getService() {
		
		if(this.service==null){
			this.service=NCLocator.getInstance().lookup(IMaschineMaintain.class);
		}
		
		return service;
	}

	public MaschineUnenableAction(){
		this.setBtnName("取消启用");
		this.setCode("unenablevalue");
	}

	public MaschineModelDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(MaschineModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public void doSetValue() throws Exception {
		// TODO 自动生成的方法存根
		
		AggMaschineVO agg = (AggMaschineVO)getModel().getSelectedData();
		
		if (null == agg) {
			throw new BusiBeanException(NCLangRes.getInstance().getStrByID("ec20120_0", "0ec20120-000043"));
		}
		
		setEnable(agg);
		
	}

	private void setEnable(AggMaschineVO agg) throws BusinessException {
		// TODO 自动生成的方法存根		
	
	  
		MaschineVO maschine=agg.getParentVO();
		
		String pkmaschine=maschine.getPk_maschine();
		
		try {
			Map<String,Object> masMap = getService().getMaschinepkInfo(pkmaschine);
			
			for(Map.Entry<String, Object> entry : masMap.entrySet()){
				String masKey= entry.getKey();
				if(masKey.toString().equals("pk_machine")){
					Object masObj = entry.getValue();
//					MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(), "错误", "数据已被引用！");
					throw new BusinessException("错误,数据已被引用！");
				}
			}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e.getMessage());
		}
		AggMaschineVO retobj=this.getService().unable(agg);
		agg.setParentVO(maschine);
		getModel().directlyUpdate(retobj);

	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		AggMaschineVO agg = (AggMaschineVO)getModel().getSelectedData();
	    if (null == agg) {
	    	return false;
	     }
	    Integer mstatus = ((MaschineVO)agg.getParentVO()).getMstatus();
	    
	    return (getModel().getUiState() == UIState.NOT_EDIT) && ("1".toString().equals(mstatus.toString()));
	}

}
