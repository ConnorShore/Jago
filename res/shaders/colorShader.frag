#version 330 core

in vec2 fragmentUV;

out vec4 color;

uniform sampler2D textureSampler;

void main(void)
{
	color = texture(textureSampler, fragmentUV);
}