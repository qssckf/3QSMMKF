package nc.ui.so.wa.piece.fetch.action;

import java.awt.event.ActionEvent;

import nc.ui.so.wa.piece.fetch.ace.view.BusFetchDataMainPanel;
import nc.ui.so.wa.piece.fetch.ace.view.DetailDataUIDialog;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.wa.piece.fetch.PieceFetchRecordVO;
import nc.vo.uif2.LoginContext;

public class DisplayDetailAction extends NCAction{
	
	private BusFetchDataMainPanel DataMainPnl;
	private DetailDataUIDialog dialog;
	private LoginContext context;
	
	public DetailDataUIDialog getDialog() {
		return dialog;
	}

	public void setDialog(DetailDataUIDialog dialog) {
		this.dialog = dialog;
	}
	
	public BusFetchDataMainPanel getDataMainPnl() {
		return DataMainPnl;
	}

	public void setDataMainPnl(BusFetchDataMainPanel dataMainPnl) {
		DataMainPnl = dataMainPnl;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}
	
	public DisplayDetailAction(){
		this.setBtnName("�鿴��ϸ��¼");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		
		PieceFetchRecordVO[] vos=(PieceFetchRecordVO[])this.getDataMainPnl().getBillListView().getBillListPanel().getBodyBillModel().getBodySelectedVOs(PieceFetchRecordVO.class.getName());
		
		if(vos.length>0){
			if (vos.length!=1){
				throw new BusinessException( "ֻ��ѡ��һ��ȡ����¼�鿴��ϸ��������ѡ��");
			}else{
				if (vos[0].getFetchsf()!=UFBoolean.TRUE){
					throw new BusinessException( "��ѡ��ļ�¼û�н���ȡ��������������ѡ��");
				}else{
					this.getDialog().initUI(vos[0],this.getDataMainPnl(),this.getContext());
					this.getDialog().showModal();
				}
			}
		}else{
			throw new BusinessException("��ѡ����Ҫ�鿴��ϸ��Ϣ�ļ�¼��");
		}
	}

}
