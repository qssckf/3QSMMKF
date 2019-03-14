package nc.tool.so.wa.fetchdata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nc.jdbc.framework.mapping.IMappingMeta;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.pub.Constructor;

public class FetchDataMappingMeta implements IMappingMeta {
	
	private ISuperVO vo;
	private Class Clazz;
	public FetchDataMappingMeta(Class c){
		vo = (ISuperVO)Constructor.construct(c);
		Clazz = c;
	}
	
	@Override
	public String getPrimaryKey() {
		// TODO 自动生成的方法存根
		return this.vo.getPrimaryKey();
	}

	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		  Method m1 = null;
		  String tb = null;
		try {
			m1 = this.Clazz.getMethod("getDefaultTableName", new Class[]{});
		} catch (SecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
		try {
			tb = (String) m1.invoke(this.Clazz, new Object[]{});
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		  return tb;
	}

	@Override
	public String[] getAttributes() {
		// TODO 自动生成的方法存根
		return this.getTableColumnName(this.vo.getMetaData().getAttributes());
	}

	private String[] getTableColumnName(IAttributeMeta[] attributes) {
		// TODO 自动生成的方法存根
		List<String> vname=new ArrayList<String>();
		for (IAttributeMeta field : attributes) {
			
			if(field.getColumn()==null&&(field.getName().equals("ts")||field.getName().equals("dr"))){
				vname.add(field.getName());
			}else if (field.getColumn()!=null){
				vname.add(field.getColumn().getName());
			}
		}
		return vname.toArray(new String[vname.size()]);
		
		}

	@Override
	public String[] getColumns() {
		// TODO 自动生成的方法存根
		return this.getTableColumnName(this.vo.getMetaData().getAttributes());
	}

}
