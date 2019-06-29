package ru.cft.focusstart.matrosov.client.model;

import ru.cft.focusstart.matrosov.client.observer.*;
import ru.cft.focusstart.matrosov.common.*;

import java.util.ArrayList;
import java.util.Date;
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

    public void sendMessage(String text) {
        JsonMessage message = new Message(text, new Date(), ConnectionManager.getInstance().getUserName());
        ConnectionManager.getInstance().sendMessage(message);
    }

    private void processMessage(ClientMessage message) {
        for (ClientObserver o : clientObservers) {
            o.onNewClient(message);
        }
    }

    private synchronized void processMessage(Message message) {
        for (MessageObserver o : messageObservers) {
            o.onNewMessage(message);
        }
    }

    private synchronized void processMessage(MessageList message) {
        for (MessageObserver o : messageObservers) {
            o.onNewMessages(message.getMessages());
        }
    }

    private synchronized void processMessage(ClientList message) {
        for (ClientObserver o : clientObservers) {
            o.onNewClientList(message.getMessages());
        }
    }
    private synchronized void processMessage(InfoMessage message) {
        for (InfoObserver o : infoObservers) {
            o.onInfo(message.getInfo());
        }
    }
    private synchronized void processMessage(ErrorMessage message) {
        for (ErrorObserver o : errorObservers) {
            o.onError(message.getCause());
        }
    }
    private synchronized void processMessage(SuccessMessage message) {
        for (SuccessObserver o : successObservers) {
            o.onSuccess(message.getSuccess());
        }
    }
}
