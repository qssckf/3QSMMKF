package nc.bs.so.qs.readyplan.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.util.IQLock;
import nc.vo.so.qs.util.QsBDPKLockUitl;
import nc.vo.util.BDPKLockUtil;

public class BDPKLockFatherVORule<E extends IQLock> implements IRule<E>{

	@Override
	public void process(E[] vos) {
		// TODO 自动生成的方法存根
		try
		{
			QsBDPKLockUitl.lockSuperVO(vos);
			
		}catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		
		
	}
	
	

}
