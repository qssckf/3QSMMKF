package nc.vo.so.qs.util;

import java.util.ArrayList;

import nc.bs.uap.lock.PKLock;
import nc.bs.uif2.LockFailedException;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class QsBDPKLockUitl {
	
	public QsBDPKLockUitl(){};
	
	public static void lockSuperVO(IQLock... superVOs) throws BusinessException {
		
		if (superVOs == null) {
			throw new BusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("ueaa", "BDVersionValidationUtil-000000"));
		}
		
		ArrayList<String> lockPks = new ArrayList();
		
		ArrayList<String> ChlockPks = new ArrayList();
		
		for (IQLock superVO : superVOs) {
			
			if (superVO.getMainLockKey() != null) {
				lockPks.add(superVO.getMainLockKey());
			}
			
			if(superVO.getChillLockKey() != null){
				ChlockPks.add(superVO.getChillLockKey());
			}
			
			if (lockPks.size() > 0){
				
			      boolean lock_acquired = PKLock.getInstance().addBatchDynamicLock((String[])lockPks.toArray(new String[0]));
			      
			      if (!lock_acquired) {
			        throw new LockFailedException((String[])lockPks.toArray(new String[0]));
			      }
			}
			
			if (ChlockPks.size() > 0){
				
			      boolean lock_acquired = PKLock.getInstance().addBatchDynamicLock((String[])ChlockPks.toArray(new String[0]));
			      
			      if (!lock_acquired) {
			        throw new LockFailedException((String[])ChlockPks.toArray(new String[0]));
			      }
			}
			
			
		}
		
	}

}
