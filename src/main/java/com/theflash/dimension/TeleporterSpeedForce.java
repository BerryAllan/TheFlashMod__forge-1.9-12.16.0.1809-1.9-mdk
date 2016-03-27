package com.theflash.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterSpeedForce extends Teleporter
{
	private final WorldServer world;
	private BlockPos coords;

	public TeleporterSpeedForce(WorldServer world, BlockPos coords)
	{
		this(world);
		this.coords = coords;
	}

	public TeleporterSpeedForce(WorldServer world)
	{
		super(world);
		this.world = world;
	}

	public void placeInPortal(Entity entity, float p_180266_2_)
	{
		if (this.coords != null)
		{
			entity.setPositionAndRotation(this.coords.getX() + 0.5F, this.coords.getY() + 0.1F,
					this.coords.getZ() + 0.5F, entity.rotationYaw, 0.0F);
		}
		while (!this.world.getCollisionBoxes(entity.getEntityBoundingBox()).isEmpty())
		{
			entity.setPosition(entity.posX, entity.posY + 1.0D, entity.posZ);
		}
	}
}
