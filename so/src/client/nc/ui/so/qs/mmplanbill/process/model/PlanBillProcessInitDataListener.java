package nc.ui.so.qs.mmplanbill.process.model;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.ui.ecpubapp.uif2app.model.IDefaultInitDataProcessor;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class PlanBillProcessInitDataListener implements IFuncNodeInitDataListener {
	
	private PlanBillAppModel PlanModel;
	private BomVerAppModel BomModel;
	IDefaultInitDataProcessor processor = null;
	private IPlanBillSerive PlanService;
	private IMaterialPubService_C MatService;
	
	
	
	public IMaterialPubService_C getMatService() {
		if(this.MatService==null){
			this.MatService = (IMaterialPubService_C)NCLocator.getInstance().lookup(IMaterialPubService_C.class);
		}
		return MatService;
	}


	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}

	
	public BomVerAppModel getBomModel() {
		return BomModel;
	}


	public void setBomModel(BomVerAppModel bomModel) {
		BomModel = bomModel;
	}


	public PlanBillAppModel getPlanModel() {
		return PlanModel;
	}


	public void setPlanModel(PlanBillAppModel planModel) {
		PlanModel = planModel;
	}


	@Override
	public void initData(FuncletInitData funinitdata) {
		// TODO 自动生成的方法存根
		
		if(funinitdata.getInitData()==null){
			this.getPlanModel().initModel(null);
			return;
		}
		
		if(funinitdata.getInitData() instanceof PlanBillProcessPara){
			
			PlanBillProcessPara initdata=(PlanBillProcessPara) funinitdata.getInitData();
			
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
			
			try {
				
				SuperVO[] bomverVos=null;
				
				PlanBillProcessPara initdata=(PlanBillProcessPara) data.getInitData();
				
				MmPlanBillVO vo=initdata.getPlanVo();
				
				
				String pk_org=vo.getPk_org();
				
				
				((PlanBillAppModel)PlanBillProcessInitDataListener.this.getPlanModel()).initModelByPara(initdata);
				
				
				PlanBillProcessInitDataListener.this.getPlanModel().getContext().setPk_org(pk_org);
				
				
				PlanBillProcessInitDataListener.this.getPlanModel().initModel(vo);
				
				if(initdata.getEdited()){
					
					PlanBillProcessInitDataListener.this.getPlanModel().setAppUiState(AppUiState.EDIT);
		
				}else{
					PlanBillProcessInitDataListener.this.getPlanModel().setAppUiState(AppUiState.NOT_EDIT);
				}
				
				
				Map<String, MaterialVO> mats=PlanBillProcessInitDataListener.this.getMatService().queryMaterialBaseInfoByPks(new String[]{vo.getPk_material()}, new String[] { "def4" });
				
				if(!mats.containsKey(vo.getPk_material())){
					throw new BusinessException("没有找到物料信息，不能完成界面初始化，处理失败！");
				}
				
				MaterialVO matvo= mats.get(vo.getPk_material());
				
				if("Y".equals(matvo.getDef4())){
					
					PlanBillProcessInitDataListener.this.getPlanModel().setSftm(true);
					
					bomverVos=PlanBillProcessInitDataListener.this.getPlanService().queryBomVersionByTM(vo.getPk_material(), vo.getPk_org());
					
				}else{
					
					PlanBillProcessInitDataListener.this.getPlanModel().setSftm(false);
					
					bomverVos=PlanBillProcessInitDataListener.this.getPlanService().queryBomVersion(vo.getPk_material(),vo.getPk_org());
				}
				
				
				
				
				if(bomverVos!=null && bomverVos.length>0){
					
					PlanBillProcessInitDataListener.this.getBomModel().initModel(bomverVos);
					
					
				}else{
					PlanBillProcessInitDataListener.this.getBomModel().initModel(null);
				}
				
				
				
				
			} catch (BusinessException e) {
				// TODO 自动生成的 catch 块
				ExceptionUtils.wrappException(e);
			}
			

			
		}
		
		
	}

}
