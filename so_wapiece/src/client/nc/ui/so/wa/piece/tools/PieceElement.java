package nc.ui.so.wa.piece.tools;

public class PieceElement {
	
		private String code; //计件要素编码
		private String name; //计件要素名称
		private String type;  //计件要素类型
		private String[] applyrange;  //计件要素公式适用范围,用于验证公式上下文要素是否匹配类型
		private String[] applyposition; //计件要素适用位置
		private String fetchMethod; //取数要素执行方法
		private String fetchrulesf;
		
		public String getFetchrulesf() {
			return fetchrulesf;
		}

		public void setFetchrulesf(String fetchrulesf) {
			this.fetchrulesf = fetchrulesf;
		}

		public String[] getApplyposition() {
			return applyposition;
		}

		public void setApplyposition(String[] applyposition) {
			this.applyposition = applyposition;
		}

		public String getFetchMethod() {
			return fetchMethod;
		}

		public void setFetchMethod(String fetchMethod) {
			this.fetchMethod = fetchMethod;
		}


		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String[] getApplyrange() {
			return applyrange;
		}

		public void setApplyrange(String[] applyrange) {
			this.applyrange = applyrange;
		}

	


		
		public PieceElement(String code,String name,String type,String range,String postion,String method,String fetchrulesf){
			this.code=code;
			this.name=name;
			this.type=type;
			this.applyrange=range.split(",");
			this.applyposition=postion.split(",");
			this.fetchMethod=method;
			this.fetchrulesf=fetchrulesf;
		}
		
		
}
