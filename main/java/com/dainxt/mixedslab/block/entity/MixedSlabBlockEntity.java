package com.dainxt.mixedslab.block.entity;

import com.dainxt.mixedslab.MixedSlab;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;

public class MixedSlabBlockEntity extends BlockEntity implements BlockEntityClientSerializable{
	   public int nB = 0;
	   public int nT = 0;
	   
	public MixedSlabBlockEntity() {
		super(MixedSlab.MIXEDSLAB_BLOCKENTITY);
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		
		nB = tag.getInt("bottom");
		nT = tag.getInt("top");
	}
	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.putInt("bottom", nB);
	    tag.putInt("top", nT);
	    
	    return tag;
	}
	
	@Override
	public void fromClientTag(CompoundTag tag) {
		nB = tag.getInt("bottom");
		nT = tag.getInt("top");
		
	}
	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		tag.putInt("bottom", nB);
	    tag.putInt("top", nT);
	    
	    return tag;
	}

	@Override
	public double getSquaredRenderDistance() {
		return 256.D;
	}
	
	
	
}
