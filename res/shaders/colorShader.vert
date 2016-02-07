#version 400 core

in vec3 vertexPosition;
in vec2 vertexUVs;

out vec2 fragmentUVs;

void main(void)
{
	gl_Position = vec4(vertexPosition, 1.0);
	
	fragmentUVs = vertexUVs;
}