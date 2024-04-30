package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectHeartFull extends SuperObject {
    public ObjectHeartFull() {
        type = ObjectType.HEART_FULL;
        name = "Heart_full";
        collision = true;
        try {
            image = ImageIO.read(new File("res/object/heart_full.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
