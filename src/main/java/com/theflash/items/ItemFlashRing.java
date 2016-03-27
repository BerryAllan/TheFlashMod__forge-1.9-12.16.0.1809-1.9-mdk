/*
 * Decompiled with CFR 0_110.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.theflash.items;

import com.theflash.handlers.CreativeTabHandler;
import com.theflash.handlers.SpeedForceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemFlashRing extends Item
{
	public ItemFlashRing(String id)
	{
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabHandler.tabFlash);
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("\u00a76Right-click to eject suit.");
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstackIn, World par2World, EntityPlayer playerIn,
			EnumHand hand)
	{
		if (playerIn.inventory.armorItemInSlot(3) == null && playerIn.inventory.armorItemInSlot(2) == null
				&& playerIn.inventory.armorItemInSlot(1) == null && playerIn.inventory.armorItemInSlot(0) == null)
		{
			//System.out.println("equipping");
			
				playerIn.inventory.armorInventory[3] = new ItemStack(FlashItems.helmetFlash);
			playerIn.inventory.armorInventory[2] = new ItemStack(FlashItems.chestPlateFlash);
			playerIn.inventory.armorInventory[1] = new ItemStack(FlashItems.legsFlash);
			playerIn.inventory.armorInventory[0] = new ItemStack(FlashItems.bootsFlash);
		}
		else if (playerIn.inventory.armorItemInSlot(3) != null && playerIn.inventory.armorItemInSlot(3).getItem()
				.equals(FlashItems.helmetFlash) && playerIn.inventory.armorItemInSlot(2) != null
				&& playerIn.inventory.armorItemInSlot(2).getItem().equals(FlashItems.chestPlateFlash)
				&& playerIn.inventory.armorItemInSlot(1) != null && playerIn.inventory.armorItemInSlot(1).getItem()
				.equals(FlashItems.legsFlash) && playerIn.inventory.armorItemInSlot(0) != null
				&& playerIn.inventory.armorItemInSlot(0).getItem().equals(FlashItems.bootsFlash))
		{
			//System.out.println("dequipping");
			
			playerIn.inventory.armorInventory[3] = null;
			playerIn.inventory.armorInventory[2] = null;
			playerIn.inventory.armorInventory[1] = null;
			playerIn.inventory.armorInventory[0] = null;
			
			SpeedForceHandler.flashFactor = 0;
		}
		return new ActionResult(EnumActionResult.PASS, itemstackIn);
	}
}

