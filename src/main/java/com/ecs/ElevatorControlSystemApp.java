package com.ecs;

/**
 * Elevator Control System application is used in
 * testing the Elevator Control System
 * @author sankarbalamanoharan
 *
 */
public class ElevatorControlSystemApp
{
	public static void main(String args[])
	{
		System.out.println("Elevator Control System");
		Elevator elevator1;
		Elevator elevator2;
		Elevator elevator3;

		ElevatorControlSystem elevatorControlSystem;

		elevator1 = new Elevator(1);
		elevator2 = new Elevator(2);
		elevator3 = new Elevator(3);

		elevatorControlSystem = new ElevatorControlSystem();
		elevatorControlSystem.addElevator(elevator1);
		elevatorControlSystem.addElevator(elevator2);
		elevatorControlSystem.addElevator(elevator3);

		System.out.println("Number of elevators:"
		        + elevatorControlSystem.getElevators().size());

		elevatorControlSystem.pickUp(3, 1);
		System.out.println("Elevator 1 floor: " + elevator1.getCurrentFloor());
		System.out.println("Elevator 2 floor: " + elevator2.getCurrentFloor());
		System.out.println("Elevator 3 floor: " + elevator3.getCurrentFloor());

		elevatorControlSystem.step();
		System.out.println("Elevator 1 floor: " + elevator1.getCurrentFloor());
		System.out.println("Elevator 2 floor: " + elevator2.getCurrentFloor());
		System.out.println("Elevator 3 floor: " + elevator3.getCurrentFloor());

		elevatorControlSystem.step();
		System.out.println("Elevator 1 floor: " + elevator1.getCurrentFloor());
		System.out.println("Elevator 2 floor: " + elevator2.getCurrentFloor());
		System.out.println("Elevator 3 floor: " + elevator3.getCurrentFloor());

		elevatorControlSystem.step();
		System.out.println("Elevator 1 floor: " + elevator1.getCurrentFloor());
		System.out.println("Elevator 2 floor: " + elevator2.getCurrentFloor());
		System.out.println("Elevator 3 floor: " + elevator3.getCurrentFloor());

	}

}
