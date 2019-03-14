package nc.qs.ws.app.pub;

import org.json.JSONObject;

public class reqData {
	
	private String Appid;
	private String Serviceid;
	private JSONObject Params;
	
	public String getServiceid() {
		return Serviceid;
	}
	public void setServiceid(String serviceid) {
		Serviceid = serviceid;
	}
	
	public String getAppid() {
		return Appid;
	}
	public void setAppid(String appid) {
		Appid = appid;
	}
	public JSONObject getParams() {
		return Params;
	}
	public void setParams(JSONObject params) {
		Params = params;
	}
	
	
	

}
