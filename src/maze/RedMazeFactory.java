package maze;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class RedMazeFactory extends MazeFactory {


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
        return new RedWall();
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    @Override
    public Room makeRoom(int number) {
        return new RedRoom(number);
    }
}
