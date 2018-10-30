/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.io.FileNotFoundException;

/**
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class MazeGameAbstractFactory {
    /**
     * Creates a small maze.
     */
    public Maze createMaze(MazeFactory inFactory) {


        Maze maze = new Maze();


        Room room0 = inFactory.makeRoom(0);
        Room room1 = inFactory.makeRoom(1);
        Door door0 =inFactory.makeDoor(room0, room1);
        maze.addRoom(room0);
        maze.addRoom(room1);
        room0.setSide(Direction.North, inFactory.makeWall());
        room0.setSide(Direction.East, door0);
        room0.setSide(Direction.South, inFactory.makeWall());
        room0.setSide(Direction.West,inFactory.makeWall());
        room1.setSide(Direction.North,inFactory.makeWall());
        room1.setSide(Direction.East, inFactory.makeWall());
        room1.setSide(Direction.South, inFactory.makeWall());
        room1.setSide(Direction.West, door0);

        maze.setCurrentRoom(0);

        System.out.println("The maze does not have any rooms yet!");
        return maze;


    }




    public static void main(String[] args) throws FileNotFoundException {

        Maze maze = null;
        MazeFactory inFactory = null;
        MazeGameAbstractFactory mgaf = new MazeGameAbstractFactory();
        String filePath;
        String mazeColor;


        //check if the argument has been passed to the program
        //call makeMaze() if argument is passed
        //else call createMaze()

        //if only one argument is passed and that is a color or maze type
        if (args.length == 1) {

            //check for red
            if (args[0].equals("red")){
                inFactory = new RedMazeFactory();
                maze = mgaf.createMaze(inFactory);
            }
            //check for blue
            else if (args[0].equals("blue")){
                inFactory = new BlueMazeFactory();
                maze = mgaf.createMaze(inFactory);
            }
            //check for which maze type and create the basic maze
            else{
                filePath = args[0];
                System.out.println("The argument passed is " + filePath);
                inFactory = new BasicMazeFactory();
                try{
                    maze = inFactory.makeMaze(filePath);
                }catch (Exception e){
                    System.out.println("Please provide a valid file.\nExiting the system.");
                    System.exit(0);
                }

            }

            //if two arguments then check for which color and what maze type it is
        } else if(args.length == 2){

            mazeColor = args[0];
            filePath = args[1];

            System.out.println("The arguments passed is "  + mazeColor + " " + filePath);

            switch  (mazeColor){
                case "red":
                    inFactory = new RedMazeFactory();
                    break;
                case "blue":
                    inFactory = new BlueMazeFactory();
                    break;
                default:
                    System.out.println("Invalid File.\nExiting the program");
                    System.exit(0);
            }

            try {
                maze = inFactory.makeMaze(filePath);
            } catch (Exception e) {
                System.out.println("File not found");
                System.out.println("Exiting the program");
                System.exit(0);
            }
        }

        //else if only the maze type is provided then load the Basic Maze with the desired maze type
        else {
            inFactory = new BasicMazeFactory();
            maze = mgaf.createMaze(inFactory);
        }


        MazeViewer viewer = new MazeViewer(maze);
        viewer.run();
    }
}
