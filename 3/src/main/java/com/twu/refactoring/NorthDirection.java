package com.twu.refactoring;

public class NorthDirection extends Direction {
    public NorthDirection() {
        super('N');
    }

    @Override
    public Direction turnRight() {
        return new EastDirection();
    }

    @Override
    public Direction turnLeft() {
        return new WestDirection();
    }
}
