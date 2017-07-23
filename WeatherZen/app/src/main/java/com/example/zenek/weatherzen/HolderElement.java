package com.example.zenek.weatherzen;

import android.support.annotation.LayoutRes;

public class HolderElement<T> {
    public T data;
    public int type;
    @LayoutRes
    public int res;

    public HolderElement(int type) {
        this.type = type;
    }

    public HolderElement(int type, T data) {
        this.data = data;
        this.type = type;
    }

    public HolderElement(int type, int res) {
        this.type = type;
        this.res = res;
    }

    public HolderElement(int type, int res, T data) {
        this.data = data;
        this.res = res;
        this.type = type;
    }
}
