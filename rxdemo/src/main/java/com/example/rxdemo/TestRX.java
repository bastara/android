package com.example.rxdemo;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;

public class TestRX {
    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                }
            }
    );
}
