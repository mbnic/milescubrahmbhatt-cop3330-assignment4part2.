package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private ListModel listModel;

    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> itemDescriptionColumn;
    @FXML private TableColumn<Item, String> itemDueDateColumn;
    @FXML private TableColumn<Item, Boolean> itemCompletionColumn;
    @FXML private TextField newTaskDescription;
    @FXML private DatePicker datePicker;
    @FXML private Text errorText;

    public MainWindowController(ListModel listModel) {
        this.listModel = listModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(true);

        itemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        itemDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemDueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        itemDueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemCompletionColumn.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));
        itemCompletionColumn.setCellFactory(CheckBoxTableCell.forTableColumn(itemCompletionColumn));

        listModel.addItem(new Item("123456789K", "nic", false));
        listModel.addItem(new Item("1234567OOO", "nic", false));
        listModel.addItem(new Item("1234567UUU", "bry", true));
        listModel.addItem(new Item("1234567WWW", "bry", true));


        tableView.setItems(listModel.getAllItems());
    }

    @FXML
    public void editTaskDescription(TableColumn.CellEditEvent<Item, String> CellEditEvent) {
//        item itemChanged = tableView.getSelectionModel().getSelectedItem();
//        itemChanged.setDescription(CellEditEvent.getNewValue());

    }

    @FXML
    public void editDueDate(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
//        item itemChanged = tableView.getSelectionModel().getSelectedItem();
//        itemChanged.setDueDate(itemStringCellEditEvent.getNewValue());
    }

    @FXML
    public void completionStatusChanged(TableColumn.CellEditEvent<Item, Boolean> itemBooleanCellEditEvent) {
//        item itemChanged = tableView.getSelectionModel().getSelectedItem();
//
//
//        if (itemBooleanCellEditEvent.getNewValue() == null) {
//            itemChanged.setCompletionStatus(itemBooleanCellEditEvent.getNewValue());
//            completeItems.add(itemChanged);
//            incompleteItems.remove(itemChanged);
//        }
//        if(itemBooleanCellEditEvent.getNewValue() == false) {
//            itemChanged.setCompletionStatus(itemBooleanCellEditEvent.getNewValue());
//            completeItems.remove(itemChanged);
//            incompleteItems.add(itemChanged);
//        }
//        if(itemBooleanCellEditEvent.getNewValue() == null) {
//            itemChanged.setCompletionStatus(itemBooleanCellEditEvent.getNewValue());
//            completeItems.add(itemChanged);
//            incompleteItems.remove(itemChanged);
//        }
    }

    @FXML
    public void viewIncompleteOptionClicked(ActionEvent actionEvent) {
//        tableView.setItems(incompleteItems);
    }

    @FXML
    public void viewCompletedClicked(ActionEvent actionEvent) {
//        tableView.setItems(completeItems);
    }

    @FXML
    public void viewAllTasksClicked(ActionEvent actionEvent) {
//        tableView.setItems(allItems);
    }


    @FXML
    public void addTaskClicked(ActionEvent actionEvent) {
        LocalDate dueDate = datePicker.getValue();
        String newDateWithFormat = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String newDescription = newTaskDescription.getText();

        if (listModel.isCorrectDescriptionLength(newDescription)) {
            listModel.addItem(new Item(newDescription, newDateWithFormat, false));
            newTaskDescription.clear();
            datePicker.setValue(null);
            errorText.setText("");
        }
        else {
            errorText.setText("Description must be between 1 and 256 character in length");
        }
    }

    @FXML
    public void removeTaskClicked(ActionEvent actionEvent) {
        Item item = tableView.getSelectionModel().getSelectedItem();
        listModel.removeItem(item);
    }

    @FXML
    public void clearAllTasksClicked(ActionEvent actionEvent) {
        listModel.getAllItems().clear();
        listModel.getCompleteItems().clear();
        listModel.getIncompleteItems().clear();
        tableView.getItems().clear();
    }
}
