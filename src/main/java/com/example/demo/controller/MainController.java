package com.example.demo.controller;

import com.example.demo.visitor.VoidVisitorComplete;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.stmt.Statement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("test")
public class MainController {

    @GetMapping("/1")
    public Statement list() {
        Statement statement = StaticJavaParser.parseStatement("int a = 0;");
        return statement;
    }

    @GetMapping("/2")
    public void list2() throws FileNotFoundException {
        VoidVisitorComplete vvc = new VoidVisitorComplete();
        vvc.main();
    }
}
