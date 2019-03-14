package nc.bs.so.fq.state.pub;
/*    */ 

/*    */ import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.qs.sc.AggShipmentsVOMeta;
import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BillStateUtil
/*    */ {
/*    */   public BillStateUtil() {}
/*    */   
/*    */   private boolean canBeExecuteState(ShipmentsVO head)
/*    */   {
/* 18 */     Integer status = head.getFstatusflag();
/* 19 */     boolean flag = (BillStatus.AUDIT.equalsValue(status)) || (BillStatus.CLOSED.equalsValue(status)) || (BillStatus.FREEZE.equalsValue(status));
/*    */     
/*    */ 
/*    */ 
/* 23 */     return flag;
/*    */   }
/*    */   
/*    */   public boolean canBeExecuteState(ShipmentsViewVO view) {
/* 27 */     boolean flag = canBeExecuteState(view.getHead());
/* 28 */     return flag;
/*    */   }
/*    */   
/*    */   public boolean canBeExecuteState(AggShipmentsVOMeta bill) {
/* 32 */     boolean flag = canBeExecuteState((ShipmentsVO) bill.getParent());
/* 33 */     return flag;
/*    */   }
/*    */ }

