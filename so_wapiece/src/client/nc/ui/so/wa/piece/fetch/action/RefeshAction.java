package nc.ui.so.wa.piece.fetch.action;

import java.awt.event.ActionEvent;

import nc.ui.so.wa.piece.fetch.ace.view.BusFetchDataMainPanel;
import nc.ui.uif2.NCAction;

public class RefeshAction extends NCAction {

	private BusFetchDataMainPanel listFetchDataMainPnl;
	
	public BusFetchDataMainPanel getListFetchDataMainPnl() {
		return listFetchDataMainPnl;
	}

	public void setListFetchDataMainPnl(BusFetchDataMainPanel listFetchDataMainPnl) {
		this.listFetchDataMainPnl = listFetchDataMainPnl;
	}
	
	public RefeshAction(){
		this.setBtnName("ˢ��");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO �Զ����ɵķ������
		
		this.getListFetchDataMainPnl().refreshDates();

	}

}
