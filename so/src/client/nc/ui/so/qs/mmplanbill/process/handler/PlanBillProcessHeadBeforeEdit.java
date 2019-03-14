package nc.ui.so.qs.mmplanbill.process.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

public class PlanBillProcessHeadBeforeEdit implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent event) {
		// TODO 自动生成的方法存根
		
		if("vfree1".equals(event.getKey()) || "vfree2".equals(event.getKey()) || "vfree3".equals(event.getKey())){
			event.setReturnValue(false);
			return;
		}
		
		event.setReturnValue(true);
		
		
	}

}
