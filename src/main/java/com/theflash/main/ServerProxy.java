package com.theflash.main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy
{
	public void init(FMLInitializationEvent e)
	{

	}

	public void registerRenderThings()
	{

	}
	
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
