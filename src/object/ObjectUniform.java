package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectUniform extends SuperObject {
    public ObjectUniform() {
        name = "Uniform";
        type = ObjectType.UNIFORM;
        try {
            image = ImageIO.read(new File("res/object/uniform.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
