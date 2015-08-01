
**Elevator Control System Interview**

Classes: (JavaDocs are added in each class)
1) Elevator
2) Request
3) ElevatorControlSystem
4) ElevatorContants



Functions and Properties of Elevator Class:
<ol>
<li> elvatorID: Unique ID representing each elevator </li>
<li> Direction: direction of the elevator travel. Can be UP, DOWN or IDLE </li>
<li> requestsScheduled: Requests scheduled for the elevator (requests given inside the elevator to travel to the goal floor) </li>
<li> requestsToPickUp: Requests to Pick Up (requests given at the floor, level) </li>
<li> currentFloor: Current Floor of the elevator </li>
</ol>

Functions and Properties of Request Class:
<ol>
<li> currentFloor: Current Floor of the elevator </li>
<li> Direction: direction of the request. Can be UP or DOWN </li>
<li> goalFloor: Goal Floor is set if the request is placed inside the elevator for going to a particular floor </li>
<li> elevator: Elevator Object </li>
</ol>


Functions and Properties of ElevatorControlSystem class:
<ol>
<li> getElevatorStatus(int elevatorId): Returns the elevator object which contains the elevator's current floor, goalFloors, direction which represents the state </li>
<li> pickUp(int currentFloor, int direction): This function is used to place a pick up request on any floor. If direction is represented as 1(UP) and -1(DOWN) </li>  
<li> step(): time step through the elevator control system, for sumulation purpose </li> 
</ol>

**Scheduling Algorithm**

Given a pick up request, the algorithm is designed in the following way:
<ol>
<li>Pick an idle elevator located on the current floor of the passenger if present.</li>
<li>Pick an elevator that is closest to the request and is either idle or moving in the same direction. We both options are true, a moving elevator is picked for time efficiency</li>
<li>Pick the elevator with the lowest number of requests</li>
</ol>


TO DO:
1) Handle the maximum floors and maximum requests each elevator can accept
2) Write more test case scenario and stress test the application to see how the system behaves

**Build Instruction**

Project is build using maven
There is an application file, ElevatorControlSystemApp which can be used to testing purpose
