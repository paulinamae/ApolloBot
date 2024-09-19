package me.paulinamae.applemusicbot.lavaplayer;

import java.util.HashMap;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildMessageChannel;

public class PlayerManager {
	private static PlayerManager INSTANCE; 
	
	private Map<Long, GuildMusicManager> musicManagers = null;
	private AudioPlayerManager audioPlayerManager = null; 
	
	public PlayerManager() {
		this.musicManagers = new HashMap<>();
		this.audioPlayerManager = new DefaultAudioPlayerManager(); 
		
		AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
		AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
	}
	
	public GuildMusicManager getMusicManager(Guild guild) {
		return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
			final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
			
			guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
			
			return guildMusicManager;
		});
	}
	
	public void playRickRoll(GuildMessageChannel channel, String TrackUrl) {
		
		final GuildMusicManager musicManager = this.getMusicManager((channel.getGuild()));
		
		this.audioPlayerManager.loadItemOrdered(musicManager, TrackUrl, (AudioLoadResultHandler) new AudioLoadResultHandler() {
			
			
			public void trackLoaded(AudioTrack track) {
				musicManager.scheduler.queue(track);
			}
			
			
			public void playlistLoaded(AudioPlaylist playlist) {
				//
			}
			
			public void noMatches() {
				
			}
			
			public void loadFailed(FriendlyException exception) {
				
			}
			
		});
		
		
	}
	
	public static PlayerManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PlayerManager(); 
		}
		
		return INSTANCE; 
	}
	
}
