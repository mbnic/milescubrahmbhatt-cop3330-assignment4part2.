package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowControllerTest {

    @Test
    void editTaskDescription() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));

        main.editTaskDescription(listModel.getAllItems().get(0), "go to zoo");
        String actual = listModel.getAllItems().get(0).getDescription();

        assertEquals("go to zoo", actual);
    }

    @Test
    void changeItemDueDate() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));

        main.changeItemDueDate(listModel.getAllItems().get(0), "2286-09-04");
        String actual = listModel.getAllItems().get(0).getDueDate();

        assertEquals("2286-09-04", actual);

    }

    @Test
    void changeItemStatus() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));

        main.changeItemStatus(listModel.getAllItems().get(0));
        String actual = listModel.getAllItems().get(0).getCompletionStatus();

        assertEquals("complete", actual);
    }

    @Test
    void addTask() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));

        main.addTask("testy test test test", "1864-11-16");

        int actual = listModel.getAllItems().size();

        assertEquals(2, actual);
    }

    @Test
    void removeTask() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));
        listModel.addItem(new Item("incomplete", "testy test test test", "1999-07-21"));

        Item item = listModel.getAllItems().get(0);
        main.removeTask(item);

        int actual = listModel.getAllItems().size();

        assertEquals(1, actual);
    }

    @Test
    void clearAllTasks() {
        ListModel listModel = new ListModel();
        MainWindowController main = new MainWindowController(listModel);
        listModel.addItem(new Item("incomplete", "testy test", "1999-07-21"));
        listModel.addItem(new Item("incomplete", "testy test test test", "1999-07-21"));

        main.clearAllTasks();

        int actual = listModel.getAllItems().size();

        assertEquals(0, actual);
    }
}