public class ElevatorController {

    private Elevator elevator;

    public Elevator getElevator() {
        return elevator;
    }

    public ElevatorController() {
        elevator = new Elevator();
    }

    public ElevatorController(int numOfFloors) {
        elevator = new Elevator(numOfFloors);
    }

    public void requestElevator(Passenger passenger) {
        validateRequest(passenger.getFrom(), passenger.getTo(), passenger.getDirection());
    }

    private void validateRequest(int from, int to, Direction direction) {
        if (from > elevator.getNumOfFloors() || to > elevator.getNumOfFloors()) {
            throw new RuntimeException("Invalid floor service requested");
        }
        if (direction.equals(Direction.UP) && from >= to) {
            throw new RuntimeException("For direction UP, request from floor A should be less than request to floor B");
        }
        if (direction.equals(Direction.DOWN) && from <= to) {
            throw new RuntimeException("For direction DOWN, request from floor A should be greater than request to floor B");
        }
    }
}
