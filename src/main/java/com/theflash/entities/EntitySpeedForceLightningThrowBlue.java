package com.theflash.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntitySpeedForceLightningThrowBlue extends EntityThrowable
{
	Minecraft mc = Minecraft.getMinecraft();
	double nx = mc.thePlayer.posX, ny = mc.thePlayer.posY + 1, nz = mc.thePlayer.posZ;
	final EntityLightningBolt fakeBolt = new EntityLightningBolt(this.worldObj, nx, ny, nz, true);

	public EntitySpeedForceLightningThrowBlue(World worldIn)
	{
		super(worldIn);
	}

	public EntitySpeedForceLightningThrowBlue(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	public EntitySpeedForceLightningThrowBlue(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result)
	{
		if (result.entityHit != null)
		{
			if (result.entityHit instanceof EntityPlayer)
			{

			}
			else
			{
				//result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10.0F);
				//result.entityHit.onStruckByLightning(fakeBolt);
			}
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}

	public void onUpdate()
	{
		super.onUpdate();

		List<Entity> entityList = this.worldObj
				.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox().expandXyz(3));

		if (entityList.size() > 0)
		{
			for (int i = 0; i < entityList.size(); ++i)
			{
				Entity entity = entityList.get(i);

				if (entity instanceof EntityPlayer || entity instanceof EntitySpeedForceTrailBlue
						|| entity instanceof EntitySpeedForceLightningThrowBlue
						|| entity instanceof EntityFutureFlashLightning || entity instanceof EntitySpeedForceTrailYellow
						|| entity instanceof EntitySpeedForceLightningThrowYellow)
				{
					
				}
				else if (entity instanceof EntityLivingBase)
				{
					EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
					entityLivingBase.performHurtAnimation();
					entityLivingBase.setHealth(entityLivingBase.getHealth() - 5.0F);
					float f = mc.thePlayer.rotationYaw * 0.017453292f;
					entityLivingBase.motionX -= (double) (MathHelper.sin(f)) / 5;
					entityLivingBase.motionZ += (double) (MathHelper.cos(f)) / 5;
					entityLivingBase.motionY += 0.4;
					entityLivingBase.onStruckByLightning(fakeBolt);
				}
			}
		}
	}

	public void func_184538_a(Entity player, float yaw, float pitch, float v2, float velocity, float v4)
	{
		float f = -MathHelper.sin(pitch * 0.017453292F) * MathHelper.cos(yaw * 0.017453292F);
		float f1 = -MathHelper.sin((yaw + v2) * 0.017453292F);
		float f2 = MathHelper.cos(pitch * 0.017453292F) * MathHelper.cos(yaw * 0.017453292F);
		this.setThrowableHeading((double) f, (double) f1, (double) f2, velocity, v4);
		this.motionX += player.motionX;
		this.motionZ += player.motionZ;

		if (!player.onGround)
		{
			this.motionY += player.motionY;
		}
	}

	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy)
	{
		float f = MathHelper.sqrt_double(x * x + y * y + z * z);
		x = x / (double) f;
		y = y / (double) f;
		z = z / (double) f;
		//x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		//y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		//z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		x = x * (double) velocity;
		y = y * (double) velocity;
		z = z * (double) velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
		this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
	}

	@SideOnly(Side.CLIENT) public int getBrightnessForRender(float partialTicks)
	{
		return 15728880;
	}

	public float getBrightness(float partialTicks)
	{
		return 1.0F;
	}
}
