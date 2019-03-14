package nc.bs.so.fq.state.bill;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

import nc.bs.so.fq.state.pub.State38CalculateUtil;
/*    */ import nc.bs.so.m38.plugin.StatePlugInPoint;
/*    */ import nc.bs.so.m38.state.BillStateUtil;
/*    */ import nc.bs.so.m38.state.StateCalculateUtil;
/*    */ import nc.bs.so.m38.state.row.RowCloseState;
/*    */ import nc.impl.pubapp.bill.state.AbstractBillState;
/*    */ import nc.impl.pubapp.bill.state.IState;
/*    */ import nc.impl.pubapp.bill.state.ITransitionState;
/*    */ import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
/*    */ import nc.vo.pubapp.pattern.log.TimeLog;
/*    */ import nc.vo.so.m38.entity.PreOrderVO;
/*    */ import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bill38CloseState
/*    */   extends AbstractBillState<PreOrderVO>
/*    */   implements ITransitionState<PreOrderVO, PreOrderViewVO>
/*    */ {
/*    */   private State38CalculateUtil stateCalculateUtil;
/*    */   
/*    */   public Bill38CloseState()
/*    */   {
/* 33 */     super("fstatusflag", BillStatus.CLOSED.value());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public IState<PreOrderViewVO> getTransitTargetState()
/*    */   {
/* 41 */     return new RowCloseState();
/*    */   }
/*    */   
/*    */   public boolean isAutoTransitable(PreOrderVO vo)
/*    */   {
/* 46 */     if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     return getStateCalculateUtil().isAutoTransitBillClose(vo);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isPrevStateValid(PreOrderVO vo)
/*    */   {
/* 56 */     BillStateUtil statePriority = new BillStateUtil();
/* 57 */     return statePriority.canBeExecuteState(vo);
/*    */   }
/*    */   
/*    */   public List<IState<PreOrderVO>> next()
/*    */   {
/* 62 */     List<IState<PreOrderVO>> list = new ArrayList();
/* 63 */     return list;
/*    */   }
/*    */   
/*    */   public void setState(PreOrderVO[] bills)
/*    */   {
/* 68 */     AroundProcesser<PreOrderVO> processer = new AroundProcesser(StatePlugInPoint.BillCloseState);
/*    */     
/*    */ 
/* 71 */     TimeLog.logStart();
/* 72 */     PreOrderVO[] vos = (PreOrderVO[])processer.before(bills);
/* 73 */     TimeLog.info("整单关闭前执行业务规则");
/*    */     
/* 75 */     TimeLog.logStart();
/* 76 */     super.setState(vos);
/* 77 */     TimeLog.info("整单关闭前执行业务规则");
/*    */     
/* 79 */     TimeLog.logStart();
/* 80 */     processer.after(vos);
/* 81 */     TimeLog.info("整单关闭后执行业务规则");
/*    */   }
/*    */   
/*    */   private State38CalculateUtil getStateCalculateUtil() {
/* 85 */     if (this.stateCalculateUtil == null) {
/* 86 */       this.stateCalculateUtil = new State38CalculateUtil();
/*    */     }
/* 88 */     return this.stateCalculateUtil;
/*    */   }
/*    */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_sellingrequisition.jar
 * Qualified Name:     nc.bs.so.m38.state.bill.BillCloseState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */