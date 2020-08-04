package com.dainxt.mixedslab.block;

import java.util.ArrayList;
import java.util.List;

import com.dainxt.mixedslab.MixedSlab;
import com.dainxt.mixedslab.block.entity.MixedSlabBlockEntity;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class MixedSlabBlock extends Block implements BlockEntityProvider{

	public MixedSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	public float getBlastResistance() {
		return super.getBlastResistance();
	}
	@Override
	public BlockSoundGroup getSoundGroup(BlockState state) {
		
		return BlockSoundGroup.STONE;
	}
	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContext.Builder builder) {
		MixedSlabBlockEntity e = (MixedSlabBlockEntity) builder.get(LootContextParameters.BLOCK_ENTITY);
		List<ItemStack> drops = new ArrayList<>();
        if (e != null) {
        	drops.addAll(Block.getStateFromRawId(e.nB).getDroppedStacks(builder));
        	drops.addAll(Block.getStateFromRawId(e.nT).getDroppedStacks(builder));
        }
        return drops;
	}
	
	
	@Override
	public long getRenderingSeed(BlockState state, BlockPos pos) {
		return super.getRenderingSeed(state, pos);
	}
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
	}
	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new MixedSlabBlockEntity();
	}
	
	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity,
			ItemStack stack) {
		RayTraceContext context;

		HitResult hit = player.rayTrace(20, 0, false);
		if(hit instanceof BlockHitResult && player.isSneaking()) {

			if(blockEntity instanceof MixedSlabBlockEntity) {
				if(((BlockHitResult)hit).getSide() == Direction.UP){
					BlockState slab = Block.getStateFromRawId(((MixedSlabBlockEntity)blockEntity).nB);
					((MixedSlabBlockEntity)blockEntity).nB = Block.getRawIdFromState(Blocks.AIR.getDefaultState());
					world.setBlockState(pos, slab.with(Properties.SLAB_TYPE,SlabType.BOTTOM));
					
					
					
				}
				
				if(((BlockHitResult)hit).getSide() == Direction.DOWN){
					BlockState slab = Block.getStateFromRawId(((MixedSlabBlockEntity)blockEntity).nT);
					((MixedSlabBlockEntity)blockEntity).nT = Block.getRawIdFromState(Blocks.AIR.getDefaultState());
					world.setBlockState(pos, slab.with(Properties.SLAB_TYPE,SlabType.TOP));
					
					
					
				}
				
			}
		}
		
		super.afterBreak(world, player, pos, state, blockEntity, stack);
	}
	public static double whichSlabIsSeeing(PlayerEntity player, BlockPos pos){
		Vec3d vec3d = player.getRotationVec(1.0F).normalize();
        Vec3d vec3d1 = new Vec3d(pos.getX() - player.getPos().x, pos.getY() + 0.5F - (player.getPos().y + (double)player.getEyeY()), pos.getZ() - player.getPos().z);
        double d0 = vec3d1.length();
        vec3d1 = vec3d1.normalize();
        double d1 = vec3d.dotProduct(vec3d1);
        return d1;
	}
	@Override
	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {

		return true;
	}

	   @Environment(EnvType.CLIENT)
	   public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
	      return 1.0F;
	   }

}
