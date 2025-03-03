package natdertale.flashlights.utils;

import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

public class OrientationUtils {

    public static Quaternionf getOrientation(Vec3d lookdir) {

        float angle = (float) Math.atan2(lookdir.getX(), lookdir.getZ());
        float angle2 = (float) Math.asin(lookdir.getY());;

        float x = (float) 0;
        float y = (float) -Math.toRadians(1 * Math.sin(angle/2));
        float z = (float) 0;
        float w = (float) (Math.toRadians(Math.cos(angle/2)));

        float x2 = (float) Math.toRadians(1* Math.sin(angle2/2));
        float y2 = (float) 0;
        float z2 = (float) 0;
        float w2 = (float) (Math.toRadians(Math.cos(angle2/2)));

        Quaternionf q = new Quaternionf(x, y, z, w);
        Quaternionf q2 = new Quaternionf(x2, y2, z2, w2);

        return q2.mul(q).normalize();
    }

}
