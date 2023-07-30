package fr.firstmegagame4.lightfloras;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import java.util.HashMap;
import java.util.Map;

public class LightFlorasFlowers {

	public static final Map<Identifier, ItemStack> IDENTIFIERS = new HashMap<>();

	public static void register() {
		LightFlorasFlowers.registerSmallFlower("purple_bells", DyeColor.PURPLE);
		LightFlorasFlowers.registerSmallFlower("short_purple_bells", DyeColor.PURPLE);
		LightFlorasFlowers.registerSmallFlower("red_bells", DyeColor.RED);
		LightFlorasFlowers.registerSmallFlower("short_red_bells", DyeColor.RED);
		LightFlorasFlowers.registerSmallFlower("white_bells", DyeColor.WHITE);
		LightFlorasFlowers.registerSmallFlower("short_white_bells", DyeColor.WHITE);
		LightFlorasFlowers.registerSmallFlower("pink_carnation_flower", DyeColor.PINK);
		LightFlorasFlowers.registerSmallFlower("red_carnation_flower", DyeColor.RED);
		LightFlorasFlowers.registerSmallFlower("orange_chrysanthemums", DyeColor.ORANGE);
		LightFlorasFlowers.registerSmallFlower("pink_chrysanthemums", DyeColor.PINK);
		LightFlorasFlowers.registerSmallFlower("white_chrysanthemums", DyeColor.WHITE);
		LightFlorasFlowers.registerSmallFlower("yellow_chrysanthemums", DyeColor.YELLOW);
		LightFlorasFlowers.registerSmallFlower("daffodil", DyeColor.YELLOW);
		LightFlorasFlowers.registerSmallFlower("light_blue_hortensia", DyeColor.LIGHT_BLUE);
		LightFlorasFlowers.registerSmallFlower("pink_hortensia", DyeColor.PINK);
		LightFlorasFlowers.registerSmallFlower("white_hortensia", DyeColor.WHITE);
		LightFlorasFlowers.registerTallFlower("pink_hyacinths", DyeColor.PINK);
		LightFlorasFlowers.registerTallFlower("purple_hyacinths", DyeColor.PURPLE);
		LightFlorasFlowers.registerTallFlower("red_hyacinths", DyeColor.RED);
		LightFlorasFlowers.registerTallFlower("white_hyacinths", DyeColor.WHITE);
		LightFlorasFlowers.registerTallFlower("lavender", DyeColor.PURPLE);
		LightFlorasFlowers.registerSmallFlower("short_lavender", DyeColor.PURPLE);
		LightFlorasFlowers.registerTallFlower("tall_lavender", DyeColor.PURPLE);
		LightFlorasFlowers.registerSmallFlower("pilea", DyeColor.GREEN);
		LightFlorasFlowers.registerSmallFlower("pink_violets", DyeColor.PINK);
		LightFlorasFlowers.registerSmallFlower("purple_violets", DyeColor.PURPLE);
	}

	public static void recipes() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, dye) -> RecipeManagerHelper.registerStaticRecipe(
			VanillaRecipeBuilders.shapelessRecipe(dye).ingredient(new ItemStack(Registry.ITEM.get(identifier))).build(identifier, "")
		));
	}

	public static void renderLayers() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, ignored) -> BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(identifier)));
	}

	private static void registerSmallFlower(String path, DyeColor dye) {
		LightFlorasFlowers.registerFlower(path, new FlowerBlock(StatusEffects.SATURATION, 7, QuiltBlockSettings.copyOf(Blocks.ALLIUM)), dye, 1);
	}

	private static void registerTallFlower(String path, DyeColor dye) {
		LightFlorasFlowers.registerFlower(path, new TallFlowerBlock(QuiltBlockSettings.copyOf(Blocks.LILAC)), dye, 2);
	}

	private static void registerFlower(String path, Block flower, DyeColor dye, int count) {
		Identifier identifier = LightFloras.createId(path);

		Registry.register(Registry.BLOCK, identifier, flower);
		Registry.register(Registry.ITEM, identifier, new BlockItem(flower, new QuiltItemSettings().group(LightFloras.LIGHT_FLORAS_FLOWERS)));

		LightFlorasFlowers.IDENTIFIERS.put(identifier, new ItemStack(DyeItem.byColor(dye), count));
	}
}
