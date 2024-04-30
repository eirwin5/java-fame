package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectHeartBlank extends SuperObject {
    public ObjectHeartBlank() {
        type = ObjectType.HEART_BLANK;
        name = "Heart_blank";
        collision = true;
        try {
            image = ImageIO.read(new File("res/object/heart_blank.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
