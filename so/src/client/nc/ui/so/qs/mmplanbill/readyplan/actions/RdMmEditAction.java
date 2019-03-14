package nc.ui.so.qs.mmplanbill.readyplan.actions;

import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.qs.mmplanbill.readyplan.model.RdMmModel;

public class RdMmEditAction extends EditAction{

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		
		Boolean edit=((RdMmModel)this.getModel()).getEdit();
		
		return edit;
	}
	
	

}
