package org.example;

import java.util.*;
import java.util.concurrent.*;
class Topic {
    private final String name;
    private final PriorityBlockingQueue<Message> messageQueue;

    public Topic(String name) {
        this.name = name;
        this.messageQueue = new PriorityBlockingQueue<>();
    }

    public String getName() {
        return name;
    }

    public void publishMessage(Message message) {
        addMessageToQueue(message);
    }

    private void addMessageToQueue(Message message) {
        if (messageQueue.offer(message)) {
            logMessageAddition(message);
        } else {
            System.out.println("Failed to add message to topic " + name + ": Queue is full.");
        }
    }

    private void logMessageAddition(Message message) {
        System.out.println("Message added to topic " + name + ": " + message.getContent());
    }

    public Message retrieveMessage() {
        return messageQueue.poll();
    }

    public boolean hasMessages() {
        return !messageQueue.isEmpty();
    }
}

