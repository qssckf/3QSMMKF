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
		this.setBtnName("刷新");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		// TODO 自动生成的方法存根
		
		this.getListFetchDataMainPnl().refreshDates();

	}

}
