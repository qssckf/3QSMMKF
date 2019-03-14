package nc.vo.so.qs.sc.planbill.process;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.Converter;

import nc.jdbc.framework.util.BeanConvertor;
import nc.jdbc.framework.util.StringConvertor;
import nc.vo.hr.append.IAppendableVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.SuperVO;

@SuppressWarnings("serial")
public class BomVersionVO extends SuperVO implements IAppendableVO{
	
	private java.lang.String pk_material;
	private java.lang.String matername;
	private java.lang.String materspec;
	private java.lang.String matertype;
	private java.lang.String bomtype;
	private java.lang.String bomversion;
	private java.lang.String memo;
	private java.lang.String cbomid;
	private java.lang.String pk_org;
	private java.lang.String tmbomid;
	private Double tmitemnum;
	

	public static final String PK_MATERIAL = "pk_material";
	public static final String MATERNAME = "matername";
	public static final String MATERSPEC = "materspec";
	public static final String MATERTYPE = "matertype";
	public static final String BOMTYPE = "bomtype";
	public static final String BOMVERSION = "bomversion";
	public static final String MEMO = "memo";
	public static final String CBOMID = "cbomid";
	public static final String PK_ORG = "pk_org";
	public static final String TMBOMID = "tmbomid";
	public static final String TMITEMNUM="tmitemnum";
	
	
	public java.lang.String getTmbomid() {
		return tmbomid;
	}

	public void setTmbomid(java.lang.String tmbomid) {
		this.tmbomid = tmbomid;
	}

	public Double getTmitemnum() {
		return tmitemnum;
	}

	public void setTmitemnum(Double tmitemnum) {
		this.tmitemnum = tmitemnum;
	}
	
	
	public java.lang.String getPk_org() {
		return pk_org;
	}

	public void setPk_org(java.lang.String pk_org) {
		this.pk_org = pk_org;
	}

	public java.lang.String getCbomid() {
		return cbomid;
	}

	public void setCbomid(java.lang.String cbomid) {
		this.cbomid = cbomid;
	}
	
	
	public java.lang.String getBomversion() {
		return bomversion;
	}

	public void setBomversion(java.lang.String bomversion) {
		this.bomversion = bomversion;
	}
	
	public java.lang.String getPk_material() {
		return pk_material;
	}
	
	public void setPk_material(java.lang.String pk_material) {
		this.pk_material = pk_material;
	}
	
	public java.lang.String getMatername() {
		return matername;
	}
	
	public void setMatername(java.lang.String matername) {
		this.matername = matername;
	}
	
	public java.lang.String getMaterspec() {
		return materspec;
	}
	
	public void setMaterspec(java.lang.String materspec) {
		this.materspec = materspec;
	}
	
	public java.lang.String getMatertype() {
		return matertype;
	}
	
	public void setMatertype(java.lang.String matertype) {
		this.matertype = matertype;
	}
	
	public java.lang.String getBomtype() {
		return bomtype;
	}
	
	public void setBomtype(java.lang.String bomtype) {
		this.bomtype = bomtype;
	}
	
	public java.lang.String getMemo() {
		return memo;
	}
	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}
	
	public void setAttributeValue(String name, Object value) {
		// TODO 自动生成的方法存根
		
		String attributeName = name.toLowerCase();
		Method method = BeanHelper.getMethod(this, attributeName);
		if (method != null)
		{
			Converter converter = BeanConvertor.getConVerter(method.getParameterTypes()[0]);
			if ((converter != null) && (value != null))
			{
				if (!(converter instanceof StringConvertor)) {
					value = converter.convert(method.getParameterTypes()[0], value);
				}
			}
			
			super.setAttributeValue(attributeName, value);
			}
		
		super.setAttributeValue(name, value);
	}

}
