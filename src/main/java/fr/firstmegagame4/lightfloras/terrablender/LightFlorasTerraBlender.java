package fr.firstmegagame4.lightfloras.terrablender;

import fr.firstmegagame4.lightfloras.LightFloras;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class LightFlorasTerraBlender implements TerraBlenderApi {

	@Override
	public void onTerraBlenderInitialized() {
		Regions.register(new LightFlorasRegion(LightFloras.createId("overworld"), 2));
		// SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, LightFloras.id(), LightFlorasSurfaceRules.createSurfaceRules());
	}
}
