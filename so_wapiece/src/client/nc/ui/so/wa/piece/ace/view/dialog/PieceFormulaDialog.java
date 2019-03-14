package nc.ui.so.wa.piece.ace.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.ui.pub.formula.dialog.FormulaVariablePanel;
import nc.ui.pub.formula.dialog.IFormulaActionWithDialog;
import nc.ui.pub.formula.dialog.IFormulaEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaRealEditorPanel;
import nc.ui.pub.formula.dialog.IFormulaTabBuilder;
import nc.ui.pub.formula.dialog.IFormulaTabbedPanel;
import nc.ui.pub.formula.dialog.ReplaceConvertor;
import nc.ui.pub.formulaedit.FormulaEditorDialog;
import nc.ui.pub.formulaedit.FormulaWordSorter;
import nc.ui.pub.formulaparse.FormulaParse;
import nc.vo.uif2.LoginContext;

public class PieceFormulaDialog extends FormulaEditorDialog {


	private String configXmlFilePath;
	private LoginContext context;
	private IFormulaEditorPanel formulaEditorPanel;
	private String formulacode = "";
	private String formulaDesc;
	private String formulaDescWithDummyMap;
	
	
	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	private static final long serialVersionUID = -2469272849699528417L;
	
	public PieceFormulaDialog(Container parent,String configXmlFilePath, LoginContext context) {
		
		super(parent,configXmlFilePath);
		this.configXmlFilePath = configXmlFilePath;
		this.context = context;
		initialize(new FormulaParse());
		
	}
	
	private void initialize(FormulaParse parse)
	{
		setTitle("计件工资公式设置");
		this.formulaEditorPanel = new PieceDefaultFormulaEditorPanel();
		this.formulaEditorPanel.initUI(this.configXmlFilePath, parse);
		setLayout(new BorderLayout());
		add((Component)this.formulaEditorPanel, "Center");
		setSize(new Dimension(750, 600));
		initUI();
	}
	
	  public void initUI()
	  {
		  addPropertyToButtons();
	  }

	  private void addPropertyToButtons()
	  {
		  if (null == this.formulaEditorPanel) {
			  return;
		  }
		  
		  List<IFormulaActionWithDialog> actions = this.formulaEditorPanel.getFormulaDialogActionList();
		  if (actions != null) {
			  IFormulaActionWithDialog action;
			  for (Iterator<IFormulaActionWithDialog> i = actions.iterator(); i.hasNext(); action.setDialog(this)) {
				  action = (IFormulaActionWithDialog)i.next();
			  }
		  }
	  }

	public void updateListData() {
		// TODO 自动生成的方法存根
		
		List<IFormulaTabBuilder> listBuilder = ((FormulaVariablePanel)getFormulaVariablePanel()).getCustomerTabBuilders();

		for (IFormulaTabBuilder formulaTabBuilder : listBuilder) {
			((AbstractPieceTabBuilder)formulaTabBuilder).setLoginContext(getContext());
			
	}

	}
	
	 public String getFormulacode()
	 {
		 	return this.formulacode;
	   }
	   
	   public void setFormulacode(String formulacode)
	   {
		   this.formulacode = formulacode;
	   }
	   
	   public int getTabNumber(int option)
	   {
		   switch (option) {
		   case 0: 
			   if (getFormulaFunctionPanel() != null) {
				   return getFormulaFunctionPanel().getTabNum();
			   }
		   case 1: 
			   if (getFormulaVariablePanel() != null)
				   return getFormulaVariablePanel().getTabNum();
			   	   break;
		   }
		   return 0;
	   }
	   
	   public void addCustomTabBuilder(int index, IFormulaTabBuilder builder, int option)
	   {
		   switch (option) {
		   case 0: 
			   if (getFormulaFunctionPanel() != null) {
				   getFormulaFunctionPanel().addTabBuilderToTabbedPane(index, builder);
			   }
			   break;
		   case 1: 
			   if (getFormulaVariablePanel() != null) {
				   getFormulaVariablePanel().addTabBuilderToTabbedPane(index, builder);
			   }
			   break;
		   }
	   }
	 
	   public void addCustomTabBuilder(IFormulaTabBuilder builder, int option)
	   {
		   addCustomTabBuilder(getTabNumber(option), builder, option);
	   }
	   
	   public void setCustomTabBuilder(int index, IFormulaTabBuilder builder, int option)
	   {
		   if (index < getTabNumber(option)) {
			   removeTabBuilder(index, option);
			   addCustomTabBuilder(index, builder, option);
		   }
	   }
	   
	   public void removeTabBuilder(int index, int option)
	   {
		   switch (option) {
		   case 0: 
			   if (getFormulaFunctionPanel() != null) {
				   getFormulaFunctionPanel().removeTabBuilder(index);
			   }
			   break;
		   case 1: 
			   if (getFormulaVariablePanel() != null) {
				   getFormulaVariablePanel().removeTabBuilder(index);
			   }
			   break;
		   }
	   }
	   
	   public String getFormulaDescWithDummyMap()
	   {
		   return this.formulaDescWithDummyMap;
	   }
	      
	      public void setDummyInputSigMap(Map<String, String> mapInputSig)
	      {
	    	  this.formulaEditorPanel.getFormulaRealEditorPanel().addConvertor(new ReplaceConvertor(mapInputSig));
	      }
	      
	      public String getFormulaDesc()
	      {
	    	  return this.formulaDesc;
	      }
	      
	      public void setFormulaDesc(String formulaDesc)
	      {
	    	  this.formulaDesc = formulaDesc;
	    	  getFormulaRealEditorPanel().setConvertedText(formulaDesc);
	      }
	      
	      public int getBuilderIndex(String tabname, int option)
	      {
	    	  switch (option) {
	    	  case 0: 
	    		  return getFormulaFunctionPanel().getBuilderIndex(tabname);
	        case 1: 
	        	return getFormulaVariablePanel().getBuilderIndex(tabname);
	        }
	        
	    	  return -1;
	      }
	      
	      public void setSelectedTab(String tabName, int option)
	      {
	    	  switch (option) {
	    	  case 0: 
	    		  	getFormulaFunctionPanel().setSelectedTab(tabName);
	    		  	break;
	    	  case 1: 
	    		  getFormulaVariablePanel().setSelectedTab(tabName);
	    		  break;
	        }
	        
	      }
	 
	   public IFormulaRealEditorPanel getFormulaValueRealEditorPanel()
	   {
		   return ((PieceDefaultFormulaEditorPanel)this.formulaEditorPanel).getFormulaValueRealEditorPanel();
	   }
	   
	   public IFormulaTabbedPanel getFormulaFunctionPanel()
	   {
		   return ((PieceDefaultFormulaEditorPanel)this.formulaEditorPanel).getFormulaFunctionPanel();
	   }
	   
	   public IFormulaRealEditorPanel getFormulaRealEditorPanel()
	   {
		   return ((PieceDefaultFormulaEditorPanel)this.formulaEditorPanel).getFormulaRealEditorPanel();
	   }
	   
	   public IFormulaTabbedPanel getFormulaVariablePanel()
	   {
		   return ((PieceDefaultFormulaEditorPanel)this.formulaEditorPanel).getFormulaVariablePanel();
	   }
	   
	   public FormulaWordSorter getFormulaWordSorter()
	   {
		   return ((PieceDefaultFormulaEditorPanel)this.formulaEditorPanel).getFormulaRealEditorPanel().getFormulaWordSorter();
	   }

	public void setHasEditFormula(boolean b) {
		// TODO 自动生成的方法存根
		
	}
}


