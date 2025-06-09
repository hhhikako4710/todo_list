package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Todo;

public class TodoValidator {
    // バリデーションを実行する
    public static List<String> validate(Todo t) {
        List<String> errors = new ArrayList<String>();

        String content_error = validateContent(t.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "やることを入力してください。";
        }

        return "";
    }
}