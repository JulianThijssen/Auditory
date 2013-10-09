package com.auditory.audio;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

import com.auditory.Entity;
import com.auditory.util.ResourceLoader;

public class AudioSource {
	public static final float DEFAULT_GAIN = 1f;
	public static final float DEFAULT_PITCH = 1f;
	
	private IntBuffer source = BufferUtils.createIntBuffer(1);
	private IntBuffer audio = BufferUtils.createIntBuffer(1);
	private FloatBuffer position = (FloatBuffer) BufferUtils.createFloatBuffer(3).clear();
	private FloatBuffer velocity = (FloatBuffer) BufferUtils.createFloatBuffer(3).clear();
	
	public AudioSource(Entity parent) {
		setPosition(parent.position.x, parent.position.y, parent.position.z);
		setVelocity(parent.velocity.x, parent.velocity.y, parent.velocity.z);
		
		AL10.alGenSources(source);
		
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, DEFAULT_GAIN);
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, DEFAULT_PITCH);
		AL10.alSource(source.get(0), AL10.AL_POSITION, position);
		AL10.alSource(source.get(0), AL10.AL_VELOCITY, velocity);
	}
	
	public AudioSource(Entity parent, String name) {
		this(parent);
		loadAudio(name);
	}
	
	public void setPosition(float x, float y, float z) {
		position.put(0, x);
		position.put(1, y);
		position.put(2, z);
		position.position(0);
		AL10.alSource(source.get(0), AL10.AL_POSITION, position);
	}
	
	public void setVelocity(float x, float y, float z) {
		velocity.put(0, x);
		velocity.put(1, y);
		velocity.put(2, z);
		AL10.alSource(source.get(0), AL10.AL_VELOCITY, velocity);
	}
	
	public void setGain(float gain) {
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, gain);
	}
	
	public void setPitch(float pitch) {
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, pitch);
	}
	
	public void loadAudio(String name) {
		AL10.alGenBuffers(audio);
		
		WaveData wav = ResourceLoader.getWave(name);
		if(wav == null) {
			System.out.println("Failed to load: " + name);
			return;
		}
		
		AL10.alBufferData(audio.get(0), wav.format, wav.data, wav.samplerate);
		wav.dispose();
		
		AL10.alSourcei(source.get(0), AL10.AL_BUFFER, audio.get(0));
	}
	
	public void playAudio(boolean loop) {
		if(loop) {
			AL10.alSourcei(source.get(0), AL10.AL_LOOPING, AL10.AL_TRUE);
		}
		AL10.alSourcePlay(source.get(0));
	}
	
	public boolean isPlaying() {
		int playing = AL10.alGetSourcei(source.get(0), AL10.AL_SOURCE_STATE);
		if(playing == AL10.AL_PLAYING) {
			return true;
		}
		return false;
	}
	
	public void stopAudio() {
		AL10.alSourceStop(source.get(0));
	}
	
	public void unloadAudio() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(audio);
	}
}
