package org.example;

import java.util.concurrent.atomic.AtomicLong;

public class RandomAlg {
    private long a, c, m;
    private AtomicLong x;

    public RandomAlg() {
        this.a = 25214903917l;
        this.m = 2^48;
    }

    public RandomAlg withSeed(int c) {
        this.c = c;
        x = new AtomicLong(c);
        return this;
    }

    public int next() {
        return (int) ((a*x.getAndIncrement()+c) % m);
    }
}
