package com.ecs;

import java.util.ArrayList;
import java.util.List;

import com.ecs.Elevator.Direction;

/**
 * ElevatorControlSytem is the main driver class which 
 * schedules the request for elevators 
 * @author sankarbalamanoharan
 *
 */
public class ElevatorControlSystem
{

	List<Elevator> elevators;
	List<Request> requests;

	public ElevatorControlSystem()
	{
		elevators = new ArrayList<Elevator>();
		requests = new ArrayList<Request>();
	}

	public ElevatorControlSystem(List<Elevator> elevators,
	        List<Request> requests)
	{
		this.elevators = elevators;
		this.requests = requests;
	}

	public void pickUp(int currentFloor, int direction)
	{
		Direction dir = null;
		if (direction == 1)
		{
			dir = Direction.UP;
		}
		else
		{
			dir = Direction.DOWN;
		}
		Request request = new Request(currentFloor, dir);
		requests.add(request);
	}
	
	public Elevator getElevatorStatus(int elevatorID)
	{
		for (Elevator elevator: elevators)
		{
			if(elevator.getElevatorID() == elevatorID)
			{
				return elevator;
			}
		}
		return null;		
	}

	public void step()
	{
		for (Request request : requests)
		{
			if (!request.isScheduled())
			{
				schedule(request);
			}
		}

		for (Elevator elevator : elevators)
		{
			update(elevator);
		}
	}

	public void addElevator(Elevator elevator)
	{
		elevators.add(elevator);
	}

	public void pickUpRequest(Request request)
	{
		requests.add(request);
	}

	public void updateDirection(Elevator elevator, Direction direction)
	{
		elevator.setDirection(direction);
	}

	public void updateCurrentFloor(Elevator elevator, int floor)
	{
		elevator.setCurrentFloor(floor);
	}

	public List<Elevator> getElevators()
	{
		return elevators;
	}

	public List<Request> getRequests()
	{
		return requests;
	}

	private void schedule(Request request)
	{

		Elevator minLoadElevator = elevators.get(0);
		Elevator closestElevatorMovingToFloor = null;
		Elevator closestStandingElevator = null;

		for (Elevator elevator : elevators)
		{

			if (elevator.getRequests().size()
			        + elevator.getScheduledRequests().size() < minLoadElevator
			        .getRequests().size()
			        + minLoadElevator.getScheduledRequests().size())
			{
				minLoadElevator = elevator;
			}

			int floorDifference = request.getCurrentFloor()
			        - elevator.getCurrentFloor();

			if (elevator.getDirection() == Direction.IDLE)
			{
				closestStandingElevator = findCloser(closestStandingElevator,
				        elevator, request);
			}

			if (elevator.getDirection() == request.getDirection())
			{

				int multiplier = (request.getDirection() == Direction.UP) ? 1
				        : -1;
				int floorDiff = floorDifference * multiplier;

				if (floorDiff > 0)
				{
					closestElevatorMovingToFloor = findCloser(
					        closestElevatorMovingToFloor, elevator, request);
				}
			}

			if (elevator.getCurrentFloor() == request.getCurrentFloor())
			{
				if (elevator.getDirection() == Direction.IDLE)
				{
					request.getOn(elevator);
				}
				else if (elevator == request.getCurrentElevator()
				        && request.isScheduled())
				{
					request.getOn(elevator);
				}
				else if (elevator.getDirection() == request.getDirection())
				{
					request.getOn(elevator);
				}
			}
		}

		if (!request.isScheduled())
		{
			if (closestElevatorMovingToFloor == null
			        && closestStandingElevator == null)
			{
				minLoadElevator.schedule(request);
			}
			else if (closestElevatorMovingToFloor != null
			        && closestStandingElevator == null)
			{
				closestElevatorMovingToFloor.schedule(request);
			}
			else if (closestElevatorMovingToFloor == null
			        && closestStandingElevator != null)
			{
				closestStandingElevator.schedule(request);
			}
			else
			{
				findCloser(closestElevatorMovingToFloor,
				        closestStandingElevator, request).schedule(request);
			}
		}

	}

	private void update(Elevator elevator)
	{

		List<Request> requestScheduledElevator = new ArrayList<Request>(
		        elevator.getRequests());

		for (Request request : requestScheduledElevator)
		{
			if (request.getGoalFloor() == elevator.getCurrentFloor())
			{
				elevator.removeRequest(request);
				requests.remove(request);
			}

			else if (request.getCurrentFloor() == elevator.getCurrentFloor())
			{
				elevator.removeRequest(request);
				requests.remove(request);
			}

		}

		if (elevator.getRequests().size() == 0
		        && elevator.getScheduledRequests().size() == 0)
		{
			elevator.setDirection(Direction.IDLE);
		}

		if (elevator.getDirection() == Direction.UP)
		{
			elevator.incrementFloor(1);
		}
		else if (elevator.getDirection() == Direction.DOWN)
		{
			elevator.decrementFloor(1);
		}

	}

	private Elevator findCloser(Elevator min, Elevator elev, Request request)
	{

		if (min == null)
		{
			return elev;
		}
		else
		{
			int floorDiff0 = Math.abs(min.getCurrentFloor()
			        - request.getCurrentFloor());
			int floorDiff1 = Math.abs(elev.getCurrentFloor()
			        - request.getCurrentFloor());
			if (floorDiff0 > floorDiff1)
			{
				return elev;
			}
			else
			{
				return min;
			}

		}

	}

}
