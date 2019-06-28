package ru.cft.focusstart.matrosov.client.model;

import ru.cft.focusstart.matrosov.client.observer.*;
import ru.cft.focusstart.matrosov.common.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageManager {
    private static MessageManager instance;

    private List<MessageObserver> messageObservers;
    private List<ClientObserver> clientObservers;
    private List<InfoObserver> infoObservers;
    private List<ErrorObserver> errorObservers;
    private List<SuccessObserver> successObservers;

    private MessageManager() {
        messageObservers = new ArrayList<>();
        clientObservers = new ArrayList<>();
        infoObservers = new ArrayList<>();
        errorObservers = new ArrayList<>();
        successObservers = new ArrayList<>();
    }

    public static synchronized MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }

        return instance;
    }

    public void addMessageObserver(MessageObserver o) {
        messageObservers.add(o);
    }

    public void removeMessageObserver(MessageObserver o) {
        messageObservers.remove(o);
    }

    public void addClientObserver(ClientObserver o) {
        clientObservers.add(o);
    }

    public void removeClientObserver(ClientObserver o) {
        clientObservers.remove(o);
    }

    public void addInfoObserver(InfoObserver o) {
        infoObservers.add(o);
    }

    public void removeInfoObserver(InfoObserver o) {
        infoObservers.remove(o);
    }

    public void addErrorObserver(ErrorObserver o) {
        errorObservers.add(o);
    }

    public void removeErrorObserver(ErrorObserver o) {
        errorObservers.remove(o);
    }

    public void addSuccessObserver(SuccessObserver o) {
        successObservers.add(o);
    }

    public void removeSuccessObserver(SuccessObserver o) {
        successObservers.remove(o);
    }

    public void processMessage(JsonMessage message) {
        if (message instanceof ClientMessage) {
            processMessage((ClientMessage) message);
        } else if (message instanceof Message) {
            processMessage((Message) message);
        } else if (message instanceof MessageList) {
            processMessage((MessageList) message);
        } else if (message instanceof ClientList) {
            processMessage((ClientList) message);
        } else if (message instanceof InfoMessage) {
            processMessage((InfoMessage) message);
        } else if (message instanceof ErrorMessage) {
            processMessage((ErrorMessage) message);
        } else if (message instanceof SuccessMessage) {
            processMessage((SuccessMessage) message);
        }
    }

    private void processMessage(ClientMessage message) {
        System.out.println(message);

        Iterator<ClientObserver> iterator = clientObservers.iterator();
        while (iterator.hasNext()) {
            ClientObserver o = iterator.next();
            o.onNewClient(message);
        }
    }

    private synchronized void processMessage(Message message) {
        System.out.println(message);

        // We use iterator cause else we can get ConcurrentModificationException
        Iterator<MessageObserver> iterator = messageObservers.iterator();
        while (iterator.hasNext()) {
            MessageObserver o = iterator.next();
            o.onNewMessage(message);
        }
    }

    private synchronized void processMessage(MessageList message) {
        System.out.println(message);

        Iterator<MessageObserver> iterator = messageObservers.iterator();
        while (iterator.hasNext()) {
            MessageObserver o = iterator.next();
            o.onNewMessages(message.getMessages());
        }
    }

    private synchronized void processMessage(ClientList message) {
        System.out.println(message);

        Iterator<ClientObserver> iterator = clientObservers.iterator();
        while (iterator.hasNext()) {
            ClientObserver o = iterator.next();
            o.onNewClientList(message.getMessages());
        }
    }
    private synchronized void processMessage(InfoMessage message) {
        System.out.println(message);

        Iterator<InfoObserver> iterator = infoObservers.iterator();
        while (iterator.hasNext()) {
            InfoObserver o = iterator.next();
            o.onInfo(message.getInfo());
        }
    }
    private synchronized void processMessage(ErrorMessage message) {
        System.out.println(message);

        Iterator<ErrorObserver> iterator = errorObservers.iterator();
        while (iterator.hasNext()) {
            ErrorObserver o = iterator.next();
            o.onError(message.getCause());
        }
    }
    private synchronized void processMessage(SuccessMessage message) {
        System.out.println(message);

        Iterator<SuccessObserver> iterator = successObservers.iterator();
        while (iterator.hasNext()) {
            SuccessObserver o = iterator.next();
            o.onSuccess(message.getSuccess());
        }
    }
}
