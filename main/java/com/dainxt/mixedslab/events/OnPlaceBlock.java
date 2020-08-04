package com.dainxt.mixedslab.events;

import com.dainxt.mixedslab.MixedSlab;
import com.dainxt.mixedslab.block.MixedSlabBlock;
import com.dainxt.mixedslab.block.VerticalSlabBlock;
import com.dainxt.mixedslab.block.entity.MixedSlabBlockEntity;
import com.dainxt.mixedslab.block.entity.VerticalSlabBlockEntity;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class OnPlaceBlock implements UseBlockCallback{

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
		BlockPos pos = hitResult.getBlockPos();
		
		BlockState state = world.getBlockState(pos);
		
		ItemStack stack = player.getStackInHand(hand);
		
		Block ground = state.getBlock();
		
		Block hnd = Block.getBlockFromItem(stack.getItem());
		
		if(!hitResult.getSide().equals(Direction.DOWN) && !hitResult.getSide().equals(Direction.UP) && hnd instanceof SlabBlock) {
			/*if(ground instanceof VerticalSlabBlock) {
				
				VerticalSlabBlockEntity bE= (VerticalSlabBlockEntity) world.getBlockEntity(pos);

				((VerticalSlabBlockEntity) world.getBlockEntity(pos)).nT = Block.getRawIdFromState(hnd.getDefaultState().with(Properties.SLAB_TYPE,SlabType.TOP));
				((VerticalSlabBlockEntity) world.getBlockEntity(pos)).markDirty();
				
				BlockSoundGroup blockSoundGroup = hnd.getDefaultState().getSoundGroup();
	            world.playSound(player, pos, blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
	            
				if (player == null || !player.abilities.creativeMode) {
	                  stack.decrement(1);
	            }
	            
				return ActionResult.SUCCESS;
			}
			else {*/
				pos = pos.offset(hitResult.getSide());
				
				if(world.getBlockState(pos).equals(Blocks.AIR.getDefaultState())) {
				
					world.setBlockState(pos,(MixedSlab.VERTICALSLAB_BLOCK.getDefaultState()));
					
					
					MixedSlab.LOGGER.info("Side1 {}", hitResult.getSide());
					MixedSlab.LOGGER.info("Side2 {}", hitResult.getSide().getVector());
					VerticalSlabBlockEntity bE= (VerticalSlabBlockEntity) world.getBlockEntity(pos);
					bE.nB = Block.getRawIdFromState(hnd.getDefaultState());
					bE.direction = hitResult.getSide();
					bE.markDirty();
					
					BlockSoundGroup blockSoundGroup = hnd.getDefaultState().getSoundGroup();
		            world.playSound(player, pos, blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
		            
					if (player == null || !player.abilities.creativeMode) {
		                  stack.decrement(1);
		            }
		            
		            return ActionResult.SUCCESS;
				}
				return ActionResult.FAIL;
			//}
		}
		
		//Do MixedSlab
		else {

			if(ground instanceof SlabBlock && hnd instanceof SlabBlock && !ground.equals(hnd) && state.get(Properties.SLAB_TYPE) != SlabType.DOUBLE) {
	
				world.setBlockState(pos,MixedSlab.MIXEDSLAB_BLOCK.getDefaultState());
				MixedSlabBlockEntity bE= (MixedSlabBlockEntity) world.getBlockEntity(pos);
				if(state.get(Properties.SLAB_TYPE) == SlabType.TOP) {
					((MixedSlabBlockEntity) world.getBlockEntity(pos)).nT = Block.getRawIdFromState(state);
					((MixedSlabBlockEntity) world.getBlockEntity(pos)).nB = Block.getRawIdFromState(hnd.getDefaultState().with(Properties.SLAB_TYPE,SlabType.BOTTOM));
				} else if (state.get(Properties.SLAB_TYPE) == SlabType.BOTTOM){
					((MixedSlabBlockEntity) world.getBlockEntity(pos)).nB = Block.getRawIdFromState(state);
					((MixedSlabBlockEntity) world.getBlockEntity(pos)).nT = Block.getRawIdFromState(hnd.getDefaultState().with(Properties.SLAB_TYPE,SlabType.TOP));
				}
				
				((MixedSlabBlockEntity) world.getBlockEntity(pos)).markDirty();
				
				BlockSoundGroup blockSoundGroup = hnd.getDefaultState().getSoundGroup();
	            world.playSound(player, pos, blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
	            
				if (player == null || !player.abilities.creativeMode) {
	                  stack.decrement(1);
	            }
	            
				return ActionResult.SUCCESS;
				//((MixedSlabBlockEntity)world.getBlockEntity(pos)).nB = state.get() Block.getRawIdFromState(((SlabBlock)ground))
			}
			
			return ActionResult.PASS;
			
		}
	}

}
