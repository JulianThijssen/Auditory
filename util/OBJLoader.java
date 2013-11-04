package com.auditory.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.auditory.geom.Face;
import com.auditory.geom.Model;
import com.auditory.geom.Vector3;

public class OBJLoader {
	/**
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Model load(String filename) throws FileNotFoundException, IOException {
		Model model = new Model();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = in.readLine()) != null) {
			String[] segments = line.split(" ");
			String type = segments[0];
			if(type.equals("v")) {
				float x = Float.parseFloat(segments[1]);
				float y = Float.parseFloat(segments[2]);
				float z = Float.parseFloat(segments[3]);
				model.vertices.add(new Vector3(x, y, z));
			}
			if(type.equals("vn")) {
				float x = Float.parseFloat(segments[1]);
				float y = Float.parseFloat(segments[2]);
				float z = Float.parseFloat(segments[3]);
				model.normals.add(new Vector3(x, y, z));
			}
			if(type.equals("f")) {
				int[] vertices = new int[3];
				int[] normals = new int[3];
				for(int i = 1; i < 4; i++) {
					String[] elements = segments[i].split("/");
					vertices[i-1] = Integer.parseInt(elements[0]);
					normals[i-1] = Integer.parseInt(elements[2]);
				}
				model.faces.add(new Face(vertices, normals));
			}
		}
		
		in.close();
		return model;
	}
}
