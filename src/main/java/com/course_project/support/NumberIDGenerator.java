package com.course_project.support;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class NumberIDGenerator {

    public static <T> int generate(ObservableList<T> observableList, TableColumn.CellDataFeatures cellDataFeatures) {
        return observableList.indexOf((T) cellDataFeatures.getValue()) + 1;
    }
}
