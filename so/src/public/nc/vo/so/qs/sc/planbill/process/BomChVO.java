package nc.vo.so.qs.sc.planbill.process;

import java.lang.reflect.Method;
import java.util.Comparator;

import org.apache.commons.beanutils.Converter;

import nc.jdbc.framework.util.BeanConvertor;
import nc.jdbc.framework.util.StringConvertor;
import nc.vo.hr.append.IAppendableVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

@SuppressWarnings("serial")
public class BomChVO extends SuperVO implements IAppendableVO,Comparable<Object>{
	
	private java.lang.String pk_materail;
	private java.lang.String parentmatcode;
	private java.lang.String parentmatname;
	private java.lang.String c_materail;
	private java.lang.String matercode;
	private java.lang.String matername;
	private java.lang.String materspec;
	private java.lang.String matertype;
	private java.lang.String munit;
	private java.lang.String munitid;
	private java.lang.String qunitid;
	private UFDouble itemnum;
	private java.lang.String cbomid;
	private java.lang.String cbomversion;
	private java.lang.String itemmemo;
	private Integer bomlevel;
	private java.lang.String kz;
	private java.lang.String ys;
	private java.lang.String rate;
	
	public java.lang.String getCbomid() {
		return cbomid;
	}
	public void setCbomid(java.lang.String cbomid) {
		this.cbomid = cbomid;
	}
	
	public java.lang.String getRate() {
		return rate;
	}
	public void setRate(java.lang.String rate) {
		this.rate = rate;
	}
	public java.lang.String getKz() {
		return kz;
	}
	public void setKz(java.lang.String kz) {
		this.kz = kz;
	}
	public java.lang.String getYs() {
		return ys;
	}
	public void setYs(java.lang.String ys) {
		this.ys = ys;
	}
	public java.lang.String getMunitid() {
		return munitid;
	}
	public void setMunitid(java.lang.String munitid) {
		this.munitid = munitid;
	}
	public java.lang.String getQunitid() {
		return qunitid;
	}
	public void setQunitid(java.lang.String qunitid) {
		this.qunitid = qunitid;
	}
	
	public java.lang.String getC_materail() {
		return c_materail;
	}
	public void setC_materail(java.lang.String c_materail) {
		this.c_materail = c_materail;
	}

	
	public Integer getBomlevel() {
		return bomlevel;
	}
	public void setBomlevel(Integer bomlevel) {
		this.bomlevel = bomlevel;
	}
	public java.lang.String getPk_materail() {
		return pk_materail;
	}
	public void setPk_materail(java.lang.String pk_materail) {
		this.pk_materail = pk_materail;
	}
	public java.lang.String getParentmatcode() {
		return parentmatcode;
	}
	public void setParentmatcode(java.lang.String parentmatcode) {
		this.parentmatcode = parentmatcode;
	}
	public java.lang.String getParentmatname() {
		return parentmatname;
	}
	public void setParentmatname(java.lang.String parentmatname) {
		this.parentmatname = parentmatname;
	}
	public java.lang.String getMatercode() {
		return matercode;
	}
	public void setMatercode(java.lang.String matercode) {
		this.matercode = matercode;
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
	public java.lang.String getMunit() {
		return munit;
	}
	public void setMunit(java.lang.String munit) {
		this.munit = munit;
	}
	public UFDouble getItemnum() {
		return itemnum;
	}
	public void setItemnum(UFDouble itemnum) {
		this.itemnum = itemnum;
	}
	public java.lang.String getCbomversion() {
		return cbomversion;
	}
	public void setCbomversion(java.lang.String cbomversion) {
		this.cbomversion = cbomversion;
	}
	public java.lang.String getItemmemo() {
		return itemmemo;
	}
	public void setItemmemo(java.lang.String itemmemo) {
		this.itemmemo = itemmemo;
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
	@Override
	public int compareTo(Object o) {
		// TODO 自动生成的方法存根
		if (this.bomlevel == null) {
			return -1;
		}
		if (((BomChVO)o).bomlevel == null) {
			return 1;
		}
		return Integer.valueOf(this.bomlevel).intValue() > Integer.valueOf(((BomChVO)o).bomlevel).intValue() ? 1 : -1;
		
		
	}

	

}
