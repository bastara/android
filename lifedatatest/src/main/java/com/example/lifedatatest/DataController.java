package com.example.lifedatatest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

class DataController {

	private MutableLiveData<String> liveData = new MutableLiveData<>();
	private MutableLiveData<Integer> liveData1 = new MutableLiveData<>();
	private static DataController INSTANCE;

	LiveData<String> getData() {
		return liveData;
	}

//	public static DataController getInstance() {
//		return INSTANCE;
//	}

	public static DataController getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new DataController();
		}
		return INSTANCE;
	}

	public void setValue() {
		liveData.setValue("SSSSSSSSS");
	}

	public void runCycle() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100000; i++) {
			liveData.setValue(String.valueOf(i));
		}

	}
}
