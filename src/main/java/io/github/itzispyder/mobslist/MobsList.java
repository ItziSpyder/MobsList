package io.github.itzispyder.mobslist;

import io.github.itzispyder.mobslist.data.MobTrackerTicker;
import io.github.itzispyder.mobslist.gui.huds.MobListHud;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class MobsList implements ModInitializer {

    public static boolean enabled = false;

    @Override
    public void onInitialize() {
        this.init();
    }

    public void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("mobslist").executes(context -> {
                enabled = !enabled;
                send("MobsList hud is now " + (enabled ? "enabled" : "disabled"));
                return 1;
            }));
            dispatcher.register(ClientCommandManager.literal("mb").executes(context -> {
                enabled = !enabled;
                send("MobsList hud is now " + (enabled ? "enabled" : "disabled"));
                return 1;
            }));
        });
        HudRenderCallback.EVENT.register(new MobListHud());
        ClientTickEvents.END_CLIENT_TICK.register(new MobTrackerTicker());
    }

    public static void send(String msg) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.sendMessage(Text.literal(msg));
        }
    }
}
