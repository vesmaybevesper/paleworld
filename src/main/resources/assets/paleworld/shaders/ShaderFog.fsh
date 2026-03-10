#version 330 compatibility

uniform sampler2D colortex0;
uniform sampler2D depthtex0;

uniform mat4 gbufferProjectionInverse;

in vec2 texcoord;

vec3 project(mat4 projectionMatrix, vec3 pos){
	vec4 homPos = projectionMatrix * vec4(pos, 1.0);
	return homPos.xyz/ homPos.w;
}

layout(location = 0) out vec4 color;

void main(){
	color = texture(colortex0, texcoord);
	//color.rgb = mix();

	float depth = texture(depthtex0, texcoord).r;
	if(depth == 1.0){
		return;
	}

	vec3 NDCPos = vec3(texcoord.xy, depth) * 2.0 - 1.0;
	vec3 viewPos = project(gbufferProjectionInverse, NDCPos);
}
