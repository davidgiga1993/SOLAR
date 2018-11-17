package dhbw.karlsruhe.it.solar.core.graphics;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * To reduce floating point errors this shader first subtracts the camera's real position from vertex coordinates
 * before multiplying them with given transformation matrix.
 *
 * This requires the camera's position to be set to zero first.
 */
public class OffsetShader extends ShaderProgram {

    private static final String VERTEX_SHADER = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "uniform vec4 u_cameraOffset;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "varying vec4 v_temp;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "   v_color.a = v_color.a * (255.0/254.0);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   v_temp = " + ShaderProgram.POSITION_ATTRIBUTE + "- u_cameraOffset;\n"
            + "   gl_Position =  u_projTrans * v_temp;\n" //(" + ShaderProgram.POSITION_ATTRIBUTE + " - (u_cameraOffset / 2));\n"
            + "}\n";
    private static final String FRAGMENT_SHADER = "#ifdef GL_ES\n"
            + "#define LOWP lowp\n"
            + "precision mediump float;\n"
            + "#else\n"
            + "#define LOWP \n"
            + "#endif\n"
            + "varying LOWP vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "uniform sampler2D u_texture;\n"
            + "void main()\n"//
            + "{\n"
            + "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n"
            + "}";

    public OffsetShader() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
        if (!isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + getLog());
        }
    }

}
