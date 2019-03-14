package nc.ui.so.wa.piece.ace.view.dialog.action;

import java.awt.event.ActionEvent;

import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.FormulaOKAction;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaCodeRealEditorPanel;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaValidata;

public class PieceFormulaOKAction extends FormulaOKAction {

	private PieceFormulaCodeRealEditorPanel formulaValueEditorPanel;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
		PieceFormulaValidata formulaValidate = new PieceFormulaValidata(getFormulaRealEditorPanel(),this.getFormulaValueEditorPanel());
		if (!formulaValidate.validata((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel())) {
			return;
		}
		     
		String formulaText = getFormulaRealEditorPanel().getConvertedText();
		String[] formulas = splitFormulaText(formulaText);
		     
		StringBuffer formulaDescBuf = new StringBuffer();
		for (int i = 0; i < formulas.length; i++) {
			formulaDescBuf.append(formulas[i] + ";");
		}
		if (null == getDialog()) {
			return;
		}
		     
		getDialog().setFormulaDesc(formulaText);
		((PieceFormulaDialog)getDialog()).setHasEditFormula(true);
		((PieceFormulaDialog)getDialog()).setFormulacode(getFormulaValueEditorPanel().getConvertedText());
		getDialog().closeOK();
		
	}
	
	  	private String[] splitFormulaText(String formulaText)
	  	{
		  formulaText = formulaText.replaceAll("\n", "").replaceAll("\t", "");
		  return formulaText.split(";");
	  	}
	  
	  	public PieceFormulaCodeRealEditorPanel getFormulaValueEditorPanel() {
			return formulaValueEditorPanel;
	  	}

		public void setFormulaValueEditorPanel(
				PieceFormulaCodeRealEditorPanel formulaValueEditorPanel) {
			this.formulaValueEditorPanel = formulaValueEditorPanel;
		}

}
