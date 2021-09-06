package ru.projectx.clicker.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfinityList<E> {
    private final List<E> objects = new ArrayList<>();
    private int index = -1;

    public InfinityList(E[] list) {
        if (list.length == 0) throw new RuntimeException("List cannot be empty");
        Collections.addAll(this.objects, list);
    }

    public InfinityList(List<E> list) {
        this((E[]) list.toArray());
    }

    public InfinityList() {}

    public E getNext() {
        this.index = this.index >= this.objects.size() - 1 ? 0 : this.index + 1;
        return this.objects.get(this.index);
    }

    public E get(int i) {
        this.index = i;
        return this.objects.get(i);
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void add(E object) {
        this.objects.add(object);
    }
}
