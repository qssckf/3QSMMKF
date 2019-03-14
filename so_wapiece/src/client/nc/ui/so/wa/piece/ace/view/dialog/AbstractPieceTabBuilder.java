package nc.ui.so.wa.piece.ace.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import nc.ui.pub.beans.UIList;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaEventListener;
import nc.ui.pub.formula.dialog.IFormulaTabBuilder;
import nc.vo.cm.driver.entity.DriverQueryCondition;
import nc.vo.pub.formulaedit.FormulaItem;
import nc.vo.uif2.LoginContext;


public abstract class AbstractPieceTabBuilder extends UIPanel implements IFormulaTabBuilder
{

  private DriverQueryCondition queryCondition;
  private static final long serialVersionUID = 413127735206256115L;
  private LoginContext loginContext;
  private UIPanel variablePanel = null;
  private UIScrollPane variableListScrollPane = null;
  private UIList variableUIList = null;
  private FormulaRealEditorPanel formulaRealEditorPanel;
  private List<IFormulaEventListener> listeners;
  public AbstractPieceTabBuilder() {}
  
  
  public void initUI()
  {
	  setLayout(new BorderLayout());
	  add(getVariablePanel(), "Center");
  }


  
	
	public void setVariableUIList(UIList variableUIList) {
		this.variableUIList = variableUIList;
		this.variableUIList.addMouseListener(new MouseAdapter(){
			  public void mouseClicked(MouseEvent e)
		        {
						  AbstractPieceTabBuilder.this.mouseClick(e);
		        }
		      });
	}
	
	  public UIList getVariableUIList() {
			return variableUIList;
		}
  

  public abstract UIList getUIList();
  

  private UIScrollPane getVariableListScrollPane()
  {
	  if (this.variableListScrollPane == null) {
		  this.variableListScrollPane = new UIScrollPane(getUIList());
    }
	  return this.variableListScrollPane;
  }
  

  public abstract Map<String, FormulaItem> getAllVariableItems();

  public abstract String getTabName();

  private UIPanel getVariablePanel()
  {
	  if (this.variablePanel == null) {
		  this.variablePanel = new UIPanel();
		  this.variablePanel.setLayout(new BorderLayout());
		  this.variablePanel.add(getVariableListScrollPane(), "Center");
    }
    
	  return this.variablePanel;
  }
  

  

  public int getWordType()
  {
	  	return 2;
  }
  

  public List<IFormulaEventListener> getListeners()
  {
	  return this.listeners;
  }
  

  public void setListeners(List<IFormulaEventListener> listeners)
  {
	  this.listeners = listeners;
  }
  
  public Map<String, FormulaItem> getName2FormulaItemMap()
  {
	  return getAllVariableItems();
  }
  

  public void setWordType(int wordType) {}
  

  public FormulaRealEditorPanel getFormulaRealEditorPanel()
  {
	  return this.formulaRealEditorPanel;
  }
  
  public void setFormulaRealEditorPanel(FormulaRealEditorPanel formulaRealEditorPanel)
  {
	  this.formulaRealEditorPanel = formulaRealEditorPanel;
  }

  public LoginContext getLoginContext()
  {
	  return this.loginContext;
  }

  public void setLoginContext(LoginContext loginContext)
  {
	  this.loginContext = loginContext;
  }

  private void mouseClick(MouseEvent e)
  {
	  String tableName = (String)getVariableUIList().getSelectedValue();
	  FormulaItem formulaItem = (FormulaItem)getName2FormulaItemMap().get(tableName);
	  PieceFormulaEventSource eventSource;
	  if (formulaItem != null) {
		  eventSource = new PieceFormulaEventSource();
		  eventSource.setEventSource(this);
		  int count = e.getClickCount();
		  if (count == 1)
		  {
			  mouseOneClicked(e, formulaItem, eventSource);
		  }
		  else if (count == 2) {
			  mouseDoubleClicked(e, formulaItem, eventSource);
		  }
		  if (getListeners() != null) {
			  for (IFormulaEventListener listener : getListeners()) {
				  listener.notifyFormulaEvent(eventSource);
        }
      }
    }
  }

  protected void mouseOneClicked(MouseEvent e, FormulaItem formulaItem, FormulaEventSource eventSource)
  {
	  eventSource.setEventType(FormulaEventSource.FormulaEventType.MESSAGE_TO_HINTPANEL);
	  eventSource.setNewString(formulaItem.getHintMsg());
  }
  

  protected void mouseDoubleClicked(MouseEvent e, FormulaItem formulaItem, PieceFormulaEventSource eventSource)
  {
	  eventSource.setEventType(FormulaEventSource.FormulaEventType.INSERT_TO_EDITOR);
	  eventSource.setNewString(formulaItem.getDisplayName());
	  eventSource.setNewValueString(formulaItem.getInputSig());
  }
  

  protected DriverQueryCondition getQueryCondtion()
  {
	  if (this.queryCondition == null) {
		  this.queryCondition = new DriverQueryCondition();
    }
	  
	  this.queryCondition.setPk_org(getLoginContext().getPk_org());
	  this.queryCondition.setPk_group(getLoginContext().getPk_group());
	  
	  return this.queryCondition;
  }
}
