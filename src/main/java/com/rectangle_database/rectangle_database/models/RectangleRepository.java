package com.rectangle_database.rectangle_database.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface RectangleRepository extends JpaRepository<Rectangle,Integer> {
    List<Rectangle> findById(int id);
}
