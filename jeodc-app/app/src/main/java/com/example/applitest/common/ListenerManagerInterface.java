package com.example.applitest.common;

/**
 * A generic interface to add and remove listeners
 *
 * @param <Listener> A notification listener type
 */
public interface ListenerManagerInterface<Listener> {
    /**
     * Call this to start receiving Listener events
     *
     * @param listener A NotificationListener to be added
     */
    void addListener(Listener listener);

    /**
     * Call this to stop receiving Listener events
     *
     * @param listener The NotificationListener to be removed
     */
    void removeListener(Listener listener);

}
