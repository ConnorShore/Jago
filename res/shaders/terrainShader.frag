#version 330 core

in vec2 fragmentUV;
in vec3 fragmentNormal;
in vec3 toLightVector;

out vec4 color;

uniform sampler2D backgroundTexture;
uniform sampler2D rTexture;
uniform sampler2D gTexture;
uniform sampler2D bTexture;
uniform sampler2D blendMap;

uniform vec3 lightColor;

void main(void)
{
	vec4 blendMapColor = texture(blendMap, fragmentUV);
	float backTextureAmount = 1 - (blendMapColor.r + blendMapColor.g + blendMapColor.b);
	
	vec2 tiledCoords = fragmentUV * 40.0;
	
	vec4 backgroundTextureColor = texture(backgroundTexture, tiledCoords) * backTextureAmount;
	vec4 rTextureColor = texture(rTexture, tiledCoords) * blendMapColor.r;
	vec4 gTextureColor = texture(gTexture, tiledCoords) * blendMapColor.g;
	vec4 bTextureColor = texture(bTexture, tiledCoords) * blendMapColor.b;
	
	vec4 totalColor = backgroundTextureColor + rTextureColor + gTextureColor + bTextureColor;
	
	vec3 unitNormal = normalize(fragmentNormal);
	vec3 unitLightNormal = normalize(toLightVector);
	
	float nDot1 = dot(unitNormal, unitLightNormal);
	float brightness = max(nDot1, 0.15);
	vec3 diffuse = brightness * lightColor;
	
	color = vec4(diffuse, 1.0) * totalColor;
}