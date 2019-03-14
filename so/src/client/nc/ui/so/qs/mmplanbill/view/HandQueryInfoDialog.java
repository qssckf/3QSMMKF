package nc.ui.so.qs.mmplanbill.view;

import java.awt.Container;

import javax.swing.BorderFactory;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;

public class HandQueryInfoDialog extends UIDialog{
	
	private static final int HEIGTH = 530;
	private static final int LENGTH = 800;
	private OnhandPanelAdaptor adaptor;

	public OnhandPanelAdaptor getAdaptor() {
		return adaptor;
	}

	public void setAdaptor(OnhandPanelAdaptor adaptor) {
		this.adaptor = adaptor;
	}

	public HandQueryInfoDialog(Container container,OnhandPanelSrc list){
		
		super(container);
		
		OnhandPanelAdaptor adaptor = new OnhandPanelAdaptor("nc.ui.ic.onhand.pane.QueryOnHandInfoPanel");
		
		if (this.adaptor == null){
			
			UIPanel refpanel = adaptor.getRefPane();
			
			this.adaptor = adaptor;
			
			adaptor.setOnhandPanelSrc(list);
			
			adaptor.initialize();
			
			refpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Style.getColor("系统按钮条.背景")));
			
			add(refpanel);
			
			setSize(1000, 800);
			
		}
		
	}
	

	
	
	
	
	
	

}
