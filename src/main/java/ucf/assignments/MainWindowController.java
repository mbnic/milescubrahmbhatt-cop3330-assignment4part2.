package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController implements Initializable {

    private ListModel listModel;

    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> itemDescriptionColumn;
    @FXML private TableColumn<Item, String> itemDueDateColumn;
    @FXML private TableColumn<Item, String> itemCompletionColumn;
    @FXML private TextField newTaskDescription;
    @FXML private DatePicker datePicker;
    @FXML private DatePicker changeItemDueDatePicker;
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
        itemCompletionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.setItems(listModel.getAllItems());
    }

    @FXML
    public void editTaskDescription(TableColumn.CellEditEvent<Item, String> CellEditEvent) {
        //get item and send to editTaskDescription

        Item item = tableView.getSelectionModel().getSelectedItem();
        editTaskDescription(item, CellEditEvent.getNewValue());
        tableView.refresh();
    }

    @FXML
    public void changeItemDueDatePickerClicked(ActionEvent event) {
        //get item, dueDate, and format it before sending to changeItemDueDate

        Item item = tableView.getSelectionModel().getSelectedItem();
        LocalDate dueDate = changeItemDueDatePicker.getValue();
        String newDateWithFormat = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        changeItemDueDate(item, newDateWithFormat);
        changeItemDueDatePicker.setValue(null);
        tableView.refresh();
    }

    @FXML
    public void changeItemStatusButtonClicked(ActionEvent actionEvent) {
        //get selected item and send to changeStatus

        Item item = tableView.getSelectionModel().getSelectedItem();
        changeItemStatus(item);
        tableView.refresh();
    }

    @FXML
    public void viewIncompleteOptionClicked(ActionEvent actionEvent) {
        tableView.setItems(listModel.getIncompleteItems());
    }

    @FXML
    public void viewCompleteOptionClicked(ActionEvent actionEvent) {
        tableView.setItems(listModel.getCompleteItems());
    }

    @FXML
    public void viewAllOptionClicked(ActionEvent actionEvent) {
        tableView.setItems(listModel.getAllItems());
    }

    @FXML
    public void addTaskClicked(ActionEvent actionEvent) {
        //get new info
        LocalDate dueDate = datePicker.getValue();
        String newDateWithFormat = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String newDescription = newTaskDescription.getText();

        //make sure follows requirements and then send to addTask
        if (listModel.isCorrectDescriptionLength(newDescription)) {
            addTask(newDescription, newDateWithFormat);

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
        //get item to send to removeTask

        Item item = tableView.getSelectionModel().getSelectedItem();
        removeTask(item);
    }

    @FXML
    public void clearAllTasksClicked(ActionEvent actionEvent) {
        clearAllTasks();
        tableView.getItems().clear();
    }

    @FXML
    public void saveMenuItemClicked(ActionEvent event) {
        //open new filechooser window and add the extension you'll use

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(stage);

        //check if null
        if (file != null) {
            saveFile(file);
        }
    }

    @FXML
    public void loadMenuItemClicked(ActionEvent event) {
        //open new stage for fileChooser and set the extension to be used
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(stage);

        //make sure user actually selected file
        try {
            loadFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void aboutMenuItemClicked(ActionEvent event) {
        //open stage and scene for the help window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpWindow.fxml"));
            Parent root = loader.load();
            HelpWindowController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTaskDescription(Item item, String description) {
        item.setDescription(description);
    }

    public void changeItemDueDate(Item item, String dueDate) {
        listModel.changeItemDueDate(item, dueDate);
    }

    public void changeItemStatus(Item item) {
        listModel.changeItemStatus(item);
    }

    public void addTask(String newDescription, String newDateWithFormat) {
        listModel.addItem(new Item("incomplete", newDescription, newDateWithFormat));
    }

    public void removeTask(Item item) {
        listModel.removeItem(item);
    }

    public void clearAllTasks() {
        listModel.getAllItems().clear();
        listModel.getCompleteItems().clear();
        listModel.getIncompleteItems().clear();
    }

    public void loadFile(File file) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        //scan in all lines of info
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (!line.isEmpty())
                lines.add(line);
        }

        //separate info into different array indices
        for (int i = 0; i < lines.size(); i++) {
            String[] splits = lines.get(i).split("//");

            listModel.addItem(new Item(splits[0], splits[1], splits[2]));
        }
    }

    public void saveFile(File file) {
        StringBuilder listData = new StringBuilder();
        String completionStatus;
        String description;
        String dueDate;

        ObservableList<Item> allItems = listModel.getAllItems();

        //build string of all items to put into the save file
        for (Item allItem : allItems) {
            completionStatus = allItem.getCompletionStatus();
            description = allItem.getDescription();
            dueDate = allItem.getDueDate();

            listData.append(completionStatus
                    + "//" + description
                    + "//" + dueDate + "\n");
        }

        //write the date to the save file
        try {
            PrintWriter write = new PrintWriter(file);
            write.println(listData);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
