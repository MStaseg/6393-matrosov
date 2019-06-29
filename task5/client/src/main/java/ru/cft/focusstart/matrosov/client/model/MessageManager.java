package ru.cft.focusstart.matrosov.client.model;

import ru.cft.focusstart.matrosov.client.observer.*;
import ru.cft.focusstart.matrosov.common.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageManager {
    public static final MessageManager instance = new MessageManager();

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
        if (message instanceof LoginMessage) {
            processMessage((LoginMessage) message);
        } else if (message instanceof ChatMessage) {
            processMessage((ChatMessage) message);
        } else if (message instanceof ClientListMessage) {
            processMessage((ClientListMessage) message);
        } else if (message instanceof InfoMessage) {
            processMessage((InfoMessage) message);
        } else if (message instanceof LoginFailMessage) {
            processMessage((LoginFailMessage) message);
        } else if (message instanceof LoginSuccessMessage) {
            processMessage((LoginSuccessMessage) message);
        }
    }

    public void sendMessage(String text) {
        JsonMessage message = new ChatMessage(text, new Date(), ConnectionManager.instance.getUser());
        ConnectionManager.instance.sendMessage(message);
    }

    private void processMessage(LoginMessage message) {
        for (ClientObserver o : clientObservers) {
            o.onNewClient(message);
        }
    }

    private synchronized void processMessage(ChatMessage message) {
        for (MessageObserver o : messageObservers) {
            o.onNewMessage(message);
        }
    }

    private synchronized void processMessage(ClientListMessage message) {
        for (ClientObserver o : clientObservers) {
            o.onNewClientList(message.getUsers());
        }
    }
    private synchronized void processMessage(InfoMessage message) {
        for (InfoObserver o : infoObservers) {
            o.onInfo(message.getInfo());
        }
    }
    private synchronized void processMessage(LoginFailMessage message) {
        for (ErrorObserver o : errorObservers) {
            o.onError(message.getCause());
        }
    }
    private synchronized void processMessage(LoginSuccessMessage message) {
        for (SuccessObserver o : successObservers) {
            o.onSuccess();
        }
    }
}
