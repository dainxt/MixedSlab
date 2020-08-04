package com.dainxt.mixedslab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dainxt.mixedslab.block.MixedSlabBlock;
import com.dainxt.mixedslab.block.VerticalSlabBlock;
import com.dainxt.mixedslab.block.entity.MixedSlabBlockEntity;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;
import com.dainxt.mixedslab.handlers.EventsHandler;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MixedSlab implements ModInitializer {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MODID = "mixedslab";
	
	public static BlockEntityType<MixedSlabBlockEntity> MIXEDSLAB_BLOCKENTITY;
	public static Block MIXEDSLAB_BLOCK = new MixedSlabBlock(FabricBlockSettings.of(Material.METAL).strength(1.5F, 6.0F).nonOpaque());
	
	public static BlockEntityType<VerticalSlabBlockEntity> VERTICALSLAB_BLOCKENTITY;
	public static Block VERTICALSLAB_BLOCK = new VerticalSlabBlock(FabricBlockSettings.of(Material.METAL).strength(1.5F, 6.0F).nonOpaque());
	
	@Override
	public void onInitialize() {
		
		EventsHandler.registerEvents();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		MIXEDSLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(MixedSlab.MODID, "mixedslab_block"), MIXEDSLAB_BLOCK);
		MIXEDSLAB_BLOCKENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MixedSlab.MODID, "mixedslab_block"), BlockEntityType.Builder.create(MixedSlabBlockEntity::new, MIXEDSLAB_BLOCK).build(null));
		
		VERTICALSLAB_BLOCK = Registry.register(Registry.BLOCK, new Identifier(MixedSlab.MODID, "verticalslab_block"), VERTICALSLAB_BLOCK);
		VERTICALSLAB_BLOCKENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MixedSlab.MODID, "verticalslab_block"), BlockEntityType.Builder.create(VerticalSlabBlockEntity::new, VERTICALSLAB_BLOCK).build(null));
		
	}
}
