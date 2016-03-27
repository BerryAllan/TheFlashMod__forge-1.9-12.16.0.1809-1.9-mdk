package com.theflash.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeysHandler
{
	
	public static KeyBinding UP_KEY;
	public static KeyBinding DOWN_KEY;
	public static KeyBinding R_KEY;
	public static KeyBinding F_KEY;
	public static KeyBinding C_KEY;
	public static KeyBinding X_KEY;
	public static KeyBinding Z_KEY;
	public static KeyBinding V_KEY;
	public static KeyBinding G_KEY;
	public static KeyBinding T_KEY;
	public static KeyBinding PGUP_KEY;
	public static KeyBinding PGDOWN_KEY;
	public static KeyBinding SPACEBAR;
	
	public static void mainRegistry()
	{
		UP_KEY = new KeyBinding("key.up", Keyboard.KEY_UP, "key.categories.theflashmod");
		DOWN_KEY = new KeyBinding("key.down", Keyboard.KEY_DOWN, "key.categories.theflashmod");
		R_KEY = new KeyBinding("key.r", Keyboard.KEY_R, "key.categories.theflashmod");
		F_KEY = new KeyBinding("key.f", Keyboard.KEY_F, "key.categories.theflashmod");
		C_KEY = new KeyBinding("key.c", Keyboard.KEY_C, "key.categories.theflashmod");
		X_KEY = new KeyBinding("key.x", Keyboard.KEY_X, "key.categories.theflashmod");
		Z_KEY = new KeyBinding("key.z", Keyboard.KEY_Z, "key.categories.theflashmod");
		V_KEY = new KeyBinding("key.v", Keyboard.KEY_V, "key.categories.theflashmod");
		G_KEY = new KeyBinding("key.g", Keyboard.KEY_G, "key.categories.theflashmod");
		T_KEY = new KeyBinding("key.t", Keyboard.KEY_T, "key.categories.theflashmod");
		PGUP_KEY = new KeyBinding("key.pageup", Keyboard.KEY_PRIOR, "key.categories.theflashmod");
		PGDOWN_KEY = new KeyBinding("key.pagedown", Keyboard.KEY_NEXT, "key.categories.theflashmod");
		SPACEBAR = new KeyBinding("key.spacebar", Keyboard.KEY_SPACE, "key.categories.theflashmod");
		
		ClientRegistry.registerKeyBinding(UP_KEY);
		ClientRegistry.registerKeyBinding(DOWN_KEY);
		ClientRegistry.registerKeyBinding(R_KEY);
		ClientRegistry.registerKeyBinding(F_KEY);
		ClientRegistry.registerKeyBinding(C_KEY);
		ClientRegistry.registerKeyBinding(X_KEY);
		ClientRegistry.registerKeyBinding(Z_KEY);
		ClientRegistry.registerKeyBinding(V_KEY);
		ClientRegistry.registerKeyBinding(G_KEY);
		ClientRegistry.registerKeyBinding(T_KEY);
		ClientRegistry.registerKeyBinding(PGUP_KEY);
		ClientRegistry.registerKeyBinding(PGDOWN_KEY);
		ClientRegistry.registerKeyBinding(SPACEBAR);
	}
	
}
