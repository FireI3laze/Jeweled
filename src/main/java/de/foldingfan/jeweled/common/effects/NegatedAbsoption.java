package de.foldingfan.jeweled.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class NegatedAbsoption extends MobEffect {

    protected NegatedAbsoption(MobEffectCategory category, int duration) {
        super(category, duration);
    }

    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int p_19419_) {
        AttributeInstance attribute = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        attribute.setBaseValue(livingEntity.getMaxHealth() + 1);

        super.removeAttributeModifiers(livingEntity, attributeMap, p_19419_);
    }

    public void applyEffectTick(LivingEntity livingEntity, int p_19635_){
        livingEntity.setHealth(livingEntity.getHealth() - (float)(2 * (p_19635_ + 1)));
    }

    public void addAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int p_19423_) {

        //livingEntity.setHealth(livingEntity.getHealth() - (float)(2 * (p_19423_ + 1)));
        AttributeInstance attribute = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        attribute.setBaseValue(livingEntity.getMaxHealth() - 1);

        super.addAttributeModifiers(livingEntity, attributeMap, p_19423_);
    }
}
