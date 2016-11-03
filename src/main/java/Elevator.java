public class Elevator {

    private int initialPos;

    private int numOfFloors;

    private Direction direction = Direction.IDLE;

    public int getInitialPos() {
        return initialPos;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public Direction getDirection() {
        return direction;
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
    }
}
