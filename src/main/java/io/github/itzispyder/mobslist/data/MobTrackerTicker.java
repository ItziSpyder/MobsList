package io.github.itzispyder.mobslist.data;

import io.github.itzispyder.mobslist.MobsList;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobTrackerTicker implements ClientTickEvents.EndTick {

    public static final Map<Class<? extends Entity>, Integer> MOB_COUNT = new HashMap<>();
    public static final Map<Class<? extends Entity>, Integer> MOB_DISTANCE = new HashMap<>();
    private static int tickTimer = 0;

    @Override
    public void onEndTick(MinecraftClient client) {
        if (!MobsList.enabled) {
            return;
        }

        if (client.player != null && tickTimer++ > 20) {
            MOB_COUNT.clear();
            MOB_DISTANCE.clear();

            ClientPlayerEntity player = getPlayer();
            World world = player.getWorld();
            int renderDistance = client.options.getClampedViewDistance();
            Box box = player.getBoundingBox().expand(renderDistance * 16);
            List<Entity> entities = world.getOtherEntities(player, box, entity -> entity instanceof LivingEntity && entity.isAlive());

            for (Entity entity : entities) {
                MOB_COUNT.put(entity.getClass(), getCount(entity) + 1);
                MOB_DISTANCE.put(entity.getClass(), Math.min(getDistance(entity), (int)entity.distanceTo(getPlayer())));
            }

            tickTimer = 0;
        }
    }

    public static ClientPlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }

    public static int getCount(Entity entity) {
        return MOB_COUNT.getOrDefault(entity.getClass(), 0);
    }

    public static int getDistance(Entity entity) {
        return MOB_DISTANCE.getOrDefault(entity.getClass(), (int)entity.distanceTo(getPlayer()));
    }
}
