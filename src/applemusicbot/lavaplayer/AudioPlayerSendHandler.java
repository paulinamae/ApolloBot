package me.paulinamae.applemusicbot.lavaplayer;

import java.nio.ByteBuffer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;

import net.dv8tion.jda.api.audio.AudioSendHandler;

public class AudioPlayerSendHandler implements AudioSendHandler {

	private AudioPlayer audioPlayer = null;
	private ByteBuffer buffer = null; 
	private MutableAudioFrame frame = new MutableAudioFrame();
	
	public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
		this.buffer = ByteBuffer.allocate(1024);
		this.frame = new MutableAudioFrame(); 
		this.frame.setBuffer(buffer);
	}
	
	@Override
	public boolean canProvide() {
		// TODO Auto-generated method stub
		return this.audioPlayer.provide(this.frame);
	}

	@Override
	public ByteBuffer provide20MsAudio() {
		// TODO Auto-generated method stub
		return this.buffer.flip();
	}
	
	@Override
	public boolean isOpus() {
		return true;
	}
	
}
