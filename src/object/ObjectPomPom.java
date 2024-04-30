package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectPomPom extends SuperObject {
    public ObjectPomPom() {
        type = ObjectType.POMPOM;
        name = "Pompoms";
        try {
            image = ImageIO.read(new File("res/object/pompom.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
