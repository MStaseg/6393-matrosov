package ru.cft.focusstart.matrosov.server.service;

public final class Services {

    public static MessageService getMessageService() {
        return DefaultMessageService.instance;
    }

    private Services() {}
}
