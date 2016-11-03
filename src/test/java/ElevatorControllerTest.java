import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElevatorControllerTest {

    private ElevatorController elevatorController;

    @Test
    public void createElevatorWithDefaults() {
        elevatorController = new ElevatorController();
        Elevator elevator = elevatorController.getElevator();
        assertEquals(1, elevator.getInitialPos());
        assertEquals(20, elevator.getNumOfFloors());
        assertEquals(Direction.IDLE, elevator.getDirection());
    }

    @Test
    public void createElevatorWithSpecifiedNumberOfFloors() {
        elevatorController = new ElevatorController(50);
        Elevator elevator = elevatorController.getElevator();
        assertEquals(1, elevator.getInitialPos());
        assertEquals(50, elevator.getNumOfFloors());
    }

    @Test(expected = RuntimeException.class)
    public void throwAnExceptionForAnInvalidRequest() {
        elevatorController = new ElevatorController(50);
        Passenger badPassenger = new Passenger(1, 20, 100);
        elevatorController.requestElevator(badPassenger);
    }

    @Test
    public void singlePassengerRequestForDirectionUp() {
        elevatorController = new ElevatorController(30);
        Passenger passenger = new Passenger(1, 10, 20, Direction.UP);
        elevatorController.requestElevator(passenger);
        elevatorController.processRequest();
    }

    @Test
    public void singlePassengerRequestForDirectionDown() {
        elevatorController = new ElevatorController(10);
        Passenger passenger = new Passenger(1, 10, 8, Direction.DOWN);
        elevatorController.requestElevator(passenger);
        elevatorController.processRequest();
    }

    @Test
    public void multiplePassengersRequestForSameDirections() {
        elevatorController = new ElevatorController(50);
        Passenger passenger1 = new Passenger(1, 10, 20, Direction.UP);
        Passenger passenger2 = new Passenger(2, 8, 10, Direction.UP);
        elevatorController.requestElevator(passenger1);
        elevatorController.requestElevator(passenger2);
        elevatorController.processRequest();
        Elevator elevator = elevatorController.getElevator();
        assertTrue(elevator.getRequestQueue().isEmpty());
        assertTrue(elevator.getEntrySet().isEmpty());
        assertTrue(elevator.getExitSet().isEmpty());
    }

    @Test
    public void multiplePassengersRequestForDifferentDirections() {
        elevatorController = new ElevatorController(60);
        Passenger passenger1 = new Passenger(1, 20, 40, Direction.UP);
        Passenger passenger2 = new Passenger(2, 10, 30, Direction.UP);
        Passenger passenger3 = new Passenger(3, 5, 1, Direction.DOWN);
        Passenger passenger4 = new Passenger(4, 8, 4, Direction.DOWN);
        elevatorController.requestElevator(passenger1);
        elevatorController.requestElevator(passenger2);
        elevatorController.requestElevator(passenger3);
        elevatorController.requestElevator(passenger4);
        elevatorController.processRequest();
        Elevator elevator = elevatorController.getElevator();
        assertEquals(Direction.IDLE, elevator.getDirection());
        assertTrue(elevator.getEntrySet().isEmpty());
        assertTrue(elevator.getExitSet().isEmpty());
    }
}
