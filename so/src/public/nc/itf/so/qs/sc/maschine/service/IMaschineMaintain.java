package nc.itf.so.qs.sc.maschine.service;

//import nc.vo.pd.pd0404.entity.AggWkVO;
import java.util.Map;

import nc.bs.bd.service.ValueObjWithErrLog;
import nc.vo.pub.BusinessException;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;

public abstract interface IMaschineMaintain
{
  public abstract void delete(AggMaschineVO[] paramArrayOfAggMaschineVO) throws BusinessException;
  
  public abstract AggMaschineVO[] insert(AggMaschineVO[] paramArrayOfAggMaschineVO) throws BusinessException;
  
  public abstract AggMaschineVO[] update(AggMaschineVO[] paramArrayOfAggMaschineVO) throws BusinessException;
  
  public abstract AggMaschineVO enable(AggMaschineVO agg) throws BusinessException;
  
  public abstract AggMaschineVO unable(AggMaschineVO obj) throws BusinessException;
  
  public abstract AggMaschineVO disable(AggMaschineVO obj) throws BusinessException;
  
  public abstract Map<String, Object> getMaschinepkInfo(String maschine) throws Exception;
}
