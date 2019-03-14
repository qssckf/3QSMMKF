package nc.ui.so.qs.intoprod.action;

import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.uitheme.ui.ThemeResourceCenter;

public class IpQueryAction extends DefaultQueryAction{

	private final String iconurl="themeres/ui/toolbaricons/refer_file.png";
	
	public IpQueryAction(){
		
		super();
		
		this.setBtnName("查询待处理信息");
		this.setCode("RdQuyer");
		this.putValue("ShortDescription", "查询待处理的未生成生产订单记录");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		
	}

	@Override
	protected void showQueryInfo() {
		// TODO 自动生成的方法存根
		
	}
	

}
