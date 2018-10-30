package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class MazeFactory {



    public Maze makeMaze(final String path) throws FileNotFoundException {
        Maze maze = new Maze();


        HashMap<String, String> roomMap = null;
        HashMap<String, String> doorMap = null;

        ArrayList<HashMap> roomList = new ArrayList<>();
        ArrayList<HashMap> doorList = new ArrayList<>();

        //open the file and start reading
        File file = new File(path);
        Scanner sc = null;

        sc = new Scanner(file);


        ArrayList<String> mazeInfo = new ArrayList<>(); //ArrayList to add the lines of the file
        ArrayList<Room> roomObjs = new ArrayList<>();  //ArrayList to add the Room objects
        ArrayList<Door> doorObjs = new ArrayList<>();   //ArrayList to add the Door objects

        //Run till the file has line in it


        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            //Only add the line if it is not blank
            if (line.length() > 0) {
                mazeInfo.add(line);
            }

        }


        //Split the line and add the attributes of room and doors to respective maps
        for (String info : mazeInfo) {

            String[] element = info.split(" ");

            if (element[0].equals("room")) {
                roomMap = new HashMap<>();
                roomMap.put("number", element[1]);
                roomMap.put("north", element[2]);
                roomMap.put("south", element[3]);
                roomMap.put("east", element[4]);
                roomMap.put("west", element[5]);
                roomList.add(roomMap);
            } else {
                doorMap = new HashMap<>();
                doorMap.put("name", element[1]);
                doorMap.put("room1", element[2]);
                doorMap.put("room2", element[3]);
                doorMap.put("openOrClose", element[4]);
                doorList.add(doorMap);
            }
        }


        System.out.println();

        //Instantiate Room objects and add them to the maze, and also to the ArrayList of the Room objects
        for (HashMap<String, String> rmap : roomList) {
            //System.out.println(rmap.get("number"));
            int roomNum = Integer.parseInt(rmap.get("number"));
            Room room = makeRoom(roomNum);
            roomObjs.add(room);
            maze.addRoom(room);
        }

        //Instantiate Door objects and add them to the ArrayList of Door objects
        for (HashMap<String, String> dmap : doorList) {
            String doorName = dmap.get("name");
            int room1 = Integer.parseInt(dmap.get("room1"));
            int room2 = Integer.parseInt(dmap.get("room2"));

            Door door = makeDoor(roomObjs.get(room1), roomObjs.get(room2));
            if (dmap.get("openOrClose").equals("close")) {
                door.setOpen(false);
            } else {
                door.setOpen(true);
            }
            doorObjs.add(door);

        }

        //Iterate through the rooms and add set the sides
        for (int i = 0; i < roomObjs.size(); i++) {

            decideCase(roomList, i, doorObjs, roomObjs, "north", Direction.North);
            decideCase(roomList, i, doorObjs, roomObjs, "south", Direction.South);
            decideCase(roomList, i, doorObjs, roomObjs, "east", Direction.East);
            decideCase(roomList, i, doorObjs, roomObjs, "west", Direction.West);
        }

        maze.setCurrentRoom(0);

        return maze;
    }

    //Function that decides what to set on the sides of the rooms
    public void decideCase(ArrayList<HashMap> roomList, int i, ArrayList<Door> doorObjs, ArrayList<Room> roomObjs, String direction, Direction dir) {

        String lookUp = roomList.get(i).get(direction).toString();

        //Add wall
        if (lookUp.equals("wall")) {
            roomObjs.get(i).setSide(dir, makeWall());

            //Add respective door
        } else if (lookUp.charAt(0) == 'd') {
            int doorNum = (Character.getNumericValue(lookUp.charAt(1)));
            roomObjs.get(i).setSide(dir, doorObjs.get(doorNum));

            //Add respective room
        } else {
            int roomNum = Integer.parseInt(lookUp);
            roomObjs.get(i).setSide(dir, roomObjs.get(roomNum));

        }

    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room room1, Room room2) {
        return new Door(room1, room2);
    }

    public Room makeRoom(int number) {
        return new Room(number);
    }


}
