package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectBow extends SuperObject {
    public ObjectBow() {
        type = ObjectType.BOW;
        name = "Bow";
        collision = true;
        try {
            image = ImageIO.read(new File("res/object/bow.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
