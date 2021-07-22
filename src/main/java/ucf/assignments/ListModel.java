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

    public void addItem(Item item) {
        if (allItems.size() < 101) {
            allItems.add(item);

            //uncomment this later
            //incompleteItems.add(item);
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

    public void setAllItems(ObservableList<Item> allItems) {
        this.allItems = allItems;
    }

    public ObservableList<Item> getIncompleteItems() {
        return incompleteItems;
    }

    public void setIncompleteItems(ObservableList<Item> incompleteItems) {
        this.incompleteItems = incompleteItems;
    }

    public ObservableList<Item> getCompleteItems() {
        return completeItems;
    }

    public void setCompleteItems(ObservableList<Item> completeItems) {
        this.completeItems = completeItems;
    }
}
