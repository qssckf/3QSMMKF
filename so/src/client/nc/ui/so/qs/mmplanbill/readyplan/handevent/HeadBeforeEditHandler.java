package nc.ui.so.qs.mmplanbill.readyplan.handevent;
 
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;


public class HeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{
 
  public HeadBeforeEditHandler() {}
  
  public void handleAppEvent(CardHeadTailBeforeEditEvent event){
	  
	  if("vfree1".equals(event.getKey()) || "vfree2".equals(event.getKey()) || "vfree3".equals(event.getKey())){
			
		  event.setReturnValue(false);
		  return;
			
	  }
	  
	  event.setReturnValue(Boolean.TRUE);
	  String editkey = event.getKey();
	  
	  if ("pk_dept".equals(editkey)) {
		  
		  DeptEditHandler handler = new DeptEditHandler();
		  handler.beforeEdit(event);
	  
	  }
	  
	  
    
  }
}

