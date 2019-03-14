package nc.qs.ws.app.gateway.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.AbstractAttribute;
import nc.bs.framework.common.RuntimeEnv;
import nc.qs.ws.app.exception.AppRuntimeException;
import nc.qs.ws.app.gateway.service.xml.GatewayNode;
import nc.qs.ws.app.gateway.service.xml.ServiceNode;
import nc.vo.jcom.lang.StringUtil;


public class NcServiceFactory {
	
	public static ServiceNode FindNCService(String appid,String serverid) throws UnsupportedEncodingException, FileNotFoundException, DocumentException{
		
		return loadServicesFromFile(appid).get(serverid);
		
	}
	
	
	private static GatewayNode loadServicesFromFile(String appid) throws UnsupportedEncodingException, FileNotFoundException, DocumentException {
		
		Document doc = getDocument(new File(getFileName(appid)));
		
		GatewayNode rn=new GatewayNode(appid);
		
		List serviceList = doc.selectNodes("//gateway/service");
		
		for (Iterator iter = serviceList.iterator(); iter.hasNext();) {
			
			Element element = (Element)iter.next();
			
			ServiceNode sn=createServiceNode(element.attributes());
			
			sn.setPropertiesMap(getProperties(element.selectNodes("properties")));
			
			rn.put(sn.getId(), sn);
			
		}
		
		return rn;
		
	}
	
	
	private static Map<String, Object> getProperties(List source) {
		// TODO 自动生成的方法存根
		
		Map<String, Object> proMap = new HashMap();
		for (Iterator proIter = source.iterator(); proIter.hasNext();) {
			
			Element proElement = (Element)proIter.next();
			
			Iterator subProIter = proElement.elementIterator();
			while (subProIter.hasNext()) {
				Element subProElement = (Element)subProIter.next();
				String subProName = subProElement.getName().trim();
				String subProValue = subProElement.getText().trim();
				proMap.put(subProName, subProValue);
			}
		}
		return proMap;
		
	}


	private static ServiceNode createServiceNode(List<Attribute> attributes) {
		// TODO 自动生成的方法存根
		
		 ServiceNode node = new ServiceNode();
		 
		 for (Iterator element = attributes.iterator(); element.hasNext();) {
			 
			 AbstractAttribute item = (AbstractAttribute)element.next();
			 
			 String key = item.getName();
			 String value = item.getValue();
			 
			 if ("id".equalsIgnoreCase(key)) {
				 node.setId(value);
			 }
			 
			 if ("returntype".equalsIgnoreCase(key)) {
				 node.setReturnType(value);
			 }
			 
			 if ("method".equalsIgnoreCase(key)) {
				 node.setMethod(value);
			 }
			 
			 
			 
		 }
		 
		return node;
	}


	private static Document getDocument(File filename) throws UnsupportedEncodingException, FileNotFoundException, DocumentException {
		// TODO 自动生成的方法存根
		return new SAXReader().read(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
	}


	private static String getFileName(String appid) throws AppRuntimeException{
		
		
		
		if (StringUtil.isEmpty(appid)) {
			throw new AppRuntimeException("appid不能为空！" + appid);
		}
		
		String[] paths = appid.split("/");
		if ((paths == null) || (paths.length == 0)) {
			paths = new String[1];
			paths[0] = appid;
		}
		/*     */     
		StringBuffer sb = new StringBuffer();
		for (String p : paths) {
			sb.append(p).append(File.separator);
		}
		
		return new File(getNcHome()).getPath() + File.separator + "configure" + File.separator + sb.toString() + "service.xml";
		
		
		
	}
	
	private static String getNcHome(){

		return RuntimeEnv.getInstance().getNCHome() + File.separator + "conf";

	}

}
