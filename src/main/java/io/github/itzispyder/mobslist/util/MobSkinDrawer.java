package io.github.itzispyder.mobslist.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.Identifier;

import java.util.Map;

public class MobSkinDrawer {

    public static final String ID = "mobslist";
    public static final Map<Class<? extends Entity>, Identifier> REGISTRY = ManualMap.fromItems(
            AllayEntity.class, new Identifier(ID, "textures/entities/allay.png"),
            AxolotlEntity.class, new Identifier(ID, "textures/entities/axolotl.png"),
            BatEntity.class, new Identifier(ID, "textures/entities/bat.png"),
            BeeEntity.class, new Identifier(ID, "textures/entities/bee.png"),
            BlazeEntity.class, new Identifier(ID, "textures/entities/blaze.png"),
            MooshroomEntity.class, new Identifier(ID, "textures/entities/brown_mooshroom.png"),
            CamelEntity.class, new Identifier(ID, "textures/entities/camel.png"),
            CatEntity.class, new Identifier(ID, "textures/entities/cat.png"),
            CaveSpiderEntity.class, new Identifier(ID, "textures/entities/cave_spider.png"),
            ChickenEntity.class, new Identifier(ID, "textures/entities/chicken.png"),
            CodEntity.class, new Identifier(ID, "textures/entities/cod.png"),
            CowEntity.class, new Identifier(ID, "textures/entities/cow.png"),
            CreeperEntity.class, new Identifier(ID, "textures/entities/creeper.png"),
            DolphinEntity.class, new Identifier(ID, "textures/entities/dolphin.png"),
            DrownedEntity.class, new Identifier(ID, "textures/entities/drowned.png"),
            EnderDragonEntity.class, new Identifier(ID, "textures/entities/enderdragon.png"),
            EndermanEntity.class, new Identifier(ID, "textures/entities/enderman.png"),
            EndermiteEntity.class, new Identifier(ID, "textures/entities/endermite.png"),
            EvokerEntity.class, new Identifier(ID, "textures/entities/evoker.png"),
            FoxEntity.class, new Identifier(ID, "textures/entities/fox.png"),
            FrogEntity.class, new Identifier(ID, "textures/entities/frog.png"),
            GhastEntity.class, new Identifier(ID, "textures/entities/ghast.png"),
            GlowSquidEntity.class, new Identifier(ID, "textures/entities/glow_squid.png"),
            GoatEntity.class, new Identifier(ID, "textures/entities/goat.png"),
            GuardianEntity.class, new Identifier(ID, "textures/entities/guardian.png"),
            HoglinEntity.class, new Identifier(ID, "textures/entities/hoglin.png"),
            HorseEntity.class, new Identifier(ID, "textures/entities/horse.png"),
            HuskEntity.class, new Identifier(ID, "textures/entities/husk.png"),
            IllusionerEntity.class, new Identifier(ID, "textures/entities/illusioner.png"),
            IronGolemEntity.class, new Identifier(ID, "textures/entities/iron_golem.png"),
            LlamaEntity.class, new Identifier(ID, "textures/entities/llama.png"),
            MagmaCubeEntity.class, new Identifier(ID, "textures/entities/magmacube.png"),
            OcelotEntity.class, new Identifier(ID, "textures/entities/ocelot.png"),
            PandaEntity.class, new Identifier(ID, "textures/entities/panda.png"),
            ParrotEntity.class, new Identifier(ID, "textures/entities/parrot.png"),
            PhantomEntity.class, new Identifier(ID, "textures/entities/phantom.png"),
            PigEntity.class, new Identifier(ID, "textures/entities/pig.png"),
            PiglinEntity.class, new Identifier(ID, "textures/entities/piglin.png"),
            PiglinBruteEntity.class, new Identifier(ID, "textures/entities/piglin_brute.png"),
            PillagerEntity.class, new Identifier(ID, "textures/entities/pillager.png"),
            PolarBearEntity.class, new Identifier(ID, "textures/entities/polarbear.png"),
            PufferfishEntity.class, new Identifier(ID, "textures/entities/pufferfish.png"),
            RabbitEntity.class, new Identifier(ID, "textures/entities/rabbit.png"),
            RavagerEntity.class, new Identifier(ID, "textures/entities/ravager.png"),
            SalmonEntity.class, new Identifier(ID, "textures/entities/salmon.png"),
            TurtleEntity.class, new Identifier(ID, "textures/entities/sea_turtle.png"),
            SheepEntity.class, new Identifier(ID, "textures/entities/sheep.png"),
            ShulkerEntity.class, new Identifier(ID, "textures/entities/shulker.png"),
            SilverfishEntity.class, new Identifier(ID, "textures/entities/silverfish.png"),
            SkeletonEntity.class, new Identifier(ID, "textures/entities/skeleton.png"),
            SlimeEntity.class, new Identifier(ID, "textures/entities/slime.png"),
            SnifferEntity.class, new Identifier(ID, "textures/entities/sniffer.png"),
            SnowGolemEntity.class, new Identifier(ID, "textures/entities/snow_golem.png"),
            SpiderEntity.class, new Identifier(ID, "textures/entities/spider.png"),
            SquidEntity.class, new Identifier(ID, "textures/entities/squid.png"),
            StrayEntity.class, new Identifier(ID, "textures/entities/stray.png"),
            StriderEntity.class, new Identifier(ID, "textures/entities/strider.png"),
            TropicalFishEntity.class, new Identifier(ID, "textures/entities/tropical_fish.png"),
            VexEntity.class, new Identifier(ID, "textures/entities/vex.png"),
            VillagerEntity.class, new Identifier(ID, "textures/entities/villager.png"),
            VindicatorEntity.class, new Identifier(ID, "textures/entities/vindicator.png"),
            WanderingTraderEntity.class, new Identifier(ID, "textures/entities/wandering_trader.png"),
            WardenEntity.class, new Identifier(ID, "textures/entities/warden.png"),
            WitchEntity.class, new Identifier(ID, "textures/entities/witch.png"),
            WitherEntity.class, new Identifier(ID, "textures/entities/wither.png"),
            WitherSkeletonEntity.class, new Identifier(ID, "textures/entities/wither_skeleton.png"),
            WolfEntity.class, new Identifier(ID, "textures/entities/wolf.png"),
            ZoglinEntity.class, new Identifier(ID, "textures/entities/zoglin.png"),
            ZombieEntity.class, new Identifier(ID, "textures/entities/zombie.png"),
            ZombieVillagerEntity.class, new Identifier(ID, "textures/entities/zombie_villager.png"),
            ZombifiedPiglinEntity.class, new Identifier(ID, "textures/entities/zombified_piglin.png")
    );

    public static Identifier getIdentifier(Class<? extends Entity> entity) {
        return REGISTRY.get(entity);
    }

    public static void drawHead(DrawContext context, Class<? extends Entity> entity, int x, int y, int size) {
        Identifier tex = getIdentifier(entity);
        if (tex != null) {
            context.drawTexture(tex, x, y, 0, 0, size, size, size, size);
        }
    }

    public static void drawHead(DrawContext context, Entity entity, int x, int y, int size) {
        drawHead(context, entity.getClass(), x, y, size);
    }
}
