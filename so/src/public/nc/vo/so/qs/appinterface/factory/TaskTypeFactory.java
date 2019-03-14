package nc.vo.so.qs.appinterface.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nc.vo.pf.mobileapp.SubmittedHandled;
import nc.vo.pf.mobileapp.SubmittedHandling;
import nc.vo.pf.mobileapp.SubmittedUnhandled;
import nc.vo.so.qs.appinterface.query.ITaskType;
import nc.vo.so.qs.appinterface.query.ReceivedHandled;
import nc.vo.so.qs.appinterface.query.ReceivedUnhandled;




public class TaskTypeFactory
{
  private static TaskTypeFactory instance = null;
  
  private Map<String, Map<String, ITaskType>> typeMap = null;
  private List<ITaskType> typeList = null;
  
  private TaskTypeFactory()
  {
    init();
  }
  
  public static TaskTypeFactory getInstance() {
    if (instance == null) {
      synchronized (TaskTypeFactory.class) {
        if (instance == null) {
          instance = new TaskTypeFactory();
        }
      }
    }
    return instance;
  }
  
  private void init() {
    this.typeList = new ArrayList();
 
//    this.typeList.add(new SubmittedUnhandled());
//	this.typeList.add(new SubmittedHandling());
//	this.typeList.add(new SubmittedHandled());
    this.typeList.add(new ReceivedUnhandled());
    this.typeList.add(new ReceivedHandled());
    
  }
  
  public ITaskType get(String category, String code) {
    Map<String, ITaskType> map = (Map)getTypeMap().get(category);
    
    if (map == null) {
      return null;
    }
    return (ITaskType)map.get(code);
  }
  

  public ITaskType[] get(String category)
  {
    Map<String, ITaskType> map = (Map)getTypeMap().get(category);
    
    if (map == null) {
      return null;
    }
    return (ITaskType[])map.values().toArray(new ITaskType[0]);
  }
  
  public String[] getCategories()
  {
    return (String[])getTypeMap().keySet().toArray(new String[0]);
  }
  
  private Map<String, Map<String, ITaskType>> getTypeMap()
  {
    if (this.typeMap == null) {
      synchronized (TaskTypeFactory.class) {
        if (this.typeMap == null)
        {

          this.typeMap = new HashMap();
          
          for (ITaskType c : this.typeList)
          {
            if (!this.typeMap.containsKey(c.getCategory())) {
              Map<String, ITaskType> map = new LinkedHashMap();
              this.typeMap.put(c.getCategory(), map);
            }
            
            ((Map)this.typeMap.get(c.getCategory())).put(c.getCode(), c);
          }
        }
      }
    }
    
    return this.typeMap;
  }
}