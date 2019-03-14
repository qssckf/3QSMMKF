package nc.vo.so.qs.appinterface.util;


public abstract interface IBillType
{
  
  public abstract String getCategory();
  
  public abstract String getCode();
  
  public abstract String getName();
  
  public abstract BillQuery createNewTaskQuery();
}
