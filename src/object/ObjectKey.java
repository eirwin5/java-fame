package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectKey extends SuperObject {
    public ObjectKey() {
        type = ObjectType.KEY;
        name = "Key";
        try {
            image = ImageIO.read(new File("res/object/pompom1.png"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
