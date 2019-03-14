package nc.bs.so.qs.sc.maschine.rule;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;







public class MaschineUpdateAfterEventRule
  implements IRule<AggMaschineVO>
{
  public MaschineUpdateAfterEventRule() {}
  
  public void process(AggMaschineVO[] vos)
  {
    BusinessEvent afterEvent = new BusinessEvent("c86a967d-98e0-49a9-8150-e8dc0158a5cd", "1004", vos);
    try
    {
      EventDispatcher.fireEvent(afterEvent);
    } catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
