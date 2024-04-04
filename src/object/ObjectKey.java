package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectKey extends SuperObject {
    public ObjectKey() {
        type = ObjectType.KEY;
        name = "Key";
        try {
            image = ImageIO.read(new File("res/object/key.png"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
