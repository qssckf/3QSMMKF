package nc.bs.so.qs.sc.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

public enum IpPluginPoint implements IPluginPoint{
	
	DELETE,  INSERT,  UPDATE, RELEASE,RDCREATEPMO,RDCREATEDMO;

	@Override
	public String getComponent() {
		// TODO 自动生成的方法存根
		return "IntoProdDetailVO";
	}

	@Override
	public String getModule() {
		// TODO 自动生成的方法存根
		return "so";
	}

	@Override
	public String getPoint() {
		// TODO 自动生成的方法存根
		return getClass().getName() + "." + name();
	}

}
