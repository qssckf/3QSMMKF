package nc.vo.so.qs.appinterface.query;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bill.data.access.BillAccess;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.pub.pf.IMobileBillConstructListener;
import nc.bs.pub.pf.PfUtilTools;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.pf.mobileapp.ITaskType;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pf.mobileapp.TaskMetaData;
import nc.vo.pf.mobileapp.exception.LoopTerminateNotification;
import nc.vo.pf.mobileapp.log.PerformanceLoggerUtil;
import nc.vo.pf.mobileapp.query.IPaginationQuery;
import nc.vo.pf.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pub.billtype2.Billtype2VO;
import nc.vo.pub.billtype2.ExtendedClassEnum;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

 
 
 
 public abstract class RefDataQuery implements IPaginationQuery<Map<String, Object>>{
	 
   public RefDataQuery() {
	   
   }
   
   private Class VoClass = null;
   private String VoClassName=null;
   private String pk_group = null;
   private String condition = null;
   private IUAPQueryBS qry = null;
   private String orgfieldname = null;
   private String idfieldname=null;
   private String codefieldname=null;
   private String groupfieldname=null;
   private String namefieldname=null;
   private String pksql=null;
   private String entityname=null;
   private List<String> filed=new ArrayList();
   
   
   
   
   public List<String> getFiled() {
	   return filed;
   }

   public void setFiled(List<String> filed) {
	   this.filed = filed;
   }

   public String getEntityname() {
	   return entityname;
   }

   public void setEntityname(String entityname) {
	   this.entityname = entityname;
   }	

   public String getVoClassName() {
	   return VoClassName;
   }

   public void setVoClassName(String voClassName) {
	   VoClassName = voClassName;
   }

   public String getPksql() {
	   return pksql;
   }

   public void setPksql(String pksql) {
	   this.pksql = pksql;
   }


   public String getGroupfieldname() {
	   return groupfieldname;
   }


   public void setGroupfieldname(String groupfieldname) {
	   this.groupfieldname = groupfieldname;
   }



   public String getOrgfieldname() {
	   return orgfieldname;
   }


   public void setOrgfieldname(String orgfieldname) {
	   this.orgfieldname = orgfieldname;
   }


   public String getIdfieldname() {
	   return idfieldname;
   }


   public void setIdfieldname(String idfieldname) {
	   this.idfieldname = idfieldname;
   }


   public String getCodefieldname() {
	   return codefieldname;
   }


   public void setCodefieldname(String codefieldname) {
	   this.codefieldname = codefieldname;
   }


   public String getNamefieldname() {
	   return namefieldname;
   }


   public void setNamefieldname(String namefieldname) {
	   this.namefieldname = namefieldname;
   }


   public Class getVoClass() {
	   return this.VoClass;
   }


   public void setVoClass(Class clz) {
	   VoClass = clz;
   }

   protected IUAPQueryBS getQueryService() {
     if (this.qry == null) {
       this.qry = ((IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class));
     }
     return this.qry;
   }
  
   
   public String getPk_group() {
     return this.pk_group;
   }
   
   public void setPk_group(String pk_group) {
     this.pk_group = pk_group;
   }
   

   
   public String getCondition() {
     return this.condition;
   }
   
   public void setCondition(String condition) {
     this.condition = condition;
   }
   
   
 }

