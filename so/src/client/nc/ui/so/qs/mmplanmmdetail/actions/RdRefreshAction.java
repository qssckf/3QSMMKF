package nc.ui.so.qs.mmplanmmdetail.actions;

import nc.uitheme.ui.ThemeResourceCenter;
import nc.ui.uif2.actions.RefreshAction;

public class RdRefreshAction extends RefreshAction{
	
	private final String iconurl="themeres/ui/toolbaricons/refresh_press.png";
	
	public RdRefreshAction(){
		
		super();
		
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		this.setEnabled(false);
		
	}

}
