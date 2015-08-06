package com.Arteman.HellLand.utils.network.command;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.relauncher.Side;

public interface ICommandReceiver 
{
	void receiveCommand(String command, Side side, Object sender, ByteBuf stream);
}
