package ru.cft.focusstart.matrosov.server.repository;

public final class Repositories {

    public static MessageRepository getMessageRepository() {
        return MemoryMessageRepository.instance;
    }

    public static ClientRepository getClientRepository() {
        return MemoryClientRepository.instance;
    }

    private Repositories() {}
}