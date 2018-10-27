package maze;

public class RedMazeFactory extends MazeFactory {


    @Override
    public Maze makeMaze() {
        return new Maze();
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
