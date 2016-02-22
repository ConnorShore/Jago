#version 400 core

in vec3 vertexPosition;
in vec2 vertexUV;
in vec3 vertexNormal;

out vec2 fragmentUV;
out vec3 fragmentNormal;
out vec3 toLightVector;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

uniform vec3 lightPosition;

void main(void)
{
	vec4 worldPosition = transformationMatrix * vec4(vertexPosition, 1.0);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;
	
	fragmentUV = vertexUV;
	fragmentNormal = (transformationMatrix * vec4(vertexNormal, 0.0)).xyz;
	toLightVector = lightPosition - worldPosition.xyz;
}