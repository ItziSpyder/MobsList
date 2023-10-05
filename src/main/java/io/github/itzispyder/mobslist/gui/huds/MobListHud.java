package io.github.itzispyder.mobslist.gui.huds;

import io.github.itzispyder.mobslist.MobsList;
import io.github.itzispyder.mobslist.data.MobTrackerTicker;
import io.github.itzispyder.mobslist.util.MobSkinDrawer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MobListHud implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, float delta) {
        if (!MobsList.enabled) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        this.drawMobs(client, context);
        this.drawPlayers(client, context);
    }

    public void drawMobs(MinecraftClient client, DrawContext context) {
        AtomicInteger caret = new AtomicInteger(20);
        AtomicInteger count = new AtomicInteger();
        Comparator<Object> comparator = Comparator.comparing(map -> ((Map.Entry<Class<? extends Entity>, Integer>)map).getValue()).reversed();
        int margin = 10;
        int size = 8;

        MobTrackerTicker.MOB_COUNT.entrySet().stream().sorted(comparator).forEach(entry -> {
            if (entry.getKey() == PlayerEntity.class || MobSkinDrawer.getIdentifier(entry.getKey()) == null) {
                return;
            }
            MobSkinDrawer.drawHead(context, entry.getKey(), margin + 1, caret.get() + 1, size);
            int dist = MobTrackerTicker.MOB_DISTANCE.get(entry.getKey());
            String msg = "§7x" + entry.getValue();

            if (Monster.class.isAssignableFrom(entry.getKey()) && dist <= 16) {
                msg = msg.concat(" §e§o.." + dist);
            }
            else {
                msg = msg.concat(" §8§o.." + dist);
            }

            context.drawText(client.textRenderer, msg, margin + 10, caret.get() + 1, 0xFFFFFFFF, true);
            count.addAndGet(entry.getValue());
            caret.addAndGet(10);
        });

        context.drawText(client.textRenderer, "§7Total: " + count.get(), margin, margin, 0xFFFFFFFF, true);
    }

    public void drawPlayers(MinecraftClient client, DrawContext context) {
        if (client.player != null) {
            int marginTop = 10;
            int margin = 70;
            int caret = 20;
            int size = 8;
            int count = 0;

            for (PlayerEntity player : client.player.getWorld().getPlayers()) {
                if (count >= 5) {
                    context.drawText(client.textRenderer, "§7...", margin + 1, caret + 1, 0xFFFFFFFF, true);
                    break;
                }
                if (player.getGameProfile().getName().equals(client.player.getGameProfile().getName())) {
                    continue;
                }

                PlayerListEntry entry = client.player.networkHandler.getPlayerListEntry(player.getGameProfile().getId());
                if (entry != null) {
                    int dist = (int)player.distanceTo(client.player);
                    String msg = " §8§o" + entry.getProfile().getName() + " §e.." + dist;
                    PlayerSkinDrawer.draw(context, entry.getSkinTextures(), margin + 1, caret + 1, size);
                    context.drawText(client.textRenderer, msg, margin + 10, caret + 1, 0xFFFFFFFF, true);
                    caret += 10;
                    count ++;
                }
            }

            if (count > 0) {
                context.drawText(client.textRenderer, "§7Players: " + count + (count >= 5 ? "+" : ""), margin, marginTop, 0xFFFFFFFF, true);
            }
        }
    }
}
