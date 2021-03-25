package com.course_project.support.creator;

import com.course_project.data_access.model.Cargo;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.manager.CargoManager;

/**
 * Класс который реализует всю логику создания грузоперевозок
 *
 */
public class CargoCreator {
    private CargoManager cargoManager;

    public CargoCreator() {
        cargoManager = new CargoManager();
    }

    public boolean isValidPoint(String currentValue) {
        if (!Checker.checkEmptyValue(currentValue)
                && Checker.checkStringValue(currentValue)) {
            return true;
        } else {
            AlertGenerator.error("Точка вказана невірно");
            return false;
        }
    }

    public boolean isValidDate(String currentDate) {
        if (!Checker.checkEmptyValue(currentDate)) {
            return true;
        } else {
            AlertGenerator.error("Дата вказана невірно");
            return false;
        }
    }

    public boolean isValidEmail(String value) {
        if (!Checker.checkEmptyValue(value)
                && Checker.checkValidEmail(value)) {
            return true;
        } else {
            AlertGenerator.error("Пошта вказана невірно");
            return false;
        }
    }

    public void createCargo(Cargo cargo) {
        System.out.println(cargo);
        if (cargoManager.create(cargo)) {
            AlertGenerator.info("Ваш заказ успішно прийнятий");
        } else {
            AlertGenerator.error("Виникла помилка при створені заказу");
        }
    }
}
