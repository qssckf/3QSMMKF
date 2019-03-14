package nc.ui.so.qs.mmplanbill.process.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.qs.mmplanbill.model.ManageAppModel;
import nc.ui.so.qs.mmplanbill.service.PlanBillAppModelService;
import nc.ui.so.qs.mmplanbill.view.PlanListView;
import nc.vo.pub.SuperVO;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillAppModel extends BillManageModel{
	
	
	private BillManageModel parentModel;
	private ShowUpableBillListView parentForm;
	private IPlanBillSerive PlanService;
	private Boolean sftm=false;
	
	public Boolean getSftm() {
		return sftm;
	}


	public void setSftm(Boolean sftm) {
		this.sftm = sftm;
	}


	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}

	
	public BillManageModel getParentModel() {
		return parentModel;
	}

	public void setParentModel(
			BillManageModel parentModel) {
		this.parentModel = parentModel;
	}

	public ShowUpableBillListView getParentForm() {
		return parentForm;
	}

	public void setParentForm(ShowUpableBillListView showUpableBillListView) {
		this.parentForm = showUpableBillListView;
	}

	public void initModelByPara(PlanBillProcessPara initdata){
		
		this.setParentModel((ManageAppModel) initdata.getModel());
		this.setParentForm(initdata.getList());
	}

	@Override
	public Object add(Object object) throws Exception {
		// TODO 自动生成的方法存根
		
		Object ret=super.add(object);
		
		if(ret instanceof MmPlanBillVO){
			
			MmPlanBillVO mplan=(MmPlanBillVO)ret;
			
			this.getParentModel().directlyUpdate(mplan);
			
			InitPlanBillDetail(mplan.getPk_planbill());
				
				
		}
		
		return ret;
	}

	@Override
	public Object update(Object object) throws Exception {
		// TODO 自动生成的方法存根
		Object ret=super.update(object);
		
		if(ret instanceof MmPlanBillVO){
			
			MmPlanBillVO mplan=(MmPlanBillVO)ret;
			
			this.getParentModel().directlyUpdate(mplan);
			
			InitPlanBillDetail(mplan.getPk_planbill());
				
				
		}
			
		
		return ret;
	}
	
	
	public void InitPlanBillDetail(String pk_planbill) throws Exception{
		
		
		SuperVO[] plandetails=this.getPlanService().quyerPlanDetailVOs("pk_planbill='"+pk_planbill+"'");
		
		if(plandetails!=null && plandetails.length>0){
			((PlanListView)this.getParentForm()).getPlanDetailModel().initModel(plandetails);

		}else{
			((PlanListView)this.getParentForm()).getPlanDetailModel().initModel(null);

		}
		
		
	}
	
	
	
	
	

}
