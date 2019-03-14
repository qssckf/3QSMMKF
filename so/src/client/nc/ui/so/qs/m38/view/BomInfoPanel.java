package nc.ui.so.qs.m38.view;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.qs.sc.planbill.process.BomVersionVO;

public class BomInfoPanel extends UIRefPane{
	
	private BillManageModel appModel;
	private ShowUpableBillForm editor;
	private BomInfoDialog BomDialog;
	private int selectRow;
	private IPlanBillSerive PlanService;
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}

	
	
	public BomInfoPanel(ShowUpableBillForm billform){
		
		this.editor=billform;
		this.appModel=(BillManageModel) billform.getModel();
		
	}
	
	public int getSelectRow() {
		return selectRow;
	}
	
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	public void onButtonClicked() {
		// TODO 自动生成的方法存根
		try {
			
			this.BomDialog=new BomInfoDialog(this.appModel);
			
			this.BomDialog.setInitData(this.getInitData(this.getSelectRow()));
			
			this.BomDialog.showModal();
			
			if(1==this.BomDialog.getResult()){
				
				BomVersionVO selectvo=this.BomDialog.getSelectBomVo();
				
				if(selectvo!=null){
					
					this.editor.getBillCardPanel().setBodyValueAt(selectvo.getBomversion(), this.selectRow, "vbdef11");
					this.editor.getBillCardPanel().setBodyValueAt(selectvo.getCbomid(), this.selectRow, "vbdef12");
					
					this.editor.getBillCardPanel().stopEditing();
					
				}
				
				
			}
			
		
		} catch (Exception e) {
				// TODO 自动生成的 catch 块
			
			ExceptionUtils.wrappException(e);
		}
			
	}
	
	
	
	private BomVersionVO[] getInitData(int selectRow) throws BusinessException{
		
		IKeyValue keyValue = new CardKeyValue(this.editor.getBillCardPanel());
		
		String pk_org=keyValue.getBodyStringValue(selectRow, "pk_org");
		String matrial=keyValue.getBodyStringValue(selectRow, "cmaterialvid");
		
		BomVersionVO[] bomverVos=(BomVersionVO[]) this.getPlanService().queryBomVersion(matrial,pk_org);
		
		return bomverVos;

	}
	

}
