package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Message implements Comparable<Message> {
    private final String content;
    private final Priority priority;

    public Message(String content, Priority priority) {
        this.content = content;
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Message other) {
        return comparePriorities(this.priority, other.priority);
    }

    private int comparePriorities(Priority priority1, Priority priority2) {
        return priority1.ordinal() - priority2.ordinal();
    }
}

