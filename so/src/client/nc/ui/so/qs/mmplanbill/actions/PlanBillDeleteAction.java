package nc.ui.so.qs.mmplanbill.actions;

import java.awt.event.ActionEvent;
import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.bd.pub.actions.BDDeleteAction;
import nc.ui.uif2.CheckDataPermissionUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.NCAsynAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.CommonConfirmDialogUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.IMultiRowSelectModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillDeleteAction extends NCAsynAction{

	private IPlanBillSerive PlanService;
	private AbstractAppModel model;
	IValidationService validationService;
	
	public PlanBillDeleteAction(){
		super();
		this.setCode("PDCancel");
		this.setBtnName("取消调度");
		this.putValue("ShortDescription", "根据选择的调度单取消调度信息");

	}
	
	
	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}

	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		Boolean pkexits=false;
		UFBoolean sfextend;
		
		try{
			
			if(this.getModel().getSelectedData()!=null){
				
				MmPlanBillVO mmplan=(MmPlanBillVO) this.model.getSelectedData();
				
				sfextend=mmplan.getSfexand();
				
				if(this.getPlanService().QueryPlanBillIsExists(mmplan.getPk_planbill())){
					
					pkexits=true;
					
				}else{
					pkexits=true;
				}
				
			}else{
				sfextend=UFBoolean.TRUE;
			}
			

			
			
			return (this.model.getUiState() == UIState.NOT_EDIT) && (this.model.getSelectedData() != null) && (UFBoolean.FALSE.equals(sfextend)) && (pkexits);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return false;
			
		}
	
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		
		Object value=this.getModel().getSelectedData();
		
		validate(value);
		
		if(value instanceof MmPlanBillVO){
			
			MmPlanBillVO vo=(MmPlanBillVO) value;
			
			String[] pks=this.getPlanService().delete(new MmPlanBillVO[]{vo});
			
			if(pks!=null && pks.length>0){
				
				SuperVO[] vos=this.getPlanService().queryVObyPks(pks);
								
				this.getModel().directlyUpdate(vos);
				
			}
			
		}
			
		ShowStatusBarMsgUtil.showStatusBarMsg("取消调度单信息成功！", this.getModel().getContext());
		
	}
	
	protected void validate(Object value){
		
		if (this.validationService != null) {
	       try
	       {
	         this.validationService.validate(value);
	       } catch (ValidationException e) {
	         throw new BusinessExceptionAdapter(e);
	       }
	     }
	   }

	@Override
	public boolean beforeStartDoAction(ActionEvent paramActionEvent)
			throws Exception {
		// TODO 自动生成的方法存根
		 boolean ret = false;
		 
		 ret = 4 == CommonConfirmDialogUtils.showConfirmDeleteDialog(this.model.getContext().getEntranceUI());

		 return ret;
	}
	

	

	


	@Override
	public void doAfterSuccess(ActionEvent paramActionEvent) {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean doAfterFailure(ActionEvent paramActionEvent,
			Throwable paramThrowable) {
		// TODO 自动生成的方法存根
		return false;
	}

}
