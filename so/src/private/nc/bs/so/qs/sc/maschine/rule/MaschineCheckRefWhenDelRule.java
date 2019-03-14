package nc.bs.so.qs.sc.maschine.rule;

import java.util.ArrayList;
import java.util.List;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.bd.refcheck.IReferenceCheck;
import nc.util.mmf.framework.base.MMValueCheck;
//import nc.vo.pd.pd0404.entity.AggWkVO;
//import nc.vo.pd.pd0404.entity.WkVO;
import nc.vo.pd.pd0404.message.MMPDLangConstPd0404;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;


public class MaschineCheckRefWhenDelRule implements IRule<AggMaschineVO>
{
  public MaschineCheckRefWhenDelRule() {}
  
  public void process(AggMaschineVO[] vos)
  {
    checkReference(vos);
  }
  
  private void checkReference(AggMaschineVO[] vos)
  {
    try {
      if (nc.util.mmf.framework.base.MMArrayUtil.isEmpty(vos)) {
        return;
      }
      IReferenceCheck refcheck = (IReferenceCheck)NCLocator.getInstance().lookup(IReferenceCheck.class);
      List<String> maschineidList = new ArrayList();
   
      for (AggMaschineVO aggVO : vos) {
        MaschineVO head = (MaschineVO)aggVO.getParent();
        maschineidList.add(head.getPk_maschine());
        Integer st = head.getMstatus();
        ss(st);
      }
      if (MMValueCheck.isEmpty(maschineidList)) {
        return;
      }
     
      if (refcheck.isReferenced("so_machine", (String[])maschineidList.toArray(new String[0]))) {
        ExceptionUtils.wrappBusinessException(MMPDLangConstPd0404.getHIT_PDWKCENTERREF());
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
  public void ss(Integer stas){
	  if("1".toString().equals(stas.toString()) || "2".toString().equals(stas.toString())){
		  ExceptionUtils.wrappException(new BusinessException("单据状态不是【未启用】状态，不允许删除"));
    	  return;
      }
  }
}