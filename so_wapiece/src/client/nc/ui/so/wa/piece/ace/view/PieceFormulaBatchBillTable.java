package nc.ui.so.wa.piece.ace.view;

import javax.swing.JComponent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaPane;

public class PieceFormulaBatchBillTable extends ShowUpableBatchBillTable {
	
	private PieceFormulaPane formulapanel;

	public PieceFormulaBatchBillTable() {}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		BillItem formula=this.getBillCardPanel().getBodyItem("formula");
		formula.setComponent(getFormulaPane());
		formula.setEdit(false);
		
		
		this.getBillCardPanel().setBillData(this.getBillCardPanel().getBillData());
	}

	private JComponent getFormulaPane() {
		// TODO 自动生成的方法存根
		if (null==this.formulapanel){
			
			this.formulapanel=new PieceFormulaPane(getModel().getContext().getEntranceUI(),getModel().getContext(),this,getModel());
		}
		
		return this.formulapanel;
		
	}

	@Override
	protected void onEdit() {
		// TODO 自动生成的方法存根
		super.onEdit();
		
		getBillCardPanel().getBodyItem("formula").setEdit(true);
		getBillCardPanel().getBodyItem("pk_group").setEdit(false);
		getBillCardPanel().getBodyItem("pk_org").setEdit(false);
		getBillCardPanel().getBodyItem("pk_org_v").setEdit(false);
		getBillCardPanel().getBodyItem("code").setEdit(false);
		getBillCardPanel().getBodyItem("creator").setEdit(false);
		getBillCardPanel().getBodyItem("creationtime").setEdit(false);
		getBillCardPanel().getBodyItem("modifier").setEdit(false);
		getBillCardPanel().getBodyItem("modifiedtime").setEdit(false);
	}
	
	

}
