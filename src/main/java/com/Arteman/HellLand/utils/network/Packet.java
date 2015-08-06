package com.Arteman.HellLand.utils.network;

import io.netty.buffer.ByteBuf;

public abstract class Packet 
{
	protected boolean isChunkDataPacket = false;

	public abstract int getID();

	public abstract void readData(ByteBuf data);

	public abstract void writeData(ByteBuf data);
}
