varying vec2 v_texCoords;

uniform sampler2D u_src;
uniform sampler2D u_srcDepth;
uniform sampler2D u_ref;

void main() {
    float srcDepth = texture2D(u_srcDepth, v_texCoords).r;
    float dstDepth = texture2D(u_ref, v_texCoords).r;

    if (srcDepth < dstDepth) discard;
    gl_FragColor = texture2D(u_src, v_texCoords);
    gl_FragDepth = srcDepth;
}
