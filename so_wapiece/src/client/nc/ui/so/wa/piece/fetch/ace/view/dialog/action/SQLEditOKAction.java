package nc.ui.so.wa.piece.fetch.ace.view.dialog.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import nc.ui.pub.formula.ui.JEditTextArea;
import nc.ui.so.wa.piece.fetch.ace.view.SQLEditDialog;

public class SQLEditOKAction extends AbstractAction {
	
	private SQLEditDialog dlg;

	public SQLEditOKAction(SQLEditDialog sqlEditDialog) {
		// TODO �Զ����ɵĹ��캯�����
		this.dlg=sqlEditDialog;
	}

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		// TODO �Զ����ɵķ������
				this.dlg.closeOK();
	}

}
