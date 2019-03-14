package nc.bs.so.fq.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

public enum PieceProductPlugInPoint implements IPluginPoint{
	
	SetDefault;

	@Override
	public String getComponent() {
		// TODO 自动生成的方法存根
		return "so_pd";
	}

	@Override
	public String getModule() {
		// TODO 自动生成的方法存根
		return "so";
	}

	@Override
	public String getPoint() {
		// TODO 自动生成的方法存根
		
		return this.getClass().getName() + "." + this.name();
		
	}

}
