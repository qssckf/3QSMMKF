package nc.ui.so.qs.sc.action.bill;
import java.awt.event.ActionEvent;


import nc.bs.uif2.BusinessExceptionAdapter;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.uif2.CheckDataPermissionUtil;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.CommonConfirmDialogUtils;
import nc.ui.uif2.editor.IEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.uif2.annoations.MethodType;
import nc.uif2.annoations.ModelMethod;
import nc.uif2.annoations.ModelType;
import nc.uif2.annoations.ViewMethod;
import nc.uif2.annoations.ViewType;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.uif2.LoginContext;


public abstract class SetBillValueAction extends NCAction
 {
   private static final long serialVersionUID = 283994281412077261L;
   private AbstractAppModel model;


   private IValidationService validationService;
   private String mdOperateCode = null;
   private String operateCode = null;
   private String resourceCode = null;
   
   public AbstractAppModel getModel() {
	   return model;
   }


   public void setModel(AbstractAppModel model) {
	   this.model = model;
	   this.model.addAppEventListener(this);
   }
   
 
   public abstract void doSetValue() throws Exception;
   
   public void doAction(ActionEvent e)
     throws Exception
   {
     checkPermission();
     
     validate();
     
     doSetValue();
   }
   
   
   protected void validate() {
	 Object vo = (Object)getModel().getSelectedData();
     validate(vo);
   }
   
   protected void checkPermission() throws Exception {
     Object[] objs = getUnDataPermissionData();
     if ((objs != null) && (objs.length > 0))
     {
       throw new BusinessException(IShowMsgConstant.getDataPermissionInfo());
     }
   }
   
 
 
 
 

  protected Object[] getUnDataPermissionData()
  {
    Object obj = getCheckData();
    return CheckDataPermissionUtil.getUnDataPermissionData(this.operateCode, this.mdOperateCode, this.resourceCode, this.model.getContext(), obj);
  }
  

  protected Object getCheckData()
  {
    return getModel().getSelectedData();
  }
  

  protected boolean isActionEnable()
  {
    return (getModel().getUiState() == UIState.NOT_EDIT) && (this.model.getSelectedData() != null) && (!isCurrentDataEnable());
  }
  
  
  public boolean isCurrentDataEnable()
  {
    CircularlyAccessibleValueObject vo = (CircularlyAccessibleValueObject)getModel().getSelectedData();
    
    return (vo.getAttributeValue("enablestate") != null) && (2 == ((Integer)vo.getAttributeValue("enablestate")).intValue());
  }
  

  public IValidationService getValidationService()
  {
    return this.validationService;
  }
  
  public void setValidationService(IValidationService validationService) {
    this.validationService = validationService;
  }
  



  protected void validate(Object value)
  {
    if (this.validationService != null) {
      try {
        this.validationService.validate(value);
      } catch (ValidationException e) {
        throw new BusinessExceptionAdapter(e);
      }
    }
  }
  
  public String getMdOperateCode()
  {
    return this.mdOperateCode;
  }
  
  public void setMdOperateCode(String mdOperateCode) {
    this.mdOperateCode = mdOperateCode;
  }
  
  public String getOperateCode() {
    return this.operateCode;
  }
  
  public void setOperateCode(String operateCode) {
    this.operateCode = operateCode;
  }
  
  public String getResourceCode() {
    return this.resourceCode;
  }
  
  public void setResourceCode(String resourceCode) {
    this.resourceCode = resourceCode;
  }
}

