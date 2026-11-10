package dev.fufel.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

public final class HudRenderer {
    private static final int PANEL = 0xB8101414;
    private static final int GREEN = 0xFF7CFF4D;
    private static final int WHITE = 0xFFE8F0E8;
    private static final int MUTED = 0xFF9AA69A;

    private HudRenderer() {}

    public static void render(GuiGraphics graphics, net.minecraft.client.DeltaTracker tickCounter) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (!ModuleState.hudEnabled || client.options.hideGui || player == null) {
            return;
        }

        int left = 8;
        int top = 8;
        int width = 150;
        int rows = 1 + (ModuleState.fpsEnabled ? 1 : 0)
                + (ModuleState.coordinatesEnabled ? 1 : 0);
        int height = 12 + rows * 12;

        graphics.fill(left, top, left + width, top + height, PANEL);
        graphics.fill(left, top, left + 2, top + height, GREEN);
        graphics.drawString(client.font, "FUFEL CLIENT", left + 8, top + 5, GREEN, false);

        int y = top + 19;
        if (ModuleState.fpsEnabled) {
            graphics.drawString(client.font, "FPS: " + client.getFps(), left + 8, y, WHITE, false);
            y += 12;
        }

        if (ModuleState.coordinatesEnabled) {
            String coords = String.format("XYZ: %d %d %d",
                    player.getBlockX(), player.getBlockY(), player.getBlockZ());
            graphics.drawString(client.font, coords, left + 8, y, MUTED, false);
        }
    }
}
