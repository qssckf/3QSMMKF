package nc.bs.so.fq.state.row;
 
 import java.util.ArrayList;
 import java.util.List;

import nc.bs.so.fq.plugin.StatePlugInPoint;
import nc.bs.so.fq.state.bill.Bill38OpenState;
import nc.bs.so.fq.state.pub.Bill38StateUtil;
import nc.bs.so.fq.state.pub.BillStateUtil;
import nc.bs.so.fq.state.pub.State38CalculateUtil;
import nc.bs.so.fq.state.pub.StateCalculateUtil;
 import nc.impl.pubapp.bill.state.AbstractRowState;
 import nc.impl.pubapp.bill.state.IState;
 import nc.impl.pubapp.bill.state.ITransitionState;
 import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
 import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.AggShipmentsVOMeta;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
 

 public class Row38OpenState extends AbstractRowState<PreOrderViewVO> implements ITransitionState<PreOrderViewVO, PreOrderVO>
 {
   private State38CalculateUtil stateCalculateUtil;
   
   public Row38OpenState()
   {
     super(PreOrderBVO.class, "blineclose", UFBoolean.FALSE);
   }
   
 

   
   public boolean isAutoTransitable(PreOrderViewVO vo)
   {
     if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
       return false;
     }
     return getStateCalculateUtil().isAutoTransitRowOpen(vo);
   }
   
 
   public boolean isPrevStateValid(PreOrderViewVO vo)
   {
	   Bill38StateUtil statePriority = new Bill38StateUtil();
     return statePriority.canBeExecuteState(vo);
   }
   
 
 
   public boolean isRowOpen(PreOrderViewVO vo)
   {
     if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
       return false;
     }
     return getStateCalculateUtil().isRowOpen(vo);
   }
   
   public List<IState<PreOrderViewVO>> next()
   {
     List<IState<PreOrderViewVO>> list = new ArrayList();
     
     return list;
   }
   
   public void setState(PreOrderViewVO[] views)
   {
     AroundProcesser<PreOrderViewVO> processer = new AroundProcesser(StatePlugInPoint.Row38OpenState);
     
     TimeLog.logStart();
     PreOrderViewVO[] vos = (PreOrderViewVO[])processer.before(views);
     TimeLog.info("行打开前执行业务规则");
     
     TimeLog.logStart();
     super.setState(vos);
     TimeLog.info("修改表体状态为行打开");
     
     TimeLog.logStart();
     processer.after(vos);
     
     TimeLog.info("行打开后执行业务规则");
   }
   
   private State38CalculateUtil getStateCalculateUtil() {
     if (this.stateCalculateUtil == null) {
       this.stateCalculateUtil = new State38CalculateUtil();
     }
     return this.stateCalculateUtil;
   }




   @Override
   public IState<PreOrderVO> getTransitTargetState() {
	// TODO 自动生成的方法存根
	   return new Bill38OpenState();
   }






   
}

