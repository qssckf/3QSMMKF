package nc.ui.so.wa.piece.fetch.ace.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.formula.ui.JEditTextArea;
import nc.ui.so.wa.piece.fetch.ace.view.dialog.action.SQLEditOKAction;

public class SQLEditDialog extends UIDialog {
	
	private UIPanel SQLTextPanel;
	private JEditTextArea textedit;
	private UIPanel buttonpanel;

	public UIPanel getSQLTextPanel() {
		return SQLTextPanel;
	}

	public void setSQLTextPanel(UIPanel sQLTextPanel) {
		SQLTextPanel = sQLTextPanel;
	}

	public JEditTextArea getTextedit() {
		return textedit;
	}

	public void setTextedit(JEditTextArea textedit) {
		this.textedit = textedit;
	}

	public UIPanel getButtonpanel() {
		return buttonpanel;
	}

	public void setButtonpanel(UIPanel buttonpanel) {
		this.buttonpanel = buttonpanel;
	}

	public SQLEditDialog(Container parent){
		super(parent);
		init();
	}

	private void init() {
		// TODO 自动生成的方法存根
		setTitle("取数语句显示");
		this.setSize(800, 500);
		setLayout(new BorderLayout());
		add(getSQLTextPanle(),"Center");
//		add(getButtonPanle(),"South");
	}

	private UIPanel getButtonPanle() {
		// TODO 自动生成的方法存根
		if (this.buttonpanel==null){
			this.buttonpanel=new UIPanel();
			this.buttonpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			UIButton okbtn = new UIButton("closed");
			okbtn.setAction(new SQLEditOKAction(this));
			okbtn.setName("closed");
			
			this.buttonpanel.add(okbtn);
		}
		return this.buttonpanel;
	}

	private UIPanel getSQLTextPanle(){
		// TODO 自动生成的方法存根
		if (this.SQLTextPanel==null){
			this.SQLTextPanel=new UIPanel();
			this.SQLTextPanel.setBorder(BorderFactory.createTitledBorder("Sql取数语句"));
			this.SQLTextPanel .setBorder(BorderFactory.createLineBorder(Color.black));
			this.SQLTextPanel.setLayout(new BorderLayout());
			this.SQLTextPanel.add(createUIScrollPane(getTextEditor()), "Center");
		}
			
		return this.SQLTextPanel;
	}

	private JEditTextArea getTextEditor() {
		// TODO 自动生成的方法存根
		if(this.textedit==null){
			this.textedit=new JEditTextArea();
			this.textedit.setEditable(false);
		}
		return textedit;
	}

	private Component createUIScrollPane(Component view) {
		// TODO 自动生成的方法存根
		UIScrollPane srollPane = new UIScrollPane();
		srollPane.setViewportView(view);
		srollPane.setAutoscrolls(true);
		return srollPane;
	}


	
	

}
