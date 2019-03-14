package nc.qs.ws.app.gateway.service;

import nc.qs.ws.app.gateway.service.xml.ServiceNode;

import org.json.JSONArray;
import org.json.JSONException;

public interface IInvokeCallback {
	
	public abstract JSONArray handleResult(Object retObj,ServiceNode sn) throws JSONException;

}
