package nc.itf.so;

import nc.pubitf.so.fq.so.m30.Rewrite30Para;
import nc.pubitf.so.fq.so.m30.RewritefqPara;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.pub.BusinessException;

public interface IShipmentsInfoMaintain {

    public void delete(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException;

    public AggShipmentsVO[] insert(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException;
  
    public AggShipmentsVO[] update(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException;

    public String[] queryPKs(IQueryScheme queryScheme) throws BusinessException;
   
    public AggShipmentsVO[] queryBillByPK(String[] pks) throws BusinessException ;

    public AggShipmentsVO[] save(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException ;

    public AggShipmentsVO[] unsave(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException ;

    public AggShipmentsVO[] approve(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException ;

    public AggShipmentsVO[] unapprove(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException ;
    
    public AggShipmentsVO[] queryShipmentsFor30(IQueryScheme paramIQueryScheme) throws BusinessException;
    
    public PreOrderVO[] queryPreOrderFor30(IQueryScheme paramIQueryScheme) throws BusinessException;
    
    public abstract void rewriteFQNarrnumFor30(Rewrite30Para[] para) throws BusinessException;
    
    public void rewrite30NumForWithdraw(RewritefqPara[] paras) throws BusinessException;
    
    
}
