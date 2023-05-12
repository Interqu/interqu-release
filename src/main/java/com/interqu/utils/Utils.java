package com.interqu.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.interviews.questions.Question;

public class Utils {

    // @Autowired
    // private InterviewQuestionRepository iqRepo;

    // public static void extractQuestionFromCSV(String position, File file) {
    // Scanner scanner;
    // try {
    // scanner = new Scanner(file);

    // scanner.useDelimiter(",");
    // ArrayList<String> input = new ArrayList<String>();
    // while (scanner.hasNext()) {
    // input.add(scanner.next());
    // }
    // input.remove(0);
    // input.remove(0);
    // input.remove(0);
    // List<Question> questions = new ArrayList<Question>();
    // // Start at index 3; 10 for 10 questions
    // for (int i = 0; i < 10; i++) {
    // String title = input.get(0);
    // input.remove(0);
    // List<String> pos = new ArrayList<String>();
    // List<String> neg = new ArrayList<String>();
    // for (int j = 0; j < 10; j++) {
    // pos.add(input.get(0));
    // input.remove(0);
    // neg.add(input.get(0));
    // input.remove(0);
    // }
    // // questions.add(i, new Question(title, pos, neg));
    // iqRepo.insert(new Question(position, title, pos, neg));
    // }
    // scanner.close();
    // // return new InterviewQuestions("Consultant", questions);
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}
