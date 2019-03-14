package nc.ui.so.wa.piece.ace.view.dialog;

import nc.ui.pub.formula.dialog.FormulaEventSource;
import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.ui.FormulaEditorTextArea;

public class PieceFormulaRealEditorPanel
  extends FormulaRealEditorPanel
{
  private PieceFormulaCodeRealEditorPanel formulaCodeEditorPanel;
  
  public PieceFormulaRealEditorPanel() {}
  
  public PieceFormulaCodeRealEditorPanel getformulaCodeEditorPanel()
  {
	  return this.formulaCodeEditorPanel;
  }
  
  public void setformulaCodeEditorPanel(PieceFormulaCodeRealEditorPanel formulaCodeEditorPanel) {
	  this.formulaCodeEditorPanel = formulaCodeEditorPanel;
  }
  
  public void initUI()
  {
	  super.initUI();
	  getFormulaEditor().setEnabled(false);
  }
  
  public void notifyFormulaEvent(FormulaEventSource arg0)
  {
	  if (arg0.getEventType() == FormulaEventSource.FormulaEventType.INSERT_TO_EDITOR) {
		  String content = arg0.getNewString();
      
		  if ((arg0.getNewString().length() > 1) && (!arg0.getNewString().equals("00"))) {
			  content = "{" + content + "}";
      }
		  getFormulaEditor().requestFocus();

		  int pos = getFormulaEditor().getCaretPosition();
		  int insertPos = -1;
		  String forumla = getFormulaEditor().getText();
		  this.formulaCodeEditorPanel.setFormula(forumla);
		  this.formulaCodeEditorPanel.setEditorIndex(pos);
		  if ((pos >= 0) && (pos < getFormulaEditor().getText().length())) {
			  if (pos != 0) {
				  for (int i = pos; i < forumla.length(); i++) {
					  if ((insertPos == -1) && ((checkCharIsSingel(forumla.charAt(i - 1))) || (checkCharIsSingel(forumla.charAt(i)))))
            {

						  insertPos = i;
            }
					  if (forumla.charAt(i) == '{') {
						  if (insertPos != -1) break;
						  insertPos = i; break;
            }
            

					  if (forumla.charAt(i - 1) == '}') {
						  insertPos = i;
						  break;
            }
            
          }
        } else {
        	insertPos = 0;
        }
        
			  getFormulaEditor().setCaretPosition(insertPos);
        
			  getFormulaEditor().insertText(content, insertPos);
      }
      else {
    	  	  getFormulaEditor().appendText(content);
      }
    }
  }
  
  private boolean checkCharIsSingel(char aChar)
  {
	  if ((aChar == '+') || (aChar == '-') || (aChar == '*') || (aChar == '/') || (aChar == '(') || (aChar == ')')) {
		  return true;
    }
	  	  return false;
  }
}
