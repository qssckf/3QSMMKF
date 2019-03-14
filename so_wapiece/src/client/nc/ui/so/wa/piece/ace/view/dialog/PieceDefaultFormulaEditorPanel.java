package nc.ui.so.wa.piece.ace.view.dialog;

import java.awt.BorderLayout;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.formula.dialog.DefaultFormulaEditorPanel;
import nc.ui.pub.formula.dialog.FormulaButtonPanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaTabbedPanel;
import nc.vo.pub.formulaset.FormulaParseFather;


public class PieceDefaultFormulaEditorPanel extends DefaultFormulaEditorPanel {
	
	private XmlBeanFactory beanFactory;
	
	public void initUI(String configXmlFilePath, FormulaParseFather formulaParse)
	{
		ClassLoader classLoader = DefaultFormulaEditorPanel.class.getClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		ClassPathResource res = new ClassPathResource(configXmlFilePath, classLoader);
		this.beanFactory = new XmlBeanFactory(res, null);
		this.beanFactory.setBeanClassLoader(classLoader);
		UIPanel container = (UIPanel)this.beanFactory.getBean("formulaContainer");
		setLayout(new BorderLayout());
		add(container, "Center");
	}
	
	 public IFormulaRealEditorPanel getFormulaValueRealEditorPanel()
	 {
		 return (IFormulaRealEditorPanel)this.beanFactory.getBean("formulavalueRealEditorPanel");
	 }
	
	 public List<IFormulaActionWithDialog> getFormulaDialogActionList() {
		 return ((FormulaButtonPanel)this.beanFactory.getBean("formulaButtonPanel")).getActionsWithDialog();
	 }
	 
	 public IFormulaTabbedPanel getFormulaFunctionPanel() {
		 return (IFormulaTabbedPanel)this.beanFactory.getBean("formulaFunctionPanel");
	 }
	 
	 public IFormulaRealEditorPanel getFormulaRealEditorPanel() {
		 return (IFormulaRealEditorPanel)this.beanFactory.getBean("formulaRealEditorPanel");
	 }

	 public IFormulaTabbedPanel getFormulaVariablePanel() {
		 return (IFormulaTabbedPanel)this.beanFactory.getBean("formulaVariablePanel");
	 }
}
