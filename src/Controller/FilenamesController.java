package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Backend.*;
import java.io.IOException;


public class FilenamesController {

    private String comparison;
    public TextField filename1,filename2;
    public Button next;
    public RadioButton lineChoice,characterChoice;
    private Stage stage;
    private String fileName;
    private String message;
    private Similarity check= new Similarity();
    private Database logs = new Database();

    public void getFileName(String fileName){
        this.fileName=fileName;
    }

    public void passStage(Stage stage){
        this.stage = stage;
    }

    public void line() {
        comparison = "line";
    }

    public void character() {
        comparison = "character";
    }

    public void toExit()
    {
        stage.close();
    }

    public void toNext() throws IOException {
        Stage popUp= new Stage();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("../FXML/Percentage.fxml"));
        Parent root = (Parent) fxmloader.load();

        Scene scene = new Scene(root);

        popUp.setScene(scene);
        popUp.setTitle("Software Similarity Program");
        popUp.show();

        check.readFile(filename1.getText(),filename2.getText());
        PercentageController passCont = fxmloader.getController();

        if(lineChoice.isSelected()) {
            line();
            setMessage(check.ReadCodeLine(comparison));
            logs.appendToFile(message,fileName);
            passCont.passValues(message);
            clear();
        }
        if(characterChoice.isSelected()) {
            character();
            setMessage(check.ReadCodeCharacter(comparison));
            logs.appendToFile(message,fileName);
            passCont.passValues(message);
            clear();
        }

    }

    private void clear()
    {
        filename1.clear();
        filename2.clear();
    }

    private void setMessage(String message) {
        this.message = message;
    }

}


