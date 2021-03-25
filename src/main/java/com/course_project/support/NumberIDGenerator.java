package com.course_project.support;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 * Класс который служит для автоматического генерирования упорядочного номера в таблицах
 */
public class NumberIDGenerator {

    public static <T> int generate(ObservableList<T> observableList, TableColumn.CellDataFeatures cellDataFeatures) {
        return observableList.indexOf((T) cellDataFeatures.getValue()) + 1;
    }
}
