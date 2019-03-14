package nc.ui.so.wa.piece.fetch.action;

import java.awt.event.ActionEvent;

import nc.pub.smart.provider.Provider;
import nc.pub.smart.provider.impl.SqlProvider;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.so.wa.piece.fetch.ace.view.FetchRuleDefineBillList;
import nc.ui.so.wa.piece.fetch.model.FetchRuleDataModel;
import nc.ui.uif2.NCAction;
import nc.vo.smart.SmartDefVO;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;

public class DisplayDetailSqlAction extends NCAction {

	private FetchRuleDataModel model;
	private FetchRuleDefineBillList billlist;
	
	public FetchRuleDefineBillList getBilllist() {
		return billlist;
	}
	public void setBilllist(FetchRuleDefineBillList billlist) {
		this.billlist = billlist;
	}
	public FetchRuleDataModel getModel() {
		return model;
	}
	public void setModel(FetchRuleDataModel model) {
		this.model = model;
	}
	
	public DisplayDetailSqlAction(){
		this.setBtnName("�鿴��ϸ��ȡ������");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		String pk_def="";
		SmartDefVO smartdef;
		SqlProvider sqlprovide = null;
		
		PieceFetchInfoVO vo=this.getSelectVO();
		if (vo==null){
			MessageDialog.showErrorDlg(this.billlist, "����", "��ѡ��һ����¼��");
			return;
		}
		
		Integer[] row=this.model.getSelectedOperaRows();
		pk_def=(String) this.getBilllist().getBillCardPanel().getBodyValueAt(row[0], "smartdef2");
		if (pk_def==""){
			MessageDialog.showErrorDlg(this.billlist, "����", "����ģ����ϸ�����ֵΪ�գ�������ʾȡ������");
			return;
		}
		
		smartdef=this.getBilllist().getSmartDef().get(pk_def);
		if (smartdef==null){
			smartdef=this.billlist.getService().querySmartDefByPK(pk_def);
			this.getBilllist().getSmartDef().put(pk_def, smartdef);
		}
		
		Provider[] provider=(smartdef.getSmartmodel().getProviders());
		
		if (provider[0] instanceof SqlProvider){
			sqlprovide=(SqlProvider) provider[0];
		}
		
		this.billlist.getSQLDlg().getTextedit().setText(sqlprovide.getScript());
		this.billlist.getSQLDlg().showModal();
		
		
		
	}

	private PieceFetchInfoVO getSelectVO(){
		return (PieceFetchInfoVO)this.getModel().getSelectedData();
		
	}
}
