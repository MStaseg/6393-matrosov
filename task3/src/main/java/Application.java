import ru.cft.focusstart.matrosov.contoller.GameController;
import ru.cft.focusstart.matrosov.exception.IllegalGameParametersException;
import ru.cft.focusstart.matrosov.model.GameDifficulty;
import ru.cft.focusstart.matrosov.model.GameManager;

import java.util.Set;
import java.util.TreeSet;
public class Application {

    public static void main(String[] args) throws IllegalGameParametersException {
        new GameController();
    }
}
