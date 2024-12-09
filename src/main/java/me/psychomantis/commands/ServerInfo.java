package me.psychomantis.commands;

import java.util.List;
import me.psychomantis.interfaces.ICommand;
import me.psychomantis.utils.JDAColors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ServerInfo implements ICommand {

    @Override
    public String getName() {
        return "serverinfo";
    }

    @Override
    public String getDescription() {
        return "Get key server details instantly with '/serverinfo.' Discover your server's name, members, and more";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();

        String guildName = guild.getName();
        String guildId = guild.getId();
        String guildMemberCount = String.valueOf(guild.getMemberCount());
        String guildRoleCount = String.valueOf(guild.getRoles().size());
        String guildChannelsCount = String.valueOf(guild.getChannels().size());
        String guildIconrURL = guild.getIconUrl();

        String guildOwner = guild.getOwner().getAsMention();

        String userAvatarUrl = event.getUser().getAvatarUrl();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Server info for " + guildName)
                .setColor(JDAColors.PURPLE)
                .setThumbnail(guildIconrURL)
                .addField("Server Name:", guildName, false)
                .addField("Server ID:", guildId, false)
                .addField("Server Owner:", guildOwner, false)
                .addField("Member Count:", guildMemberCount, true)
                .addField("Role Count:", guildRoleCount, true)
                .addField("Channel Count:", guildChannelsCount, true)
                .setFooter("Requested by " + event.getMember().getUser().getAsTag(), userAvatarUrl);

        event.replyEmbeds(embed.build()).queue();
    }

}
