package com.auditory.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.util.WaveData;

public class ResourceLoader {
	public static final String folder = "res/";
	
	public static WaveData getWave(String name) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(getPath(name)));
			WaveData wav = WaveData.create(in);
			if(wav != null) {
				return wav;
			}
		} catch(IOException e) {
			
		}
		return null;
	}
	
	public static String getPath(String name) {
		return folder + name;
	}
}
