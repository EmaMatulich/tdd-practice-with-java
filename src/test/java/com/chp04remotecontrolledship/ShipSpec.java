package com.chp04remotecontrolledship;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShipSpec {

    private Ship ship;
    private Location location;
    private Point max;
    private Planet planet;
    private List<Point> obstacles;

    @Before
    public void beforeTest(){
        max = new Point(50, 50);
        obstacles = new ArrayList<>();
        obstacles.add(new Point(21, 12));
        planet = new Planet(max, obstacles);
        location = new Location(new Point(21,13), Direction.NORTH);
        ship = new Ship(location, planet);
    }

    @Test
    public void whenInstanciatedThenLocationIsStored(){
        assertEquals(ship.getLocation(), location);
    }

    @Test
    public void whenMoveForwardThenForward(){
        Location expected = location.copy();
        expected.forward();
        ship.moveForward();
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenMoveBackwardThenBackward(){
        Location expected = location.copy();
        expected.backward();
        ship.moveBackward();
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenTurnRightThenRight(){
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenTurnLeftThenLeft(){
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenCommandFIsReceivedThenForward(){
        Location expected = location.copy();
        expected.forward();
        ship.executeCommand("f");
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenCommandBIsReceivedThenBackward(){
        Location expected = location.copy();
        expected.backward();
        ship.executeCommand("b");
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenCommandRIsReceivedThenRight(){
        Location expected = location.copy();
        expected.turnRight();
        ship.executeCommand("r");
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenCommandLIsReceivedThenRight(){
        Location expected = location.copy();
        expected.turnLeft();
        ship.executeCommand("l");
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenCommandThenAllAreExecuted(){
        Location expected = location.copy();
        expected.turnLeft();
        expected.turnRight();
        expected.forward();
        ship.executeCommand("lrf");
        assertEquals(expected, ship.getLocation());
    }

    @Test
    public void whenInstantiatedThenPlanetIsStored(){
        assertEquals(planet, ship.getPlanet());
    }

    @Test
    public void whenPassingEastBoundaryThenPositionisReset(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        ship.executeCommand("f");
        assertEquals(location.getX(), 1);
    }

    @Test
    public void whenPassingWestBoundaryThenPositionisReset(){
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        ship.executeCommand("b");
        assertEquals(location.getX(), 50);
    }

    @Test
    public void givenObstacleInFrontWhenMovingForwardThenCurrentPosition(){
        String result = ship.executeCommand("bbfff");
        assertEquals("OOOOX", result);
    }

}
