package nc.ui.so.wa.piece.ace.view.dialog.action;
 
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.AbstractAction;
import nc.cm.pub.framework.base.CMStringUtil;
import nc.ui.cm.driver.view.dialog.DriverFormulaToolUtil;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formula.ui.FormulaEditorTextArea;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaCodeRealEditorPanel;
import nc.vo.cm.driver.entity.CMDriverLangConst;
import nc.vo.cm.driver.entity.FormulaItemVO;
 
 public class PieceFormulaDeleteAction extends AbstractAction implements IFormulaActionWithDialog{
   
   private FormulaRealEditorPanel formulaRealEditorPanel;
   private FormulaEditorDialog dialog;
   private PieceFormulaCodeRealEditorPanel formulaValueEditorPanel;
   
   public PieceFormulaDeleteAction()
   {
	   putValue("Name", CMDriverLangConst.getREF_BTN_DELETE());
   }

   public void actionPerformed(ActionEvent e)
   {
	   FormulaRealEditorPanel realEditorPanel = getFormulaRealEditorPanel();
	   String formula = realEditorPanel.getConvertedText();
	   String newformula = "";
	   String formulacode = getFormulaValueEditorPanel().getConvertedText();
	   String newformulacode = "";
	   if (CMStringUtil.isEmpty(formula)) {
		   getFormulaValueEditorPanel().setConvertedText(null);
		   return;
     }
     
	   Map<FormulaItemVO, FormulaItemVO> formulaMap = new DriverFormulaToolUtil().generateFormulaMap(formula, formulacode);
     
	   int mousePos = realEditorPanel.getFormulaEditor().getCaretPosition();
	   for (Iterator i$ = formulaMap.entrySet().iterator(); i$.hasNext(); )
     {
		   Map.Entry<FormulaItemVO, FormulaItemVO> entry = (Map.Entry)i$.next();
		   if (((((FormulaItemVO)entry.getKey()).getStartIndex() < mousePos) || (((FormulaItemVO)entry.getKey()).getStartIndex() == 0)) && ((((FormulaItemVO)entry.getKey()).getEndIndex() >= mousePos - 1) || (((FormulaItemVO)entry.getKey()).getEndIndex() == -1)))
       {
			   int sf=MessageDialog.showOkCancelDlg(getFormulaRealEditorPanel(), "警告", "光标位置:"+mousePos+"，准备删除"+((FormulaItemVO)entry.getKey()).getValue()+",是否继续？");
			  
			   if(sf==1){
				   newformula = formula.substring(0, ((FormulaItemVO)entry.getKey()).getStartIndex());
				   if (((FormulaItemVO)entry.getKey()).getEndIndex() != -1) {
					   newformula = newformula + formula.substring(((FormulaItemVO)entry.getKey()).getEndIndex() + 1);
				   }
				   mousePos = ((FormulaItemVO)entry.getKey()).getStartIndex();
				   newformulacode = formulacode.substring(0, ((FormulaItemVO)entry.getValue()).getStartIndex());
				   if (((FormulaItemVO)entry.getKey()).getEndIndex() == -1) break;
				   newformulacode = newformulacode + formulacode.substring(((FormulaItemVO)entry.getValue()).getEndIndex() + 1);
			   }
       }
     }
     
 
	   getFormulaRealEditorPanel().setConvertedText(newformula);
	   realEditorPanel.getFormulaEditor().setCaretPosition(mousePos);
	   getFormulaValueEditorPanel().setConvertedText(newformulacode);
   }
   
   public void setDialog(FormulaEditorDialog arg0)
   {
	   this.dialog = arg0;
   }
   
 
 
 
 
   public FormulaRealEditorPanel getFormulaRealEditorPanel()
   {
	   return this.formulaRealEditorPanel;
   }
   
 
 
 
 
   public void setFormulaRealEditorPanel(FormulaRealEditorPanel formulaRealEditorPanel)
   {
	   this.formulaRealEditorPanel = formulaRealEditorPanel;
   }
   
 
 
 
 
   public PieceFormulaCodeRealEditorPanel getFormulaValueEditorPanel()
   {
	   return this.formulaValueEditorPanel;
   }
   
 
 
 
 
   public void setFormulaValueEditorPanel(PieceFormulaCodeRealEditorPanel formulaValueEditorPanel)
   {
	   this.formulaValueEditorPanel = formulaValueEditorPanel;
   }
   
 
 
 
 
   public FormulaEditorDialog getDialog()
   {
	   return this.dialog;
   }
 }

