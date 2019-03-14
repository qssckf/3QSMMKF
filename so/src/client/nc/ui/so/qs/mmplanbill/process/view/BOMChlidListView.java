package nc.ui.so.qs.mmplanbill.process.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.qs.mmplanbill.process.model.QsVOBillListPanelValueSetter;

public class BOMChlidListView extends ShowUpableBillListView{
	
	public BOMChlidListView(){
		setBillListPanelValueSetter(new QsVOBillListPanelValueSetter());
	}
	
	

}
