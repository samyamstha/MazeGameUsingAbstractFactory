package maze;

public class BlueMazeFactory extends MazeFactory {


    @Override
    public Maze makeMaze() {
        return new Maze();
    }

    @Override
    public Wall makeWall() {
        return new BlueWall();
    }

    @Override
    public Door makeDoor(Room room1, Room room2) {
        return new BrownDoor(room1, room2);
    }

    @Override
    public Room makeRoom(int number) {
        return new GreenRoom(number);
    }
}
