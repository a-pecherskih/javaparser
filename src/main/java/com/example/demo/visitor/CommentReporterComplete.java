package com.example.demo.visitor;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class CommentReporterComplete {

    private static final String FILE_PATH  = "src/main/java/com/example/demo/service/ReversePolishNotation.java";

    public void main() throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(FILE_PATH));

        List<CommentReportEntry> comments = cu.getAllContainedComments()
                .stream()
                .map(p -> new CommentReportEntry(p.getClass().getSimpleName(),
                        p.getContent(),
                        p.getRange().get().begin.line,
                        !p.getCommentedNode().isPresent()))
                .collect(Collectors.toList());

        comments.forEach(System.out::println);
    }

    private static class CommentReportEntry {
        private String type;
        private String text;
        private int lineNumber;
        private boolean isOrphan;

        CommentReportEntry(String type, String text, int lineNumber, boolean isOrphan) {
            this.type = type;
            this.text = text;
            this.lineNumber = lineNumber;
            this.isOrphan = isOrphan;
        }

        @Override
        public String toString() {
            return lineNumber + "|" + type + "|" + isOrphan + "|" + text.replaceAll("\\n","").trim();
        }
    }
}
