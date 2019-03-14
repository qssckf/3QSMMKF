package nc.ui.so.qs.intoprod.action;

import java.awt.Event;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;

public class RdQueryAction extends DefaultQueryAction{
	
	public RdQueryAction(){
		
		super();
		
		this.setBtnName("查询生产排产明细");
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke((int)'P', Event.ALT_MASK));
		this.putValue(Action.SHORT_DESCRIPTION, "查询_生产排产信息 (ALT+P)");
		
	}

	@Override
	protected void showQueryInfo() {
		// TODO 自动生成的方法存根
	}

}
