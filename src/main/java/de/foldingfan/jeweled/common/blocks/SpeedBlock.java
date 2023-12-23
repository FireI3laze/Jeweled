package de.foldingfan.jeweled.common.blocks;

import de.foldingfan.jeweled.common.capability.maxhealth.CustomPlayerData;
import de.foldingfan.jeweled.common.capability.maxhealth.CustomPlayerDataProvider;
import de.foldingfan.jeweled.common.effects.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedBlock extends Block {

    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public SpeedBlock(Properties properties){
        super(properties);
    }
    public static boolean toggle;


    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player) {
            CustomPlayerData playerData = entity.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
            if (player.getMaxHealth() > 1 && playerData.getModEffect()) {
                if (playerData.getModEffect()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600));
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {


        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        /*if (!level.isClientSide && toggle){
            CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            attribute.setBaseValue(player.getMaxHealth() + 2);
            playerData.setModEffect(false);
        }
        CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
        if (playerData.getModEffect()){
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            attribute.setBaseValue(player.getMaxHealth() + 2);
            toggle = false;
        }*/

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
        if (!level.isClientSide && interactionHand == interactionHand.MAIN_HAND && player.getMaxHealth() > 1 && !playerData.getModEffect()){
            //player.addEffect(new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(), 1800, 10000));
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            if(player.getMaxHealth() > 2) attribute.setBaseValue(player.getMaxHealth() - 2);
            /*if(player.getMaxHealth() > 2){
                ICustomPlayerData playerData = player.getCapability(CustomPlayerCapabilities.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                playerData.setMaxHealth((int) player.getMaxHealth() - 2);
            }*/
            //CustomPlayerEffect playerEffect = player.getCapability(CustomPlayerCapabilities.PLAYER_EFFECT_CAPABILITY).orElseThrow(NullPointerException::new);
            //playerEffect.setEffect(true);
            playerData.setModEffect(true);
            player.addEffect(new MobEffectInstance(ModEffects.DRAINED.get()));
            player.hurt(player.damageSources().magic(), 1);
            player.sendSystemMessage(Component.literal("Speed Block is now enabled"));
        }

        else if (!level.isClientSide && interactionHand == interactionHand.MAIN_HAND && playerData.getModEffect()){
            //player.removeEffect(ModEffects.NEGATEDABSOPTION.get());
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            if(player.getMaxHealth() <= 18) attribute.setBaseValue(player.getMaxHealth() + 2);
            //
            //CustomPlayerEffect playerEffect = player.getCapability(CustomPlayerCapabilities.PLAYER_EFFECT_CAPABILITY).orElseThrow(NullPointerException::new);
            //playerEffect.setEffect(false);
            playerData.setModEffect(false);
            player.addEffect(new MobEffectInstance(ModEffects.DRAINED.get()));
            player.sendSystemMessage(Component.literal("Speed Block is now disabled"));
        }
        return super.use(state, level, pos, player, interactionHand, blockHitResult);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> componentList, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            componentList.add(Component.literal("Place and rightclick to activate (Consumes max health when active!)").withStyle(ChatFormatting.AQUA));
        } else {
            componentList.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }


        super.appendHoverText(itemStack, blockGetter, componentList, tooltipFlag);
    }
}
