package nc.ui.so.qs.mmplanbill.readyplan.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.uif2.LoginContext;

public class RdMmService implements IAppModelService{
	
	
	private IRdMmService rdservice;

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		
		return rdservice;
		
	}

	@Override
	public Object insert(Object obj) throws Exception {
		// TODO 自动生成的方法存根
		
		if(obj instanceof RdPorductDetailVO){
			
			RdPorductDetailVO vo=(RdPorductDetailVO) obj;
			
			return this.getRdservice().Insert(new RdPorductDetailVO[]{vo})[0];
			
		}
		
		return null;
	}

	@Override
	public Object update(Object obj) throws Exception {
		// TODO 自动生成的方法存根
		
		if(obj instanceof RdPorductDetailVO){
			
			RdPorductDetailVO vo=(RdPorductDetailVO) obj;
			
			return this.getRdservice().update(new RdPorductDetailVO[]{vo})[0];
			
		}

		return null;
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO 自动生成的方法存根
		
		if(obj instanceof RdPorductDetailVO){
			
			RdPorductDetailVO vo=(RdPorductDetailVO) obj;
			
			this.getRdservice().delete(vo);
			
		}
				
		
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext paramLoginContext)
			throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}
	
	

}
