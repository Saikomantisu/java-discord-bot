package me.psychomantis.commands;

import java.util.List;
import me.psychomantis.interfaces.ICommand;
import me.psychomantis.utils.JDAColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Ping implements ICommand {

    @Override
    public String getName() { return "ping"; }

    @Override
    public String getDescription() {
        return "Check your connection with a quick '/ping' command!";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("**Pong :ping_pong:**")
                .setColor(JDAColors.PURPLE)
                .setDescription(":stopwatch: " + event.getJDA().getGatewayPing() + "ms");

        event.replyEmbeds(embed.build()).queue();
    }

}
