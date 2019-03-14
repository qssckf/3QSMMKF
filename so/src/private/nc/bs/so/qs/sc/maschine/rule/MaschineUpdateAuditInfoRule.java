package nc.bs.so.qs.sc.maschine.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.vo.pubapp.util.AuditInfoUtils;
import nc.vo.so.qs.sc.AggMaschineVO;





public class MaschineUpdateAuditInfoRule
  implements IRule<AggMaschineVO>
{
  public MaschineUpdateAuditInfoRule() {}
  
  public void process(AggMaschineVO[] vos)
  {
    if (MMArrayUtil.isEmpty(vos)) {
      return;
    }
    for (AggMaschineVO vo : vos) {
      AuditInfoUtils.setUpdateAuditInfo(vo.getParent());
    }
  }
}