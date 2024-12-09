package me.psychomantis.core;

import me.psychomantis.commands.*;
import me.psychomantis.listeners.BotListener;
import me.psychomantis.managers.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class JavaDiscordBot {

    static JDA builder;
    static CommandManager manager;

    public static void main(String[] args) {
        builder = JDABuilder.createDefault(Config.getToken())
                .setActivity(Activity.playing("Air guitar with invisible strings 🎸🤘"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES)
                .enableCache(CacheFlag.VOICE_STATE)
                .build();

        manager = new CommandManager();

        initializeListeners();
        initializeCommands();
    }

    public static void initializeCommands() {
        manager.add(new Play());
        manager.add(new Userinfo());
        manager.add(new ServerInfo());
        manager.add(new Help());
        manager.add(new Ping());
        manager.add(new BotStats());
    }

    public static void initializeListeners() {
        builder.addEventListener(manager);
        builder.addEventListener(new BotListener());
    }

}
