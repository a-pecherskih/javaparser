package com.example.demo.service;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;

public class ResolveMethodCalls {

    private static final String FILE_PATH = "src/main/java/com/example/demo/simple/A.java";

    public static void main() throws Exception {
        TypeSolver typeSolver = new ReflectionTypeSolver();

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

        cu.findAll(MethodCallExpr.class).forEach(mce ->
                System.out.println(mce.resolve().getQualifiedSignature()));
    }
}
