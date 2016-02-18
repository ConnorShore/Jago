#version 330 core

in vec2 fragmentUV;
in vec3 fragmentNormal;
in vec3 toLightVector;

out vec4 color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;

void main(void)
{
	vec3 unitNormal = normalize(fragmentNormal);
	vec3 unitLightNormal = normalize(toLightVector);
	
	float nDot1 = dot(unitNormal, unitLightNormal);
	float brightness = max(nDot1, 0.15);
	vec3 diffuse = brightness * lightColor;
	
	color = vec4(diffuse, 1.0) * texture(textureSampler, fragmentUV);
}