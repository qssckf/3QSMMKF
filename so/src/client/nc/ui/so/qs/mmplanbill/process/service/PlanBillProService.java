package nc.ui.so.qs.mmplanbill.process.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.uif2.LoginContext;

public class PlanBillProService implements IAppModelService{


	private IPlanBillSerive planbillservice;
	
	public IPlanBillSerive getPlanbillservice() {
		
		if(this.planbillservice==null){
			this.planbillservice=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		
		return planbillservice;
	}
	
	@Override
	public Object insert(Object objs) throws Exception {
		// TODO 自动生成的方法存根
		
		if(objs instanceof MmPlanBillVO){
			
			MmPlanBillVO vo=(MmPlanBillVO) objs;
			
			return this.getPlanbillservice().Insert(new MmPlanBillVO[]{vo})[0];
			
		}
		
		return null;
		
	}

	@Override
	public Object update(Object objs) throws Exception {
		// TODO 自动生成的方法存根
		
		if(objs instanceof MmPlanBillVO){
			
			MmPlanBillVO vo=(MmPlanBillVO) objs;
			
			return this.getPlanbillservice().update(new MmPlanBillVO[]{vo})[0];
			
		}
		
		return null;
	}

	@Override
	public void delete(Object paramObject) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext paramLoginContext)
			throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

}
