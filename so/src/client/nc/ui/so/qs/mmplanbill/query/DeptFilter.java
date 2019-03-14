package nc.ui.so.qs.mmplanbill.query;
 
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.org.ref.DeptDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refregion.AbstractLinkageColumnListener;
import nc.ui.pubapp.uif2app.query2.refregion.CommonTwoLayerListener;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;















public class DeptFilter extends CommonTwoLayerListener
{
  private QueryConditionDLGDelegator querydlg;
  private String orgfield;
  private String deptfield;
  
  public DeptFilter(QueryConditionDLGDelegator dlg, String orgfield,String deptfield)
  {
    super(dlg);
    this.querydlg = dlg;
    this.orgfield=orgfield;
    this.deptfield=deptfield;
  }
  


  public void addEditorListener()
  {
    setFatherPath(this.orgfield);
    setChildPath(this.deptfield);
    
    this.querydlg.registerCriteriaEditorListener(this);
  }
  
  
  
  
  @Override
  public void setChildRefRegion(List<IFieldValueElement> fatherValues,IFilterEditor editor) {
	// TODO 自动生成的方法存根
	  
	  
	  List<String> diffValues = new ArrayList();
	   for (IFieldValueElement fve : fatherValues) {
		 if ((fve.getSqlString() != null) && (!diffValues.contains(fve.getSqlString())))
		 {
			 diffValues.add(fve.getSqlString());
		 }
		 
	   }
	   FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
	   
	   JComponent component = wrapper.getFieldValueElemEditorComponent();
	   
	   UIRefPane refPane = null;
	   if ((component instanceof UIRefPane)) {
		   refPane = (UIRefPane)component;
	   }
	   
	   if (refPane == null) {
		   return;
	   }
	   
	   if (refPane.getRefModel() == null) {
		   return;
	   }
	   
	   if(diffValues.size()==1){
		   
		   String[] orgs=diffValues.toArray(new String[diffValues.size()]);
		   
		   
		   FilterDeptRefUtils utils = new FilterDeptRefUtils(refPane);
		    
		   utils.filterItemRefByOrg(orgs[0]);	   
		   
		   
	   }else{
		   return;
	   }


  }

  
}

