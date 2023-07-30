package fr.firstmegagame4.lightfloras;

import fr.firstmegagame4.lightfloras.mixin.OverworldBiomeCreatorAccessor;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.jetbrains.annotations.Nullable;

public class LightFlorasGeneration {

	public static final RegistryKey<Biome> LAVENDER_PLAINS = RegistryKey.of(Registry.BIOME_KEY, LightFloras.createId("lavender_plains"));

	public static void features() {}

	public static void biomes() {
		Registry.register(BuiltinRegistries.BIOME, LAVENDER_PLAINS, lavenderPlains());
	}

	public static void addDefaultFeatures(GenerationSettings.Builder builder) {
		DefaultBiomeFeatures.addLandCarvers(builder);
		DefaultBiomeFeatures.addAmethystGeodes(builder);
		DefaultBiomeFeatures.addDungeons(builder);
		DefaultBiomeFeatures.addUndergroundVariety(builder);
		DefaultBiomeFeatures.addSprings(builder);
		DefaultBiomeFeatures.addFrozenTopLayer(builder);
	}

	public static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int foliageColor, int grassColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound musicSound) {
		return new Biome.Builder()
			.precipitation(precipitation)
			.temperature(temperature)
			.downfall(downfall)
			.effects(
				new BiomeEffects.Builder()
					.waterColor(4159204)
					.waterFogColor(329011)
					.fogColor(12638463)
					.skyColor(OverworldBiomeCreatorAccessor.getSkyColor(temperature))
					.foliageColor(foliageColor)
					.grassColor(grassColor)
					.moodSound(BiomeMoodSound.CAVE)
					.music(musicSound)
					.build()
			)
			.spawnSettings(spawnSettings.build())
			.generationSettings(generationSettings.build())
			.build();
	}

	private static Biome lavenderPlains() {
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addPlainsMobs(spawnSettings);

		GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
		LightFlorasGeneration.addDefaultFeatures(generationSettings);
		DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
		DefaultBiomeFeatures.addDefaultOres(generationSettings);
		DefaultBiomeFeatures.addDefaultDisks(generationSettings);
		DefaultBiomeFeatures.addPlainsFeatures(generationSettings);
		DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
		DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
		return LightFlorasGeneration.createBiome(Biome.Precipitation.RAIN, 0.7f, 0.4F, 11354869, 9551193, spawnSettings, generationSettings, null);
	}
}
