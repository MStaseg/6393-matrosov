package ru.cft.focusstart.matrosov.server.service;

public final class Services {

    public static MessageService getMessageService() {
        return DefaultMessageService.instance;
    }

    public static ClientService getClientService() {
        return DefaultClientService.instance;
    }

    private Services() {}
}
