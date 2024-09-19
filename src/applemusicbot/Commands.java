package me.paulinamae.applemusicbot;

import me.paulinamae.applemusicbot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildMessageChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class Commands extends ListenerAdapter{
	public String prefix = "!";
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		if(args[0].equalsIgnoreCase(prefix + "play")) {
			
			final GuildMessageChannel channel = (GuildMessageChannel) event.getChannel();
			final Member self = event.getGuild().getSelfMember();
			final GuildVoiceState selfVoiceState = self.getVoiceState();
			
			if (selfVoiceState.inAudioChannel()) {
				channel.sendMessage("I'm already in a voice channel!").queue();
				return;
			}
			
			final Member member = event.getMember();
			final GuildVoiceState memberVoiceState = member.getVoiceState();
			
			if (!memberVoiceState.inAudioChannel()) {
				channel.sendMessage("You need to be in a voice channel for this command to work!").queue();
				return;
			}
			
			final AudioManager audioManager = event.getGuild().getAudioManager();
			final AudioChannel memberChannel = memberVoiceState.getChannel();
			
			audioManager.openAudioConnection(memberChannel);
			channel.sendMessage("Connecting to " + memberChannel.getName()).queue();
			
			PlayerManager.getInstance().playRickRoll(channel, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
			
		}
		
		if(args[0].equalsIgnoreCase(prefix + "leave")) {
			final AudioManager audioManager = event.getGuild().getAudioManager();
			final GuildMessageChannel channel = (GuildMessageChannel) event.getChannel();
			
			audioManager.closeAudioConnection();
			channel.sendMessage("Disconnecting!");
		}
	}
	
}
