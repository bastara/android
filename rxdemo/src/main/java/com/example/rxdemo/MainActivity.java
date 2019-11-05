package com.example.rxdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "TAGG";

	@SuppressLint("CheckResult")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getString()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
//                .subscribe(str -> Log.i(TAG, str));
				.subscribe(getObserver());
	}

	Func1<String, Integer> stringToInteger = Integer::parseInt;

//    private Observable<String> getString() {
//        return Observable.create(emitter -> {
//            emitter.onNext("James");
//            emitter.onNext("Anthony");
//            emitter.onNext("Kristine");
//            emitter.onComplete();
//        });
//    }

//	private Observable<String> getString() {
////        Observable<String> observable = Observable.fromArray(new String[]{"one", "two", "three"});
//		Observable<String> observable = Observable.range(10, 4);
////        return Observable.just(new String[]{"one", "two", "three"});
//		return observable;
//	}

	private Observable<Integer> getString() {
		return Observable.range(10, 4);
	}

//	private Observer<String> getObserver() {
//		return new Observer<String>() {
//
//			@Override
//			public void onSubscribe(Disposable d) {
//				Log.d(TAG, " onSubscribe : " + d.isDisposed());
//			}
//
//			@Override
//			public void onNext(String value) {
//				Log.d(TAG, " onNext : value : " + value);
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				Log.d(TAG, " onError : " + e.getMessage());
//			}
//
//			@Override
//			public void onComplete() {
//				Log.d(TAG, " onComplete");
//			}
//		};
//	}

	private Observer<Integer> getObserver() {
		return new Observer<Integer>() {

			@Override
			public void onSubscribe(Disposable d) {
				Log.d(TAG, " onSubscribe : " + d.isDisposed());
			}

			@Override
			public void onNext(Integer value) {
				Log.d(TAG, " onNext : value : " + value);
			}

			@Override
			public void onError(Throwable e) {
				Log.d(TAG, " onError : " + e.getMessage());
			}

			@Override
			public void onComplete() {
				Log.d(TAG, " onComplete");
			}
		};
	}
}

interface Func1<T, R> {

	R call(T t);
}