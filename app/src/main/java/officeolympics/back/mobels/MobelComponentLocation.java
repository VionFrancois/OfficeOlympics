package officeolympics.back.mobels;

public class MobelComponentLocation {
    private int x;
    private int y;

    public MobelComponentLocation(int targetX, int targetY) {
        this.x = targetX;
        this.y = targetY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;

        MobelComponentLocation casted = (MobelComponentLocation) obj;
        return x == casted.x && y == casted.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
