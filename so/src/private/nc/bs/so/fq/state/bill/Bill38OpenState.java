package nc.bs.so.fq.state.bill;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;

import nc.bs.so.fq.state.pub.State38CalculateUtil;
/*    */ import nc.bs.so.m38.plugin.StatePlugInPoint;
/*    */ import nc.bs.so.m38.state.BillStateUtil;
/*    */ import nc.bs.so.m38.state.StateCalculateUtil;
/*    */ import nc.bs.so.m38.state.row.RowOpenState;
/*    */ import nc.impl.pubapp.bill.state.AbstractBillState;
/*    */ import nc.impl.pubapp.bill.state.IState;
/*    */ import nc.impl.pubapp.bill.state.ITransitionState;
/*    */ import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
/*    */ import nc.vo.pubapp.pattern.log.TimeLog;
/*    */ import nc.vo.so.m38.entity.PreOrderHVO;
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
/*    */ public class Bill38OpenState
/*    */   extends AbstractBillState<PreOrderVO>
/*    */   implements ITransitionState<PreOrderVO, PreOrderViewVO>
/*    */ {
/*    */   private State38CalculateUtil stateCalculateUtil;
/*    */   
/*    */   public Bill38OpenState()
/*    */   {
/* 33 */     super("fstatusflag", BillStatus.AUDIT.value());
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public IState<PreOrderViewVO> getTransitTargetState()
/*    */   {
/* 41 */     return new RowOpenState();
/*    */   }
/*    */   
/*    */   public boolean isAutoTransitable(PreOrderVO vo)
/*    */   {
/* 46 */     if ((isThisState(vo)) || (!isPrevStateValid(vo))) {
/* 47 */       return false;
/*    */     }
/*    */     
/*    */ 
/* 51 */     return getStateCalculateUtil().isAutoTransitBillOpen(vo);
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isPrevStateValid(PreOrderVO vo)
/*    */   {
/* 57 */     BillStateUtil statePriority = new BillStateUtil();
/* 58 */     return statePriority.canBeExecuteState(vo);
/*    */   }
/*    */   
/*    */   public boolean isThisState(PreOrderViewVO view) {
/* 62 */     Integer value = view.getHead().getFstatusflag();
/* 63 */     return BillStatus.AUDIT.equalsValue(value);
/*    */   }
/*    */   
/*    */   public List<IState<PreOrderVO>> next()
/*    */   {
/* 68 */     List<IState<PreOrderVO>> list = new ArrayList();
/* 69 */     return list;
/*    */   }
/*    */   
/*    */   public void setState(PreOrderVO[] bills)
/*    */   {
/* 74 */     AroundProcesser<PreOrderVO> processer = new AroundProcesser(StatePlugInPoint.BillOpenState);
/*    */     
/*    */ 
/* 77 */     TimeLog.logStart();
/* 78 */     PreOrderVO[] vos = (PreOrderVO[])processer.before(bills);
/* 79 */     TimeLog.info("整单打开前执行业务规则");
/*    */     
/* 81 */     TimeLog.logStart();
/* 82 */     super.setState(vos);
/* 83 */     TimeLog.info("修改表头状态为整单打开");
/*    */     
/* 85 */     TimeLog.logStart();
/* 86 */     processer.after(vos);
/* 87 */     TimeLog.info("整单打开后执行业务规则");
/*    */   }
/*    */   
/*    */   private State38CalculateUtil getStateCalculateUtil() {
/* 91 */     if (this.stateCalculateUtil == null) {
/* 92 */       this.stateCalculateUtil = new State38CalculateUtil();
/*    */     }
/* 94 */     return this.stateCalculateUtil;
/*    */   }
/*    */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_sellingrequisition.jar
 * Qualified Name:     nc.bs.so.m38.state.bill.BillOpenState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */