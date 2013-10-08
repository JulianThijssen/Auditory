package com.auditory.util;

import org.lwjgl.util.WaveData;

public class ResourceLoader {
	public static final String folder = "res/";
	
	public WaveData getWave(String name) {
		WaveData wav = WaveData.create(folder + name);
		if(wav != null) {
			return wav;
		}
		return null;
	}
}
