package nc.ui.so.wa.piece.fetch.ace.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;

import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.ui.org.ref.FactoryDefaultRefModel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.IBillModelRowStateChangeEventListener;
import nc.ui.pub.bill.RowStateChangeEvent;
import nc.ui.so.wa.piece.fetch.action.DisplayDetailAction;
import nc.ui.so.wa.piece.fetch.action.FetchDataAction;
import nc.ui.so.wa.piece.fetch.uidataset.PieceBusSetUIData;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.BillManageModel;
import nc.ui.wa.ref.WaPeriodRefTreeModel;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;

public class BusFetchDataMainPanel extends UIPanel implements ValueChangedListener{
	
	private UIPanel upPanel;
	private UIRefPane refFactory;
	private BillManageModel model;
	private UIPanel bussineType;
	private UIComboBox tetFetchDataObj;
	private UIRefPane waperiodrefpanel;
	private FetchDataAction fetchDataAction;
	private DisplayDetailAction displaydetailAction;
	private BillListView billListView;
	
	
	public void setBillListView(BillListView billListView) {
		this.billListView = billListView;
	}

	public FetchDataAction getFetchDataAction() {
		return fetchDataAction;
	}

	public void setFetchDataAction(FetchDataAction fetchDataAction) {
		this.fetchDataAction = fetchDataAction;
	}

	public DisplayDetailAction getDisplaydetailAction() {
		return displaydetailAction;
	}

	public void setDisplaydetailAction(DisplayDetailAction displaydetailAction) {
		this.displaydetailAction = displaydetailAction;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public void initUI() throws SQLException{
		
		setLayout(new BorderLayout());
		add(getUpPanel(),"North");
		add(this.getBillListView(),"Center");

		this.refreshDates();
	}
	

	public BillListView getBillListView() {
		// TODO �Զ����ɵķ������
		
		this.billListView.getBillListPanel().getBodyBillModel().addRowStateChangeEventListener(new BillRowStateChangeEvent());
		
		return billListView;
	}
	
	public UIRefPane getRefFactory() {
		// TODO �Զ����ɵķ������
		
		if (this.refFactory==null){
			this.refFactory=new UIRefPane();
			this.refFactory.setPreferredSize(new Dimension(200, 20));
			this.refFactory.addValueChangedListener(this);
			FactoryDefaultRefModel factoryModel = new FactoryDefaultRefModel();
			this.refFactory.setRefModel(factoryModel);
			this.initPermisionFactory();
		}
	
		return this.refFactory;
	}
	
	private void initPermisionFactory(){
		
		String[] hesPermissionOrgs = getModel().getContext().getPkorgs();
		String wherePart = null;
		if ((hesPermissionOrgs == null) || (hesPermissionOrgs.length == 0)) {
			wherePart = "1=2";
		}else {
			SqlBuilder sb = new SqlBuilder();
			sb.append("pk_factory", hesPermissionOrgs);
			wherePart = sb.toString();
		}
		this.refFactory.getRefModel().setWherePart(wherePart);
	}
	
	public UIPanel getBusFetchType() {
		// TODO �Զ����ɵķ������
		if(this.bussineType==null){
			this.bussineType=new UIPanel(); 
			this.bussineType.setLayout(new FlowLayout(0));
			this.bussineType.add(new UILabel("ҵ�����"));
			this.bussineType.add(getFetchDataObj());
		}
		
		return this.bussineType;
	}


	public UIComboBox getFetchDataObj() {
		// TODO �Զ����ɵķ������
		
		if(this.tetFetchDataObj==null){
			this.tetFetchDataObj=new UIComboBox();
			this.tetFetchDataObj.addItem("����");
			this.tetFetchDataObj.addItem("����");
			this.tetFetchDataObj.addActionListener(new EventActionListener());
		}
		
		return this.tetFetchDataObj;
	}
	
	public void refreshDates() throws SQLException {
		// TODO �Զ����ɵķ������
		
		this.getBillListView().getBillListPanel().getHeadBillModel().clearBodyData();
		PieceBusSetUIData PbsetUI=new PieceBusSetUIData();
		PbsetUI.setUIItemforFetchRule(this,this.getRefFactory().getRefPK(),this.model.getContext().getPk_group());
		getBillListView().getBillListPanel().getBodyBillModel().loadLoadRelationItemValue();
		
		getBillListView().getBillListPanel().getHeadTable().repaint();
		getBillListView().getBillListPanel().getHeadTable().updateUI();
		
		this.checkForActionEnable();
	}


	private void checkForActionEnable() {
		// TODO �Զ����ɵķ������
		
		PieceFetchRecordVO[] vos=(PieceFetchRecordVO[]) this.getBillListView().getBillListPanel().getBodyBillModel().getBodySelectedVOs(PieceFetchRecordVO.class.getName());
		
		if(MMArrayUtil.isEmpty(vos)){
			this.getFetchDataAction().setEnabled(false);
			this.getDisplaydetailAction().setEnabled(false);
		}else{
			this.getFetchDataAction().setEnabled(true);
			this.getDisplaydetailAction().setEnabled(true);
		}
		
		
		
	}

	private UIPanel getUpPanel() {
		// TODO �Զ����ɵķ������
		
		if(this.upPanel==null){
			
			this.upPanel=new UIPanel();
			this.upPanel.setLayout(new FlowLayout(0));
			
			//��ӹ������տؼ�
			this.upPanel.add(new UILabel("����"));
			this.upPanel.add(getRefFactory());
			

			//��ӷ���ѡ���ؼ�
			this.upPanel.add(getBusFetchType());
		
			//��ӻ���ڼ�ѡ���ؼ�
			this.upPanel.add(new UILabel("н���ڼ�"));
			this.upPanel.add(getWaPeriodRefPane());
		}
		
		
		return upPanel;
	}
	
	public UIRefPane getWaPeriodRefPane() {
		
		if (this.waperiodrefpanel==null){
			this.waperiodrefpanel=new UIRefPane();
			this.waperiodrefpanel.addValueChangedListener(this);
			this.waperiodrefpanel.setSize(new Dimension(200, 20));
			this.waperiodrefpanel.setButtonFireEvent(true);
	
			WaPeriodRefTreeModel refmodel =new WaPeriodRefTreeModel(null);
			refmodel.setRefNodeName("н���ڼ�");
			
			this.waperiodrefpanel.setRefModel(refmodel);
		}
		
		return this.waperiodrefpanel;
		
	}


	@Override
	public void valueChanged(ValueChangedEvent event) {
		// TODO �Զ����ɵķ������
		try{
			
			ShowStatusBarMsgUtil.showStatusBarMsg("", this.getModel().getContext());
			
			this.refreshDates();

		}catch(SQLException e){
			
			ExceptionUtils.wrapException(e);
			
		}
	
		
	}
	
	

	
	class EventActionListener implements ActionListener{
		
		EventActionListener() {}

		public void actionPerformed(ActionEvent e){
			try {
				BusFetchDataMainPanel.this.refreshDates();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				ExceptionUtils.wrapException(e1);
			}
		}
	}
	
	class BillRowStateChangeEvent implements IBillModelRowStateChangeEventListener {
		
		BillRowStateChangeEvent(){}
		
		public void valueChanged(RowStateChangeEvent event) { 
			
			BusFetchDataMainPanel.this.checkForActionEnable(); 
			
		}
	}
	
	
}
