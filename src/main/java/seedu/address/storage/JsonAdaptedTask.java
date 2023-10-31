package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Task;
import seedu.address.model.animal.TaskList;



/**
 * A Jackson-friendly adapted version of the {@link TaskList} model class.
 */
public class JsonAdaptedTask {
    private final String description;
    private final String taskStatus;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given Task details.
     * This constructor is used internally by Jackson to deserialize {@link Task} JSON objects.
     */
    @JsonCreator
    public JsonAdaptedTask(String description, String taskStatus) {
        this.description = description;
        this.taskStatus = taskStatus;
    }

    /**
     * Converts a given {@code Task} into a {@code JsonAdaptedTask}, which can then be
     * serialized by Jackson into JSON objects.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription();
        taskStatus = source.getStausIcon();
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getIsDone() {
        return taskStatus;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toTaskType() throws IllegalValueException {
        if (!Task.isValidTask(description)) {
            throw new IllegalValueException(Task.MESSAGE_CONSRAINTS);
        }
        boolean isDone = (taskStatus.equals("X"));
        return new Task(description, isDone);
    }

}
