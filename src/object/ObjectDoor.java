package object;

import javax.imageio.ImageIO;
import java.io.File;

public class ObjectDoor extends SuperObject {
    public ObjectDoor() {
        name = "Door";
        try {
            image = ImageIO.read(new File("res/object/door.png"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
