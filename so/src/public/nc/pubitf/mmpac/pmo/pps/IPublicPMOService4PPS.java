package nc.pubitf.mmpac.pmo.pps;

import nc.pubitf.mmpub.pub.mmpps.calc.IDemandResultForCalc;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.pub.BusinessException;

public abstract interface IPublicPMOService4PPS
{
  public abstract void pushMO(PMOAggVO[] paramArrayOfPMOAggVO)
    throws BusinessException;
  
  public abstract PMOAggVO[] RdpushMO(PMOAggVO[] paramArrayOfPMOAggVO)
		    throws BusinessException;
  
  public abstract IQueryScheme getSql(String paramString1, String paramString2)
    throws BusinessException;
  
  public abstract IDemandResultForCalc getSupply()
    throws BusinessException;
}

