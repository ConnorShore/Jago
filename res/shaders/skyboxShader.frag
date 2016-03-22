#version 330 core

in vec3 textureCoords;
out vec4 color;

uniform samplerCube cubeMap;

void main(void)
{
	color = texture(cubeMap, textureCoords);
}