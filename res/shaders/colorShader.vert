#version 400 core

in vec3 vertexPosition;
in vec2 vertexUV;

out vec2 fragmentUV;

void main(void)
{
	gl_Position = vec4(vertexPosition, 1.0);
	
	fragmentUV = vertexUV;
}