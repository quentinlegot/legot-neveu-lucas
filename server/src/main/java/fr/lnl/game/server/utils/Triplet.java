package fr.lnl.game.server.utils;

import java.util.Objects;

public class Triplet<A,B,C> {

    private A a;
    private B b;
    private C c;

    public Triplet(A a, B b, C c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triplet(){
        this(null, null, null);
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void setC(C c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> tuple = (Triplet<?, ?, ?>) o;
        return Objects.equals(a, tuple.a) && Objects.equals(b, tuple.b) && Objects.equals(c, tuple.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
