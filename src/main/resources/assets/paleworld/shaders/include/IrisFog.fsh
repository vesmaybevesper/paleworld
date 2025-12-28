#version 330

uniform vec4 FogColor;
uniform vec4 Camera;

in VertexData{
    vec4 mColor;
    vec4 mVertex;
} VertexIn;

float getFog(float num){
    const float FogMax =  20.0;
    const float FogMin =  10.0;

    if (num >= FogMax) return 1;
    if (num <= FogMin) return 0;

    return 1 - (FogMax - num) / (FogMax - FogMin);
}

void main() {

    vec4 V = VertexIn.mVertex;
    float Distance = distance(Camera, V);
    float Alpha = getFog(Distance);

    gl_FragColor = mix(VertexIn.mColor, FogColor, Alpha);

}