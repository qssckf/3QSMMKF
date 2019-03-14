package nc.itf.so.qs.sc.FQ01trantype;

import nc.vo.pub.BusinessException;
//import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.qs.sc.FQ01trantype.entity.FQ01TranTypeVO;

public abstract interface IFQ01TranTypeService
{
  public abstract FQ01TranTypeVO queryTranTypeVO(String paramString) throws BusinessException;
  
  public abstract FQ01TranTypeVO[] queryTranTypeVOs(String[] paramArrayOfString) throws BusinessException;
  
  public abstract FQ01TranTypeVO queryTranType(String paramString1, String paramString2) throws BusinessException;
  
  public abstract FQ01TranTypeVO[] queryTranTypeVOs(String paramString, String[] paramArrayOfString) throws BusinessException;
}
