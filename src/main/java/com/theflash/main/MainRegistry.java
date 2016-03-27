/*
If this comment is removed, the program will blow up 
       ,~~.
      (  6 )-_,
 (\___ )=='-'
  \ .   ) )
   \ `-' /
~'`~'`~'`~'`~
*/

package com.theflash.main;

import com.theflash.blocks.FlashBlocks;
import com.theflash.dimension.BiomeGenSpeedForce;
import com.theflash.dimension.WorldProviderSpeedForce;
import com.theflash.entities.*;
import com.theflash.gui.SpeedGUIHandler;
import com.theflash.handlers.CraftingHandler;
import com.theflash.handlers.KeysHandler;
import com.theflash.handlers.SpeedForceHandler;
import com.theflash.items.FlashItems;
import com.theflash.lib.Abilities;
import com.theflash.lib.RefStrings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.lang.reflect.Field;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION, name = RefStrings.NAME) public class MainRegistry
{
	@SidedProxy(clientSide = "com.theflash.main.ClientProxy", serverSide = "com.theflash.main.ServerProxy") public static ServerProxy proxy;

	@Mod.Metadata public static ModMetadata meta;
	
	@Mod.Instance(RefStrings.MODID) public static MainRegistry modInstance;

	public static Potion speedForce;
	public static Potion speedStrength;
	public static Potion speedDigging;

	public static int dimId = DimensionManager.getNextFreeDimId();

	public static boolean usable()
	{
		return Minecraft.getMinecraft().isSingleplayer();
	}
	
	public void initConfiguration(FMLPreInitializationEvent preEvent)
	{
		Configuration config = new Configuration(new File("config/config.cfg"));
		config.load();

		FlashItems.flashDurability = 1000000000;

		config.save();
	}

	@Mod.EventHandler public void PreLoad(FMLPreInitializationEvent preEvent)
	{
		this.initConfiguration(preEvent);
		proxy.registerRenderThings();

		FlashEntities.registerEntity(EntitySpeedForceTrailBlue.class, "speed_force_trail_blue");
		FlashEntities.registerEntity(EntitySpeedForceTrailYellow.class, "speed_force_trail_yellow");
		FlashEntities.registerEntity(EntityFutureFlashLightning.class, "future_flash_lightning");
		FlashEntities.registerEntity(EntitySpeedForceLightningThrowBlue.class, "lightning_throw_blue");
		FlashEntities.registerEntity(EntitySpeedForceLightningThrowYellow.class, "lightning_throw_yellow");

		FlashBlocks.mainRegistry();
		FlashItems.mainRegistry();
		CraftingHandler.mainRegistry();
		KeysHandler.mainRegistry();

		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields())
		{
			f.setAccessible(true);
			try
			{
				if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a"))
					continue;
				Field modfield = Field.class.getDeclaredField("modifiers");
				modfield.setAccessible(true);
				modfield.setInt(f, f.getModifiers() & -17);
				potionTypes = (Potion[]) f.get(null);
				Potion[] newPotionTypes = new Potion[256];
				System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
				f.set(null, newPotionTypes);
				continue;
			}
			catch (Exception e)
			{
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}

		FMLCommonHandler.instance().bus().register(new KeysHandler());
		MinecraftForge.EVENT_BUS.register(new SpeedForceHandler());
		MinecraftForge.EVENT_BUS.register(new SpeedGUIHandler());
	}
	
	@Mod.EventHandler public void load(FMLInitializationEvent event)
	{
		BiomeGenSpeedForce.instance = new BiomeGenSpeedForce((new BiomeGenBase.BiomeProperties("The Speed Force")));

		DimensionType.register("The Speed Force", "_the_speed_force", dimId, WorldProviderSpeedForce.class, false);
		DimensionManager.registerDimension(dimId, DimensionType.getById(dimId));

		speedForce = (new Abilities(false, 0).setIconIndex(0, 0)).setPotionName("potion.speedForce")
				.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
						"91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);

		speedStrength = new Abilities(false, 0).setIconIndex(4, 0).setPotionName("potion.strongPotion")
				.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE,
						"648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0, 1);

		speedDigging = (new Abilities(false, 0)).
				setPotionName("potion.digFast").func_188413_j()
				.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED,
						"AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612D, 2);

		if (event.getSide() == Side.CLIENT)
		{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

			//blocks
			registerBlockRenderers();
		}

		proxy.init(event);
	}
	
	@Mod.EventHandler public void PostLoad(FMLPostInitializationEvent postEvent)
	{

	}

	public void registerBlockRenderers()
	{
		// DEBUG
		System.out.println("Registering block renderers");

		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	}
}
