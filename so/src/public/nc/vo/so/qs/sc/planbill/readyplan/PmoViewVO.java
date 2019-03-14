package nc.vo.so.qs.sc.planbill.readyplan;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.Converter;

import nc.jdbc.framework.util.BeanConvertor;
import nc.jdbc.framework.util.StringConvertor;
import nc.vo.hr.append.IAppendableVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class PmoViewVO extends SuperVO implements IAppendableVO{
	
	
	private java.lang.String cpmohid;
	private java.lang.String ctrantypename;
	private UFDate dbilldate;
	private String vbillcode;
	private String cuscode;
	private String cusname;
	private String dept;
	private String machinecode;
	private String machinename;
	private String matcode;
	private String matname;
	private String matspec;
	private String splitstofftype;
	private UFDouble silkwide;
	private UFDouble elongation;
	private UFDouble tension;
	private UFBoolean spacer;
	private String otherproduction;
	private String tagline;
	private UFDouble latitudedensity;
	private UFDouble width;
	private UFDouble length;
	private String unit;
	private UFDouble nnum;
	private String qunit;
	private UFDouble nastnum;
	private UFDateTime startdate;
	private UFDateTime enddate;
	
	
	
	
	
	public java.lang.String getCpmohid() {
		return cpmohid;
	}





	public void setCpmohid(java.lang.String cpmohid) {
		this.cpmohid = cpmohid;
	}





	public java.lang.String getCtrantypename() {
		return ctrantypename;
	}





	public void setCtrantypename(java.lang.String ctrantypename) {
		this.ctrantypename = ctrantypename;
	}





	public UFDate getDbilldate() {
		return dbilldate;
	}





	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}





	public String getVbillcode() {
		return vbillcode;
	}





	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
	}





	public String getCuscode() {
		return cuscode;
	}





	public void setCuscode(String cuscode) {
		this.cuscode = cuscode;
	}





	public String getCusname() {
		return cusname;
	}





	public void setCusname(String cusname) {
		this.cusname = cusname;
	}





	public String getDept() {
		return dept;
	}





	public void setDept(String dept) {
		this.dept = dept;
	}





	public String getMachinecode() {
		return machinecode;
	}





	public void setMachinecode(String machinecode) {
		this.machinecode = machinecode;
	}





	public String getMachinename() {
		return machinename;
	}





	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}





	public String getMatcode() {
		return matcode;
	}





	public void setMatcode(String matcode) {
		this.matcode = matcode;
	}





	public String getMatname() {
		return matname;
	}





	public void setMatname(String matname) {
		this.matname = matname;
	}





	public String getMatspec() {
		return matspec;
	}





	public void setMatspec(String matspec) {
		this.matspec = matspec;
	}





	public String getSplitstofftype() {
		return splitstofftype;
	}





	public void setSplitstofftype(String splitstofftype) {
		this.splitstofftype = splitstofftype;
	}





	public UFDouble getSilkwide() {
		return silkwide;
	}





	public void setSilkwide(UFDouble silkwide) {
		this.silkwide = silkwide;
	}





	public UFDouble getElongation() {
		return elongation;
	}





	public void setElongation(UFDouble elongation) {
		this.elongation = elongation;
	}





	public UFDouble getTension() {
		return tension;
	}





	public void setTension(UFDouble tension) {
		this.tension = tension;
	}





	public UFBoolean getSpacer() {
		return spacer;
	}





	public void setSpacer(UFBoolean spacer) {
		this.spacer = spacer;
	}





	public String getOtherproduction() {
		return otherproduction;
	}





	public void setOtherproduction(String otherproduction) {
		this.otherproduction = otherproduction;
	}





	public String getTagline() {
		return tagline;
	}





	public void setTagline(String tagline) {
		this.tagline = tagline;
	}





	public UFDouble getLatitudedensity() {
		return latitudedensity;
	}





	public void setLatitudedensity(UFDouble latitudedensity) {
		this.latitudedensity = latitudedensity;
	}





	public UFDouble getWidth() {
		return width;
	}





	public void setWidth(UFDouble width) {
		this.width = width;
	}





	public UFDouble getLength() {
		return length;
	}





	public void setLength(UFDouble length) {
		this.length = length;
	}





	public String getUnit() {
		return unit;
	}





	public void setUnit(String unit) {
		this.unit = unit;
	}





	public UFDouble getNnum() {
		return nnum;
	}





	public void setNnum(UFDouble nnum) {
		this.nnum = nnum;
	}





	public String getQunit() {
		return qunit;
	}





	public void setQunit(String qunit) {
		this.qunit = qunit;
	}





	public UFDouble getNastnum() {
		return nastnum;
	}





	public void setNastnum(UFDouble nastnum) {
		this.nastnum = nastnum;
	}





	public UFDateTime getStartdate() {
		return startdate;
	}





	public void setStartdate(UFDateTime startdate) {
		this.startdate = startdate;
	}





	public UFDateTime getEnddate() {
		return enddate;
	}





	public void setEnddate(UFDateTime enddate) {
		this.enddate = enddate;
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
