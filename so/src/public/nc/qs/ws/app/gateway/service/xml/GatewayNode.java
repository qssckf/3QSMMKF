package nc.qs.ws.app.gateway.service.xml;

import java.util.HashMap;

public class GatewayNode extends HashMap<String, ServiceNode> {
	
	private String appid;
	
	public GatewayNode(String appid){
		this.appid=appid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	

}
