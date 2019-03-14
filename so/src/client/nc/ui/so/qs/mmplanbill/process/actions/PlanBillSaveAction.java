package nc.ui.so.qs.mmplanbill.process.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.pubapp.uif2app.actions.SaveAction;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillSaveAction extends SaveAction{

	private IPlanBillSerive PlanService;
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		Object value = this.getEditor().getValue();
			
		validate(value);
		
		MmPlanBillVO pbVo=(MmPlanBillVO)value;
		
		String pk_planbill=pbVo.getPk_planbill();
		
		if(getPlanService().QueryPlanBillIsExists(pk_planbill)){
			
			pbVo.setStatus(1);
			this.doEditSave(pbVo);
				
		}else{
			
			pbVo.setStatus(2);
			this.doAddSave(pbVo);
				
		}

		
		
		
		
		
		
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		return super.isActionEnable();
	}

}
