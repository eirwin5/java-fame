package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectMegaphone extends SuperObject {
    public ObjectMegaphone() {
        type = ObjectType.MEGAPHONE;
        name = "Megaphone";
        collision = true;
        try {
            image = ImageIO.read(new File("res/object/megaphone.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
