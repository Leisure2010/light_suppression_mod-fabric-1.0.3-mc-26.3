package com.leisure.light_suppression_mod.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Shadow
    public abstract Block getBlock();

    /**
     * 将地狱传送门方块的光照等级从 11 修改为 0。
     */
    @Inject(
            method = "getLightEmission",
            at = @At("HEAD"),
            cancellable = true
    )
    private void lightSuppressionMod$setNetherPortalLightToZero(
            CallbackInfoReturnable<Integer> cir
    ) {
        if (this.getBlock() == Blocks.NETHER_PORTAL) {
            cir.setReturnValue(0);
        }
    }
}