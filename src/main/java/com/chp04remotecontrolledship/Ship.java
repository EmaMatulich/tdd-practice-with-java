package com.chp04remotecontrolledship;

public class Ship {

    private static final char FORWARD_COMMAND = 'f';
    private static final char BACKWARD_COMMAND = 'b';
    private static final char RIGHT_COMMAND = 'r';
    private static final char LEFT_COMMAND = 'l';
    private final Location location;
    private Planet planet;

    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public Location getLocation() {
        return location;
    }

    public Planet getPlanet() {
        return planet;
    }

    public boolean moveForward() {
        return location.forward(planet.getMax(), planet.getObstacles());
    }

    public boolean moveBackward() {
        return location.backward(planet.getMax(), planet.getObstacles());
    }

    public void turnRight() {
        location.turnRight();
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public String executeCommand(final String commands) {
        String result = "";
        for (char command : commands.toCharArray()) {
            switch (command) {
                case FORWARD_COMMAND:
                    result += this.moveForward() ? "O" : "X";
                    break;
                case BACKWARD_COMMAND:
                    result += this.moveBackward() ? "O" : "X";
                    break;
                case RIGHT_COMMAND:
                    this.turnRight();
                    break;
                case LEFT_COMMAND:
                    this.turnLeft();
                    break;
            }
        }
        return result;
    }

}
