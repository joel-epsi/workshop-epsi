package com.example.applitest.common;

import com.example.applitest.common.ListenerManagerInterface;


import java.util.LinkedList;
import java.util.List;

/**
 * A generic listener manager to add and remove listeners from a listener list
 *
 * @param <Listener> A notification listener type
 */
public class ListenerManager<Listener> implements ListenerManagerInterface<Listener> {

    /**
     * List of listeners. Make sur to use a synchronized block or method to iterate over the list
     * as it can be modified at anytime by a listener.
     */
    protected List<Listener> listeners = new LinkedList<>();

    @Override
    public void addListener(final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ListenerManager.this) {
                    listeners.add(listener);
                }
            }
        }).start();
    }

    @Override
    public void removeListener(final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ListenerManager.this) {
                    listeners.remove(listener);
                }
            }
        }).start();
    }
}
