package nc.itf.so.wa.piece.pf;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.wa.piece.PieceProductVO;
import nc.vo.pub.BusinessException;
import nc.itf.pubapp.pub.smart.ISmartService;

public interface IPieceProductVOMaintain extends ISmartService{
	
    public PieceProductVO[] query(IQueryScheme queryScheme) throws BusinessException, Exception;
    
    public String isHaveDefault(String pk_org) throws BusinessException;
    
    public PieceProductVO setDefault(PieceProductVO pd) throws BusinessException;
}
