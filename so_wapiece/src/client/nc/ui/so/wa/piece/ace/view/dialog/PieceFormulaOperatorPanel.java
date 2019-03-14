package nc.ui.so.wa.piece.ace.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.LineBorder;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.IFormulaEventListener;


public class PieceFormulaOperatorPanel
  extends UIPanel
{
  private static final long serialVersionUID = -8109346213142017196L;
  private UIPanel operatorPanel;
  private UIButton[] caculateAndLogicBts;
  private String[] caculateAndLogics = { "+", "-", "*", "/", "(", ")" };
  

  private List<IFormulaEventListener> listeners;
  

  public PieceFormulaOperatorPanel()
  {
	  setLayout(new BorderLayout());
	  add(getPanelOperate(), "Center");
  }
  


  private UIPanel getPanelOperate()
  {
	  if (this.operatorPanel == null) {
		  this.operatorPanel = new UIPanel();
		  this.operatorPanel.setName("UIPanelOprate");
		  this.operatorPanel.setPreferredSize(new Dimension(10, 200));
		  this.operatorPanel.setBorder(new LineBorder(this.operatorPanel.getBackground(), 5));
		  this.operatorPanel.setLayout(new GridLayout(3, 4));
		  this.operatorPanel.setBounds(6, 110, 186, 75);
		  UIButton[] bts = getCalculatAndLogicButtons();
		  if (bts != null) {
		  for (int i = 0; i < bts.length; i++) {
			  getPanelOperate().add(bts[i], bts[i].getName());
        }
      }
    }
	  
	  return this.operatorPanel;
  }
  


  private UIButton[] getCalculatAndLogicButtons()
  {
	  if (this.caculateAndLogicBts == null) {
		  this.caculateAndLogicBts = createButtons(getCalculatAndLogicBtnProps());
    }
	  return this.caculateAndLogicBts;
  }
  




  private String[][] getCalculatAndLogicBtnProps()
  {
	  String[] caculateAndLogicsTemp = this.caculateAndLogics;
	  String[][] textAndCmds = new String[caculateAndLogicsTemp.length][3];
	  for (int i = 0; i < textAndCmds.length; i++) {
		  textAndCmds[i][0] = caculateAndLogicsTemp[i];
		  textAndCmds[i][1] = caculateAndLogicsTemp[i];
		  textAndCmds[i][2] = caculateAndLogicsTemp[i];
    }
	  return textAndCmds;
  }
  






  private UIButton[] createButtons(String[][] textAndCmds)
  {
	  if ((textAndCmds == null) || (textAndCmds.length == 0)) {
		  return null;
    }
	  int size = textAndCmds.length;
	  UIButton[] bts = new UIButton[size];
	  for (int i = 0; i < size; i++) {
		  bts[i] = new UIButton();
		  bts[i].setText(textAndCmds[i][0]);
		  bts[i].setName(textAndCmds[i][1]);
		  bts[i].setActionCommand(textAndCmds[i][2]);
		  bts[i].addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
        	if (PieceFormulaOperatorPanel.this.getListeners() != null)
          {

        		for (IFormulaEventListener listener : PieceFormulaOperatorPanel.this.getListeners()) {
        			FormulaEventSource eventSource = new FormulaEventSource();
        			eventSource.setEventSource(e.getSource());
        			String name = ((UIButton)e.getSource()).getName();
        			eventSource.setEventType(FormulaEventSource.FormulaEventType.INSERT_TO_EDITOR);
        			eventSource.setNewString(name);
        			listener.notifyFormulaEvent(eventSource);
            }
          }
        }
      });
    }
	  return bts;
  }
  

  public void setListeners(List<IFormulaEventListener> listeners)
  {
	  this.listeners = listeners;
  }
  




  public List<IFormulaEventListener> getListeners()
  {
	  return this.listeners;
  }
}