package nc.so.qs.sc.bs.bd.service;
 
 import java.io.Serializable;
 import nc.vo.pub.SuperVO;
 import org.apache.commons.lang.StringUtils;
 
 public class ErrLogElement
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private SuperVO vo;
   private String errReason;
   
   public ErrLogElement() {}
   
   public ErrLogElement(SuperVO v)
   {
	   this.vo=v;
   }
   
   public SuperVO getVo() {
	return vo;
   }

   public void setVo(SuperVO vo) {
	this.vo = vo;
   }
   
   public ErrLogElement(SuperVO vo, String errReason) {
     this(vo);
     setErrReason(errReason);
   }
   
   public String getErrReason()
   {
     return this.errReason;
   }
   
   public void setErrReason(String errReason) {
     this.errReason = errReason;
   }
   
   protected Object getObjAttributeValue(SuperVO vo, String attributeName) {
     if ((vo != null) && (StringUtils.isNotBlank(attributeName))) {
       return vo.getAttributeValue(attributeName);
     }
     return null;
   }
 }

