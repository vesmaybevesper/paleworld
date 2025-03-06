#version 460

uniform float fogStart;
uniform float fogEnd;
uniform vec4 fogColor;

void main() {

}

vec4 applyFog(vec4 color, float distance){
        float fog = smoothstep(fogStart, fogEnd, distance);
    return mix(color, fogColor, fog);
}