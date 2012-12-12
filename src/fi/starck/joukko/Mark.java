package fi.starck.joukko;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Create a visible selection mark.
 *
 * @author Tuomas Starck
 */
class Mark extends Drawable {
    private float[] mark = new float[] {
           0,    0, 0.3f,
           0, 1.0f, 0.3f,
        0.1f, 0.1f, 0.3f,
        0.1f, 0.9f, 0.3f,

           0, 1.0f, 0.3f,
        1.0f, 1.0f, 0.3f,
        0.1f, 0.9f, 0.3f,
        0.9f, 0.9f, 0.3f,

        1.0f, 1.0f, 0.3f,
        1.0f,   0f, 0.3f,
        0.9f, 0.9f, 0.3f,
        0.9f, 0.1f, 0.3f,

        1.0f,   0f, 0.3f,
           0,    0, 0.3f,
        0.9f, 0.1f, 0.3f,
        0.1f, 0.1f, 0.3f
    };

    Mark(int file, int rank, int unit) {
        count = mark.length/3;

        float[] rgb = new float[count*4];

        for (int i=0; i<mark.length; i++) {
            mark[i] = mark[i++] * unit + unit*(file-4);
            mark[i] = mark[i++] * unit + unit*(-rank+4);
        }

        for (int i=0; i<rgb.length; i++) {
            rgb[i++] = 0.9f;
            rgb[i++] = 0.7f;
            rgb[i++] = 0.2f;
            rgb[i]   = 1.0f;
        }

        ByteBuffer b1 = ByteBuffer.allocateDirect(rgb.length*4);
        ByteBuffer b2 = ByteBuffer.allocateDirect(mark.length*4);

        b1.order(ByteOrder.nativeOrder());
        b2.order(ByteOrder.nativeOrder());

        colours  = b1.asFloatBuffer();
        vertices = b2.asFloatBuffer();

        colours.put(rgb);
        vertices.put(mark);

        colours.position(0);
        vertices.position(0);
    }
}
