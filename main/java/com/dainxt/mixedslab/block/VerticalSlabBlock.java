package com.dainxt.mixedslab.block;

import java.util.ArrayList;
import java.util.List;

import com.dainxt.mixedslab.MixedSlab;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;
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
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
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

public class VerticalSlabBlock extends Block implements BlockEntityProvider{
	
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0f, 0f, 8.0f, 16.0f, 16.0f, 16.0f);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0f, 0f, 0f, 16.0f, 16.0f, 8f);
	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0f, 0f, 0f, 8.0f, 16.0f, 16.0f);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(8.0f, 0f, 0f, 16.0f, 16.0f, 16.0f);

	public VerticalSlabBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public BlockSoundGroup getSoundGroup(BlockState state) {
		
		return BlockSoundGroup.STONE;
	}
	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContext.Builder builder) {
		VerticalSlabBlockEntity e = (VerticalSlabBlockEntity) builder.get(LootContextParameters.BLOCK_ENTITY);
		List<ItemStack> drops = new ArrayList<>();
        if (e != null) {
        	drops.addAll(Block.getStateFromRawId(e.nB).getDroppedStacks(builder));
        }
        return drops;
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new VerticalSlabBlockEntity();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return super.getCollisionShape(state, world, pos, context);
	}
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		VerticalSlabBlockEntity slab = (VerticalSlabBlockEntity) world.getBlockEntity(pos);
		VoxelShape shape = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
		
		if(slab != null) {
			if(slab.direction.equals(Direction.NORTH)) {
				shape = NORTH_SHAPE;
			}else if(slab.direction.equals(Direction.SOUTH)){
				shape = SOUTH_SHAPE;
			}else if(slab.direction.equals(Direction.WEST)) {
				shape = WEST_SHAPE;
			}else if(slab.direction.equals(Direction.EAST)){
				shape = EAST_SHAPE;
			}
			
		}

		return shape;
	}
	@Override
	public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}
	@Override
	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}
	@Override
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		return super.isSideInvisible(state, stateFrom, direction);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return super.getRenderType(state);
	}
	
	@Environment(EnvType.CLIENT)
	   public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
	      return 1.0F;
	   }

}
