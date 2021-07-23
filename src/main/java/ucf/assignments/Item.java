package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty description;
    private SimpleStringProperty dueDate;
    private SimpleStringProperty completionStatus;

    public Item(String completionStatus, String description, String dueDate) {
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.completionStatus = new SimpleStringProperty(completionStatus);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public String getCompletionStatus() {
        return completionStatus.get();
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus.set(completionStatus);
    }
}
