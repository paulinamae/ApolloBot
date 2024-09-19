package me.paulinamae.applemusicbot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class AppleMusicBot {
	public static JDA jda; 
	
	//main method
	public static void main(String[] args) throws LoginException{
		JDABuilder jda = JDABuilder.createDefault("insert discord bot token"); 
		
		jda.setActivity(Activity.listening("!play"));
		
		jda.setStatus(OnlineStatus.ONLINE);
		
		jda.addEventListeners(new Commands());
		
		jda.build();
	}

}
