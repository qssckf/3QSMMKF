package nc.vo.so.qs.appinterface.util;

import nc.vo.pub.SuperVO;
 
 
 public class TaskMetaData
 {
	 
   private SuperVO billvo;	 

   private String billType;
   
   private String billId;
   
   private String stepID;
   
   private String firststepid;


   private String billNo;
   
   private String cuserid;
   
   private String title;
   
   private String startDate;
   
   private int fstatusflag;
   
   public SuperVO getBillvo() {
	   return billvo;
   }


   public void setBillvo(SuperVO billvo) {
	   this.billvo = billvo;
   }
   
   
   public String getFirststepid() {
	   return firststepid;
   }


   public void setFirststepid(String firststepid) {
	   this.firststepid = firststepid;
   }


   
   public int getFstatusflag() {
	   return fstatusflag;
   }
   
   
   public String getStepID() {
	return stepID;
   }

   public void setStepID(String stepID) {
	this.stepID = stepID;
   }

   public void setFstatusflag(int fstatusflag) {
	   this.fstatusflag = fstatusflag;
   }

   public TaskMetaData() {}
   
   public String getBillType() {
	   return this.billType; 
   }
   
   public void setBillType(String billType) { this.billType = billType; }
   
   public String getBillId() {
     return this.billId;
   }
   
   public void setBillId(String billId) { this.billId = billId; }
   
   public String getBillNo() {
     return this.billNo;
   }
   
   public void setBillNo(String billNo) { this.billNo = billNo; }
   
     
   public String getTitle() {
     return this.title;
   }
   
   public void setTitle(String title) { this.title = title; }
   
   public String getStartDate() {
     return this.startDate;
   }
   
   public void setStartDate(String startDate) { this.startDate = startDate; }
   
   public String getCuserid() {
     return this.cuserid;
   }
   
   public void setCuserid(String cuserid) { this.cuserid = cuserid; }
 }
