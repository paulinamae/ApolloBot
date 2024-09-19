package me.paulinamae.applemusicbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {

	public AudioPlayer audioPlayer = null;
	public TrackScheduler scheduler = null; 
	private AudioPlayerSendHandler sendHandler = null; 
	
	public GuildMusicManager(AudioPlayerManager manager) {
		this.audioPlayer= manager.createPlayer();
		this.scheduler = new TrackScheduler(this.audioPlayer);
		this.audioPlayer.addListener(this.scheduler);
		this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
	}
	
	public AudioPlayerSendHandler getSendHandler() {
		return sendHandler; 
	}
}