package nc.itf.so.wa.piece;

import java.util.List;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pub.BusinessException;

public interface IPieceWaMaintain extends ISmartService{

	public List<String> QueryFetchRule() throws BusinessException;
	
	public abstract Boolean CheckPieceFormulaExist(String fid)  throws BusinessException;

}
