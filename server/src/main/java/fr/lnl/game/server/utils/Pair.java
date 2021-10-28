package fr.lnl.game.server.utils;

import java.util.Objects;

public class Pair<A,B> {

    private A a;
    private B b;

    public Pair(A a, B b){
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return this.a;
    }

    public B getB() {
        return this.b;
    }


    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> point = (Pair<?, ?>) o;
        return Objects.equals(a, point.a) && Objects.equals(b, point.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Pair[" + a + "," + b + ']';
    }
}
