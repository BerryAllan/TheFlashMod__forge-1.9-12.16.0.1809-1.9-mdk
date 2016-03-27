/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Timer
 *  net.minecraftforge.client.event.TextureStitchEvent
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  org.lwjgl.opengl.GL11
 */
package com.theflash.renderers;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value = Side.CLIENT) public class FlashRenderer
{
	private static float lightmapLastX;
	private static float lightmapLastY;
	private static boolean optifineBreak;

	public static void glowOn()
	{
		FlashRenderer.glowOn(15);
	}
	
	public static void glowOn(int glow)
	{
		GL11.glPushAttrib(64);
		try
		{
			lightmapLastX = OpenGlHelper.lastBrightnessX;
			lightmapLastY = OpenGlHelper.lastBrightnessY;
		}
		catch (NoSuchFieldError e)
		{
			optifineBreak = true;
		}
		RenderHelper.disableStandardItemLighting();
		float glowRatioX = Math.min((float) glow / 15.0f * 240.0f + lightmapLastX, 240.0f);
		float glowRatioY = Math.min((float) glow / 15.0f * 240.0f + lightmapLastY, 240.0f);
		if (!optifineBreak)
		{
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, glowRatioX, glowRatioY);
		}
	}
	
	public static void glowOff()
	{
		if (!optifineBreak)
		{
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightmapLastX, lightmapLastY);
		}
		GL11.glPopAttrib();
	}
}
