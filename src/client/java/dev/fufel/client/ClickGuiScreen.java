package dev.fufel.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ClickGuiScreen extends Screen {
    private static final int GREEN = 0xFF7CFF4D;
    private static final int PANEL = 0xF0121712;

    public ClickGuiScreen() {
        super(Component.literal("FUFEL CLIENT"));
    }

    @Override
    protected void init() {
        int panelWidth = 220;
        int left = (this.width - panelWidth) / 2;
        int top = Math.max(28, (this.height - 210) / 2);
        int buttonWidth = 184;
        int x = left + 18;

        addRenderableWidget(Button.builder(moduleLabel("HUD", ModuleState.hudEnabled), button -> {
            ModuleState.hudEnabled = !ModuleState.hudEnabled;
            button.setMessage(moduleLabel("HUD", ModuleState.hudEnabled));
            ClientConfig.save();
        }).bounds(x, top + 42, buttonWidth, 22).build());

        addRenderableWidget(Button.builder(moduleLabel("FPS", ModuleState.fpsEnabled), button -> {
            ModuleState.fpsEnabled = !ModuleState.fpsEnabled;
            button.setMessage(moduleLabel("FPS", ModuleState.fpsEnabled));
            ClientConfig.save();
        }).bounds(x, top + 70, buttonWidth, 22).build());

        addRenderableWidget(Button.builder(moduleLabel("Coordinates", ModuleState.coordinatesEnabled), button -> {
            ModuleState.coordinatesEnabled = !ModuleState.coordinatesEnabled;
            button.setMessage(moduleLabel("Coordinates", ModuleState.coordinatesEnabled));
            ClientConfig.save();
        }).bounds(x, top + 98, buttonWidth, 22).build());

        addRenderableWidget(Button.builder(Component.literal("Done"), button -> onClose())
                .bounds(x, top + 142, buttonWidth, 22).build());
    }

    private static Component moduleLabel(String name, boolean enabled) {
        return Component.literal(name + "  [" + (enabled ? "ON" : "OFF") + "]");
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);

        int panelWidth = 220;
        int panelHeight = 190;
        int left = (this.width - panelWidth) / 2;
        int top = Math.max(28, (this.height - 210) / 2);

        graphics.fill(left, top, left + panelWidth, top + panelHeight, PANEL);
        graphics.fill(left, top, left + panelWidth, top + 2, GREEN);
        graphics.drawCenteredString(this.font, "FUFEL CLIENT", this.width / 2, top + 13, GREEN);
        graphics.drawCenteredString(this.font, "UTILITY MODULES", this.width / 2, top + 29, 0xFF9AA69A);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
