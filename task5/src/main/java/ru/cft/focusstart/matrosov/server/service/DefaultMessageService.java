package ru.cft.focusstart.matrosov.server.service;

import ru.cft.focusstart.matrosov.common.JsonMessage;
import ru.cft.focusstart.matrosov.common.Message;
import ru.cft.focusstart.matrosov.server.exception.ServiceClientException;
import ru.cft.focusstart.matrosov.server.net.Client;
import ru.cft.focusstart.matrosov.server.repository.Repositories;
import ru.cft.focusstart.matrosov.server.util.JsonParser;

import java.io.IOException;
import java.util.List;

public final class DefaultMessageService implements MessageService {

    static final DefaultMessageService instance = new DefaultMessageService();

    private DefaultMessageService() {}

    @Override
    public Message add(Message message) {
        Repositories.getMessageRepository().add(message);
        return message;
    }

    @Override
    public List<Message> getMessages() {
        return Repositories.getMessageRepository().get();
    }

    @Override
    public void sendMessage(Client client, JsonMessage message) throws ServiceClientException {
        try {
            String msg = JsonParser.getInstance().getMapper().writeValueAsString(message);
            client.sendMessage(msg);
        } catch (IOException e) {
            throw new ServiceClientException("Ошибка при отправке сообщения", e);
        }
    }

    @Override
    public void sendMessage(List<Client> clients, JsonMessage message) throws ServiceClientException {
        for (Client client: clients) {
            try {
                String msg = JsonParser.getInstance().getMapper().writeValueAsString(message);
                client.sendMessage(msg);
            } catch (IOException e) {
                throw new ServiceClientException("Ошибка при рассылке сообщений", e);
            }
        }
    }

    @Override
    public void sendMessageList(Client client, List<Message> messages) throws ServiceClientException {

    }
}
