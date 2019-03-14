package nc.ui.so.qs.sc.maschine.actions;

import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//import nc.ui.so.xlx.tran.tranflow.ace.serviceproxy.AceTranFlowMaintainProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.maschine.service.IMaschineMaintain;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
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
//import nc.vo.so.xlx.tranflow.AggTranFlowVO;
//import nc.vo.so.xlx.tranflow.TranFlowVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
import nc.vo.uap.busibean.exception.BusiBeanException;
import nc.ui.so.qs.sc.maschine.service.MaschineQueryService;

public class MaschineDisableAction extends SetBillValueAction {
	
	private MaschineModelDataManager dataManager;
	
	private IMaschineMaintain service;
	
	public IMaschineMaintain getService() {
		
		if(this.service==null){
			this.service=NCLocator.getInstance().lookup(IMaschineMaintain.class);
		}
		
		return service;
	}

	public MaschineDisableAction(){
		this.setBtnName("停用");
		this.setCode("disablevalue");
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
	
		AggMaschineVO retobj=this.getService().disable(agg);
	  
		MaschineVO maschine=retobj.getParentVO();
	  
//		List<AggMaschineVO> list= ((MaschineBillManageModel)getModel()).getData();
//	  
//		if ((null == list) || (list.size() == 0)) {
//			return;
//		}
	   
//		List<String> updatePks = new ArrayList();
//		String pk = null;
//		MaschineVO temp = null;
//		for (AggMaschineVO aggVO : list) {
//			temp = (MaschineVO)aggVO.getParentVO();
//		  	pk = temp.getPk_maschine();
//		  	  
//		  	if (("2".equals(temp.getMstatus())) && (null != pk) && (!pk.equals(maschine.getPk_maschine())))
//		  	{
//		  		updatePks.add(pk);
//		  	}
//		}
//	   
//	   if (updatePks.size() > 0) {
// 	 
//		   Object[] objs = ((MaschineQueryService)getDataManager().getPageQuery()).queryObjectByPks((String[])updatePks.toArray(new String[updatePks.size()]));
//		    	 
//		   getModel().directlyUpdate(objs);
//	   }
	   
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
