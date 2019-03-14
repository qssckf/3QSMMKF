package nc.ui.so.qs.mmplanbill.readyplan.view;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.table.BillTableBooleanCellRenderer;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.qs.mmplanbill.process.model.BomChildAppModel;
import nc.ui.so.qs.mmplanbill.process.model.QsVOBillListPanelValueSetter;
import nc.ui.so.qs.mmplanbill.process.view.BomVersionTableCellRenderer;
import nc.ui.so.qs.mmplanbill.readyplan.model.MachineModel;
import nc.ui.so.qs.mmplanbill.readyplan.model.MmOrderModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class MachineInfoListView extends ShowUpableBillListView implements ListSelectionListener{
	
	public int selectIndex = 0;
	private Boolean IsChange;
	private IPlanBillSerive PlanService;
	private IRdMmService rdservice;
	private MmOrderModel cmodel;
	private Boolean IsFrist;
	private ShowUpableBillForm card;
	private BomVersionTableCellRenderer cellRenderer;
	
	public ShowUpableBillForm getCard() {
		return card;
	}

	public void setCard(ShowUpableBillForm card) {
		this.card = card;
	}
	
	public MmOrderModel getCmodel() {
		return cmodel;
	}

	public void setCmodel(MmOrderModel cmodel) {
		this.cmodel = cmodel;
	}

	
	public IRdMmService getRdservice() {
		
		if(this.rdservice==null){
			this.rdservice=NCLocator.getInstance().lookup(IRdMmService.class);
		}
		
		return rdservice;
	}


	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.IsFrist=true;
		
		BillListPanel billlist = this.getBillListPanel();
		
		//建立单据项目超链接操作事件
		billlist.getHeadItem("opera").addBillItemHyperlinkListener(new BillItemHyperlinkListener(){

			@Override
			public void hyperlink(BillItemHyperlinkEvent event) {
				// TODO 自动生成的方法存根
				
				String maid = (String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(event.getRow(), "pk_maschine");
				String machcode = (String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(event.getRow(), "machcode");
				
				MachineInfoListView.this.getCard().getBillCardPanel().getHeadItem("pk_machine").setValue(maid);
				MachineInfoListView.this.getCard().getBillCardPanel().getHeadItem("def2").setValue(machcode);
				
				
			}
			
		});
		
		initCellRenderer(this.billListPanel);
		
		getBillListPanel().getParentListPanel().getTable().getSelectionModel().addListSelectionListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自动生成的方法存根
		 if (!e.getValueIsAdjusting()) {
			 int headRow = ((ListSelectionModel)e.getSource()).getAnchorSelectionIndex();
			 
			 if(headRow==this.selectIndex && !this.IsFrist){
				 this.IsChange=false;
			 }else{
				 
				 if(this.IsFrist){
					 
					 this.IsFrist=false;
					 
				 }

				 this.IsChange=true;
				 
			 }
			 
			 this.selectIndex = headRow;
			 getCellRenderer().setCurrentRow(headRow);
			 if (headRow >= 0) {
				 headRowChange(headRow);
				 
				 if(IsChange){
					 try{
						 SetMmOrderInfo(headRow);
					 }catch(Exception ex){
						 ExceptionUtils.wrappException(ex);
					 }
					 
				 }
				 
			 }
		 }
	}
	
	
	
	private void SetMmOrderInfo(int headRow) throws BusinessException{
		// TODO 自动生成的方法存根
		
		String maid = (String) getBillListPanel().getParentListPanel().getTableModel().getValueAt(headRow, "pk_maschine");
		
		SuperVO[] mmvos=this.getRdservice().queryPmoVObyPks(new String []{maid});
		
		if(mmvos!=null && mmvos.length>0){
			this.getCmodel().initModel(mmvos);
		}else{
			this.getCmodel().initModel(null);
		}
		
	}

	private void headRowChange(int headRow) {
		// TODO 自动生成的方法存根
		
		int state = getBillListPanel().getParentListPanel().getTableModel().getRowState(headRow);
		   
		getBillListPanel().repaint();
		if (state == -1) {
		     getBillListPanel().getChildListPanel().cancelSelectAllTableRow();
		     getBillListPanel().updateUI();
		}
		
		
		
	}
	
	
	private void initCellRenderer(BillListPanel panel)
	{
	   TableColumnModel columnModel = panel.getHeadTable().getColumnModel();
	   for (int i = 0; i < columnModel.getColumnCount(); i++) {
	     TableColumn column = columnModel.getColumn(i);
	     
	     if (!(column.getCellRenderer() instanceof BillTableBooleanCellRenderer))
	     {
	
	       column.setCellRenderer(getCellRenderer());
	     }
	   }
	}
	
	private BomVersionTableCellRenderer getCellRenderer()
	{
	     if (this.cellRenderer == null) {
	       this.cellRenderer = new BomVersionTableCellRenderer(this.billListPanel.getHeadBillModel(), false);
	     }
	     
	     return this.cellRenderer;
	}
	

	
	

}
