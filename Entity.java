package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.util.Vector3;

public abstract class Entity {
	public Vector3 position = new Vector3(0, 0, 0);
	public Vector3 velocity = new Vector3(0, 0, 0);
	public Vector3 forward = new Vector3(0, 0, -1);
	
	//Audio
	protected AudioListener listener;
	protected List<AudioSource> sources = new ArrayList<AudioSource>();
	
	public Entity(float x, float y, float z) {
		position = new Vector3(x, y, z);
	}
	
	public void addAudioListener(AudioListener listener) {
		this.listener = listener;
		this.listener.setPosition(position.x, position.y, position.z);
	}
	
	public void addAudioSource(AudioSource source) {
		sources.add(source);
	}
	
	public AudioSource getAudioSource(int index) {
		return sources.get(index);
	}
}
