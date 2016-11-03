import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Elevator {

    private int initialPos;

    private int numOfFloors;

    private int currentFloor;

    private Direction direction = Direction.IDLE;

    private Queue<Passenger> requestQueue = new ConcurrentLinkedQueue<>();

    private TreeSet<Passenger> entrySet;

    private TreeSet<Passenger> exitSet;

    public int getInitialPos() {
        return initialPos;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Queue<Passenger> getRequestQueue() {
        return requestQueue;
    }

    public TreeSet<Passenger> getExitSet() {
        return exitSet;
    }

    public TreeSet<Passenger> getEntrySet() {
        return entrySet;
    }

    public Elevator() {
        this(20, 1);
    }

    public Elevator(int numOfFloors) {
        this(numOfFloors, 1);
    }

    public Elevator(int numOfFloors, int initialPos) {
        this.numOfFloors = numOfFloors;
        this.initialPos = initialPos;
        initializeEntryAndExitSets();
    }

    private void initializeEntryAndExitSets() {
        entrySet = new TreeSet<>((Passenger p1, Passenger p2) -> p1.getFrom() - p2.getFrom());
        exitSet = new TreeSet<>((Passenger p1, Passenger p2) -> p1.getTo() - p2.getTo());
    }

    public void move() {
        if (direction.equals(Direction.UP)) {
            currentFloor++;
        } else {
            currentFloor--;
        }
    }
}
