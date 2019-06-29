package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.server.exception.ServiceMessageException;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;
import ru.cft.focusstart.matrosov.server.util.JsonParser;

import java.io.IOException;
import java.util.List;

public final class DefaultMessageService implements MessageService {

    static final DefaultMessageService instance = new DefaultMessageService();

    private DefaultMessageService() {}

    @Override
    public JsonMessage add(JsonMessage message) {
        Repositories.getMessageRepository().add(message);
        return message;
    }

    @Override
    public void sendMessage(Client client, JsonMessage message) throws ServiceMessageException {
        add(message);

        try {
            String msg = JsonParser.getInstance().getMapper().writeValueAsString(message);
            client.sendMessage(msg);
            Repositories.getMessageRepository().add(message);
        } catch (IOException e) {
            throw new ServiceMessageException("Ошибка при отправке сообщения", e);
        }
    }

    @Override
    public void sendMessageToAll(JsonMessage message) throws ServiceMessageException {
        add(message);

        List<Client> clients = Services.getClientService().getClients();
        for (Client client: clients) {
            try {
                String msg = JsonParser.getInstance().getMapper().writeValueAsString(message);
                client.sendMessage(msg);
                Repositories.getMessageRepository().add(message);
            } catch (IOException e) {
                throw new ServiceMessageException("Ошибка при рассылке сообщений", e);
            }
        }
    }
}
