package com.mmodding.lightfloras;

import com.mmodding.mmodding_lib.library.blocks.settings.AdvancedBlockSettings;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import com.mmodding.mmodding_lib.library.items.settings.AdvancedItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import java.util.HashMap;
import java.util.Map;

public class LightFlorasFlowers implements ElementsInitializer {

	public static final Map<Identifier, ItemStack> IDENTIFIERS = new HashMap<>();

	public static final Block PURPLE_BELLS = LightFlorasFlowers.registerSmallFlower("purple_bells", DyeColor.PURPLE, Region.EUROPE);
	public static final Block SHORT_PURPLE_BELLS = LightFlorasFlowers.registerSmallFlower("short_purple_bells", DyeColor.PURPLE, Region.EUROPE);
	public static final Block RED_BELLS = LightFlorasFlowers.registerSmallFlower("red_bells", DyeColor.RED, Region.EUROPE);
	public static final Block SHORT_RED_BELLS = LightFlorasFlowers.registerSmallFlower("short_red_bells", DyeColor.RED, Region.EUROPE);
	public static final Block WHITE_BELLS = LightFlorasFlowers.registerSmallFlower("white_bells", DyeColor.WHITE, Region.EUROPE);
	public static final Block SHORT_WHITE_BELLS = LightFlorasFlowers.registerSmallFlower("short_white_bells", DyeColor.WHITE, Region.EUROPE);
	public static final Block PINK_CARNATION_FLOWER = LightFlorasFlowers.registerSmallFlower("pink_carnation_flower", DyeColor.PINK, Region.EUROPE);
	public static final Block RED_CARNATION_FLOWER = LightFlorasFlowers.registerSmallFlower("red_carnation_flower", DyeColor.RED, Region.EUROPE);
	public static final Block RED_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("red_chrysanthemums", DyeColor.RED, Region.EUROPE);
	public static final Block PINK_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("pink_chrysanthemums", DyeColor.PINK, Region.EUROPE);
	public static final Block WHITE_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("white_chrysanthemums", DyeColor.WHITE, Region.EUROPE);
	public static final Block YELLOW_CHRYSANTHEMUMS = LightFlorasFlowers.registerSmallFlower("yellow_chrysanthemums", DyeColor.YELLOW, Region.EUROPE);
	public static final Block DAFFODIL = LightFlorasFlowers.registerSmallFlower("daffodil", DyeColor.YELLOW, Region.EUROPE);
	public static final Block LIGHT_BLUE_HORTENSIA = LightFlorasFlowers.registerSmallFlower("light_blue_hortensia", DyeColor.LIGHT_BLUE, Region.EUROPE);
	public static final Block PINK_HORTENSIA = LightFlorasFlowers.registerSmallFlower("pink_hortensia", DyeColor.PINK, Region.EUROPE);
	public static final Block WHITE_HORTENSIA = LightFlorasFlowers.registerSmallFlower("white_hortensia", DyeColor.WHITE, Region.EUROPE);
	public static final Block PINK_HYACINTHS = LightFlorasFlowers.registerTallFlower("pink_hyacinths", DyeColor.PINK, Region.EUROPE);
	public static final Block PURPLE_HYACINTHS = LightFlorasFlowers.registerTallFlower("purple_hyacinths", DyeColor.PURPLE, Region.EUROPE);
	public static final Block RED_HYACINTHS = LightFlorasFlowers.registerTallFlower("red_hyacinths", DyeColor.RED, Region.EUROPE);
	public static final Block WHITE_HYACINTHS = LightFlorasFlowers.registerTallFlower("white_hyacinths", DyeColor.WHITE, Region.EUROPE);
	public static final Block LAVENDER = LightFlorasFlowers.registerTallFlower("lavender", DyeColor.PURPLE, Region.EUROPE);
	public static final Block SHORT_LAVENDER = LightFlorasFlowers.registerSmallFlower("short_lavender", DyeColor.PURPLE, Region.EUROPE);
	public static final Block TALL_LAVENDER = LightFlorasFlowers.registerTallFlower("tall_lavender", DyeColor.PURPLE, Region.EUROPE);
	public static final Block PILEA = LightFlorasFlowers.registerSmallFlower("pilea", DyeColor.GREEN, Region.EUROPE);
	public static final Block PINK_VIOLETS = LightFlorasFlowers.registerSmallFlower("pink_violets", DyeColor.PINK, Region.EUROPE);
	public static final Block PURPLE_VIOLETS = LightFlorasFlowers.registerSmallFlower("purple_violets", DyeColor.PURPLE, Region.EUROPE);

	public void register() {
		this.recipes();
	}

	public void recipes() {
		LightFlorasFlowers.IDENTIFIERS.forEach((identifier, dye) -> RecipeManagerHelper.registerStaticRecipe(
			VanillaRecipeBuilders.shapelessRecipe(dye).ingredient(new ItemStack(Registry.ITEM.get(identifier))).build(identifier, "")
		));
	}

	private static Block registerSmallFlower(String path, DyeColor dye, Region region) {
		if (LightFloras.STATIC_CONFIG.getBoolean(region.getParameter())) {
			return LightFlorasFlowers.registerFlower(path, new FlowerBlock(StatusEffects.SATURATION, 7, AdvancedBlockSettings.copyOf(Blocks.ALLIUM)), dye, 1);
		} else {
			return null;
		}
	}

	private static Block registerTallFlower(String path, DyeColor dye, Region region) {
		if (LightFloras.STATIC_CONFIG.getBoolean(region.getParameter())) {
			return LightFlorasFlowers.registerFlower(path, new TallFlowerBlock(AdvancedBlockSettings.copyOf(Blocks.LILAC)), dye, 2);
		} else {
			return null;
		}
	}

	private static Block registerFlower(String path, Block flower, DyeColor dye, int count) {

		Identifier identifier = LightFloras.createId(path);

		Block registeredFlower = Registry.register(Registry.BLOCK, identifier, flower);
		Registry.register(Registry.ITEM, identifier, new BlockItem(flower, new AdvancedItemSettings().group(LightFloras.LIGHT_FLORAS_FLOWERS)));

		LightFlorasFlowers.IDENTIFIERS.put(identifier, new ItemStack(DyeItem.byColor(dye), count));

		return registeredFlower;
	}

	public enum Region {
		NORTH_AMERICA("loadNorthAmericaRegion"),
		SOUTH_AMERICA("loadSouthAmericaRegion"),
		EUROPE("loadEuropeRegion"),
		NORTH_AFRICA("loadNorthAfricaRegion"),
		SOUTH_AFRICA("loadSouthAfricaRegion"),
		ASIA("loadAsiaRegion"),
		OCEANIA("loadOceaniaRegion");

		private final String parameter;

		Region(String parameter) {
			this.parameter = parameter;
		}

		public String getParameter() {
			return this.parameter;
		}
	}
}
