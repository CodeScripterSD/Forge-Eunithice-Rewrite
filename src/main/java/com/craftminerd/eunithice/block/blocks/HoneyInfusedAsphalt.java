package com.craftminerd.eunithice.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static java.lang.Math.abs;

public class HoneyInfusedAsphalt extends Block {


    public HoneyInfusedAsphalt(Properties properties) {
        super(properties);
    }

    public void stepOn(Level world, BlockPos p_154574_, BlockState p_154575_, Entity entity) {


        if (!entity.isSteppingCarefully()) this.slowDown(entity);
        super.stepOn(world, p_154574_, p_154575_, entity);
    }

    private void slowDown(Entity entity) {
        double slowDownFactor = 0.4D;
        Vec3 vec3 = entity.getDeltaMovement();
        if (abs(vec3.x) > 0.10D || abs(vec3.z) > 0.10D) {
            entity.setDeltaMovement(vec3.x * slowDownFactor, vec3.y, vec3.z * slowDownFactor);
        }
    }

    public void fallOn(Level p_153362_, BlockState p_153363_, BlockPos p_153364_, Entity p_153365_, float p_153366_) {
        p_153365_.causeFallDamage(p_153366_, 0.1F, DamageSource.FALL);
    }

}