package nc.vo.so.qs.sc.tools;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//import nc.bs.so.xlx.exception.CarLockFailedException;
import nc.bs.uap.lock.PKLock;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.qs.sc.pub.pf.IMaschineBillLock;
//import nc.vo.so.xlx.pub.pf.ITranBillLock;
import nc.vo.util.bizlock.BizLockFailedException;
import nc.vo.util.bizlock.IBizlockDataProvider;

public class MaschineBizlockDataUtil {
	
	
	public MaschineBizlockDataUtil() {}
	
	
	public static void lockDataByBizlock(IMaschineBillLock... vos) throws BusinessException{
		
		String[] lockKeys = getBizLockKeys(vos);
		
		if (lockKeys != null) {
			
			boolean lock_acquired = PKLock.getInstance().addBatchDynamicLock(lockKeys);
			
//			if (!lock_acquired) {
//				throw new CarLockFailedException(lockKeys);
//			}
			
		}
		
	}
	
	
	
	private static String[] getBizLockKeys(IMaschineBillLock... objs) throws BusinessException
	{
		Set<String> lockKeyList = new HashSet();
		
		for (IMaschineBillLock obj : objs) {

				lockKeyList.add(obj.getLockValue());
		}
		
		return lockKeyList == null ? null : (String[])lockKeyList.toArray(new String[0]);
	}

}
