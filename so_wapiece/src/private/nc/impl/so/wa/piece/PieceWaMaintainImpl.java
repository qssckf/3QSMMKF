package nc.impl.so.wa.piece;

import java.util.List;

import nc.itf.so.wa.piece.IPieceWaMaintain;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.uif2.LoginContext;

public class PieceWaMaintainImpl implements IPieceWaMaintain {

	@Override
	public BatchOperateVO batchSave(BatchOperateVO paramBatchOperateVO)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ISuperVO[] queryByDataVisibilitySetting(LoginContext paramLoginContext, Class<? extends ISuperVO> paramClass)
			throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ISuperVO[] selectByWhereSql(String paramString,
			Class<? extends ISuperVO> paramClass) throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<String> QueryFetchRule() throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Boolean CheckPieceFormulaExist(String fid) throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}



}
