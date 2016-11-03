public class Passenger {

    private int id;

    private int from;

    private int to;

    private Direction direction;

    public int getId() {
        return id;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Direction getDirection() {
        return direction;
    }

    public Passenger(int id, int from, int to) {
        this(id, from, to, Direction.UP);
    }

    public Passenger(int id, int from, int to, Direction direction) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
