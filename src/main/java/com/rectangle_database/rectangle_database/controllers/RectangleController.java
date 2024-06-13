package com.rectangle_database.rectangle_database.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.rectangle_database.rectangle_database.models.Rectangle;
import com.rectangle_database.rectangle_database.models.RectangleRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RectangleController {

    @Autowired
    private RectangleRepository rectangleRepo;

    // Makes an attribute holding all the rectangle information to display on main page
    @GetMapping("/")
    public String getAllRectangles(Model model) {
        List<Rectangle> rectangles = rectangleRepo.findAll();
        model.addAttribute("recs", rectangles);
        return "rectangles/home";
    }   

    // User is sent here by clicking on the name of a rectangle on the main page
    // Find the rectangle associated with the id 
    // Send user to editing page
    @GetMapping("rectangles/edit{id}") 
    public String edit(@RequestParam int id, Model model) {
        System.out.println("editing");
        Rectangle rectangle = rectangleRepo.findById(id).get(0);
        model.addAttribute("info", rectangle);
        model.addAttribute("id",id);
        model.addAttribute("editing", true);
        return "rectangles/info";
    }

    // User sent here from new rectangle button on main page
    // Takes user to rectangle creation page 
    // Sets editing to false so the info page knows not to display anything yet
    @GetMapping("rectangles/create") 
    public String create(Model model) {
        System.out.println("creating");
        model.addAttribute("editing", false);   
        return "rectangles/info";
    }

    // Gets information from the form after the user clicks the edit button
    // Update the rectangle with new information
    // Send user back to main page
    @PostMapping("rectangles/edit{id}")
    public RedirectView editRectangle(@RequestParam Map<String, String> newRectangle, @RequestParam int id) {  
        Rectangle rectangle = rectangleRepo.findById(id).get(0);
        rectangle.setName(newRectangle.get("name"));
        rectangle.setWidth(Integer.parseInt(newRectangle.get("width")));
        rectangle.setHeight(Integer.parseInt(newRectangle.get("height")));
        rectangle.setColor(newRectangle.get("color"));
        rectangleRepo.save(rectangle);

        return new RedirectView("/");
    }

    // Gets information from the form after the user clicks the edit button
    // Use the information to create a new rectangle
    // Send user back to main page
    @PostMapping("rectangles/create")
    public RedirectView createRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response) {
        System.out.println("Add rectangle");
        String newName = newRectangle.get("name");
        int newWidth = Integer.parseInt(newRectangle.get("width"));
        int newHeight = Integer.parseInt(newRectangle.get("height"));
        String newColor = newRectangle.get("color");
        rectangleRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
        response.setStatus(201);
    
        return new RedirectView("/");
    }

    // Cancel button sends user back to main page
    @GetMapping("rectangles/cancel")
    public RedirectView cancel() {
        return new RedirectView("/");
    }

    @GetMapping("rectangles/delete{id}")
    public RedirectView delete(@RequestParam int id) {
        rectangleRepo.deleteById(id);
        return new RedirectView("/");
    }

}
