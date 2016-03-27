package com.theflash.renderers;

import com.theflash.entities.EntitySpeedForceTrailBase;
import com.theflash.lib.RefStrings;
import com.theflash.models.ModelSpeedForceTrail;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSpeedForceTrailBlue extends RenderLiving<EntitySpeedForceTrailBase>
{
	protected ResourceLocation golemTexture = new ResourceLocation(
			RefStrings.MODID + ":textures/entity/SpeedForceTrailBlue.png");
	
	public RenderSpeedForceTrailBlue(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSpeedForceTrail(), 0.0f);
	}

	public void doRender(EntitySpeedForceTrailBase entitySpeedForceTrailBase, double posX, double posY, double posZ,
			float rotationYaw, float rotationPitch)
	{
		super.doRender(entitySpeedForceTrailBase, posX, posY, posZ, rotationYaw, rotationPitch);
	}

	protected ResourceLocation getEntityTexture(EntitySpeedForceTrailBase golem)
	{
		return golemTexture;
	}
}

