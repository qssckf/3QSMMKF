package nc.ui.so.wa.piece.ace.view.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.cm.pub.framework.base.CMStringUtil;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.ui.so.wa.piece.tools.PieceFormulaParse;
import nc.vo.cm.driver.entity.CMDriverLangConst;
import nc.vo.ml.NCLangRes4VoTransl;

public class PieceFormulaValidata
 {
   IFormulaRealEditorPanel formulaRealEditorPanel;
   private PieceFormulaCodeRealEditorPanel formulaCodeEditPanel;
   private String matchString = "*?";
   private List<String[]> businessRule = null;
   private PieceFormulaParse pfparse;
   
   public PieceFormulaValidata(IFormulaRealEditorPanel formulaRealEditorPanel,PieceFormulaCodeRealEditorPanel formulaCodeEditPanel)
   {
	   this.formulaRealEditorPanel = formulaRealEditorPanel;
	   this.formulaCodeEditPanel=formulaCodeEditPanel;
	   this.pfparse=new PieceFormulaParse();
   }
   
   public boolean validata(AbstractFormulaRealEditorPanel formulaRealEditorPanel)
   {
	   String formulaText = this.formulaCodeEditPanel.getConvertedText();
	   
	   
	   formulaText = formulaText.replaceAll("\\[", "");     //将[替换成空格
	   formulaText = formulaText.replaceAll("\\]", "");    //将]替换成空格
	   formulaText = formulaText.replaceAll("\\(%\\)", "");
	   formulaText = formulaText.replaceAll(NCLangRes4VoTransl.getNCLangRes().getStrByID("3830001_0", "03830001-0003"), "");
	   
	   this.pfparse.setFormula(formulaText);
     
	   if (!checkMatch(formulaText)) {                         //检查()数目是否正确
		   return false;
	   }	

	   if (!checkDivZero(formulaText)) {
		   return false;
		   }
     
	   String replaedString = replaceSpecialString(formulaText);
     
	   List<String> splitString = new ArrayList();
	   if (!splitWithCaculateSign(replaedString, splitString)) {
		   return false;
		   }
	   
	   String formulaOldText = setWildcard(formulaText);
	   formulaText = formulaOldText.replaceAll("\\(", "");
	   formulaText = formulaText.replaceAll("\\)", "");
	   String[] formulas = splitFormulaText(formulaText);
	   if (getFormulaRealEditorPanel().getFormulaParse().checkExpressArray(formulas)) {
		   String msg = IsNumOrFloat(splitString);
		   if (msg != null)
       {
			   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), msg);
         
			   return false;
       }
		   if (!businessValidate(formulaOldText)) {
			   return false;
		   }
     }
     else
     {
    	 MessageDialog.showErrorDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_GRAMMER());
       
    	 return false;
     }
	   
	   if (!this.pfparse.CheckFormula(formulaRealEditorPanel)){
		   return false;
	   }
	   
	   
	   return true;
   }
   
 
 
 
 
 
   private String setWildcard(String formula)
   {
	   int cusera = formula.indexOf("{");
	   if (cusera < 0) {
		   return formula;
     }

	   StringBuffer newFormula = new StringBuffer();
	   boolean noReplace = true;
	   
	   for (int i = 0; i < formula.length(); i++) {
		   if (formula.charAt(i) == '{') {
			   noReplace = false;
		   }
		   else if (formula.charAt(i) == '}') {
			   newFormula = newFormula.append("$");
			   noReplace = true;
		   }
		   else if (noReplace) {
			   newFormula = newFormula.append(formula.charAt(i));
		   }
	   }
	   return newFormula.toString();
   }
   
 
 
 
 
 
   private boolean checkMatch(String formulaText)
   {
	   byte[] bytes = formulaText.getBytes();
	   byte left = 40;     //40代表(
	   byte right = 41;  //41代表)
	   int count = 0;
     
	   for (int i = 0; i < bytes.length; i++) {
		   if ((bytes[i] == left) || (bytes[i] == right)) {
			   count++;
		   }
     }
	   
	   if (count % 2 != 0)
     {
		   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_MATCH());
		   
		   return false;
     }
	   return true;
   }
   
 
 
 
 
 
   private boolean checkDivZero(String formulaText)
   {
	   String[] formulas = formulaText.split("/\\(");
     
	   if (formulas.length > 1) {
		   String div = formulas[1].substring(0, formulas[1].lastIndexOf(")"));
		   String[] caculateSign = { "\\-", "\\*" };
       
		   if (div.indexOf("-") > 0) {
			   String[] divs = div.split(caculateSign[0]);
			   if (divs[0].equals(divs[1]))
			   {
				   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
           
				   return false;
         }
       }
		   if (div.indexOf("*") > 0) {
			   String[] divs = div.split(caculateSign[1]);
			   for (int i = 0; i < divs.length; i++) {
				   if (divs[i].equals("0")){
					   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
					   return false;
           }
         }
       }
     }
     
	   return true;
   }

   private String[] splitFormulaText(String formulaText)
   {
	   formulaText = formulaText.replaceAll("\n", "").replaceAll("\t", "");
	   return formulaText.split(";");
   }
   
   private boolean splitWithCaculateSign(String formula, List<String> list)
   {
	   String[] caculateSign = { "\\+", "\\-", "\\*", "/" };
     
	   list.add(formula);
	   for (int i = 0; i < caculateSign.length; i++) {
		   for (int j = list.size() - 1; j >= 0; j--) {
			   String[] formulaParts = ((String)list.get(j)).split(caculateSign[i]);
			   list.remove(j);
			   if ((i == 3) && (formulaParts.length > 1) && (((isNum(formulaParts[1])) && (Integer.parseInt(formulaParts[1]) == 0)) || ((isFloat(formulaParts[1])) && (Float.parseFloat(formulaParts[1]) == 0.0F))))
			   {
				   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_ZERO());
				   return false;
			   }
			   addArrayToList(formulaParts, list);
       }
     }
     
 
	   return true;
   }
   

   private String replaceSpecialString(String formula)
   {
	   String[][] replaceString = { { "\n", "" }, { "\t", "" }, { "\\(", "" }, { "\\)", "" } };
     
	   for (int i = 0; i < replaceString.length; i++) {
		   formula = formula.replaceAll(replaceString[i][0], replaceString[i][1]);
	   }
	   
	   return formula;
   }
   

   private void addArrayToList(String[] arrayData, List<String> list)
   {
	   if ((arrayData == null) || (arrayData.length <= 0)) {
		   return;
     }
     
	   if (list == null) {
		   list = new ArrayList();
     }
     
	   for (int i = 0; i < arrayData.length; i++) {
		   if (CMStringUtil.isNotEmpty(arrayData[i])) {
			   	list.add(arrayData[i]);
       }
     }
   }

   private IFormulaRealEditorPanel getFormulaRealEditorPanel()
   {
	   return this.formulaRealEditorPanel;
   }

   private boolean businessValidate(String formulas)
   {
	   String message = null;
	   message = checkFormulaRule(formulas);
	   if (CMStringUtil.isNotEmpty(message)) {
		   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), message);
		   
		   return false;
     }
	   return true;
   }
   
   private String checkFormulaRule(String formulas) {
	   String message = null;
	   for (int i = 0; i < formulas.length(); i++) {
		   if (formulas.charAt(i) == '$') {
			   if ((i != 0) && (!checkCharIsSingel(formulas.charAt(i - 1)))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
				   break;
         }
			   if ((i < formulas.length() - 1) && (!checkCharIsSingel(formulas.charAt(i + 1)))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
				   break;
         }
       }
		   else if (formulas.charAt(i) == '(') {
			   if ((i != 0) && (!checkCharIsSingelLeft(formulas.charAt(i - 1)))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT1();
				   break;
         }
			   if (i == formulas.length() - 1) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT2();
				   break;
         }
			   if (checkCharIsSingelRight(formulas.charAt(i + 1))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_LEFT3();
				   break;
         }
       }
		   else if (formulas.charAt(i) == ')') {
			   if (i == 0) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT1();
				   break;
         }
			   if (checkCharIsSingelLeft(formulas.charAt(i - 1))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT2();
				   break;
         }
			   if ((i < formulas.length() - 1) && (!checkCharIsSingelRight(formulas.charAt(i + 1)))) {
				   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_RIGHT3();
				   break;
         }
       }
     }
	   return message;
   }
   
   private boolean checkCharIsSingel(char aChar) {
	   if ((aChar == '+') || (aChar == '-') || (aChar == '*') || (aChar == '/') || (aChar == '(') || (aChar == ')')) {
		   return true;
     }
	   return false;
   }
   
   private boolean checkCharIsSingelLeft(char aChar) {
	   if ((aChar == '+') || (aChar == '-') || (aChar == '*') || (aChar == '/') || (aChar == '(')) {
		   return true;
     }
	   return false;
   }
   
   private boolean checkCharIsSingelRight(char aChar) {
	   if ((aChar == '+') || (aChar == '-') || (aChar == '*') || (aChar == '/') || (aChar == ')')) {
		   return true;
     }
	   return false;
   }
   
   private String isAllFormulaIncludeRule(String[] rules, List<String> formulas)
   {
	   String message = null;
	   if ((rules == null) || (rules.length <= 0) || (formulas == null) || (formulas.size() <= 0)) {
		   return message;
     }
	  
	   for (int i = 0; i < formulas.size(); i++) {
		   String formula = (String)formulas.get(i);
		   boolean isFind = false;
       
		   int count = 0;
		   for (int j = 0; j < rules.length; j++) {
			   Pattern pattern = Pattern.compile(rules[j]);
			   Matcher matcher = pattern.matcher(formula);
         
			   if (matcher.find())
			   {
				   isFind = true;
           
				   count++;
				   if ((rules[j].indexOf(this.matchString) < 0) && (!rules[j].equals(formula))) {
					   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
					   break;
				   }
				   
				   if ((rules[j].indexOf(this.matchString) >= 0) && (isNum(formula.substring(formula.length() - 1))))
				   {
					   message = CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOINNUM();
					   break;
           }
           
				   String rule = rules[j];
				   if (rules[j].indexOf(this.matchString) > 0) {
					   rule = rules[j].substring(0, rules[j].indexOf(this.matchString));
           }
				   if (formula.indexOf(rule) != formula.lastIndexOf(rule)) {
					   count++;
           }
         }
       }
       
 
		   if (count > 1) {
			   return CMDriverLangConst.getFORMULA_VALIDATE_FAIL_JOIN();
       }
		   if (!isFind) {
			   return CMDriverLangConst.getFORMULA_VALIDATE_FAIL_BASE();
       }
     }
     
	   return message;
   }
   

   private boolean isIncludeBaseFormula(String[] rules, String baseFormula)
   {
	   for (int i = 0; i < rules.length; i++) {
		   if (rules[i] != null)
		   {
			   Pattern pattern = Pattern.compile(rules[i]);
			   Matcher matcher = pattern.matcher(baseFormula);
         
			   if (matcher.find()) {
				   return true;
         }
       }
     }
	   MessageDialog.showHintDlg((AbstractFormulaRealEditorPanel)getFormulaRealEditorPanel(), CMDriverLangConst.getFORMULA_VALIDATE(), CMDriverLangConst.getFORMULA_VALIDATE_FAIL_BASE());
     
	   return false;
   }

   private String IsNumOrFloat(List<String> formulas)
   {
	   for (int i = 0; i < formulas.size(); i++) {
		   if (isNum((String)formulas.get(i))) {
			   formulas.remove(formulas.get(i));
			   i -= 1;
       }
		   else if (isFloat((String)formulas.get(i)))
       {
			   String dot = ".";
			   if (((String)formulas.get(i)).indexOf(dot) >= 0) {
				   String formus = ((String)formulas.get(i)).substring(0, ((String)formulas.get(i)).indexOf(dot));
				   if ((formus.indexOf("0") != formus.lastIndexOf("0")) && (Integer.parseInt(formus) == 0)) {
					   return CMDriverLangConst.getFORMULA_VALIDATE_FAIL_NUM();
           }
         }
			   formulas.remove(formulas.get(i));
			   i -= 1;
       }
     }
	   if (formulas.size() == 0)
	   {
		   return CMDriverLangConst.getFORMULA_VALIDATE_VARABAL_NULL();
     }
	   return null;
   }
   

   private boolean isNum(String baseFormula)
   {
	   	Pattern pattern = Pattern.compile("^-?\\d+$");
	   	Matcher matcher = pattern.matcher(baseFormula);
	   	if (matcher.find()) {
	   		return true;
     }
	   	return false;
   }
   
   private boolean isFloat(String baseFormula)
   {
	   Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?");
	   Matcher matcher = pattern.matcher(baseFormula);
	   if (matcher.find()) {
		   return true;
     }
	   return false;
   }
 }
