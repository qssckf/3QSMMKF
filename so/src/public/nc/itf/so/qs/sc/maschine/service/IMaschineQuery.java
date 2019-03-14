package nc.itf.so.qs.sc.maschine.service;

import nc.ui.querytemplate.querytree.IQueryScheme;
//import nc.vo.pd.pd0404.entity.AggWkVO;
//import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public abstract interface IMaschineQuery
{
  public abstract AggMaschineVO[] queryByQueryScheme(IQueryScheme paramIQueryScheme)
    throws BusinessException;
  
  public abstract AggMaschineVO[] queryMaschineByWhereSql(String paramString1, UFBoolean paramUFBoolean, String paramString2)
    throws BusinessException;
	
//  public abstract Object[] queryMDVOByPks(String[] pks) throws BusinessException;
//	
//  public abstract PageQueryVO queryM30App(IQueryScheme Scheme) throws BusinessException;
//  
//  public abstract AggMaschineVO[] queryM30App(String[] paramArrayOfString) throws BusinessException;

	
//  public abstract Integer[] queryFreeNum(String paramString)
//    throws BusinessException;
}