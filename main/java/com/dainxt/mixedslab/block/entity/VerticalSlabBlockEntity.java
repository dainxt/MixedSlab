package com.dainxt.mixedslab.block.entity;

import com.dainxt.mixedslab.MixedSlab;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class VerticalSlabBlockEntity extends BlockEntity implements BlockEntityClientSerializable{
	
	public Direction direction = Direction.DOWN;
	
	public int nB = 0;
	public int nT = 0;
	
	public VerticalSlabBlockEntity() {
		super(MixedSlab.VERTICALSLAB_BLOCKENTITY);
	}

	@Override
	public double getSquaredRenderDistance() {
		return 256.D;
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		nT = tag.getInt("top");
		nB = tag.getInt("bottom");
		direction = Direction.byId(tag.getInt("direction"));
	}
	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.putInt("top", nT);
		tag.putInt("bottom", nB);
	    tag.putInt("direction", direction.getId());
	    return tag;
	}
	
	@Override
	public void fromClientTag(CompoundTag tag) {
		nT = tag.getInt("top");
		nB = tag.getInt("bottom");
		direction = Direction.byId(tag.getInt("direction"));
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		tag.putInt("top", nT);
		tag.putInt("bottom", nB);
	    tag.putInt("direction", direction.getId());
	    return tag;
	}

}
