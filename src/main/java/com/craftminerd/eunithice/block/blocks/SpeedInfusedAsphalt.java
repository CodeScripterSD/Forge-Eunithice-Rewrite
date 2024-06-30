package com.craftminerd.eunithice.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SpeedInfusedAsphalt extends Block {
    private final double speedUpMultiplier;
    public SpeedInfusedAsphalt(Properties properties, double speedUpMultiplier) {
        super(properties);
        this.speedUpMultiplier = speedUpMultiplier;
    }

    public void stepOn(Level world, BlockPos p_154574_, BlockState p_154575_, Entity entity) {
        if (!entity.isSteppingCarefully()) this.speedUp(entity);

        super.stepOn(world, p_154574_, p_154575_, entity);
    }

    private void speedUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        double x = Math.min(vec3.x * this.speedUpMultiplier, 8);
        double z = Math.min(vec3.z * this.speedUpMultiplier, 8);

        entity.setDeltaMovement(x, vec3.y, z);
    }

}