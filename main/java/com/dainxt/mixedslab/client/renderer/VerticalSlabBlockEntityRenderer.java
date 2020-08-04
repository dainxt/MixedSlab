package com.dainxt.mixedslab.client.renderer;

import com.dainxt.mixedslab.MixedSlab;
import com.dainxt.mixedslab.block.MixedSlabBlock;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class VerticalSlabBlockEntityRenderer extends BlockEntityRenderer<VerticalSlabBlockEntity> {
    public VerticalSlabBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
 
    @Override
    public void render(VerticalSlabBlockEntity blockEntity, float tickDelta, MatrixStack matrix, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    	World world = blockEntity.getWorld();
    	BlockPos pos = blockEntity.getPos();
    	
			VerticalSlabBlockEntity entity = (VerticalSlabBlockEntity)world.getBlockEntity(pos);

			if(entity == null) {
				return;
			}
			
			int idBot = (entity.nB == 0 ? Block.getRawIdFromState(Blocks.ANDESITE_SLAB.getDefaultState()) : entity.nB);//state.get(MixedSlabBlock.SLAB_BOTTOM);
			BlockState botSlab = Block.getStateFromRawId(idBot).with(Properties.SLAB_TYPE, SlabType.BOTTOM);
			
			
			int idTop = entity.nT == 0? Block.getRawIdFromState(Blocks.ACACIA_SLAB.getDefaultState()) : entity.nT;//state.get(MixedSlabBlock.SLAB_TOP);
			BlockState topSlab = Block.getStateFromRawId(idTop).with(Properties.SLAB_TYPE, SlabType.TOP);
			
			BlockRenderManager manager = MinecraftClient.getInstance().getBlockRenderManager();
			
			RenderLayer layerTop = topSlab.isTranslucent(world, pos) ? RenderLayer.getTranslucent() : RenderLayer.getSolid();
			RenderLayer layerBot = botSlab.isTranslucent(world, pos) ? RenderLayer.getTranslucent() : RenderLayer.getSolid();
					
			VertexConsumer vertexConsumerTop = vertexConsumers.getBuffer(layerTop);
			VertexConsumer vertexConsumerBot = vertexConsumers.getBuffer(layerBot);
			
			if(entity.direction.equals(Direction.SOUTH)) {
				matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90F));
				
				matrix.translate(0.0F , 0.0F, -1.0F);
			}
			else if(entity.direction.equals(Direction.EAST)) {
				matrix.multiply(Vector3f.NEGATIVE_Z.getDegreesQuaternion(90F));
				
				matrix.translate(-1.0F , 0.0F, 0.0F);
			}
			else if(entity.direction.equals(Direction.NORTH)) {
				matrix.multiply(Vector3f.NEGATIVE_X.getDegreesQuaternion(90F));
				
				matrix.translate(0.0F , -1.0F, 0.0F);
			}
			else if(entity.direction.equals(Direction.WEST)) {
				matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
				
				matrix.translate(0.0F , -1.0F, 0.0F);
			}
			
			manager.getModelRenderer().render(world, manager.getModel(botSlab), botSlab, pos, matrix, vertexConsumerBot, false, world.random, botSlab.getRenderingSeed(pos), OverlayTexture.DEFAULT_UV);
    }
    @Override
    public boolean rendersOutsideBoundingBox(VerticalSlabBlockEntity blockEntity) {
    	
    	return true;
    }
    

}
