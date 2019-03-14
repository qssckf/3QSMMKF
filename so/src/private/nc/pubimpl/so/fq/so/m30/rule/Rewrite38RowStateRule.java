 package nc.pubimpl.so.fq.so.m30.rule;
 
 import java.util.ArrayList;
 import java.util.List;
 import nc.bs.so.fq.state.pub.ShipOrderStateMachine;
import nc.bs.so.fq.state.row.Row38CloseState;
import nc.bs.so.fq.state.row.Row38OpenState;
import nc.bs.so.m38.state.PreOrderStateMachine;
 import nc.bs.so.m38.state.row.RowCloseState;
 import nc.bs.so.m38.state.row.RowOpenState;
 import nc.impl.pubapp.bill.state.IState;
 import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m38.entity.PreOrderViewVO;


 public class Rewrite38RowStateRule implements IRule<PreOrderViewVO>{
	 
   public Rewrite38RowStateRule() {}
   
   public void process(PreOrderViewVO[] vos){
	   
	   Row38OpenState openState = new Row38OpenState();
	   Row38CloseState closeState = new Row38CloseState();
     
     List<PreOrderViewVO> closeList = new ArrayList();
     List<PreOrderViewVO> openList = new ArrayList();
     
     for (PreOrderViewVO vo : vos) {
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
   
   private void setState(List<PreOrderViewVO> list, IState<PreOrderViewVO> state) {
     
	   int size = list.size();
	   if (size <= 0) {
		   return;
	   }
	   
	   PreOrderViewVO[] views = new PreOrderViewVO[size];
	   views = (PreOrderViewVO[])list.toArray(views);
	   PreOrderStateMachine bo = new PreOrderStateMachine();
	   bo.setState(state, views);
   }

   
 }

