package ucf.assignments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty description;
    private SimpleStringProperty dueDate;
    private SimpleBooleanProperty completionStatus;

    public Item(String description, String dueDate, boolean completionStatus) {
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.completionStatus = new SimpleBooleanProperty(completionStatus);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public SimpleStringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public boolean isCompletionStatus() {
        return completionStatus.get();
    }

    public SimpleBooleanProperty completionStatusProperty() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus.set(completionStatus);
    }
}
