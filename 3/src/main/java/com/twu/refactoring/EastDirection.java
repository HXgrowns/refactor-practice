package com.twu.refactoring;

public class EastDirection extends Direction {
    public EastDirection() {
        super('E');
    }

    @Override
    public Direction turnRight() {
        return new SouthDirection();
    }

    @Override
    public Direction turnLeft() {
        return new NorthDirection();
    }
}
