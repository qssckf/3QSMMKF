package nc.ui.so.qs.mmplanbill.readyplan.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.ecpubapp.uif2app.model.IDefaultInitDataProcessor;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillAppModel;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillProcessInitDataListener;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillProcessPara;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class RdMmProcessInitDataListener implements IFuncNodeInitDataListener{
	
	private RdMmModel model;
	IDefaultInitDataProcessor processor = null;
	
	public RdMmModel getModel() {
		return model;
	}

	public void setModel(RdMmModel model) {
		this.model = model;
	}



	@Override
	public void initData(FuncletInitData funinitdata) {
		// TODO 自动生成的方法存根
		
		if(funinitdata.getInitData()==null){
			this.getModel().initModel(null);
			return;
		}
		
		if(funinitdata.getInitData() instanceof PlanDetailPara){
			
			PlanDetailPara para=(PlanDetailPara) funinitdata.getInitData();
			
			try {
				
				if (null == processor ){
					
					processor = getDefaultProcessor();
				}
				
				processor.process(funinitdata);
				
			}catch(Exception e){
				ExceptionUtils.wrappException(e);
			}
			
		}
		
	}
	
	private IDefaultInitDataProcessor getDefaultProcessor() {
		// TODO 自动生成的方法存根
		return new DefaultInitDataProcessor();
	}
	
	class DefaultInitDataProcessor implements IDefaultInitDataProcessor{
		
		DefaultInitDataProcessor() {}
		
		public void process(FuncletInitData data){
			
	
				
				PlanDetailPara initdata=(PlanDetailPara) data.getInitData();
				
				RdPorductDetailVO vo=initdata.getPlanVo();
				
				
				String pk_org=vo.getPk_org();
				
				
				((RdMmModel)RdMmProcessInitDataListener.this.getModel()).initPara(initdata);
				
				
				RdMmProcessInitDataListener.this.getModel().getContext().setPk_org(pk_org);
				
				RdMmProcessInitDataListener.this.getModel().initModel(vo);
				
				if("ADD".equals(initdata.getOperatype())){
					
					RdMmProcessInitDataListener.this.getModel().setAppUiState(AppUiState.EDIT);
					
				}else if("EDIT".equals(initdata.getOperatype())){
					
					RdMmProcessInitDataListener.this.getModel().setAppUiState(AppUiState.NOT_EDIT);
					
				}
				
				
				
				
				
				
														
			
		}
		
		
	}

}
