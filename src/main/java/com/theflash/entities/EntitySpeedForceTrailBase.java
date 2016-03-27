package com.theflash.entities;

import com.theflash.handlers.SpeedForceHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public abstract class EntitySpeedForceTrailBase extends EntityCreature implements IAnimals
{
	protected int attackTimer;
	protected boolean isPlayerCreated;
	protected ResourceLocation textureLoc;
	protected float attackDamage = 7.0f;
	protected boolean hasHome = false;
	protected boolean takesFallDamage = false;
	protected Block creativeReturn;
	Village villageObj;
	private int homeCheckTimer = 70;

	public EntitySpeedForceTrailBase(World world)
	{
		super(world);
	}

	public static ResourceLocation getGolemTexture(String texture)
	{
		return null;
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	protected void collideWithEntity(Entity entity)
	{
		super.collideWithEntity(entity);
	}

	public void onLivingUpdate()
	{
		int i;
		IBlockState iblockstate;
		int k;
		int j;
		Block block;
		super.onLivingUpdate();
		if (this.attackTimer > 0)
		{
			--this.attackTimer;
		}
	}

	protected boolean canDespawn()
	{
		return true;
	}

	public void onDeath(DamageSource src)
	{
		super.onDeath(src);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(
				Minecraft.getMinecraft().thePlayer.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
						.getBaseValue() * (SpeedForceHandler.flashFactor * 8));
	}

}

