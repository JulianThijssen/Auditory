package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.util.Vector3;

public abstract class Entity {
	public Vector3 position;
	
	//Audio
	public AudioListener listener;
	public List<AudioSource> sources = new ArrayList<AudioSource>();
	
	public void setPosition(float x, float y, float z) {
		position.x = x;
		position.y = y;
		position.z = z;
	}
	
	public void addAudioListener(AudioListener listener) {
		this.listener = listener;
	}
	
	public void addAudioSource(AudioSource source) {
		sources.add(source);
	}
}
