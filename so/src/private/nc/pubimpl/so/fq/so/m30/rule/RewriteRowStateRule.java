package nc.pubimpl.so.fq.so.m30.rule;
 
 import java.util.ArrayList;
 import java.util.List;

import nc.bs.so.fq.state.pub.ShipOrderStateMachine;
import nc.bs.so.fq.state.row.RowCloseState;
import nc.bs.so.fq.state.row.RowOpenState;
 import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.ShipmentsViewVO;

 
 
 
 
 
 
 
 public class RewriteRowStateRule implements IRule<ShipmentsViewVO>
 {
   public RewriteRowStateRule() {}
   
   public void process(ShipmentsViewVO[] vos)
   {
     RowOpenState openState = new RowOpenState();
     RowCloseState closeState = new RowCloseState();
     
     List<ShipmentsViewVO> closeList = new ArrayList();
     List<ShipmentsViewVO> openList = new ArrayList();
     for (ShipmentsViewVO vo : vos) {
       if (openState.isRowOpen(vo)) {
         openList.add(vo);
       }
       if (closeState.isRowClose(vo)) {
         closeList.add(vo);
       }
     }
     setState(openList, openState);
     setState(closeList, closeState);
   }
   
   private void setState(List<ShipmentsViewVO> list, IState<ShipmentsViewVO> state) {
     int size = list.size();
     if (size <= 0) {
       return;
     }
     ShipmentsViewVO[] views = new ShipmentsViewVO[size];
     views = (ShipmentsViewVO[])list.toArray(views);
     ShipOrderStateMachine bo = new ShipOrderStateMachine();
     bo.setState(state, views);
   }

   
 }

