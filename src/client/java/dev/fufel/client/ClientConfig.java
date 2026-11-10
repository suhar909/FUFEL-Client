package dev.fufel.client;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ClientConfig {
    private static final Path FILE =
            FabricLoader.getInstance().getConfigDir().resolve("fufel-client.properties");

    private ClientConfig() {}

    public static void load() {
        if (!Files.exists(FILE)) {
            save();
            return;
        }

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(FILE)) {
            properties.load(input);
            ModuleState.hudEnabled = Boolean.parseBoolean(
                    properties.getProperty("hud.enabled", "true"));
            ModuleState.fpsEnabled = Boolean.parseBoolean(
                    properties.getProperty("fps.enabled", "true"));
            ModuleState.coordinatesEnabled = Boolean.parseBoolean(
                    properties.getProperty("coordinates.enabled", "true"));
        } catch (IOException exception) {
            System.err.println("[FUFEL CLIENT] Could not load config: " + exception.getMessage());
        }
    }

    public static void save() {
        Properties properties = new Properties();
        properties.setProperty("hud.enabled", Boolean.toString(ModuleState.hudEnabled));
        properties.setProperty("fps.enabled", Boolean.toString(ModuleState.fpsEnabled));
        properties.setProperty("coordinates.enabled", Boolean.toString(ModuleState.coordinatesEnabled));

        try {
            Files.createDirectories(FILE.getParent());
            try (OutputStream output = Files.newOutputStream(FILE)) {
                properties.store(output, "FUFEL CLIENT settings");
            }
        } catch (IOException exception) {
            System.err.println("[FUFEL CLIENT] Could not save config: " + exception.getMessage());
        }
    }
}
