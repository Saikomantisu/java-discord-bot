package me.psychomantis.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.psychomantis.interfaces.ICommand;
import me.psychomantis.lavaplayer.PlayerManager;
import me.psychomantis.utils.Messages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;

public class Play implements ICommand {

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Play your favorite tunes with '/play' and enjoy the music!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "name", "Name of the song to play", true));

        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()) {
            EmbedBuilder embed = Messages.errorMsg("You need to in a voice channel");
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        Member self = Objects.requireNonNull(event.getGuild()).getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) memberVoiceState.getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String trackURL = event.getOption("name").getAsString();
        String searchLink = "ytsearch: " + trackURL + " official audio";
        System.out.println(trackURL + " " + searchLink);


        PlayerManager.getINSTANCE().loadAndPlay(event.getChannel().asTextChannel(), searchLink);

    }
}
