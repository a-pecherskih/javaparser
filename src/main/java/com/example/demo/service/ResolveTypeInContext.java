package com.example.demo.service;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;

public class ResolveTypeInContext {

    private static final String FILE_PATH  = "src/main/java/com/example/demo/simple/Foo.java";
    private static final String SRC_PATH = "src/main/java";

    public static void main() throws Exception {
        TypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(new File(SRC_PATH));
        reflectionTypeSolver.setParent(reflectionTypeSolver);

        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(reflectionTypeSolver);
        combinedSolver.add(javaParserTypeSolver);

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));

        FieldDeclaration fieldDeclaration = Navigator.findNodeOfGivenClass(cu, FieldDeclaration.class);

        System.out.println("Field type: " + fieldDeclaration.getVariables().get(0).getType()
                .resolve().asReferenceType().getQualifiedName());
    }
}
