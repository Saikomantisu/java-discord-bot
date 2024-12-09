package me.psychomantis.utils;

import net.dv8tion.jda.api.EmbedBuilder;

public class Messages {

    public static EmbedBuilder errorMsg(String msg) {
        EmbedBuilder embed = new EmbedBuilder()
            .setDescription(msg.toString())
            .setColor(JDAColors.WARN);

        return embed;
    }
    
    public static EmbedBuilder successMsg(String msg) {
        EmbedBuilder embed = new EmbedBuilder()
                .setDescription(msg.toString())
                .setColor(JDAColors.SUCCESS);
        
        return embed;
    }
}
