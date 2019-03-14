package nc.ui.so.qs.intoprod.input.handevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

public class HeadBeforeEditHander implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent event) {
		// TODO 自动生成的方法存根
		
		if("vfree1".equals(event.getKey()) || "vfree2".equals(event.getKey()) || "vfree3".equals(event.getKey())){
			
			  event.setReturnValue(false);
			  return;
				
		}
		
	}
	
	

}
