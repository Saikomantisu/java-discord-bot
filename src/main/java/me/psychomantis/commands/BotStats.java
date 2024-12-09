package me.psychomantis.commands;

import java.time.Instant;
import java.util.List;
import me.psychomantis.interfaces.ICommand;
import static me.psychomantis.listeners.BotListener.botStartTime;
import me.psychomantis.utils.JDAColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class BotStats implements ICommand {

     private String getBotUptime() {
        Instant currentTime = Instant.now();
        long uptimeInSeconds = currentTime.getEpochSecond() - botStartTime.getEpochSecond();

        long days = uptimeInSeconds / 86400;
        long hours = (uptimeInSeconds % 86400) / 3600;
        long minutes = (uptimeInSeconds % 3600) / 60;
        long seconds = uptimeInSeconds % 60;

        return String.format("%dd %02dh %02dm %02ds", days, hours, minutes, seconds);
    }

    @Override
    public String getName() {
        return "botstats";
    }

    @Override
    public String getDescription() {
        return "Curious about my stats? Just type '/botstats' to get a snapshot of my performance and status";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String servers = String.valueOf(event.getJDA().getGuilds().size());
        String users = String.valueOf(event.getJDA().getUsers().size());
        String userAvatarUrl = event.getUser().getAvatarUrl();
        
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Stats for Zero")
                .setColor(JDAColors.PURPLE)
                .addField("Servers:", servers, true)
                .addField("Users:", users, true)
                .addField("Online Since:", getBotUptime(), true)
                .setFooter("Requested by " + event.getMember().getUser().getAsTag(), userAvatarUrl);
        
        event.replyEmbeds(embed.build()).queue();
    }

}
