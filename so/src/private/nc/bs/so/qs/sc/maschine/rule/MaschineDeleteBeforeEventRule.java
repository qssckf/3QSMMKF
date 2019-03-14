package nc.bs.so.qs.sc.maschine.rule;
 
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.impl.pubapp.pattern.rule.IRule;
//import nc.vo.pd.pd0404.entity.AggWkVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;






public class MaschineDeleteBeforeEventRule
  implements IRule<AggMaschineVO>
{
  public MaschineDeleteBeforeEventRule() {}
  
  public void process(AggMaschineVO[] vos)
  {
    BusinessEvent beforeEvent = new BusinessEvent("c86a967d-98e0-49a9-8150-e8dc0158a5cd", "1005", vos);
    try {
      EventDispatcher.fireEvent(beforeEvent);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
