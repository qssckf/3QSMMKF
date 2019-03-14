package nc.ui.so.qs.mmplanbill.process.model;

import nc.bs.logging.Logger;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.trade.pub.IExAggVO;

public class QsVOBillListPanelValueSetter implements BillListView.IBillListPanelValueSetter{
	


	@Override
	public void setHeaderDatas(BillListPanel listPanel,Object[] allDatas) {
		// TODO 自动生成的方法存根
		
		if ((allDatas == null) || (allDatas.length == 0)) {
			listPanel.setHeaderValueVO(null);
			return;
		}
		
		CircularlyAccessibleValueObject[] vos = new CircularlyAccessibleValueObject[allDatas.length];
		for (int i = 0; i < allDatas.length; i++) {
			vos[i] = (CircularlyAccessibleValueObject) allDatas[i];
		}
		
		if (listPanel.getBillListData().isMeataDataTemplate()) {
			listPanel.getBillListData().setHeaderValueObjectByMetaData(allDatas);
		
		}else
		{
			listPanel.setHeaderValueVO(vos);
		}
		
		
		BillModel headModel = listPanel.getHeadBillModel();
		if (headModel != null)
		{
			if (listPanel.getBillListData().isMeataDataTemplate()) {
				headModel.loadLoadRelationItemValue();
			}
			headModel.execLoadFormula();
		}

	}

	@Override
	public void setHeaderRowData(BillListPanel listPanel,Object rowData, int row) {
		// TODO 自动生成的方法存根
		if (rowData == null) {
			Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000002", null, new String[] { String.valueOf(row) }));
		} else {
				
			BillModel headModel = listPanel.getBillListData().getHeadBillModel();
			if (headModel != null)
			{
				if (!(rowData instanceof AggregatedValueObject)){
					
					headModel.setBodyRowVO((CircularlyAccessibleValueObject) rowData, row);
				    
					if (listPanel.getBillListData().isMeataDataTemplate()){
					      
						BillItem[] items = headModel.getBodyItems();
					      
						if ((items != null) && (items.length > 0)){
					        
							for (BillItem item : items) {
					          headModel.loadLoadRelationItemValue(row, item.getKey());
					        }
					      }
					}
					headModel.execLoadFormulaByRow(row);
					
				}else{
					 
					headModel.setBodyRowVO(((AggregatedValueObject)rowData).getParentVO(), row);
					    
					if (listPanel.getBillListData().isMeataDataTemplate()){
					      
						BillItem[] items = headModel.getBodyItems();
					      
						if ((items != null) && (items.length > 0)){
					        
							for (BillItem item : items) {
					          headModel.loadLoadRelationItemValue(row, item.getKey());
					        }
					      }
					}
					headModel.execLoadFormulaByRow(row);
				}
				
		   
		  }
		}
	}

	@Override
	public void setBodyData(BillListPanel listPanel,Object selectedData) {
		// TODO 自动生成的方法存根
		
		if ((selectedData != null) && (!(selectedData instanceof AggregatedValueObject)))
		{
		    Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000000"));
		    
		}else if (selectedData == null) {
		    
			listPanel.setBodyValueVO(null);
			
		}else {
			
			String[] tabcodes = listPanel.getBillListData().getBodyTableCodes();
		    if ((tabcodes == null) || (tabcodes.length == 0)) {
		    	return;
		    }
		         
		    setBodyData4VO(listPanel, selectedData, tabcodes);
		    processLoadRelation(listPanel, tabcodes);
		}
		
		
	}
	
	protected void processLoadRelation(BillListPanel listPanel, String[] tabcodes)
	{
	  for (String tabcode : tabcodes)
	  {
	    BillModel bodyModel = listPanel.getBodyBillModel(tabcode);
	    if (bodyModel != null)
	    {
	      if (listPanel.getBillListData().isMeataDataTemplate())
	        bodyModel.loadLoadRelationItemValue();
	      bodyModel.execLoadFormula();
	    }
	  }
	}
	
	
	protected void setBodyData4VO(BillListPanel listPanel, Object data, String[] tabcodes)
	{
      if ((data instanceof ExtendedAggregatedValueObject))
      {
        ExtendedAggregatedValueObject extAggvo = (ExtendedAggregatedValueObject)data;
        for (String tabcode : tabcodes)
        {
          listPanel.setBodyValueVO(tabcode, extAggvo.getTableVO(tabcode));
        }
      }else if ((data instanceof IExAggVO)){
        IExAggVO extAggvo = (IExAggVO)data;
        for (String tabcode : tabcodes)
        {
          listPanel.setBodyValueVO(tabcode, extAggvo.getTableVO(tabcode));
        }
      }else if ((data instanceof AggregatedValueObject))
      {
        AggregatedValueObject avo = (AggregatedValueObject)data;
        listPanel.setBodyValueVO(avo.getChildrenVO());
      }
    }
	
	


}
