package com.twu.refactoring;

public abstract class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    public abstract Direction turnRight();

    public abstract Direction turnLeft();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
