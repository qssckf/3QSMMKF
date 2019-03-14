package nc.ui.so.qs.intoprod.input.model;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.ecpubapp.uif2app.model.IDefaultInitDataProcessor;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.so.qs.mmplanbill.readyplan.model.PlanDetailPara;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmModel;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmProcessInitDataListener;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class IpCardProcessInitDataListener implements IFuncNodeInitDataListener{
	
	private ItAppModel model;
	
	IDefaultInitDataProcessor processor = null;
	
	public ItAppModel getModel() {
		return model;
	}

	public void setModel(ItAppModel model) {
		this.model = model;
	}

	@Override
	public void initData(FuncletInitData funinitdata) {
		// TODO 自动生成的方法存根
		
		if(funinitdata.getInitData()==null){
			this.getModel().initModel(null);
			return;
		}
		
		if(funinitdata.getInitData() instanceof IntoProdDetailPara){
			
			IntoProdDetailPara para=(IntoProdDetailPara) funinitdata.getInitData();
			
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
			
				IntoProdDetailPara initdata=(IntoProdDetailPara) data.getInitData();
				
				IntoProdDetailVO vo=initdata.getItVo();
				
				String pk_org=vo.getPk_org();
				
				
				((ItAppModel)IpCardProcessInitDataListener.this.getModel()).initPara(initdata);
				
				
				IpCardProcessInitDataListener.this.getModel().getContext().setPk_org(pk_org);
				
				IpCardProcessInitDataListener.this.getModel().initModel(vo);
				
				if("ADD".equals(initdata.getOperatype())){
					
					IpCardProcessInitDataListener.this.getModel().setAppUiState(AppUiState.EDIT);
					
				}else if("EDIT".equals(initdata.getOperatype())){
					
					IpCardProcessInitDataListener.this.getModel().setAppUiState(AppUiState.NOT_EDIT);
					
				}										
			
		}
		
		
	}
	
	

}
