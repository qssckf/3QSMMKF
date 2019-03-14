package nc.bs.so.wa.piece.pf.ace.rule;


import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.wa.piece.PieceProductVO;

public class PDBusLockRule implements IRule<PieceProductVO>{
	
	
	
	

	@Override
	public void process(PieceProductVO[] vos) {
		// TODO 自动生成的方法存根
		
		try {
			BIZLockDataUtil.lockDataByBizlock(vos);
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappException(e);
		}
		
	}
	
	
}

