package nc.ui.so.qs.intoprod.input.action;

import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.qs.intoprod.input.model.ItAppModel;

public class IntoRrodEditAction extends EditAction{
	
	@Override
	protected boolean isActionEnable() {
		// TODO �Զ����ɵķ������
		
		Boolean edit=((ItAppModel)this.getModel()).getEdit();
		
		return edit;
	}

}
