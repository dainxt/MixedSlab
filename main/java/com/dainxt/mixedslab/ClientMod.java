package com.dainxt.mixedslab;

import com.dainxt.mixedslab.client.renderer.MixedSlabBlockEntityRenderer;
import com.dainxt.mixedslab.client.renderer.VerticalSlabBlockEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class ClientMod implements ClientModInitializer{

	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.INSTANCE.register(MixedSlab.MIXEDSLAB_BLOCKENTITY, MixedSlabBlockEntityRenderer::new);
		
		BlockEntityRendererRegistry.INSTANCE.register(MixedSlab.VERTICALSLAB_BLOCKENTITY, VerticalSlabBlockEntityRenderer::new);
	}

}
