package nc.bs.so.fq.state.row;
 
import java.util.ArrayList;
import java.util.List;

import nc.bs.so.fq.plugin.StatePlugInPoint;
import nc.bs.so.fq.state.pub.BillStateUtil;
import nc.bs.so.fq.state.pub.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;







public class RowCloseState extends AbstractRowState<ShipmentsViewVO> implements ITransitionState<ShipmentsViewVO, AggShipmentsVO>
{
  private StateCalculateUtil stateCalculateUtil;
  
  public RowCloseState()
  {
    super(ShipmentsBVO.class, "blineclose", UFBoolean.TRUE);
  }
  

  public boolean isAutoTransitable(ShipmentsViewVO vo)
  {
    if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
      return false;
    }
    return getStateCalculateUtil().isAutoTransitRowClose(vo);
  }
  

  public boolean isPrevStateValid(ShipmentsViewVO vo)
  {
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }
  
  public boolean isRowClose(ShipmentsViewVO vo)
  {
    if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
      return false;
    }
    return getStateCalculateUtil().isRowClose(vo);
  }
  
  public List<IState<ShipmentsViewVO>> next()
  {
    List<IState<ShipmentsViewVO>> list = new ArrayList();
    return list;
  }
  
  public void setState(ShipmentsViewVO[] views)
  {
   
	  AroundProcesser<ShipmentsViewVO> processer = new AroundProcesser(StatePlugInPoint.RowCloseState);
    

    TimeLog.logStart();
    ShipmentsViewVO[] vos = (ShipmentsViewVO[])processer.before(views);
    TimeLog.info("�йر�ǰִ��ҵ�����");
    
    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info("�޸ı���״̬Ϊ�йر�");
    
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("�йرպ�ִ��ҵ�����");
  }
  
  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }


  @Override
  public IState<AggShipmentsVO> getTransitTargetState() {
	// TODO �Զ����ɵķ������
	  return null;
  }
}
