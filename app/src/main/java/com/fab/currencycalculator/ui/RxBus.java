package com.fab.currencycalculator.ui;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class RxBus {

    public static final Object EMPTY = new Object();

    public RxBus () {}

    private BehaviorSubject<Object> bus = BehaviorSubject.create();

    public void send (Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toPublishObservable () {
        return bus.filter(v -> v != EMPTY);
    }

    public void removeSticky () {
        send(RxBus.EMPTY);
    }

}
