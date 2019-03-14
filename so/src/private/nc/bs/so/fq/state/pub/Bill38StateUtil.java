package nc.bs.so.fq.state.pub;
/*    */ 

/*    */ import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.qs.sc.AggShipmentsVOMeta;
import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bill38StateUtil
/*    */ {
/*    */   public Bill38StateUtil() {}
/*    */   
/*    */   private boolean canBeExecuteState(PreOrderHVO head)
/*    */   {
/* 18 */     Integer status = head.getFstatusflag();
/* 19 */     boolean flag = (BillStatus.AUDIT.equalsValue(status)) || (BillStatus.CLOSED.equalsValue(status)) || (BillStatus.FREEZE.equalsValue(status));
/*    */     
/*    */ 
/*    */ 
/* 23 */     return flag;
/*    */   }
/*    */   
/*    */   public boolean canBeExecuteState(PreOrderViewVO view) {
/* 27 */     boolean flag = canBeExecuteState(view.getHead());
/* 28 */     return flag;
/*    */   }
/*    */   
/*    */   public boolean canBeExecuteState(PreOrderVO bill) {
/* 32 */     boolean flag = canBeExecuteState((PreOrderHVO) bill.getParent());
/* 33 */     return flag;
/*    */   }
/*    */ }

