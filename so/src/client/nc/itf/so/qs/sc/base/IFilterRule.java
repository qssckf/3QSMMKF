package nc.itf.so.qs.sc.base;

import nc.so.qs.sc.bs.bd.service.ValueObjWithErrLog;


public interface IFilterRule {
	
	public abstract ValueObjWithErrLog process(ValueObjWithErrLog obj);

}