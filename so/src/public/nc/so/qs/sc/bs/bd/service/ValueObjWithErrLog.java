package nc.so.qs.sc.bs.bd.service;
 
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import nc.vo.pub.SuperVO;

public class ValueObjWithErrLog implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Set<SuperVO> vos = new HashSet();
  
  private ArrayList<ErrLogElement> errLogList = new ArrayList();
  

  public ValueObjWithErrLog() {}
  

  public ValueObjWithErrLog(SuperVO[] vos)
  {
    setVos(vos);
  }
  
  public ValueObjWithErrLog(SuperVO[] vos, ArrayList<ErrLogElement> errLogList)
  {
    setVos(vos);
    setErrLogList(errLogList);
  }
  




  public SuperVO[] getVos()
  {
    if (this.vos.size() == 0)
      return new SuperVO[0];
    return (SuperVO[])this.vos.toArray((SuperVO[])Array.newInstance(((SuperVO)this.vos.iterator().next()).getClass(), this.vos.size()));
  }
  





  public void setVos(SuperVO[] vos)
  {
    this.vos.clear();
    this.vos.addAll(Arrays.asList(vos));
  }
  




  public ArrayList<ErrLogElement> getErrLogList()
  {
    return this.errLogList;
  }
  




  public void setErrLogList(ArrayList<ErrLogElement> errLogList)
  {
    this.errLogList = errLogList;
  }
  
  public void addErrLogMessage(SuperVO vo, String errmsg) {
    this.vos.remove(vo);
    this.errLogList.add(new ErrLogElement(vo, errmsg));
  }
}
