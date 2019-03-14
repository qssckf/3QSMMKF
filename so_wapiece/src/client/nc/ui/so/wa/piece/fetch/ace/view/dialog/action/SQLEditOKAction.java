package nc.ui.so.wa.piece.fetch.ace.view.dialog.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import nc.ui.pub.formula.ui.JEditTextArea;
import nc.ui.so.wa.piece.fetch.ace.view.SQLEditDialog;

public class SQLEditOKAction extends AbstractAction {
	
	private SQLEditDialog dlg;

	public SQLEditOKAction(SQLEditDialog sqlEditDialog) {
		// TODO 自动生成的构造函数存根
		this.dlg=sqlEditDialog;
	}

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		// TODO 自动生成的方法存根
				this.dlg.closeOK();
	}

}
