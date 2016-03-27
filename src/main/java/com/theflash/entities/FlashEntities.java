package com.theflash.entities;

import com.theflash.main.MainRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Miguel on 3/2/2016.
 */
public class FlashEntities
{
	public static void registerEntity(Class entityClass, String name)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, MainRegistry.modInstance, 64, 1, true);
	}
}
