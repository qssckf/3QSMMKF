package nc.bs.so.fq.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

public enum PieceProductPlugInPoint implements IPluginPoint{
	
	SetDefault;

	@Override
	public String getComponent() {
		// TODO �Զ����ɵķ������
		return "so_pd";
	}

	@Override
	public String getModule() {
		// TODO �Զ����ɵķ������
		return "so";
	}

	@Override
	public String getPoint() {
		// TODO �Զ����ɵķ������
		
		return this.getClass().getName() + "." + this.name();
		
	}

}
