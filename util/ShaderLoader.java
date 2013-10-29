package com.auditory.util;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderLoader {
	public static int loadShaderpair(String vertpath, String fragpath) {
		int shaderProgram = glCreateProgram();
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		StringBuilder vertsrc = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(vertpath));
			String line;
			while((line = in.readLine()) != null) {
				vertsrc.append(line + "\n");
			}
			in.close();
		} catch (FileNotFoundException fe) {
			Log.debug("Vertex shader could not be found");
		} catch (IOException ie) {
			Log.debug("Error while loading vertex shader");
		}
	}
}
