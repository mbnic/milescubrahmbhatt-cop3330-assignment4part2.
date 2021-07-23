package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListModel {

    private ObservableList<Item> allItems;
    private ObservableList<Item> incompleteItems;
    private ObservableList<Item> completeItems;

    public ListModel() {
        allItems = FXCollections.observableArrayList();
        incompleteItems = FXCollections.observableArrayList();
        completeItems = FXCollections.observableArrayList();
    }

    public void changeItemStatus(Item item) {
        //was complete
        if (item.getCompletionStatus().equals("complete")) {
            //now incomplete
            item.setCompletionStatus("incomplete");
            incompleteItems.add(item);
            completeItems.remove(item);
        }

        //was incomplete
        else {
            //now complete
            item.setCompletionStatus("complete");
            completeItems.add(item);
            incompleteItems.remove(item);
        }
    }

    public void addItem(Item item) {
        if (allItems.size() < 101) {
            if (item.getCompletionStatus().equals("complete"))
                completeItems.add(item);
            else
                incompleteItems.add(item);

            allItems.add(item);
        }
    }

    public void removeItem(Item item) {
        allItems.remove(item);
        incompleteItems.remove(item);
        completeItems.remove(item);
    }

    public boolean isCorrectDescriptionLength(String newDescription) {
        return newDescription.length() >= 1 && newDescription.length() <= 256;
    }

    public ObservableList<Item> getAllItems() {
        return allItems;
    }

    public ObservableList<Item> getIncompleteItems() {
        return incompleteItems;
    }

    public ObservableList<Item> getCompleteItems() {
        return completeItems;
    }
}
