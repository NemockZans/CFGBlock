package com.nemock.cfgblock.handler;

import java.io.File;

import com.nemock.cfgblock.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	
	public static int amount = 0;
	public static String[] names;
	public static boolean[] stairs;
	public static boolean[] slabs;
	public static int[] IngredientAmount;
	public static boolean[] CT_Required;
	public static String[][] ingredients;
	public static boolean[] shaped;
	
	public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

	private static void loadConfiguration() {
		
		//-----Here is where the magic happens-----
		amount = configuration.getInt("Amount", "Initial", 0, 0, 150, "This value determins how many Blocks this mod will add");
		
		if(amount > 0){
			names = new String[amount];
			stairs = new boolean[amount];
			slabs = new boolean[amount];
			CT_Required = new boolean[amount];
			IngredientAmount = new int[amount];
			ingredients = new String[amount][];
			shaped = new boolean[amount];
			
			for(int i = 0; i < amount; i++){
				// basics
				String category = "BlockNumber"+i;
				names[i] = configuration.getString("Name", category, "Block"+i, "The custom blocks name");
				stairs[i] = configuration.getBoolean("Stairs", category, true, "Determins if there will be stairs for this block");
				slabs[i] = configuration.getBoolean("Slabs", category, true, "Determins if there will be slabs for this block");
				
				// recipe
				CT_Required[i] = configuration.getBoolean("BigCraftGrid", category, false, "Set this fals for 2x2 crafting grid and true for 3x3 crafting grid.");
				int maxIngredients;
				if(CT_Required[i]){
					maxIngredients = 9;
				} else {
					maxIngredients = 4;
				}
				IngredientAmount[i] = configuration.getInt("IngredientAmount", category, 0, 0, maxIngredients, "Define the amount of ingredients the blocks recipe has.");
				for(int j = 0; j > IngredientAmount[i]; j++){
					ingredients[i][j] = configuration.getString("Ingredient-"+ j+1, category, "minecraft:dirt", "This ingredient will be refered to as "+j+1+" in the crafting grid");
				}
				shaped[i] = configuration.getBoolean("IsRecipeShaped", category, true, "Determins if its a shapeles recipe (false) or a shaped one (true)");
				
			}
		}
		//-----Magic ends-----
		
		if (configuration.hasChanged())
        {
            configuration.save();
        }
	}
	
	@SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
