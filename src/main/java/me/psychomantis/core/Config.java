package me.psychomantis.core;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author Ravinath Rajapakshe
 */
public class Config {
    private static final Dotenv config = Dotenv.configure().load();

    public static String getPrefix() {
        return config.get("PREFIX");
    }
    
    public static String getToken() {
        return config.get("TOKEN");
    }
    
    public static String getEnvironment() {
        return config.get("ENVIRONMENT");
    }

    public static String getDevServer() { return config.get("DEV_SERVER"); }
}
