package fr.firstmegagame4.lightfloras.terrablender;

import com.mojang.datafixers.util.Pair;
import fr.firstmegagame4.lightfloras.LightFlorasGeneration;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class LightFlorasRegion extends Region {

	public LightFlorasRegion(Identifier name, int weight) {
		super(name, RegionType.OVERWORLD, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
		this.addBiomeSimilar(mapper, BiomeKeys.PLAINS, LightFlorasGeneration.LAVENDER_PLAINS);
	}
}
