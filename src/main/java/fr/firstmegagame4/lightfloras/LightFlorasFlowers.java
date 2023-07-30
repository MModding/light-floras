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

	public static final Block PURPLE_BELLS = LightFlorasFlowers.registerSmallFlower("purple_bells", DyeColor.PURPLE);
	public static final Block SHORT_PURPLE_BELLS = LightFlorasFlowers.registerSmallFlower("short_purple_bells", DyeColor.PURPLE);
	public static final Block RED_BELLS = LightFlorasFlowers.registerSmallFlower("red_bells", DyeColor.RED);
	public static final Block SHORT_RED_BELLS = LightFlorasFlowers.registerSmallFlower("short_red_bells", DyeColor.RED);
	public static final Block WHITE_BELLS = LightFlorasFlowers.registerSmallFlower("white_bells", DyeColor.WHITE);
	public static final Block SHORT_WHITE_BELLS = LightFlorasFlowers.registerSmallFlower("short_white_bells", DyeColor.WHITE);
	public static final Block PINK_CARNATION_FLOWER = LightFlorasFlowers.registerSmallFlower("pink_carnation_flower", DyeColor.PINK);
	public static final Block RED_CARNATION_FLOWER = LightFlorasFlowers.registerSmallFlower("red_carnation_flower", DyeColor.RED);
	public static final Block RED_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("red_chrysanthemums", DyeColor.RED);
	public static final Block PINK_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("pink_chrysanthemums", DyeColor.PINK);
	public static final Block WHITE_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("white_chrysanthemums", DyeColor.WHITE);
	public static final Block YELLOW_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("yellow_chrysanthemums", DyeColor.YELLOW);
	public static final Block DAFFODIL = LightFlorasFlowers.registerSmallFlower("daffodil", DyeColor.YELLOW);
	public static final Block LIGHT_BLUE_HORTENSIA = LightFlorasFlowers.registerSmallFlower("light_blue_hortensia", DyeColor.LIGHT_BLUE);
	public static final Block PINK_HORTENSIA = LightFlorasFlowers.registerSmallFlower("pink_hortensia", DyeColor.PINK);
	public static final Block WHITE_HORTENSIA = LightFlorasFlowers.registerSmallFlower("white_hortensia", DyeColor.WHITE);
	public static final Block PINK_HYACINTHS = LightFlorasFlowers.registerTallFlower("pink_hyacinths", DyeColor.PINK);
	public static final Block PURPLE_HYACINTHS = LightFlorasFlowers.registerTallFlower("purple_hyacinths", DyeColor.PURPLE);
	public static final Block RED_HYACINTHS = LightFlorasFlowers.registerTallFlower("red_hyacinths", DyeColor.RED);
	public static final Block WHITE_HYACINTHS = LightFlorasFlowers.registerTallFlower("white_hyacinths", DyeColor.WHITE);
	public static final Block LAVENDER = LightFlorasFlowers.registerTallFlower("lavender", DyeColor.PURPLE);
	public static final Block SHORT_LAVENDER = LightFlorasFlowers.registerSmallFlower("short_lavender", DyeColor.PURPLE);
	public static final Block TALL_LAVENDER = LightFlorasFlowers.registerTallFlower("tall_lavender", DyeColor.PURPLE);
	public static final Block PILEA = LightFlorasFlowers.registerSmallFlower("pilea", DyeColor.GREEN);
	public static final Block PINK_VIOLETS = LightFlorasFlowers.registerSmallFlower("pink_violets", DyeColor.PINK);
	public static final Block PURPLE_VIOLETS = LightFlorasFlowers.registerSmallFlower("purple_violets", DyeColor.PURPLE);

	public static void recipes() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, dye) -> RecipeManagerHelper.registerStaticRecipe(
			VanillaRecipeBuilders.shapelessRecipe(dye).ingredient(new ItemStack(Registry.ITEM.get(identifier))).build(identifier, "")
		));
	}

	public static void renderLayers() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, ignored) -> BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(identifier)));
	}

	private static Block registerSmallFlower(String path, DyeColor dye) {
		return LightFlorasFlowers.registerFlower(path, new FlowerBlock(StatusEffects.SATURATION, 7, QuiltBlockSettings.copyOf(Blocks.ALLIUM)), dye, 1);
	}

	private static Block registerTallFlower(String path, DyeColor dye) {
		return LightFlorasFlowers.registerFlower(path, new TallFlowerBlock(QuiltBlockSettings.copyOf(Blocks.LILAC)), dye, 2);
	}

	private static Block registerFlower(String path, Block flower, DyeColor dye, int count) {
		Identifier identifier = LightFloras.createId(path);

		Block registeredFlower = Registry.register(Registry.BLOCK, identifier, flower);
		Registry.register(Registry.ITEM, identifier, new BlockItem(flower, new QuiltItemSettings().group(LightFloras.LIGHT_FLORAS_FLOWERS)));

		LightFlorasFlowers.IDENTIFIERS.put(identifier, new ItemStack(DyeItem.byColor(dye), count));

		return registeredFlower;
	}
}
