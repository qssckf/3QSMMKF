package nc.ui.so.qs.intoprod.action;

import java.awt.Event;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;

public class RdQueryAction extends DefaultQueryAction{
	
	public RdQueryAction(){
		
		super();
		
		this.setBtnName("��ѯ�����Ų���ϸ");
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke((int)'P', Event.ALT_MASK));
		this.putValue(Action.SHORT_DESCRIPTION, "��ѯ_�����Ų���Ϣ (ALT+P)");
		
	}

	@Override
	protected void showQueryInfo() {
		// TODO �Զ����ɵķ������
	}

}
