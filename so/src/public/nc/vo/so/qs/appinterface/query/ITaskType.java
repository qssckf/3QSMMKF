package nc.vo.so.qs.appinterface.query;


public abstract interface ITaskType
{
  public static final String CATEGORY_SUBMITTED = "submit";
  public static final String CATEGORY_RECEIVED = "ishandled";
  public static final String SUBMITTED_UNHANDLED = "unhandled";
  public static final String SUBMITTED_HANDLED = "finished";
  public static final String SUBMITTED_HANDLING = "handling";
  public static final String RECEIVED_HANDLED = "handled";
  public static final String RECEIVED_UNHANDLED = "unhandled";
  
  public abstract String getCategory();
  
  public abstract String getCode();
  
  public abstract String getName();
  
  public abstract TaskQuery createNewTaskQuery();
}

