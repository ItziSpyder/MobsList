package io.github.itzispyder.mobslist.gui.huds;

import io.github.itzispyder.mobslist.MobsList;
import io.github.itzispyder.mobslist.data.MobTrackerTicker;
import io.github.itzispyder.mobslist.util.MobSkinDrawer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
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
            context.drawText(client.textRenderer, "§7x" + entry.getValue(), margin + 10, caret.get() + 1, 0xFFFFFFFF, true);
            count.addAndGet(entry.getValue());
            caret.addAndGet(10);
        });

        context.drawText(client.textRenderer, "§7Total: " + count.get(), margin, margin, 0xFFFFFFFF, true);
    }
}
