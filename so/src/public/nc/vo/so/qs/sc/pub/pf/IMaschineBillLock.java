package nc.vo.so.qs.sc.pub.pf;

import nc.vo.pub.BusinessException;

public interface IMaschineBillLock {
	
	public abstract String getLockValue() throws BusinessException;

}
