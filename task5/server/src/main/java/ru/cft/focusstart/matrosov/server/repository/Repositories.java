package ru.cft.focusstart.matrosov.server.repository;

public final class Repositories {

    public static MessageRepository getMessageRepository() {
        return MemoryMessageRepository.INSTANCE;
    }

    private Repositories() {}
}