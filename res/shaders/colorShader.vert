#version 400 core

in vec3 vertexPosition;

out vec3 fragmentColor;

void main(void)
{
	gl_Position = vec4(vertexPosition, 1.0);
	fragmentColor = vec3(vertexPosition.x + 0.5f, vertexPosition.y, vertexPosition.z + 0.25);
}