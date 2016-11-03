import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

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
        elevator.getRequestQueue().add(passenger);
        elevator.getEntrySet().add(passenger);
        elevator.getExitSet().add(passenger);
    }

    private void validateRequest(int from, int to, Direction direction) {
        int minFloors = 1;
        int maxFloors = elevator.getNumOfFloors();
        if (from < minFloors || to < minFloors ||from > maxFloors || to > maxFloors) {
            throw new RuntimeException("Invalid floor service requested");
        }
        if (direction.equals(Direction.UP) && from >= to) {
            throw new RuntimeException("For direction UP, request from floor A should be less than request to floor B");
        }
        if (direction.equals(Direction.DOWN) && from <= to) {
            throw new RuntimeException("For direction DOWN, request from floor A should be greater than request to floor B");
        }
    }

    public void processRequest() {
        while (!elevator.getRequestQueue().isEmpty()) {
            Passenger passenger = elevator.getRequestQueue().iterator().next();
            int currentFloor = elevator.getCurrentFloor();
            int destinationFloor = isWaitingPassenger(passenger) ? passenger.getFrom() : passenger.getTo();
            elevator.setDirection(currentFloor < destinationFloor ? Direction.UP : Direction.DOWN);
            while (currentFloor != destinationFloor) {
                elevator.move();
                currentFloor = elevator.getCurrentFloor();
                if (currentFloor == destinationFloor) {
                    elevator.setDirection(passenger.getDirection());
                }
                process(elevator.getDirection(), currentFloor);
            }
        }
        elevator.setDirection(Direction.IDLE);
    }

    private void process(Direction direction, int floor) {
        exitPassengers(direction, floor).forEach(p -> {
            elevator.getExitSet().remove(p);
            elevator.getRequestQueue().remove(p);
            System.out.println(String.format("Passenger %d exits the elevator at floor %d", p.getId(), floor));
        });
        entryPassengers(direction, floor).forEach(p -> {
            elevator.getEntrySet().remove(p);
            System.out.println(String.format("Passenger %d enters the elevator at floor %d", p.getId(), floor));
        });
    }

    private List<Passenger> entryPassengers(Direction direction, int floor) {
        Iterator<Passenger> iterator = directionBasedIterator(direction, getElevator().getEntrySet());
        List<Passenger> entryPassengers = new ArrayList<>();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (isSameDirectionAndFloor(passenger, direction, passenger.getFrom(), floor)) {
                entryPassengers.add(passenger);
            }
        }
        return entryPassengers;
    }

    private List<Passenger> exitPassengers(Direction direction, int floor) {
        Iterator<Passenger> iterator = directionBasedIterator(direction, getElevator().getExitSet());
        List<Passenger> exitPassengers = new ArrayList<>();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (!isWaitingPassenger(passenger) && isSameDirectionAndFloor(passenger, direction, passenger.getTo(), floor)) {
                exitPassengers.add(passenger);
            }
        }
        return exitPassengers;
    }

    private boolean isSameDirectionAndFloor(Passenger passenger, Direction direction, int requestedFloor, int currentFloor) {
        return passenger.getDirection().equals(direction) && requestedFloor == currentFloor;
    }

    private boolean isWaitingPassenger(Passenger passenger) {
        return elevator.getEntrySet().contains(passenger);
    }

    private Iterator<Passenger> directionBasedIterator(Direction direction, TreeSet<Passenger> set) {
        return (direction.equals(Direction.UP)) ? set.iterator() : set.descendingIterator();
    }
}
