package dhbw.karlsruhe.it.solar.core.graphics;


import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by Arga on 08.03.2015.
 */
public class AnnulusShader extends ShaderProgram{
    public static final String VERTEX_SHADER = "attribute vec2 a_position;	\n" +
            "attribute vec2 a_texCoord; \n" +

            "uniform mat4 u_projTrans; \n" +
            "uniform vec2 u_center; \n" +

            "varying vec2 v_texCoords; \n" +
            "varying vec2 v_position; \n" +

            "void main() \n" +
            "{ \n" +
            "	v_position = a_position + u_center; \n" +
            "   	v_texCoords = a_texCoord; \n" +
            "   	gl_Position = u_projTrans * vec4(v_position.xy, 0.0, 1.0); \n" +
            "} \n";
    public static final String FRAGMENT_SHADER = "#ifdef GL_ES \n" +
            "	#define LOWP lowp \n" +
            "	precision mediump float; \n" +
            "#else \n" +
            "	#define LOWP  \n" +
            "#endif \n" +

            "varying vec2 v_texCoords; \n" +
            "uniform sampler2D u_texture; \n" +

            "void main() \n" +
            "{ \n" +
            "  gl_FragColor = texture2D(u_texture, v_texCoords); \n" +
//            "  gl_FragColor = vec4(1,0,0,1); \n" +
            "} \n";

    public AnnulusShader() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
        if (isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + getLog());
    }
}
