package com.sammy.malum.core.eventhandlers;

import com.sammy.malum.common.capability.LivingEntityDataCapability;
import com.sammy.malum.common.capability.PlayerDataCapability;
import com.sammy.malum.common.effect.CorruptedAerialAura;
import com.sammy.malum.common.enchantment.ReboundEnchantment;
import com.sammy.malum.common.entity.boomerang.ScytheBoomerangEntity;
import com.sammy.malum.common.item.equipment.CeaselessImpetusItem;
import com.sammy.malum.common.item.equipment.curios.CurioTokenOfGratitude;
import com.sammy.malum.common.item.tools.ModScytheItem;
import com.sammy.malum.common.spiritaffinity.ArcaneAffinity;
import com.sammy.malum.common.spiritaffinity.EarthenAffinity;
import com.sammy.malum.compability.tetra.TetraCompat;
import com.sammy.malum.config.CommonConfig;
import com.sammy.malum.core.helper.ItemHelper;
import com.sammy.malum.core.helper.SpiritHelper;
import com.sammy.malum.core.setup.AttributeRegistry;
import com.sammy.malum.core.setup.DamageSourceRegistry;
import com.sammy.malum.core.setup.item.ItemTagRegistry;
import com.sammy.malum.core.systems.item.IEventResponderItem;
import com.sammy.malum.core.systems.spirit.SoulHarvestHandler;
import com.sammy.malum.core.systems.spirit.SpiritDataReloadListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RuntimeEvents {

    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        PlayerDataCapability.attachPlayerCapability(event);
        LivingEntityDataCapability.attachEntityCapability(event);
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        PlayerDataCapability.playerJoin(event);
        CurioTokenOfGratitude.giveItem(event);
        SoulHarvestHandler.addEntity(event);
        if (TetraCompat.LOADED) {
            TetraCompat.LoadedOnly.fireArrow(event);
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(LivingSpawnEvent.SpecialSpawn event) {
        SoulHarvestHandler.specialSpawn(event);
    }

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        CorruptedAerialAura.onEntityJump(event);
    }

    @SubscribeEvent
    public static void onLivingTarget(LivingSetAttackTargetEvent event) {
        SoulHarvestHandler.entityTarget(event);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        SoulHarvestHandler.entityTick(event);
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        LivingEntityDataCapability.syncEntityCapability(event);
        PlayerDataCapability.syncPlayerCapability(event);
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        PlayerDataCapability.playerClone(event);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        ArcaneAffinity.recoverSoulWard(event);
        EarthenAffinity.recoverHeartOfStone(event);
        SoulHarvestHandler.playerTick(event);
    }

    @SubscribeEvent
    public static void registerListeners(AddReloadListenerEvent event) {
        SpiritDataReloadListener.register(event);
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        ReboundEnchantment.onRightClickItem(event);
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        ArcaneAffinity.consumeSoulWard(event);
        EarthenAffinity.consumeHeartOfStone(event);

        LivingEntity target = event.getEntityLiving();
        if (event.getSource().isMagic()) {
            float amount = event.getAmount();
            if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                float proficiency = (float) attacker.getAttributeValue(AttributeRegistry.MAGIC_PROFICIENCY.get());
                amount *= 1 * Math.exp(0.075f * proficiency);
            }
            float resistance = (float) target.getAttributeValue(AttributeRegistry.MAGIC_RESISTANCE.get());
            event.setAmount((float) (amount * 1 * Math.exp(-0.15f * resistance)));
        }
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            float amount = event.getAmount();
            ItemStack stack = attacker.getMainHandItem();
            if (event.getSource().getDirectEntity() instanceof ScytheBoomerangEntity) {
                stack = ((ScytheBoomerangEntity) event.getSource().getDirectEntity()).scythe;
            }
            if (!event.getSource().isMagic()) {
                float damage = (float) attacker.getAttributeValue(AttributeRegistry.MAGIC_DAMAGE.get());
                if (damage > 0 && target.isAlive()) {
                    target.invulnerableTime = 0;
                    target.hurt(DamageSourceRegistry.causeVoodooDamage(attacker), damage);
                }
            }
            if (ItemTagRegistry.SOUL_HUNTER_WEAPON.getValues().contains(stack.getItem()) || (TetraCompat.LOADED && TetraCompat.LoadedOnly.hasSoulStrike(stack))) {
                LivingEntityDataCapability.getCapability(target).ifPresent(e -> e.exposedSoul = 200);
            }
            if (stack.getItem() instanceof ModScytheItem) {
                float proficiency = (float) attacker.getAttributeValue(AttributeRegistry.SCYTHE_PROFICIENCY.get());
                event.setAmount(amount + proficiency * 0.5f);
            }
            ItemHelper.eventResponders(attacker).forEach(s -> ((IEventResponderItem) s.getItem()).hurtEvent(event, attacker, target, s));
            ItemHelper.eventResponders(target).forEach(s -> ((IEventResponderItem) s.getItem()).takeDamageEvent(event, target, attacker, s));
        }
        if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity().getTags().contains("malum:soul_arrow")) {
            LivingEntityDataCapability.getCapability(target).ifPresent(e -> e.exposedSoul = 200);
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (CeaselessImpetusItem.checkTotemDeathProtection(player, event.getSource())) {
                event.setCanceled(true);
                return;
            }
        }
        if (event.getSource().getMsgId().equals(DamageSourceRegistry.FORCED_SHATTER_DAMAGE)) {
            SpiritHelper.createSpiritEntities(event.getEntityLiving());
            return;
        }
        LivingEntity target = event.getEntityLiving();
        LivingEntity attacker = null;
        if (event.getSource().getEntity() instanceof LivingEntity directAttacker) {
            attacker = directAttacker;
        }
        if (attacker == null) {
            attacker = target.getLastHurtByMob();
        }
        if (attacker != null) {
            LivingEntity finalAttacker = attacker;
            ItemStack stack = attacker.getMainHandItem();
            DamageSource source = event.getSource();
            if (source.getDirectEntity() instanceof ScytheBoomerangEntity scytheBoomerang) {
                stack = scytheBoomerang.scythe;
            }
            if (!(target instanceof Player)) {
                ItemStack finalStack = stack;
                LivingEntityDataCapability.getCapability(target).ifPresent(e -> {
                    if (e.exposedSoul > 0 && !e.soulless && (!CommonConfig.SOULLESS_SPAWNERS.get() || (CommonConfig.SOULLESS_SPAWNERS.get() && !e.spawnerSpawned))) {
                        SpiritHelper.createSpiritsFromWeapon(target, finalAttacker, finalStack);
                        e.soulless = true;
                    }
                });

            }

            ItemHelper.eventResponders(attacker).forEach(s -> ((IEventResponderItem) s.getItem()).killEvent(event, finalAttacker, target, s));
        }
    }
}