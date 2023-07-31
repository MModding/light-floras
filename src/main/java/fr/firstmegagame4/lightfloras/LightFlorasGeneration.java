package fr.firstmegagame4.lightfloras;

import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import com.mmodding.mmodding_lib.library.worldgen.features.defaults.CustomFlowerFeature;
import fr.firstmegagame4.lightfloras.mixin.OverworldBiomeCreatorAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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

public class LightFlorasGeneration implements ElementsInitializer {

	public static final RegistryKey<Biome> LAVENDER_MEADOW = RegistryKey.of(Registry.BIOME_KEY, LightFloras.createId("lavender_meadow"));

	@Override
	public void register() {
		this.features();
		this.biomes();
	}

	public void features() {
		if (LightFloras.staticConfig.getBoolean(LightFlorasFlowers.Region.EUROPE.getParameter())) {
			CustomFlowerFeature lavenderFeature = new CustomFlowerFeature(
				96,
				9,
				2,
				LightFlorasFlowers.SHORT_LAVENDER,
				LightFlorasFlowers.LAVENDER,
				LightFlorasFlowers.TALL_LAVENDER
			).setCount(5);
			lavenderFeature.register(LightFloras.createId("lavender"));
			lavenderFeature.addDefaultToBiomes(ctx -> ctx.getBiomeKey().equals(LightFlorasGeneration.LAVENDER_MEADOW));
		}
	}

	public void biomes() {
		Registry.register(BuiltinRegistries.BIOME, LAVENDER_MEADOW, BiomeCreator.lavenderMeadow());
	}

	public static final class BiomeCreator {

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

		public static void addDefaultFeatures(GenerationSettings.Builder builder) {
			DefaultBiomeFeatures.addLandCarvers(builder);
			DefaultBiomeFeatures.addAmethystGeodes(builder);
			DefaultBiomeFeatures.addDungeons(builder);
			DefaultBiomeFeatures.addUndergroundVariety(builder);
			DefaultBiomeFeatures.addSprings(builder);
			DefaultBiomeFeatures.addFrozenTopLayer(builder);
		}

		private static Biome lavenderMeadow() {
			SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
			spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1, 2));
			spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 6));
			spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 2, 4));
			DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

			GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
			BiomeCreator.addDefaultFeatures(generationSettings);
			DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
			DefaultBiomeFeatures.addDefaultOres(generationSettings);
			DefaultBiomeFeatures.addDefaultDisks(generationSettings);
			DefaultBiomeFeatures.addMeadowVegetation(generationSettings);
			DefaultBiomeFeatures.addEmeraldOre(generationSettings);
			DefaultBiomeFeatures.addInfestedStone(generationSettings);
			return BiomeCreator.createBiome(Biome.Precipitation.RAIN, 0.7f, 0.4F, 14394367, 8043635, spawnSettings, generationSettings, null);
		}
	}
}
