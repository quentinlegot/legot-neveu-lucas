package fr.lnl.game.server.utils;

import java.util.Objects;

/**
 * Tuple associating storing two value of undefined type.<br>
 * Unlike Python, value in this Tuple can be modified (but the type of its new value need to be the same)
 * @param <A> first element of the tuple
 * @param <B> second element of the tuple
 */
public class Pair<A, B> {

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

    /**
     * Absolut useful when using HashMap or HashSet (or everything using hashCode to compare Objects
     * @return object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Pair[" + a + "," + b + ']';
    }
}
