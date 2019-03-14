package nc.qs.ws.app.gateway.service.xml;

public class ServiceNode {
	
	 private String id;
	 private String method;
	 private String returnType;
	 private java.util.Map<String, Object> propertiesMap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public java.util.Map<String, Object> getPropertiesMap() {
		return propertiesMap;
	}
	public void setPropertiesMap(java.util.Map<String, Object> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

}
