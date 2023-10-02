package com.useinet.model;

import java.util.List;

public class Producer {
    private List<Interval> min;
    private List<Interval> max;

    public Producer(List<Interval> min, List<Interval> max) {
        this.min = min;
        this.max = max;
    }

    public List<Interval> getMin() {
        return min;
    }

    public void setMin(List<Interval> min) {
        this.min = min;
    }

    public List<Interval> getMax() {
        return max;
    }

    public void setMax(List<Interval> max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Producer{" + "min=" + min + ", max=" + max + '}';
    }
}
