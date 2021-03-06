package com.engine.core.skybox;

import org.lwjgl.util.vector.Matrix4f;

import com.engine.core.Camera;
import com.engine.core.render.ShaderProgram;
import com.engine.core.tools.Tools;

public class SkyboxShader extends ShaderProgram{

   private static final String VERTEX_FILE = "res/shaders/skyboxShader.vert";
   private static final String FRAGMENT_FILE = "res/shaders/skyboxShader.frag";
    
   private int location_projectionMatrix;
   private int location_viewMatrix;
    
   public SkyboxShader() {
       super(VERTEX_FILE, FRAGMENT_FILE);
   }
    
   public void loadProjectionMatrix(Matrix4f matrix){
       super.loadMatrix(location_projectionMatrix, matrix);
   }

   public void loadViewMatrix(Camera camera){
       Matrix4f matrix = Tools.createViewMatrix(camera);
       matrix.m30 = 0;
       matrix.m31 = 0;
       matrix.m32 = 0;
       super.loadMatrix(location_viewMatrix, matrix);
   }
   
   protected void getAllUniformLocations() {
       location_projectionMatrix = super.getUniformLocation("projectionMatrix");
       location_viewMatrix = super.getUniformLocation("viewMatrix");
   }

   protected void bindAttributes() {
       super.bindAttribute(0, "position");
   }
}