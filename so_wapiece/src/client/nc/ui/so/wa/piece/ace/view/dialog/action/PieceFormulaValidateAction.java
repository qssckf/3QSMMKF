package nc.ui.so.wa.piece.ace.view.dialog.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.FormulaValidateAction;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaCodeRealEditorPanel;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaValidata;
import nc.vo.cm.driver.entity.CMDriverLangConst;

public class PieceFormulaValidateAction extends FormulaValidateAction {

private PieceFormulaCodeRealEditorPanel formulaValueEditorPanel;
	


public PieceFormulaValidateAction(){}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO 自动生成的方法存根
	PieceFormulaValidata formulavalidata=new PieceFormulaValidata(this.getFormulaRealEditorPanel(),this.getFormulaValueEditorPanel());
	if (formulavalidata.validata((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel())){
		MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), "提示", "公式验证正确!");
	}
	
}

public PieceFormulaCodeRealEditorPanel getFormulaValueEditorPanel() {
	return formulaValueEditorPanel;
}

public void setFormulaValueEditorPanel(
		PieceFormulaCodeRealEditorPanel formulaValueEditorPanel) {
	this.formulaValueEditorPanel = formulaValueEditorPanel;
}

}
