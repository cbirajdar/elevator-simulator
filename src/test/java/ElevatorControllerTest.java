import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
