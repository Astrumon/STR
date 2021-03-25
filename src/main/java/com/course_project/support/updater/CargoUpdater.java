package com.course_project.support.updater;

import com.course_project.data_access.model.Cargo;
import com.course_project.support.AlertGenerator;
import com.course_project.support.manager.CargoManager;

/**
 * Класс который реализует всю логику обновления(изменения, удаления) грузоперевозок
 *
 */
public class CargoUpdater {
    private CargoManager cargoManager;
    private Cargo cargo;


    public CargoUpdater() {
        cargoManager = new CargoManager();
        cargo = new Cargo();
    }

    public void delete(Cargo cargo) {
        if (cargoManager.delete(cargo)) {
            AlertGenerator.info("Запис успішно видалений");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні запису");
        }
    }

    public void update(Cargo cargo) {
        if (cargoManager.update(cargo)) {
            AlertGenerator.info("Запис успішно оновлено");
        } else {
            AlertGenerator.error("Виникла помилка при оновлені запису");
        }
    }
}
