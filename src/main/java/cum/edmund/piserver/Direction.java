package cum.edmund.piserver;

import java.util.StringJoiner;

/**
 * Direction for MarioShart to drive in
 */


public class Direction {
    /**
     * 1.0f is full forwards, -1.0f is full backwards
     */
    private float forwardsBackwards;

    /**
     * 1.0f is full right, -1.0f is full left
     */
    private float leftRight;

    public Direction() {
        forwardsBackwards = 0f;
        leftRight = 0f;
    }

    public float getForwardsBackwards() {
        return forwardsBackwards;
    }

    public void setForwardsBackwards(float forwardsBackwards) {
        this.forwardsBackwards = forwardsBackwards;
    }

    public float getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(float leftRight) {
        this.leftRight = leftRight;
    }

    @Override
    public String toString() {
        StringJoiner line = new StringJoiner("\n");
        line.add("Forwards: " + (forwardsBackwards > 0 ? forwardsBackwards : 0));
        line.add("Backwards: " + (forwardsBackwards < 0 ? Math.abs(forwardsBackwards) : 0));
        line.add("Left: " + (leftRight > 0 ? leftRight : 0));
        line.add("Right: " + (leftRight < 0 ? Math.abs(leftRight) : 0));
        return line.toString();

    }
}
