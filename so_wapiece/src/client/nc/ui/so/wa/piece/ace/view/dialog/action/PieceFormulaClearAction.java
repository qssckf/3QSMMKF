package nc.ui.so.wa.piece.ace.view.dialog.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import nc.ui.pub.formula.dialog.FormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.ui.so.wa.piece.ace.view.dialog.PieceFormulaCodeRealEditorPanel;
import nc.vo.cm.driver.entity.CMDriverLangConst;
 

public class PieceFormulaClearAction extends AbstractAction implements IFormulaActionWithDialog
 {
   private FormulaRealEditorPanel formulaRealEditorPanel;
   private FormulaEditorDialog dialog;
   private PieceFormulaCodeRealEditorPanel formulaValueEditorPanel;
   
   public PieceFormulaClearAction()
   {
	   putValue("Name", CMDriverLangConst.getREF_BTN_CLEAR());
   }
   
 
 
   public void actionPerformed(ActionEvent e)
   {
	   getDialog().setFormulaDesc(null);
	   getFormulaValueEditorPanel().setConvertedText(null);
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

