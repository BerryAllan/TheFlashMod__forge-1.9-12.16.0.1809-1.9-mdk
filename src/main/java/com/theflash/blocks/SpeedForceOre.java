package com.theflash.blocks;

import java.util.Random;

import com.theflash.handlers.CreativeTabHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SpeedForceOre extends Block
{

	public SpeedForceOre(Material mat)
	{
		super(mat);
		this.setCreativeTab(CreativeTabHandler.tabFlash);
		this.setHarvestLevel("pickaxe", 3);
	}

	private Random rand = new Random();
}
