package com.ecs;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ecs.Elevator;
import com.ecs.ElevatorControlSystem;
import com.ecs.Elevator.Direction;

public class ElevatorControlSystemTest {

    Elevator elevator2;
    Elevator elevator1;
    ElevatorControlSystem elevatorControlSystem;

    @Before
    public void setUp() {
        elevator1 = new Elevator(1);
        elevator2 = new Elevator(2); 

        elevatorControlSystem = new ElevatorControlSystem();
    }

    @Test
    public void addElevatorTest() {
        elevatorControlSystem.addElevator(elevator2);
        elevatorControlSystem.addElevator(elevator1);
        Assert.assertEquals(2, elevatorControlSystem.getElevators().size());
    }

    @Test
    public void pickUpTest() {
        elevatorControlSystem.pickUp(3, 1);
        elevatorControlSystem.pickUp(4, -1);
        Assert.assertEquals(2, elevatorControlSystem.getRequests().size());
    }

    @Test
    public void stepTest() {

    	Elevator elevator3 = new Elevator(4);
    	elevatorControlSystem.addElevator(elevator1);
    	elevatorControlSystem.addElevator(elevator2);          
        elevatorControlSystem.addElevator(elevator3); 

        elevatorControlSystem.pickUp(6, 1); 

        elevatorControlSystem.step();

        Assert.assertEquals(Direction.IDLE, elevator1.getDirection());
        Assert.assertEquals(Direction.IDLE, elevator2.getDirection());
        Assert.assertEquals(Direction.UP, elevator3.getDirection());
        Assert.assertEquals(5, elevator3.getCurrentFloor());
        
        elevatorControlSystem.step();
        
        Assert.assertEquals(Direction.IDLE, elevator1.getDirection());
        Assert.assertEquals(Direction.IDLE, elevator2.getDirection());
        Assert.assertEquals(Direction.UP, elevator3.getDirection());
        Assert.assertEquals(6, elevator3.getCurrentFloor());


  

    }

}
