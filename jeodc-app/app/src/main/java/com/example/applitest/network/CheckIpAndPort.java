package com.example.applitest.network;

import android.telephony.mbms.StreamingServiceInfo;

import com.example.applitest.common.ListenerManager;

import java.util.HashMap;

public class CheckIpAndPort extends ListenerManager<CheckIpAndPortInterface.NotificationListener>
        implements CheckIpAndPortInterface {


    CheckIpAndPortInterface.StateChecking stateChecking = CheckIpAndPortInterface.StateChecking.INIT;


    HashMap<String, HashMap<String, String>> hashMapDatas = new HashMap<>();

    public CheckIpAndPort() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO Methods to check


            }
        });

        thread.start();
    }

    private synchronized void notifyListeners() {
        for (NotificationListener listener : listeners) {
            listener.onStateCheckingChanged();
        }

    }

    @Override
    public StateChecking getState() {
        return stateChecking;
    }
}
