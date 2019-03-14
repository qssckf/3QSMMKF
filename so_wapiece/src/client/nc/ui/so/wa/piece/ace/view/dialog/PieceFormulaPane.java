package nc.ui.so.wa.piece.ace.view.dialog;

import java.awt.Container;

import nc.ui.bill.tools.formulaeditor.FormulaRefPane;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.so.wa.piece.ace.view.PieceFormulaBatchBillTable;
import nc.ui.uif2.model.BatchBillTableModel;

import nc.vo.uif2.LoginContext;

public class PieceFormulaPane extends FormulaRefPane {
	
	private LoginContext context;
	private PieceFormulaBatchBillTable billtable;
	private BatchBillTableModel model;


	private boolean hasloadDlg=false;
	private PieceFormulaDialog dlg=null;
	private String  showText;
	

	public PieceFormulaDialog getDlg() {
		 if (this.dlg == null) {
			 this.dlg = new PieceFormulaDialog(this, "/nc/ui/so/wa/piece/ace/view/dialog/PieceFormula_Config.xml", getContext());
			 
			 this.hasloadDlg=true;
			 
			 this.dlg.updateListData();
		 	}
		 return this.dlg;
	}

	public void setDlg(PieceFormulaDialog dlg) {
		this.dlg = dlg;
	}

	private static final long serialVersionUID = 911170150050451063L;
	

	public boolean isHasloadDlg() {
		return hasloadDlg;
	}

	public void setHasloadDlg(boolean hasloadDlg) {
		hasloadDlg = hasloadDlg;
	}

	public PieceFormulaPane(Container parent,LoginContext context,PieceFormulaBatchBillTable billtable,BatchBillTableModel model) {
		super(parent);
		this.model=model;
		this.context=context;
		this.billtable=billtable;
		init();
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public PieceFormulaBatchBillTable getBilltable() {
		return billtable;
	}

	public void setBilltable(PieceFormulaBatchBillTable billtable) {
		this.billtable = billtable;
	}

	public BatchBillTableModel getModel() {
		return model;
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
	}
	
	@Override
	public void onButtonClicked() {
		String tmpString = "";
		String codeString = "";
		BillCardPanel cardpanel = this.billtable.getBillCardPanel();

		CardPanelValueUtils panel= new CardPanelValueUtils(cardpanel);
		int row=this.getModel().getSelectedIndex();
		
		tmpString=panel.getBodyStringValue(row, "formula");
		codeString=panel.getBodyStringValue(row, "code");

		
		getDlg().setFormulaDesc(tmpString);
		
		((PieceFormulaCodeRealEditorPanel)((PieceFormulaDialog)getDlg()).getFormulaValueRealEditorPanel()).setConvertedText(codeString);
		

		getDlg().showModal();
		if (getDlg().getResult() == 1) {
			tmpString = getDlg().getFormulaDesc();
			codeString = ((PieceFormulaDialog)getDlg()).getFormulacode();
			if (tmpString != null) {
				tmpString = tmpString.replaceAll("\n", "");
			}
			if (tmpString != null) {
				tmpString = tmpString.replaceAll("\r", "");
			}
			
			setShowText(tmpString);
			setText(tmpString);
			
			
			cardpanel.setBodyValueAt(codeString, row, "code");
			this.FormulaRule = tmpString;
		}
		getDlg().destroy();
	}

	private void setShowText(String tmpString) {
		// TODO 自动生成的方法存根
		 this.showText = tmpString;
	}

	public String getShowText() {
		return showText;
	}

	

}
