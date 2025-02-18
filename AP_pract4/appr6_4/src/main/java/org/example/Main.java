package org.example;
import java.util.regex.*;
import java.util.Scanner;

import java.util.*;
import java.util.concurrent.*;
public class Main {
    private static final Map<String, Producer> producers = new HashMap<>();
    private static final Map<String, Consumer> consumers = new HashMap<>();
    private static final Map<String, Topic> topics = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newCachedThreadPool();

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            try {
                switch (parts[0]) {
                    case "register":
                        handleRegister(parts);
                        break;
                    case "send":
                        handleSend(parts);
                        break;
                    case "publish":
                        handlePublish(parts);
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleRegister(String[] parts) {
        if ("producer".equals(parts[2])) {
            String id = parts[4];
            String topicName = parts[6];

            if (producers.containsKey(id)) {
                throw new IllegalArgumentException("Producer with id " + id + " already exists");
            }

            Topic topic = topics.computeIfAbsent(topicName, Topic::new);
            Producer producer = new Producer(id, topic);
            producers.put(id, producer);
            System.out.println("Producer " + id + " registered with topic " + topicName);
        } else if ("consumer".equals(parts[2])) {
            String id = parts[4];
            String topicName = parts[6];

            if (consumers.containsKey(id)) {
                throw new IllegalArgumentException("Consumer with id " + id + " already exists");
            }

            Topic topic = topics.get(topicName);
            if (topic == null) {
                throw new IllegalArgumentException("Topic " + topicName + " does not exist");
            }

            Consumer consumer = new Consumer(id);
            consumer.subscribe(topic);
            consumers.put(id, consumer);
            System.out.println("Consumer " + id + " registered and subscribed to topic " + topicName);
            new Thread(String.valueOf(consumer)).start();
        } else {
            throw new IllegalArgumentException("Invalid register command");
        }
    }

    private static void handleSend(String[] parts) {
        String producerId = parts[3];
        Priority priority = Priority.valueOf(parts[5].toUpperCase());
        String topicName = parts[7];
        String content = String.join(" ", Arrays.copyOfRange(parts, 8, parts.length));

        Producer producer = producers.get(producerId);
        if (producer == null) {
            throw new IllegalArgumentException("Producer with id " + producerId + " does not exist");
        }

        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("Topic " + topicName + " does not exist");
        }

        producer.sendMessage(content, priority, topic);
    }

    private static void handlePublish(String[] parts) {
        String topicName = parts[2];

        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new IllegalArgumentException("Topic " + topicName + " does not exist");
        }

        while (topic.hasMessages()) {
            // Messages are automatically processed by consumers
        }
    }
}
