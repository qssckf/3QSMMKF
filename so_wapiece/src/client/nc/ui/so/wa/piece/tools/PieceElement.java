package nc.ui.so.wa.piece.tools;

public class PieceElement {
	
		private String code; //�Ƽ�Ҫ�ر���
		private String name; //�Ƽ�Ҫ������
		private String type;  //�Ƽ�Ҫ������
		private String[] applyrange;  //�Ƽ�Ҫ�ع�ʽ���÷�Χ,������֤��ʽ������Ҫ���Ƿ�ƥ������
		private String[] applyposition; //�Ƽ�Ҫ������λ��
		private String fetchMethod; //ȡ��Ҫ��ִ�з���
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
