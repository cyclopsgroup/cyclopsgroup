package org.cyclopsgroup.spee.example;

public interface DocumentManager
{
    void sendToSecretary(String docId);

    void sendToReviewer(String docId, String reviewer);

    void publish(String docId);
}
