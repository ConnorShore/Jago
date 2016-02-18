package com.engine.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.engine.core.models.RawModel;

public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVao(float[] positions, float[] uvs, float[] normals, int[] indices) {
		int vaoID = createVAO();
		bindIndices(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, uvs);
		storeDataInAttributeList(2, 3, normals);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public int loadTexture(String fileName) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	public RawModel loadObjModel(String fileName) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("res/models/" + fileName));
		} catch (FileNotFoundException e) {
			Error.fatalError("Could not load model " + fileName);
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> uvs = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArray = null;
		float[] uvsArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try {
			while(true) {
				line = reader.readLine();
				String[] currentLine = line.split(" ");
				if(line.startsWith("v ")) {
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
							Float.parseFloat(currentLine[2]), 
							Float.parseFloat(currentLine[3]));
					vertices.add(vertex);
				} else if(line.startsWith("vt ")) {
					Vector2f uv = new Vector2f(Float.parseFloat(currentLine[1]), 
							Float.parseFloat(currentLine[2]));
					uvs.add(uv);
				} else if(line.startsWith("vn ")) {
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), 
							Float.parseFloat(currentLine[2]), 
							Float.parseFloat(currentLine[3]));
					normals.add(normal);
				} else if(line.startsWith("f ")) {
					uvsArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];
					break;
				}
			}
			
			while(line != null) {
				if(!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				processVertex(vertex1, indices, uvs, normals, uvsArray, normalsArray);
				processVertex(vertex2, indices, uvs, normals, uvsArray, normalsArray);
				processVertex(vertex3, indices, uvs, normals, uvsArray, normalsArray);
				line = reader.readLine();
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vertex : vertices) {
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		
		for(int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}
		
		return loadToVao(verticesArray, uvsArray, normalsArray, indicesArray);
	}
	
	private void processVertex(String[] vertexData, List<Integer> indices, 
			List<Vector2f> uvs, List<Vector3f> normals, 
			float[] uvArray, float[] normalArray) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);
		Vector2f currentTex = uvs.get(Integer.parseInt(vertexData[1])-1);
		uvArray[currentVertexPointer*2] = currentTex.x;
		uvArray[currentVertexPointer*2+1] = 1 - currentTex.y;
		Vector3f currentNormal = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalArray[currentVertexPointer*3] = currentNormal.x;
		normalArray[currentVertexPointer*3+1] = currentNormal.y;
		normalArray[currentVertexPointer*3+2] = currentNormal.z;
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void bindIndices(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private void storeDataInAttributeList(int attributeNumber, int dataSize, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dataSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void cleanUp() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		
		for(int tex : textures) {
			GL11.glDeleteTextures(tex);
		}
	}
}