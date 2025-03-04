#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 InSize;
uniform vec2 OutSize;

void main() {
    vec2 texCoord = gl_FragCoord.xy / OutSize;

    float aberrationStrength = 0.015;

    vec3 redShifted = texture2D(DiffuseSampler, texCoord + vec2(aberrationStrength, 0.0)).rgb;
    vec3 center = texture2D(DiffuseSampler, texCoord).rgb;
    vec3 blueShifted = texture2D(DiffuseSampler, texCoord - vec2(aberrationStrength, 0.0)).rgb;

    float luminance = dot(center, vec3(0.299, 0.587, 0.114));
    vec3 bwCenter = vec3(luminance);

    vec3 redDiff = abs(redShifted - center);
    vec3 blueDiff = abs(blueShifted - center);

    float redEdgeStrength = clamp(dot(redDiff, vec3(1.0)) * 2.0, 0.0, 1.0);
    float blueEdgeStrength = clamp(dot(blueDiff, vec3(1.0)) * 2.0, 0.0, 1.0);

    vec3 coloredEdges = vec3(0.0);
    coloredEdges.r = redShifted.r * redEdgeStrength;
    coloredEdges.b = blueShifted.b * blueEdgeStrength;

    vec3 finalColor = mix(bwCenter, bwCenter + coloredEdges, 0.8);
    gl_FragColor = vec4(finalColor, 1.0);
}