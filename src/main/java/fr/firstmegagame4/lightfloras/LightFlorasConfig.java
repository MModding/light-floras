package fr.firstmegagame4.lightfloras;

import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.config.client.screen.ConfigScreen;
import com.mmodding.mmodding_lib.library.config.client.screen.ConfigScreenOptions;
import net.minecraft.text.Text;

public class LightFlorasConfig implements Config {

	@Override
	public String getQualifier() {
		return "light_floras";
	}

	@Override
	public String getFilePath() {
		return "light_floras/common";
	}

	@Override
	public ConfigObject defaultConfig() {
		return new ConfigObject.Builder()
			.addBooleanParameter(LightFlorasFlowers.Region.NORTH_AMERICA.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.SOUTH_AMERICA.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.EUROPE.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.NORTH_AFRICA.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.SOUTH_AFRICA.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.ASIA.getParameter(), true)
			.addBooleanParameter(LightFlorasFlowers.Region.OCEANIA.getParameter(), true)
			.build();
	}

	@Override
	public ConfigScreenOptions getConfigOptions() {
		return new ConfigScreenOptions(Text.of("Light Floras Config"), new ConfigScreen.BlockTextureLocation("moss_block.png"));
	}
}
