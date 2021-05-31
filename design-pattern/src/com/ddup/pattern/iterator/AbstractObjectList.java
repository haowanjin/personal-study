package com.ddup.pattern.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class AbstractObjectList {
    protected List<Object> objects;

    public AbstractObjectList(List objects) {
        Objects.requireNonNull(objects);
        this.objects = objects;
    }

    public void addObject(Object obj) {
        this.objects.add(obj);
    }

    public void removeObject(Object obj) {
        this.objects.remove(obj);
    }

    public List getObjects() {
        return this.objects;
    }

    //声明创建迭代器对象的抽象工厂方法  
    public abstract AbstractIterator createIterator();
}  