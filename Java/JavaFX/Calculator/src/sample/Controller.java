package sample;

import edu.cudenver.domain.Calculator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Controller {

    public TextField txtNo1;
    public TextField txtNo2;
    public Label lblSum;
    public ListView lstTape;

    private ObservableList<String> tapeList;

    public Controller(){
        tapeList = FXCollections.observableArrayList();
    }

    public void initialize(){
        this.lstTape.setItems(tapeList);
    }

    public void calculateSum(ActionEvent actionEvent) {
        double val1 = Double.parseDouble(txtNo1.getText());
        double val2 = Double.parseDouble(txtNo2.getText());

        Calculator calc = new Calculator(val1,val2);

        double sum =calc.getSum();
        lblSum.setText(String.valueOf(sum));

        tapeList.add(String.format("%.3f + %.3f = %.3f",val1,val2,sum));
    }


    public void exit(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to exit?",
                                ButtonType.YES, ButtonType.NO);

        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            Platform.exit();
        }
    }

}
