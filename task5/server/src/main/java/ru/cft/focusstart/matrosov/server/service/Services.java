package ru.cft.focusstart.matrosov.server.service;

public final class Services {

    public static ClientService getClientService() {
        return DefaultClientService.INSTANCE;
    }

    private Services() {}
}
