package com.dainxt.mixedslab.handlers;

import com.dainxt.mixedslab.events.OnPlaceBlock;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class EventsHandler {
	public static void registerEvents(){
		OnPlaceBlock event = new OnPlaceBlock();
		UseBlockCallback.EVENT.register(event);
	}
}
