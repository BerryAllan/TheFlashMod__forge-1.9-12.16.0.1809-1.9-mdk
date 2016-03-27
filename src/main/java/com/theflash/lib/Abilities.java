package com.theflash.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class Abilities extends Potion
{
	private static final ResourceLocation texture = new ResourceLocation(
			RefStrings.MODID + ":textures/items/abilityicons.png");

	private double effectiveness;
	
	public Abilities( boolean par2, int par3)
	{
		super(par2, par3);
	}

	public Potion setPotionName(String name)
	{
		super.setPotionName(name);
		return this;
	}

	protected Potion setEffectiveness(double effectivenessIn)
	{
		this.effectiveness = effectivenessIn;
		return this;
	}

	public boolean hasStatusIcon()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		return true;
	}
	
	public Potion setIconIndex(int par1, int par2)
	{
		super.setIconIndex(par1, par2);
		return this;
	}
}
;
