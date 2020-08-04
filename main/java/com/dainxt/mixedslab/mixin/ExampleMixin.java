package com.dainxt.mixedslab.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.entity.feature.VillagerClothingFeatureRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.dainxt.mixedslab.MixedSlab;
import com.dainxt.mixedslab.block.entity.MixedSlabBlockEntity;

@Mixin(Block.class)
public class ExampleMixin {
	
	@Inject(at = @At("HEAD"), method = "afterBreak", cancellable = true)
	private void findTexture(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity,
			ItemStack stack, CallbackInfo info) {
		if(state.getBlock() instanceof SlabBlock && state.get(Properties.SLAB_TYPE) == SlabType.DOUBLE) {
			HitResult hit = player.rayTrace(20, 0, false);
			if(hit instanceof BlockHitResult && player.isSneaking()) {
					if(((BlockHitResult)hit).getSide() == Direction.UP){
						world.setBlockState(pos, state.with(Properties.SLAB_TYPE,SlabType.BOTTOM));
						state = state.getBlock().getDefaultState().with(Properties.SLAB_TYPE,SlabType.BOTTOM);
						
						state.getBlock().afterBreak(world, player, pos, state, blockEntity, stack);
						info.cancel();
					}
					
					if(((BlockHitResult)hit).getSide() == Direction.DOWN){
						world.setBlockState(pos, state.with(Properties.SLAB_TYPE,SlabType.TOP));
						state = state.getBlock().getDefaultState().with(Properties.SLAB_TYPE,SlabType.TOP);
						
						state.getBlock().afterBreak(world, player, pos, state, blockEntity, stack);
						info.cancel();
					}
					
			}
		}
	}
	
}
