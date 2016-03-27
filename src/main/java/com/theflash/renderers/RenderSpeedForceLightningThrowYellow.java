package com.theflash.renderers;

import com.theflash.lib.RefStrings;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class RenderSpeedForceLightningThrowYellow<T extends Entity> extends Render<T>
{
	public RenderSpeedForceLightningThrowYellow(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		this.bindEntityTexture(entity);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();

		GlStateManager.disableLighting();
		GlStateManager.enableBlend();

		GlStateManager.translate(x, y, z);

		GlStateManager
				.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F
						+ 5.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager
				.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks
						+ 5.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(45, 1, 0, 0);

		GlStateManager.scale(0.75, 0.15, 0.15);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexBuffer = tessellator.getBuffer();

		GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);
		vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexBuffer.pos(-2.0D, -2.0D, -2.0D).tex(0.0F, 0.15625F * 2F).endVertex();
		vertexBuffer.pos(-2.0D, -2.0D, 2.0D).tex(0.15625F * 2F, 0.15625F * 2F).endVertex();
		vertexBuffer.pos(-2.0D, 2.0D, 2.0D).tex(0.15625F * 2f, 0.3125F * 2F).endVertex();
		vertexBuffer.pos(2.0D, 2.0D, -2.0D).tex(0.0F, 0.3125F * 2F).endVertex();
		tessellator.draw();

		GlStateManager.popMatrix();
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation(RefStrings.MODID + ":textures/entity/LightningThrowYellow.png");
	}
}
