package fr.firstmegagame4.lightfloras;

import com.mmodding.mmodding_lib.library.base.MModdingModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

import java.util.ArrayList;
import java.util.List;

public class LightFloras implements MModdingModInitializer {

	public static final ItemGroup LIGHT_FLORAS_FLOWERS = QuiltItemGroup.createWithIcon(
		LightFloras.createId("flowers"),
		() -> new ItemStack(LightFlorasFlowers.SHORT_LAVENDER)
	);

	public static final ConfigObject staticConfig;

	static {
		Config config = new LightFlorasConfig();
		config.initializeConfig();
		staticConfig = config.getContent().copy();
	}

	@Nullable
	@Override
	public Config getConfig() {
		return new LightFlorasConfig();
	}

	@Override
	public List<ElementsInitializer> getElementsInitializers() {
		List<ElementsInitializer> elementsInitializers = new ArrayList<>();
		elementsInitializers.add(new LightFlorasFlowers());
		elementsInitializers.add(new LightFlorasGeneration());
		return elementsInitializers;
	}

	@Override
	public void onInitialize(ModContainer mod) {
		MModdingModInitializer.super.onInitialize(mod);
	}

	public static String id() {
		return "light_floras";
	}

	public static Identifier createId(String path) {
		return new Identifier(LightFloras.id(), path);
	}
}
