package com.prohtiman.pawsoplenty;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistryInit;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.Heightmap;
import org.intellij.lang.annotations.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PawsOPlenty implements ModInitializer {
	public static List<EntityType<?>> entities = new ArrayList<>();
	public static final String MOD_ID = "pawsoplenty";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<CustomDog> CUSTOM_DOG =
			FabricEntityTypeBuilder.<CustomDog>createMob()
			.spawnGroup(MobCategory.MONSTER)
			.entityFactory((entityType, level) -> new CustomDog(entityType, level, 150, 1000))
			.dimensions(EntityDimensions.scalable(0.5F, 1.4F))
			//.spawnRestriction(SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HostileGraveyardEntity::canSpawnInDarkness)
			.defaultAttributes(Wolf::createAttributes)
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		registerEntities();
	}

	private static void register(String name, EntityType<?> type) {
		entities.add(type);
		Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MOD_ID, name), type);
	}

	private static void registerEntities(){
		register("custom_dog", CUSTOM_DOG);
	}
}