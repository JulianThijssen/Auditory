package com.auditory;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

import com.auditory.components.MainPlayer;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;
import com.auditory.managers.EntityManager;
import com.auditory.util.IdentifierPool;
import com.auditory.util.Map;
import com.auditory.util.ShaderLoader;

public class World {
	public EntityManager entityManager = new EntityManager();
	public IdentifierPool idpool = new IdentifierPool();
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<System> systems = new ArrayList<System>();
	
	public Map<Transform> transforms = new Map<Transform>();
	public Map<Mesh>      meshes     = new Map<Mesh>();
	public Map<Velocity>  velocities = new Map<Velocity>();
	
	public Camera mainCamera = new Camera();
	public Input input = new Input(this);
	public int playerId = -1;
	
	public Matrix4f projectionMatrix, viewMatrix;
	public Matrix4f modelMatrix = new Matrix4f();
	public int projLoc, viewLoc, modelLoc;
	
	public int shaderProgram = ShaderLoader.loadShaders("res/shader.vert", "res/shader.frag");
	
	public Entity createEntity() {
		Entity e = new Entity(this, idpool.getId());
		return e;
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addComponent(Component c) {
		if(c instanceof Transform) {
			transforms.add(c.id, (Transform) c);
		} else if(c instanceof Mesh) {
			meshes.add(c.id, (Mesh) c);
		} else if(c instanceof Velocity) {
			velocities.add(c.id, (Velocity) c);
		} else if(c instanceof MainPlayer) {
			playerId = c.id;
		}
	}
	
	public void addSystem(System s) {
		systems.add(s);
		s.setWorld(this);
	}
	
	public void update() {
		glClearColor(0.0f, 0.0f, 0.2f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		projLoc = GL20.glGetUniformLocation(shaderProgram, "projectionMatrix");
		viewLoc = GL20.glGetUniformLocation(shaderProgram, "viewMatrix");
		modelLoc = GL20.glGetUniformLocation(shaderProgram, "modelMatrix");
		
		GL20.glUseProgram(shaderProgram);
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		modelMatrix.setIdentity();
		
		if(playerId != -1) {
			Entity player = entities.get(playerId);
			input.update(player);
			//mainCamera.setPosition(transforms.get(player).position);
		}
		
		
		mainCamera.update();
		
		projectionMatrix = mainCamera.getPerspectiveMatrix();
		viewMatrix = mainCamera.getViewMatrix();
		
		for(System s: systems) {
			for(Entity e: entities) {
				s.update(e);
			}
		}
		
		GL20.glUseProgram(0);
	}
	
	public void destroy() {
		for(Entity e: entities) {
			//TODO Destroy mesh lists
		}
	}
}
