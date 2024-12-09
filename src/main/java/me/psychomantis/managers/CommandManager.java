package me.psychomantis.managers;

import java.util.ArrayList;
import java.util.List;
import me.psychomantis.core.Config;
import me.psychomantis.interfaces.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.jetbrains.annotations.NotNull;

public class CommandManager extends ListenerAdapter {

    private final List<ICommand> commands = new ArrayList<>();
    private final String developerServerID = Config.getDevServer();

//     Registering slash commands
    @Override
    public void onReady(@NotNull ReadyEvent event) {
//        If in DEV registering them to the dev server
        if ("DEV".equals(Config.getEnvironment())) {
            for (ICommand command : commands) {
                Guild devGuild = event.getJDA().getGuildById(developerServerID);

                if (command.getOptions() == null) {
                    devGuild.upsertCommand(command.getName(), command.getDescription()).queue();
                    return;
                }

                devGuild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
            }
//            If in PROD registering them globaly
        } else if ("PROD".equals(Config.getEnvironment())) {

            for (ICommand command : commands) {

                if (command.getOptions() == null) {
                    event.getJDA().upsertCommand(command.getName(), command.getDescription()).queue();
                    return;
                }

                event.getJDA().upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
            }
        }

    }

//     Seting the slash command
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (ICommand command : commands) {
            if (command.getName().equals(event.getName())) {
                command.execute(event);
                return;
            }
        }
    }

    public void add(ICommand command) {
        commands.add(command);
    }
}
