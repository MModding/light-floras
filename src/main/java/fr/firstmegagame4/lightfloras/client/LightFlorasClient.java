package fr.firstmegagame4.lightfloras.client;

import com.mmodding.mmodding_lib.library.base.MModdingClientModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.initializers.ClientElementsInitializer;
import fr.firstmegagame4.lightfloras.LightFlorasFlowers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import java.util.ArrayList;
import java.util.List;

public class LightFlorasClient implements MModdingClientModInitializer {

	@Override
	public @Nullable Config getClientConfig() {
		return null;
	}

	@Override
	public List<ClientElementsInitializer> getClientElementsInitializers() {
		List<ClientElementsInitializer> clientElementsInitializers = new ArrayList<>();
		clientElementsInitializers.add(this::renderLayers);
		return clientElementsInitializers;
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		MModdingClientModInitializer.super.onInitializeClient(mod);
	}

	public void renderLayers() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, ignored) -> BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(identifier)));
	}
}
