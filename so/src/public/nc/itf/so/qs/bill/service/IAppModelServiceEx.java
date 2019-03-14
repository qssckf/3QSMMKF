package nc.itf.so.qs.bill.service;

import nc.vo.pub.BusinessException;

public interface IAppModelServiceEx {
	
	public abstract Object insert(Object paramObject) throws Exception;
		  
	public abstract Object update(Object paramObject) throws Exception;
		  
	public abstract void delete(Object paramObject) throws Exception;
	
	public Object[] queryVOsByPks(String[] pks) throws Exception;
	
	public String[] queryVosPksBySqlWhere(String[] orgIDs, String condition) throws BusinessException;
	

}
