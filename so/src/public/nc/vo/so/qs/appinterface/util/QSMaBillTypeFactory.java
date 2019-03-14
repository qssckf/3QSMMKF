package nc.vo.so.qs.appinterface.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.vo.so.qs.appinterface.query.PreOrderHanded;

public class QSMaBillTypeFactory {
	
	private static QSMaBillTypeFactory instance=null;
	
	private Map<String, Map<String, IBillType>> typeMap = null;
	private List<IBillType> billlist = null;
	
	public static QSMaBillTypeFactory getInstance() {
	    if (instance == null) {
	      synchronized (QSMaBillTypeFactory.class) {
	        if (instance == null) {
	          instance = new QSMaBillTypeFactory();
	        }
	      }
	    }
	    return instance;
	}
	
	public QSMaBillTypeFactory(){
		init();
	}

	private void init() {
		// TODO 自动生成的方法存根
		this.billlist = new ArrayList();
		
		this.billlist.add(new PreOrderHanded());
		
	}
	
	public IBillType get(String category, String code) {
	    Map<String, IBillType> map = (Map)getTypeMap().get(category);
	    
	    if (map == null) {
	      return null;
	    }
	    return (IBillType)map.get(code);
	}
	
	public IBillType[] get(String category){
		
	    Map<String, IBillType> map = (Map)getTypeMap().get(category);
	    
	    if (map == null) {
	      return null;
	    }
	    return (IBillType[])map.values().toArray(new IBillType[0]);
	}
	  
	
	public String[] getCategories(){
	    return (String[])getTypeMap().keySet().toArray(new String[0]);
	}
	
	private Map<String, Map<String, IBillType>> getTypeMap(){
		
	    if (this.typeMap == null) {
	      synchronized (QSMaBillTypeFactory.class) {
	    	  
	    	  if (this.typeMap == null){
	    		  
	    		  this.typeMap = new HashMap();
	    		  
	    		  for (IBillType c : this.billlist){
	    			  
	    			  if (!this.typeMap.containsKey(c.getCategory())) {
	    				  
	    				  Map<String, IBillType> map = new LinkedHashMap();
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
