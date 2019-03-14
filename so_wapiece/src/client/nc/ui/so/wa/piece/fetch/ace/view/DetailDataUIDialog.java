package nc.ui.so.wa.piece.fetch.ace.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.fetch.IBillTempletService;
import nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain;
import nc.ui.bd.ref.model.AccperiodRefModel;
import nc.ui.cm.bill.fetchdata.scale.FetchDataScaleUtil;
import nc.ui.cm.pub.framework.scale.ScaleDataInfo;
import nc.ui.hr.uif2.action.print.ExportListAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.wa.piece.fetch.refmodel.DeptRefModel;
import nc.ui.so.wa.piece.fetch.refmodel.PieceProductVORefModel;
import nc.ui.uif2.actions.QueryAction;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.cm.fetchdata.cmconst.FetchDataLangConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;
import nc.vo.uif2.LoginContext;

public class DetailDataUIDialog extends UIDialog implements ValueChangedListener {

	private UIPanel detailupPanel;
	private UIPanel detaildownPanel;
	private BusFetchDataDialogBillList downListPanel;
	private AbstractAppModel model;
	private String nodecode;
	private UITextField fetchText;
	private UITextField periodText;
	private IPieceFetchInfoVOMaintain IPfetchCreateDataService;
	private IBillTempletService templetquery;
	private UIRefPane refproduct;
	private UIRefPane refdept;
	private PieceFetchRecordVO fetchvo;
	private UIRefPane refQC;
	private ExportListAction outputaction;
	private LoginContext context;
	
	

	public LoginContext getContext() {
		return context;
	}


	public void setContext(LoginContext context) {
		this.context = context;
	}


	public PieceFetchRecordVO getFetchvo() {
		return fetchvo;
	}


	public void setFetchvo(PieceFetchRecordVO fetchvo) {
		this.fetchvo = fetchvo;
	}


	public String getNodecode() {
		return nodecode;
	}


	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}

	public UITextField getPeriodText() {
		if (this.periodText==null){
			this.periodText=new UITextField();
			this.periodText.setEditable(false);
			this.periodText.setPreferredSize(new Dimension(80,22));
		}
		return periodText;
	}


	public UITextField getFetchText() {
		if (this.fetchText==null){
			this.fetchText=new UITextField();
			this.fetchText.setEditable(false);
			this.fetchText.setPreferredSize(new Dimension(170, 22));
			
		}
		return this.fetchText;
	}


	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	public DetailDataUIDialog(){
		
	}
	
	public void initUI(PieceFetchRecordVO vos,BusFetchDataMainPanel panel,LoginContext context) throws Exception{
		
		if (vos.getFetchtype().equals("1")){
			this.setNodecode("saleout");
		}else if(vos.getFetchtype().equals("2")){
			this.setNodecode("prodout");
		}
		
		this.setFetchvo(vos);
		this.setContext(context);
		
		setTitle("取数明细信息");
		setSize(new Dimension(1100, 600));
		
		this.setContentPane(getUIDialogContentPane(context));
		initBillTemplet(panel);
		initData(vos);
	}
	
	private void  initBillTemplet(BusFetchDataMainPanel panel) throws BusinessException, SQLException {
		// TODO 自动生成的方法存根
		
		this.getDownListPanel().getBillListPanel().setBillType(this.nodecode);
		this.getDownListPanel().getBillListPanel().setOperator(this.model.getContext().getPk_loginUser());
		this.getDownListPanel().getBillListPanel().setCorp(this.model.getContext().getPk_org());
		
		try {
			
			BillTempletVO template=((IBillTempletService)NCLocator.getInstance().lookup(IBillTempletService.class)).queryByID(this.nodecode,this.model.getContext().getPk_group());
			
			if(template==null){
				 MessageDialog.showErrorDlg(panel, "错误", "没有找到定义的模板信息！");
			 }else{
				 this.downListPanel.getBillListPanel().setListData(new BillListData(template));
				 
//				 this.downListPanel.setListData(new BillListData(template));
				 this.downListPanel.setMaximumSize(new Dimension(700, 500));
				 this.downListPanel.setPreferredSize(new Dimension(700, 500));
				 this.downListPanel.repaint();
				 
				 FetchDataScaleUtil scaleutil = new FetchDataScaleUtil(new ScaleDataInfo());
				 scaleutil.setListScale(this.downListPanel.getBillListPanel(), this.model.getContext().getPk_group(), this.model.getContext().getPk_org());

			 }

			
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			throw new BusinessException(e);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			throw new SQLException(e);
		}

	}



	private void initData(PieceFetchRecordVO vos) {
		// TODO 自动生成的方法存根
		
		UFDouble t1=UFDouble.ZERO_DBL;
		
		this.getFetchText().setText(vos.name);
		this.getPeriodText().setText(vos.getAccountperiod());
		
	
		Class clazz2 = null;
		
		try {
			clazz2=Class.forName(vos.getVoclass2());
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappBusinessException("找不到类型名为：" + vos.getVoclass2() + " 的类型！");
		}
		
		SuperVO[] vodata = null;
		try {
			vodata = this.getQueryService().getDetailInfoVO(clazz2, this.CreateCondition(vos.getAccountperiod(), vos.getCode()));
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.wrappException(e);
		}
		
		this.getDownListPanel().getBillListPanel().getHeadBillModel().setBodyDataVO(vodata);
		this.getDownListPanel().getBillListPanel().getHeadBillModel().loadLoadRelationItemValue();
		
		
		for(SuperVO vo:vodata){
			
			t1=t1.add((UFDouble) vo.getAttributeValue("num"));
		}
		
		this.getDownListPanel().getBillListPanel().getHeadBillModel().getTotalTableModel().setValueAt(t1.toString(), 0, 8);
		
	}
	
	private String CreateCondition(String accountpeorid,String code){
		
		List<String> queryparam=new ArrayList();
		String sql="";
		queryparam.add(" and period='"+accountpeorid+"' and code='"+code+"'");
		
		if(this.getNodecode().equals("output")){
			if(this.getPieceProductRefPanel().getRefPK()!=null&&this.getPieceProductRefPanel().getRefPK()!=""){
				queryparam.add(" and product='"+this.getPieceProductRefPanel().getRefPK()+"'");
			}
			if(this.getDeptRefPanel().getRefPK()!=null&&this.getDeptRefPanel().getRefPK()!=""){
				queryparam.add(" and deptid='"+this.getDeptRefPanel().getRefPK()+"'");
			}
			
		}else if(this.getNodecode().equals("saleout")){
			if(this.getPieceProductRefPanel().getRefPK()!=null&&this.getPieceProductRefPanel().getRefPK()!=""){
				queryparam.add(" and productid='"+this.getPieceProductRefPanel().getRefPK()+"'");
			} 
		}
		
		for(String str:queryparam){
			sql=sql+str;
		}
		
		return sql;

	}

	private IPieceFetchInfoVOMaintain getQueryService() {
		// TODO 自动生成的方法存根
		if (this.IPfetchCreateDataService==null){
			this.IPfetchCreateDataService=(IPieceFetchInfoVOMaintain)NCLocator.getInstance().lookup(IPieceFetchInfoVOMaintain.class);
		}
		return this.IPfetchCreateDataService;
	}


	private JPanel getUIDialogContentPane(LoginContext context) {
		// TODO 自动生成的方法存根
		 JPanel UIDialogContentPane = null;
		 
		 try {
			 UIDialogContentPane = new JPanel();
			 UIDialogContentPane.setName("UIDialogContentPane");
			 UIDialogContentPane.setLayout(new BorderLayout());
			 UIDialogContentPane.add(getDetailDlgUpPanel(context), "North");
			 UIDialogContentPane.add(getDetailDlgDownPanel(context), "Center");
		 }
		 catch (Exception ivjExc) {
			 ExceptionUtils.wrappException(ivjExc);
		 }
		 
		 
		 return UIDialogContentPane;
		
	}

	private UIPanel getDetailDlgDownPanel(LoginContext context) {
		// TODO 自动生成的方法存根
		if (this.detaildownPanel == null) {
			try {
				this.detaildownPanel = new UIPanel();
				detaildownPanel.setName("detaildownpanel");
				detaildownPanel.setLayout(new BorderLayout());
				detaildownPanel.add(getDownListPanel(context));
				this.getDownListPanel().getBillScrollPane().setVisible(true);
				this.getDownListPanel().getBillScrollPane().setTotalRowShow(true);
			}
			catch (Exception ivjExc)
			{
				ExceptionUtils.wrappException(ivjExc);
			}
		}
		
		return this.detaildownPanel;
	}

	private BusFetchDataDialogBillList getDownListPanel() {
		// TODO 自动生成的方法存根
		
		if(this.downListPanel!=null){
			return this.downListPanel;
		}
		return null;
	}


	private BusFetchDataDialogBillList getDownListPanel(LoginContext context) {
		// TODO 自动生成的方法存根
		
		  if (this.downListPanel == null) {
//			  this.downListPanel = new BillListPanel();
			  BillManageModel model=new BillManageModel();
			  model.setContext(context);
			  this.downListPanel =new BusFetchDataDialogBillList(model);
			 
			  
			 
		  }
		  return this.downListPanel;
	}

	private UIPanel getDetailDlgUpPanel(LoginContext context) {
		// TODO 自动生成的方法存根
		if (this.detailupPanel==null){
			this.detailupPanel=new UIPanel();
			this.detailupPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			this.detailupPanel.add(new UILabel("取数类型："));
			this.detailupPanel.add(this.getFetchText());
			
			this.detailupPanel.add(new UILabel("业务期间："));
			this.detailupPanel.add(this.getPeriodText());
			
			this.detailupPanel.add(new UILabel("计件产品："));
			this.detailupPanel.add(this.getPieceProductRefPanel());
			
			this.detailupPanel.add(new UILabel("部门："));
			this.detailupPanel.add(this.getDeptRefPanel());
			
			UIButton OutputButton = new UIButton();
			this.detailupPanel.add(OutputButton);
			
			OutputButton.setText("导出");
			
			OutputButton.addActionListener(new OutputButtonActionAdapter());
			
			
		}
		
		return detailupPanel;
	}

	private UIRefPane getPieceProductRefPanel() {
		// TODO 自动生成的方法存根
		if(this.refproduct==null){
			this.refproduct = new UIRefPane();
			PieceProductVORefModel accperiodModel = new PieceProductVORefModel();
			this.refproduct.setRefModel(accperiodModel);
			this.refproduct.addValueChangedListener(this);
		}
		return this.refproduct;
	}

	private UIRefPane getDeptRefPanel() {
		// TODO 自动生成的方法存根
		if(this.refdept==null){
			this.refdept = new UIRefPane();
			DeptRefModel deptmodel = new DeptRefModel();
			this.refdept.setRefModel(deptmodel);
//			deptmodel.setWherePart("pk_org='"+this.getFetchvo().getPk_org()+"'");
			this.refdept.addValueChangedListener(this);
		}
		return this.refdept;
	}
	
	public void doOutput() {
		// TODO 自动生成的方法存根
		try {
			this.getOutputAction().doAction(null);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private ExportListAction getOutputAction() {
		// TODO 自动生成的方法存根
		if(this.outputaction==null){
			this.outputaction=new ExportListAction();
			this.outputaction.setModel(this.getModel());
			this.outputaction.setListView(this.getDownListPanel(this.getContext()));
		}
		return this.outputaction;
	}

	class OutputButtonActionAdapter implements ActionListener{
		OutputButtonActionAdapter() {}
		public void actionPerformed(ActionEvent e)
		{
			DetailDataUIDialog.this.doOutput();
		}
	}

	@Override
	public void valueChanged(ValueChangedEvent arg0) {
		// TODO 自动生成的方法存根
		this.initData(this.getFetchvo());
	}

}
