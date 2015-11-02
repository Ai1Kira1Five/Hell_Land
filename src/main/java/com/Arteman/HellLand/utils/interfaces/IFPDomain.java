package com.Arteman.HellLand.utils.interfaces;

import java.util.List;

public abstract interface IFPDomain extends IDomain{
	public abstract List<String> blockPatterns();
	
	public abstract List<String> itemPatterns();
	
	public abstract List<String> rotPatterns();
}
