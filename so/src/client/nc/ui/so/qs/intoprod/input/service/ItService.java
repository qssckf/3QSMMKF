package nc.ui.so.qs.intoprod.input.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.uif2.LoginContext;

public class ItService implements IAppModelService{
	
	private IRdMmService rdservice;

	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		return rdservice;
	}

	@Override
	public void delete(Object obj) throws Exception {
		// TODO 自动生成的方法存根
		if(obj instanceof IntoProdDetailVO){
			
			IntoProdDetailVO vo=(IntoProdDetailVO) obj;
			
			this.getRdservice().deleteIp(vo);
			
		}
	}

	@Override
	public Object insert(Object value) throws Exception {
		// TODO 自动生成的方法存根
		if(value instanceof IntoProdDetailVO){
			
			IntoProdDetailVO vo=(IntoProdDetailVO) value;
			
			return this.getRdservice().InsertIp(new IntoProdDetailVO[]{vo})[0];
			
		}
		
		return null;
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext arg0) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object update(Object value) throws Exception {
		// TODO 自动生成的方法存根
		
		if(value instanceof IntoProdDetailVO){
			
			IntoProdDetailVO vo=(IntoProdDetailVO) value;
			
			return this.getRdservice().updateIp(new IntoProdDetailVO[]{vo})[0];
			
		}
		
		return null;
	}

}
