package com.ecs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Elevator class holds the properties of the elevator and
 * is used by the Request and ElevatorControlSystem classes
 * @author sankarbalamanoharan
 *
 */
public class Elevator
{

	public static enum Direction
	{
		UP, IDLE, DOWN
	};

	private int elevatorID;
	private static AtomicInteger nextId = new AtomicInteger();
	private Direction direction;
	private List<Request> requestsScheduled;
	private List<Request> requestsToPickUp;
	private int currentFloor;

	public Elevator(int currentFloor)
	{
		this.elevatorID = nextId.incrementAndGet();
		this.currentFloor = currentFloor;
		direction = Direction.IDLE;
		requestsScheduled = new ArrayList<Request>();
		requestsToPickUp = new ArrayList<Request>();
	}

	public Elevator()
	{
		this.elevatorID = nextId.incrementAndGet();
		this.currentFloor = calculateStartFloor();
		direction = Direction.IDLE;
		requestsScheduled = new ArrayList<Request>();
		requestsToPickUp = new ArrayList<Request>();
	}

	public void addRequest(Request request)
	{
		if (request.getCurrentFloor() != currentFloor)
		{
			schedule(request);
		}
		else
		{
			requestsScheduled.add(request);
			request.setScheduled(true);
			request.setCurrentElevator(this);
			this.direction = request.getDirection();
		}
	}

	public void removeRequest(Request request)
	{
		if (request.getGoalFloor() == currentFloor)
		{
			requestsScheduled.remove(request);
			request.setScheduled(false);
			request.setCurrentElevator(null);
		}

		else if (request.getCurrentFloor() == currentFloor)
		{
			requestsScheduled.remove(request);
			request.setScheduled(false);
			request.setCurrentElevator(null);
		}
	}

	public void schedule(Request request)
	{
		requestsToPickUp.add(request);
		request.setScheduled(true);
		request.setCurrentElevator(this);
		if (direction == Direction.IDLE)
		{
			direction = (this.currentFloor > request.getCurrentFloor()) ? Direction.DOWN
			        : Direction.UP;
		}
	}

	public int calculateStartFloor()
	{
		int floor = ElevatorConstants.START_FLOOR
		        + (int) (Math.random() * (ElevatorConstants.LAST_FLOOR
		                - ElevatorConstants.START_FLOOR + 1));
		return floor;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void incrementFloor(int step)
	{
		currentFloor += step;
	}

	public void decrementFloor(int step)
	{
		currentFloor -= step;
	}

	public int getCurrentFloor()
	{
		return currentFloor;
	}

	public int getElevatorID()
	{
		return elevatorID;
	}

	public void setCurrentFloor(int floor)
	{
		currentFloor = floor;
	}

	public void setElevatorID(int id)
	{
		elevatorID = id;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public List<Request> getRequests()
	{
		return requestsScheduled;
	}

	public List<Request> getScheduledRequests()
	{
		return requestsToPickUp;
	}

}
