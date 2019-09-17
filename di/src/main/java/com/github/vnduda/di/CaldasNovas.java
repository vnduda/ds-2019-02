package com.github.vnduda.di;

import java.util.function.Supplier;

public class CaldasNovas implements Supplier<Leite> {
    @Override
    public Leite get() {
        return new Leite("CaldasNovas");
    }
}