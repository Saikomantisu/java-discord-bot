package me.psychomantis.listeners;

import java.time.Instant;
import java.util.List;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

public class BotListener extends ListenerAdapter {
    public static Instant botStartTime;
    
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        botStartTime = Instant.now();
        String botName = event.getJDA().getSelfUser().getAsTag();
        System.out.println("[INFO] Bot successfully connected as " + botName);
        
    }
}
