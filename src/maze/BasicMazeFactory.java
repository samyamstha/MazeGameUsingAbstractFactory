package maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class BasicMazeFactory extends MazeFactory{



    @Override
    public Maze makeMaze(String path) throws FileNotFoundException {
        return super.makeMaze(path);
    }

    @Override
    public void decideCase(ArrayList<HashMap> roomList, int i, ArrayList<Door> doorObjs, ArrayList<Room> roomObjs, String direction, Direction dir) {
        super.decideCase(roomList, i, doorObjs, roomObjs, direction, dir);
    }

    @Override
    public Wall makeWall() {
        return super.makeWall();
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return super.makeDoor(room1, room2);
    }

    @Override
    public Room makeRoom(int number) {
        return super.makeRoom(number);
    }
}
