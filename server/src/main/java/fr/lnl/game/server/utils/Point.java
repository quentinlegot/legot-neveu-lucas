package fr.unicaen.utils;

import java.util.Objects;

public class Point<X,Y> {

    private X x;
    private Y y;
    public Point(X x, Y y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        this(null, null);
    }

    public X getX() {
        return this.x;
    }

    public Y getY() {
        return y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point<?, ?> point = (Point<?, ?>) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
