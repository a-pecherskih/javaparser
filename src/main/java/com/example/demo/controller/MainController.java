package com.example.demo.controller;

import com.example.demo.visitor.CommentReporterComplete;
import com.example.demo.visitor.ModifyingVisitorComplete;
import com.example.demo.visitor.VoidVisitorComplete;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("test")
public class MainController {

    @GetMapping("/1")
    public void test1(VoidVisitorComplete vvc) throws FileNotFoundException {
        vvc.main();
    }

    @GetMapping("/2")
    public void test2(ModifyingVisitorComplete mvc) throws FileNotFoundException {
        mvc.main();
    }

    @GetMapping("/3")
    public void test2(CommentReporterComplete crc) throws FileNotFoundException {
        crc.main();
    }
}
