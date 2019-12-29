package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String listStudent(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String studentForm(Model model){

        model.addAttribute("student", new Student());
        return "studentform";
    }


    @PostMapping("/process")
    public String processForm(@Valid Student student, BindingResult result){
        if (result.hasErrors()){ return "studentform";}
        studentRepository.save(student);
        return "redirect:/";
    }

    @RequestMapping("/details/{id}")
    public String showStudent(@PathVariable("id") long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        return "show";
    }


    @RequestMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        return "studentform";
    }


    @RequestMapping("delete/{id}")
    public String delStudent(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}
