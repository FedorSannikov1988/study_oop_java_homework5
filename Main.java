import controllers.UserController;
import model.*;
import views.Validation;
import views.ViewUser;

public class Main {
    public static void main(String[] args) {

        FileOperation fileOperation = new FileOperationImpl("users", FormatSafeFile.csv);

        Repository repository = new RepositoryFile(fileOperation, new UserMapper());

        UserController controller = new UserController(repository, new Validation());

        ViewUser view = new ViewUser(controller);

        view.run();
    }
}
