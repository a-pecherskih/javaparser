package com.example.demo.controller;

import com.example.demo.service.LexicalPreservationComplete;
import com.example.demo.visitor.CommentReporterComplete;
import com.example.demo.visitor.ModifyingVisitorComplete;
import com.example.demo.visitor.PrettyPrintComplete;
import com.example.demo.visitor.VoidVisitorComplete;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
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
    public void test3(CommentReporterComplete crc) throws FileNotFoundException {
        crc.main();
    }

    @GetMapping("/4")
    public void test4(ClassOrInterfaceDeclaration myClass) throws FileNotFoundException {
        myClass.setComment(new LineComment("A very cool class!"));
        myClass.setName("MyClass");
        myClass.addField("String", "foo");
        System.out.println(myClass);
    }

    @GetMapping("/5")
    public void test5(PrettyPrintComplete ppc) throws FileNotFoundException {
        ppc.main();
    }

    @GetMapping("/6")
    public void test6() throws FileNotFoundException {
        String code = "// Hey, this is a comment\n\n\n// Another one\n\nclass A { }";;
        CompilationUnit cu = StaticJavaParser.parse(code);
        LexicalPreservingPrinter.setup(cu);
        System.out.println(LexicalPreservingPrinter.print(cu));
    }

    @GetMapping("/7")
    public void test7(LexicalPreservationComplete lpc) throws FileNotFoundException {
        lpc.main();
    }
}
