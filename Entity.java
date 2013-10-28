package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.geom.Vector3;

public abstract class Entity extends GameObject {
	public Vector3 velocity = new Vector3(0, 0, 0);
	
	//Audio
	protected AudioListener listener;
	protected List<AudioSource> sources = new ArrayList<AudioSource>();
	
	public Entity(Vector3 position) {
		super(position);
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
