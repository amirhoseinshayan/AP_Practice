package org.example;

class Producer {
    private final String id;
    private final Topic topic;

    public Producer(String id, Topic topic) {
        this.id = id;
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void sendMessage(String content, Priority priority, Topic targetTopic) {
        if (isTargetTopicValid(targetTopic)) {
            Message message = createMessage(content, priority);
            publishMessageToTopic(targetTopic, message);
        } else {
            handleInvalidTopic(targetTopic);
        }
    }

    private boolean isTargetTopicValid(Topic targetTopic) {
        return targetTopic.getName().equals(this.topic.getName());
    }

    private Message createMessage(String content, Priority priority) {
        return new Message(content, priority);
    }

    private void publishMessageToTopic(Topic targetTopic, Message message) {
        targetTopic.publishMessage(message);
    }

    private void handleInvalidTopic(Topic targetTopic) {
        System.out.println("Error: Producer " + id + " cannot send messages to topic " + targetTopic.getName());
    }
}

