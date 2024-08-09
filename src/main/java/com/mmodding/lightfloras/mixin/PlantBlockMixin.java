package com.mmodding.lightfloras.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.tag.api.QuiltTagKey;
import org.quiltmc.qsl.tag.api.TagType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {

    @Inject(method = "canPlantOnTop", at = @At("TAIL"), cancellable = true)
    private void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (QuiltLoader.isModLoaded("archeon")) {
            if (Registry.BLOCK.getId((PlantBlock) (Object) this).getNamespace().equals("light_floras")) {
                TagKey<Block> archeonSoil = QuiltTagKey.of(Registry.BLOCK_KEY, new Identifier("archeon", "equivalents/soil"), TagType.NORMAL);
                if (floor.isIn(archeonSoil)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
