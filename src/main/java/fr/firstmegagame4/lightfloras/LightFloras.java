package fr.firstmegagame4.lightfloras;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class LightFloras implements ModInitializer {

	public static final ItemGroup LIGHT_FLORAS_FLOWERS = QuiltItemGroup.createWithIcon(
		LightFloras.createId("flowers"),
		() -> new ItemStack(Registry.ITEM.get(LightFloras.createId("lavender")))
	);

	@Override
	public void onInitialize(ModContainer mod) {
		LightFlorasFlowers.register();
		LightFlorasFlowers.recipes();
		LightFlorasGeneration.biomes();
		LightFlorasGeneration.features();
	}

	public static String id() {
		return "light_floras";
	}

	public static Identifier createId(String path) {
		return new Identifier(LightFloras.id(), path);
	}
}
