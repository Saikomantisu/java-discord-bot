package me.psychomantis.commands;

import java.util.ArrayList;
import java.util.List;
import me.psychomantis.interfaces.ICommand;
import me.psychomantis.utils.JDAColors;
import static me.psychomantis.utils.OffsetDateTimeConverter.formatOffsetDateTime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Userinfo implements ICommand {

    @Override
    public String getName() {
        return "userinfo";
    }

    @Override
    public String getDescription() {
        return "Quickly access user info with '/userinfo.' Discover details like name and join date in seconds!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();

        data.add(new OptionData(OptionType.USER, "user", "Get user details by mentioning them", false));

        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping mentionUser = event.getOption("user");

        Member user = (mentionUser == null) ? event.getMember() : mentionUser.getAsMember();

        String userId = user.getId();
        String userName = user.getUser().getAsTag();
        String userCreated = formatOffsetDateTime(user.getUser().getTimeCreated());
        String userJoined = formatOffsetDateTime(user.getTimeJoined());
        String userBoosted = (user.isBoosting()) ? "Boosting" : "Not Boosting";
        String userIsBot = (user.getUser().isBot()) ? "Yes" : "No";
        List<Role> userRoles = user.getRoles();
        String userAvatarUrl = user.getUser().getAvatarUrl();

        String memberAvatarUrl = event.getUser().getAvatarUrl();

        String userRoleString = "";

        for (Role role : userRoles) {
            userRoleString += (role.getAsMention() + " ");
        }

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Information for " + userName, userAvatarUrl)
                .setColor(JDAColors.PURPLE)
                .setDescription("Profile: " + user.getAsMention())
                .setThumbnail(userAvatarUrl)
                .addField("User ID:", userId, true)
                .addField("Account creation: ", userCreated, true)
                .addField("Join date: ", userJoined, true)
                .addField("Boosting status: ", userBoosted, false)
                .addField("is Bot: ", userIsBot, false)
                .addField(userRoles.size() + " Roles", userRoleString, false)
                .setFooter("Requested by " + event.getMember().getUser().getAsTag(), memberAvatarUrl);

        event.replyEmbeds(embed.build()).queue();

    }

}
