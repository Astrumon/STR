package com.course_project.support.manager;


import com.course_project.database.DataSource;

/**
 * Класс который дает доступ к базе данных
 */
public abstract class Manager {
   public DataSource dataSource = new DataSource();

}
