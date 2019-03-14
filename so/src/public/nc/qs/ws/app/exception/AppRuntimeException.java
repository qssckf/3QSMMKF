package nc.qs.ws.app.exception;

public class AppRuntimeException extends RuntimeException {
	
	private String code="0";
	
	private String msg="App has exception!";
	

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public AppRuntimeException(String code, Throwable cause,String message) {

		super(message, cause);
		
		this.msg=message;
		
		this.code=code;

	}


	public AppRuntimeException(String message) {
		// TODO 自动生成的构造函数存根
		super(message);
		this.msg=message;
		this.code="0";
	}


	public AppRuntimeException(String code, String msg) {
		// TODO 自动生成的构造函数存根
		super(msg);
		this.msg=msg;
		this.code=code;
	}


	
	
	
	 
	
}
