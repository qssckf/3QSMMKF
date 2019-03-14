package nc.ui.so.qs.m38.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UISplitPane;
import nc.ui.pub.bill.BillCardBeforeEditListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItemEvent;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.qs.mmplanbill.process.model.QsVOBillListPanelValueSetter;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.qs.sc.planbill.process.BomVersionVO;

public class BomInfoDialog extends UIDialog{
	
	private JPanel contPane;
	private TemplateInfo templateInfo;
	private transient BillManageModel parentModel;
	private UIPanel uipanel1;
	private UIPanel btnPanel;
	private UISplitPane BomListPanel;
	private BillListPanel leftBomPanel;
	private BillListPanel RightBomPanel;
	private UIButton btnCancel;
	private UIButton btnOk;
	private final Listener listener = new Listener();
	private final EditListener editListener = new EditListener();
	private final String funNode="4006200202";
	private BomVersionVO selectBomVo;
	private BomVersionVO[] InitBomVo;
	private IPlanBillSerive PlanService;
	
	public BomVersionVO getSelectBomVo() {
		return selectBomVo;
	}


	public void setSelectBomVo(BomVersionVO selectBomVo) {
		this.selectBomVo = selectBomVo;
	}
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}
	
	
	public void setInitData(BomVersionVO[] vos){
		this.InitBomVo=vos;
	}
	
	public BomVersionVO[] getInitData(){
		return this.InitBomVo;
	}
	
	class Listener implements ActionListener{
		
		Listener() {}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO 自动生成的方法存根
			
			try{
				
				BomInfoDialog.this.doAction(event);
				
			}catch(Exception e){
				ExceptionUtils.wrappException(e);
			}
			
		}
	}
	
	 public void doAction(ActionEvent e) throws BusinessException{
		 
		 if (e.getSource() == this.getBtnOk()) {
			 	closeOK();
		 } else if (e.getSource() == getBtnCancel()) {
			 closeCancel();
		 }
	 }
	
	
	
	public JPanel getContPane() {
		return contPane;
	}


	public void setContPane(JPanel contPane) {
		this.contPane = contPane;
	}

	public TemplateInfo getTemplateInfo() {
		return templateInfo;
	}


	public void setTemplateInfo(TemplateInfo templateInfo) {
		this.templateInfo = templateInfo;
	}


	public BillManageModel getParentModel() {
		return parentModel;
	}


	public void setParentModel(BillManageModel parentModel) {
		this.parentModel = parentModel;
	}


	public BomInfoDialog(BillManageModel model) throws Exception{
	
		this.setParentModel(model);
		
		initialize();
		
		initListener();
		
	}
	
	protected int getDialogHight(){
	   return 500;
	}
	 
	protected int getDialogWidth(){
		
	   return 900;
	   
	}
	
	private void initialize() throws BusinessException {
		
		try{
			setName("物料清单版本");
			setTitle("物料清单版本");
			setDefaultCloseOperation(2);
			setSize(getDialogWidth(), getDialogHight());
			setContentPane(getPanel());

		}catch(Throwable e){
			throw new BusinessException(e.getMessage());
		}

		
	}


	private JPanel getPanel() {
		// TODO 自动生成的方法存根
		if(this.contPane==null){
			
			this.contPane = new JPanel();
			this.contPane.setName("PreStepContentPane");
			this.contPane.setLayout(new BorderLayout());
			this.contPane.add(getUIPanel1(), "South");
			this.contPane.add(getListPanel(), "Center");
			
		}
		
		return this.contPane;
	}


	private UISplitPane getListPanel() {
		// TODO 自动生成的方法存根
		
		if(this.BomListPanel==null){
			
			this.BomListPanel = new UISplitPane();
						
			this.leftBomPanel = new BillListPanel();
			this.leftBomPanel.setName("BomLeftListPanel");
			this.leftBomPanel.loadTemplet(this.funNode, null, getParentModel().getContext().getPk_loginUser(), InvocationInfoProxy.getInstance().getGroupId(), "BomVerB");
			this.leftBomPanel.setMultiSelect(false);
			this.leftBomPanel.setEnabled(false);
			this.leftBomPanel.addBodyEditListener(this.editListener);
			
			this.RightBomPanel = new BillListPanel();
			this.RightBomPanel.setName("BomRightListPanel");
			this.RightBomPanel.loadTemplet(this.funNode, null, getParentModel().getContext().getPk_loginUser(), InvocationInfoProxy.getInstance().getGroupId(), "BomChB");
			this.RightBomPanel.setMultiSelect(false);
			this.RightBomPanel.setEnabled(false);
			
			this.BomListPanel.setLeftComponent(this.leftBomPanel);
			this.BomListPanel.setRightComponent(this.RightBomPanel);
			
			this.BomListPanel.setVisible(true);
			
			this.BomListPanel.setDividerLocation(450);

		}
		
		
		
		return this.BomListPanel;
	
		
	}


	private UIPanel getUIPanel1() {
		// TODO 自动生成的方法存根
		if (this.uipanel1 == null) {
			 this.uipanel1 = new UIPanel();
			 this.uipanel1.setLayout(new BorderLayout());
			 this.uipanel1.add(getUpButtonPanel(), "East");
		 }
		 
		return this.uipanel1;
	 
	}


	private UIPanel getUpButtonPanel() {
		// TODO 自动生成的方法存根
		if (this.btnPanel == null) {
		     
			this.btnPanel = new UIPanel();
		     
		    if ((null != getButton()) && (getButton().length > 0)) {
		       for (UIButton button : getButton()) {
		         this.btnPanel.add(button);
		       }
		    }
		}
		
		return this.btnPanel;
	}
	
	public UIButton[] getButton(){
		
		return new UIButton[] { getBtnOk(), getBtnCancel() };
		
	}
	
	public UIButton getBtnCancel() {
		
		if (this.btnCancel == null) {
		  
		  this.btnCancel = new UIButton();
		  this.btnCancel.setName("BtnCancel");
		  this.btnCancel.setText("取消");
		  this.btnCancel.setBounds(435, 15, 40, 22);
		}
		
		return this.btnCancel;
	
	}



	public UIButton getBtnOk() {
		if (this.btnOk == null) {
			this.btnOk = new UIButton();
			this.btnOk.setName("BtnOk");
			this.btnOk.setText("确认");
			this.btnOk.setBounds(435, 15, 40, 22);
		}
		return this.btnOk;
	}
	
	private void initListener(){
		UIButton[] btns = getButton();
		if ((btns != null) && (btns.length > 0)) {
			for (UIButton uiButton : btns) {
				uiButton.addActionListener(this.listener);
			}
		}
	}


	@Override
	public int showModal() {
		// TODO 自动生成的方法存根
		
		initData();

		return super.showModal();
	}

	private void initData() {
		// TODO 自动生成的方法存根
		
		CircularlyAccessibleValueObject[] initData = getInitData();
		
		if ((initData != null) && (initData.length > 0)) {
			
			this.leftBomPanel.getBillListData().setBodyValueVO(initData);
			this.leftBomPanel.getBodyBillModel().loadLoadRelationItemValue();
			
		}else{
			
			this.leftBomPanel.getBillListData().setBodyValueVO(new CircularlyAccessibleValueObject[0]);
			this.leftBomPanel.getBodyBillModel().loadLoadRelationItemValue();
			
		}
		
		
		
	}
	
	private class EditListener implements BillEditListener, BillEditListener2, BillCardBeforeEditListener{

		@Override
		public boolean beforeEdit(BillItemEvent arg0) {
			// TODO 自动生成的方法存根
			return false;
		}

		@Override
		public void afterEdit(BillEditEvent arg0) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {
			// TODO 自动生成的方法存根
			try{
				
				int row=e.getRow();
				
				CircularlyAccessibleValueObject bodyVOs=BomInfoDialog.this.leftBomPanel.getBodyBillModel().getBodyValueRowVO(row, BomVersionVO.class.getName());
				
				String pk_org=(String) bodyVOs.getAttributeValue("pk_org");
				String cbomid=(String) bodyVOs.getAttributeValue("cbomid");
				
				CircularlyAccessibleValueObject[] chvos=BomInfoDialog.this.getPlanService().queryBomleafChildren(cbomid, pk_org);
				
				if(chvos!=null && chvos.length>0){
					
					BomInfoDialog.this.selectBomVo=(BomVersionVO) bodyVOs;
					
					BomInfoDialog.this.RightBomPanel.getBillListData().setBodyValueVO(chvos);
					BomInfoDialog.this.RightBomPanel.getBodyBillModel().loadLoadRelationItemValue();
					
				}else{
					BomInfoDialog.this.RightBomPanel.getBillListData().setBodyValueVO(new CircularlyAccessibleValueObject[0]);
					BomInfoDialog.this.RightBomPanel.getBodyBillModel().loadLoadRelationItemValue();
				}
				
			}catch(Exception ex){
				ExceptionUtils.wrappException(ex);
			}
		
						
			
		}

		@Override
		public boolean beforeEdit(BillEditEvent arg0) {
			// TODO 自动生成的方法存根
			return false;
		}
		
		
	}
	
	

}
