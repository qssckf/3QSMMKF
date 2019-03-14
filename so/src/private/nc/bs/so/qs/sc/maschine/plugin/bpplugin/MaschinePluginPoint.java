package nc.bs.so.qs.sc.maschine.plugin.bpplugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;






public enum MaschinePluginPoint
  implements IPluginPoint
{
  DELETE, INSERT, UPDATE, UNENABLE, ENABLE,  DISABLE;
  
  private MaschinePluginPoint() {}
  
  public String getComponent() { return "MaschineVO"; }
  

  public String getModule()
  {
    return "so";
  }
  
  public String getPoint()
  {
    return getClass().getName() + "." + name();
  }
}