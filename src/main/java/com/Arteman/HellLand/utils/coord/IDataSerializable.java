package com.Arteman.HellLand.utils.coord;

import java.io.IOException;

public interface IDataSerializable {
	public abstract IDataSerializable serialize(String paramString, DataHelper paramDataHelper)
		    throws IOException;
}
