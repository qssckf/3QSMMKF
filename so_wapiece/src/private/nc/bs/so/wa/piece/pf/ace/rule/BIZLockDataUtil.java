package nc.bs.so.wa.piece.pf.ace.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.uap.lock.PKLock;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class BIZLockDataUtil {
	
	public BIZLockDataUtil(){
		
	}
	
	public static void lockDataByBizlock(SuperVO... vos) throws BusinessException{
		
		Set<String> org=new HashSet();
		
		for(SuperVO vo:vos){
			
			org.add("PDDefaultLock-"+(String) vo.getAttributeValue("pk_org"));
			
		}

		boolean lock_acquired = PKLock.getInstance().addBatchDynamicLock(org.toArray(new String[org.size()]));
			
		if (!lock_acquired) {
			throw new BusinessException("当前数据以加锁，请刷新后再试！");
		}
		
	}

}
