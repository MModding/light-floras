package com.mmodding.lightfloras;

import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.config.ConfigOptions;
import com.mmodding.mmodding_lib.library.utils.TextureLocation;
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
	public ConfigOptions getConfigOptions() {
		return new ConfigOptions(Text.of("Light Floras Config"), new TextureLocation.Block("moss_block"));
	}
}
