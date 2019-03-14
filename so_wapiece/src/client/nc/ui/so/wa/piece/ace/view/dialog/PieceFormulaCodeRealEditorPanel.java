package nc.ui.so.wa.piece.ace.view.dialog;

import java.util.Map;
import java.util.Map.Entry;

import nc.ui.cm.driver.view.dialog.DriverFormulaToolUtil;
import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.ui.FormulaEditorTextArea;
import nc.vo.cm.driver.entity.FormulaItemVO;


public class PieceFormulaCodeRealEditorPanel extends FormulaRealEditorPanel{
	
  public PieceFormulaCodeRealEditorPanel() {}
  
  private String formula = null;
  
  public String getFormula() 
  {
	  return this.formula; 
  }
  
  public void setFormula(String formula)
  {
	  this.formula = formula;
  }
  
  private int editorIndex = -1;
  
  public int getEditorIndex() {
	  return this.editorIndex;
  }
  
  public void setEditorIndex(int editorIndex) {
	  this.editorIndex = editorIndex;
  }
  
  public void notifyFormulaEvent(FormulaEventSource arg0)
  {
	  if ((arg0 instanceof PieceFormulaEventSource)) {
		  PieceFormulaEventSource eventSource = (PieceFormulaEventSource)arg0;
		  eventSource.setNewString(eventSource.getNewValueString());
		      }
		  if (arg0.getEventType() == FormulaEventSource.FormulaEventType.INSERT_TO_EDITOR) {
			  String content = arg0.getNewString();
			  
			  if ((arg0.getNewString().length() > 1) && (!arg0.getNewString().equals("00"))) {
				  content = "{" + content + "}";
		        }
			  String formulacode = getFormulaEditor().getText();
			  int pos = -1;
		        
			  Map<FormulaItemVO, FormulaItemVO> formulaMap = new DriverFormulaToolUtil().generateFormulaMap(this.formula, formulacode);
		        
			  for (Map.Entry<FormulaItemVO, FormulaItemVO> entry : formulaMap.entrySet()) {
				  if ((((FormulaItemVO)entry.getKey()).getStartIndex() < this.editorIndex) && ((((FormulaItemVO)entry.getKey()).getEndIndex() >= this.editorIndex - 1) || (((FormulaItemVO)entry.getKey()).getEndIndex() == -1)))
		          {
					  if (((FormulaItemVO)entry.getKey()).getEndIndex() == -1) break;
					  	pos = ((FormulaItemVO)entry.getValue()).getEndIndex() + 1; break;
		          }
		          
		  
				  if (this.editorIndex == 0) {
					  pos = 0;
					  break;
		          }
		        }
		        
			  if ((pos >= 0) && (pos <= getFormulaEditor().getText().length())) {
				  getFormulaEditor().setCaretPosition(pos);
				  getFormulaEditor().insertText(content, pos);
		        }
		        else {
		        	getFormulaEditor().appendText(content);
		        }
		      }
  }
  
  public void initUI()
  {
	  super.initUI();
	  setVisible(false);
  }
}
