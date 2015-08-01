package com.ecs;

import com.ecs.Elevator.Direction;

/**
 * Request class is used to hold and process the incoming
 * elevator requests
 * @author sankarbalamanoharan
 *
 */
public class Request {

    private int goalFloor;
    private int currentFloor;
    private boolean scheduled;
    private Direction direction;
    private Elevator elevator;

    public Request(int currentFloor, int goalFloor) {
        this.goalFloor = goalFloor;
        this.currentFloor = currentFloor;
		direction = (currentFloor < goalFloor) ? Direction.UP : Direction.DOWN;
        this.scheduled = false;
        elevator = null;
    }
    
    public Request(int currentFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.scheduled = false;
        elevator = null;
    }
    
    public void getOn(Elevator elevator) {
        if (elevator.getCurrentFloor() == currentFloor) {
            elevator.addRequest(this);
            elevator.getScheduledRequests().remove(this);
            if(elevator.getDirection() == Direction.IDLE) {
                elevator.setDirection(direction);
            }
            this.elevator = elevator;
            scheduled = true;
        } else {
            elevator.schedule(this);
            scheduled = true;
        }
    }

    public int getGoalFloor() {
        return goalFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Elevator getCurrentElevator() {
        return elevator;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean value) {
        scheduled = value;
    }

    public void setCurrentElevator(Elevator elevator) {
        this.elevator = elevator;
    }

}
