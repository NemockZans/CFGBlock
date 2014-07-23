package com.nemock.cfgblock;

import com.nemock.cfgblock.proxy.CommonProxy;
import com.nemock.cfgblock.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid= Reference.MOD_ID ,
		name = Reference.MOD_NAME,
		version = Reference.VERSION)

public class CFGBlock{
	
	@Mod.Instance(Reference.MOD_ID)
    public static CFGBlock instance;
	
	@SidedProxy(
			clientSide = Reference.CLIENT_PROXY_LOCATION,
			serverSide = Reference.COMMON_PROXY_LOCATION)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}