package fr.firstmegagame4.lightfloras.client;

import fr.firstmegagame4.lightfloras.LightFlorasFlowers;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class LightFlorasClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		LightFlorasFlowers.renderLayers();
	}
}
