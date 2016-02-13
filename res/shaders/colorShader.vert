#version 400 core

in vec3 vertexPosition;
in vec2 vertexUV;

out vec2 fragmentUV;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void)
{
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(vertexPosition, 1.0);
	
	fragmentUV = vertexUV;
}