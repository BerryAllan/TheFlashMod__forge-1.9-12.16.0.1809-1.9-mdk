//When I wrote this, only God and I understood what I was doing
//Now, God only knows

package com.theflash.items;

import com.theflash.lib.RefStrings;
import com.theflash.main.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class FlashArmor extends FlashAbstract
{
	boolean isDown = Keyboard.getEventKeyState();
	private Minecraft gameController;
	private String[] armorTypes = new String[] { "sapphireHelmet", "sapphireChestPlate", "sapphireLegs",
			"sapphireBoots" };

	public FlashArmor(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot armorType)
	{
		super(armorMaterial, renderIndex, armorType);
	}

	public static double dotProduct(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		return x1 * x2 + y1 * y2 + z1 * z2;
	}

	@Override public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer)
	{
		if (stack.getItem().equals(FlashItems.helmetFlash) || stack.getItem().equals(FlashItems.chestPlateFlash)
				|| stack.getItem().equals(FlashItems.bootsFlash))
		{
			return RefStrings.MODID + ":textures/armor/sapphire_1.png";
		}
		else if (stack.getItem().equals(FlashItems.legsFlash))
		{
			return RefStrings.MODID + ":textures/armor/sapphire_2.png";
		}
		else
		{
			return null;
		}
	}

	@Override public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, ModelBiped _default)
	{
		ModelBiped armorModel = new ModelBiped();
		if (itemStack != null)
		{
			if (itemStack.getItem() instanceof FlashArmor)
			{
				// int type = ((ItemArmor) itemStack.getItem()).armorType;
				armorModel = ClientProxy.getArmorModel(7);
			}

			if (armorModel != null)
			{

				armorModel.bipedHead.showModel = armorSlot.getIndex() == 0;
				armorModel.bipedHeadwear.showModel = armorSlot.getIndex() == 0;
				armorModel.bipedBody.showModel = armorSlot.getIndex() == 1 || armorSlot.getIndex() == 2;
				armorModel.bipedRightArm.showModel = armorSlot.getIndex() == 1;
				armorModel.bipedLeftArm.showModel = armorSlot.getIndex() == 1;
				armorModel.bipedRightLeg.showModel = armorSlot.getIndex() == 2 || armorSlot.getIndex() == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot.getIndex() == 2 || armorSlot.getIndex() == 3;

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				//armorModel.heldItemRight = ((EntityPlayer) entityLiving).inventory.armorItemInSlot(0) != null ? 1 : 0;
				//armorModel.heldItemRight = ((EntityPlayer) entityLiving).getCurrentEquippedItem() != null ? 1 : 0;
				//armorModel.aimedBow = false;

				if (entityLiving instanceof EntityPlayer)
				{
					if (((EntityPlayer) entityLiving).getHeldItemMainhand() != null)
					{
						EnumAction enumaction = ((EntityPlayer) entityLiving).inventory.getCurrentItem()
								.getItemUseAction();

						if (enumaction == EnumAction.BLOCK)
						{
							armorModel.rightArmPose = ModelBiped.ArmPose.BLOCK;
						}
						else if (enumaction == EnumAction.BOW)
						{
							armorModel.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
						}
					}
				}
				return armorModel;
			}
		}
		return null;
	}
}
