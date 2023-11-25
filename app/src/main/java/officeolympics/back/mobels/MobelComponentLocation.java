package officeolympics.back.mobels;

public class MobelComponentLocation {
    private double x;
    private double y;

    public MobelComponentLocation(double targetX, double targetY) {
        this.x = targetX;
        this.y = targetY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;

        MobelComponentLocation casted = (MobelComponentLocation) obj;
        // at most 10 pixels away in both direction
        return Math.abs(x - casted.x) <= 10 && Math.abs(y - casted.y) <= 10;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MobelComponentLocation{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
