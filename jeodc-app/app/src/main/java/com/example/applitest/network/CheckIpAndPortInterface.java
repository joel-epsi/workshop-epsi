package com.example.applitest.network;

import com.example.applitest.common.ListenerManagerInterface;



public interface CheckIpAndPortInterface extends ListenerManagerInterface<CheckIpAndPortInterface.NotificationListener>{

    interface NotificationListener {
        /**
         * Called when a new state is raised
         **/
        void onStateCheckingChanged();
    }
    enum StateChecking{
        INIT,
        CHECKING,
        ERROR,
        FINISH

    }



    StateChecking getState();
}
