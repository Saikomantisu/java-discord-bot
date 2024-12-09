package me.psychomantis.commands;

import java.util.List;
import me.psychomantis.interfaces.ICommand;
import me.psychomantis.utils.JDAColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Help implements ICommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Discover what I can do! Simply use '/help' to explore my range of powerful slash commands";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String userAvatarUrl = event.getUser().getAvatarUrl();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("**HELP MENU**")
                .setColor(JDAColors.PURPLE)
                .setDescription("Use the '/help' command to get a list of available commands and their descriptions. I'm here to guide you through all my capabilities!")
                .setFooter("Requested by " + event.getMember().getUser().getAsTag(), userAvatarUrl);

        event.replyEmbeds(embed.build()).queue();
    }

}
