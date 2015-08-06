package com.Arteman.HellLand.utils.network.command;

import io.netty.buffer.ByteBuf;

public abstract class CommandWriter 
{
	public abstract void write(ByteBuf data);
}
