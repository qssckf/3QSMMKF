package nc.itf.so.m38;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.qs.sc.AggShipmentsVO;

public abstract interface IPreOrderMaintain
{
  public abstract void deletePreOrder(PreOrderVO[] paramArrayOfPreOrderVO)
    throws BusinessException;
  
  public abstract PreOrderVO insertPreOrder(PreOrderVO paramPreOrderVO)
    throws BusinessException;
  
  public abstract PreOrderVO[] invalidationPreorder(PreOrderVO[] paramArrayOfPreOrderVO)
    throws BusinessException;
  
  public abstract PreOrderVO[] queryPreOrder(IQueryScheme paramIQueryScheme)
    throws BusinessException;
  
  public abstract PreOrderVO[] queryPreOrder(String paramString)
    throws BusinessException;
  
  public abstract PreOrderVO[] queryPreOrderFor30(IQueryScheme paramIQueryScheme)
    throws BusinessException;
  
  public abstract PreOrderVO[] updatePreOrder(PreOrderVO[] paramArrayOfPreOrderVO1, PreOrderVO[] paramArrayOfPreOrderVO2)
    throws BusinessException;
  
  public PreOrderVO[] save(PreOrderVO[] clientFullVOs,PreOrderVO[] originBills) throws BusinessException;
  
  public PreOrderVO[] unsave(PreOrderVO[] clientFullVOs,PreOrderVO[] originBills) throws BusinessException;
}
