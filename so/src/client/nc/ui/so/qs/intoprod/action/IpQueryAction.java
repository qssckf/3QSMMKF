package nc.ui.so.qs.intoprod.action;

import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.uitheme.ui.ThemeResourceCenter;

public class IpQueryAction extends DefaultQueryAction{

	private final String iconurl="themeres/ui/toolbaricons/refer_file.png";
	
	public IpQueryAction(){
		
		super();
		
		this.setBtnName("��ѯ��������Ϣ");
		this.setCode("RdQuyer");
		this.putValue("ShortDescription", "��ѯ�������δ��������������¼");
		this.putValue("SmallIcon", ThemeResourceCenter.getInstance().getImage(iconurl));
		
	}

	@Override
	protected void showQueryInfo() {
		// TODO �Զ����ɵķ������
		
	}
	

}
