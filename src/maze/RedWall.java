package maze;

import java.awt.*;

public class RedWall extends Wall {

    @Override
    public void enter() {
        System.out.println("Ran into the huge RED WALL!!!");
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
