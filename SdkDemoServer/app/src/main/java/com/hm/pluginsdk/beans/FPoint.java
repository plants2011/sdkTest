package com.hm.pluginsdk.beans;

public class FPoint {
    public float x;
    public float y;

    public FPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public FPoint() {
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FPoint) {
            FPoint point = (FPoint) o;
            return x == point.x && y == point.y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "( x = " + x + " , y = " + y + ")";
    }

}
