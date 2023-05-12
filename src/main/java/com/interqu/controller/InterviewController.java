package com.interqu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.PositionRepository;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;

@Controller
public class InterviewController {

    private final String PAGE_PATH = "";
    private final String URL_PATH = "";

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private PositionRepository positionRepo;

    @GetMapping("dev/insert-position")
    @ResponseBody
    public String insertPosition(@PathVariable String position) {
        try {
            positionRepo.insert(new Position(position));
            return "Success!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @GetMapping("dev/insert-questions")
    @ResponseBody
    public String insertQuestions() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("Prototype I Questions - Consultant.csv"));

            scanner.useDelimiter(";");
            // iterate through each question
            while (scanner.hasNext()) {
                String title = "";
                List<String> pos = new ArrayList<String>();
                List<String> neg = new ArrayList<String>();
                String interviewTips = "";
                Scanner scannerQuestion = new Scanner(scanner.next());
                scannerQuestion.useDelimiter("#");
                // Title
                title = scannerQuestion.next();
                System.out.println(title);
                // Pos Ind
                Scanner posInd = new Scanner(scannerQuestion.next());
                posInd.useDelimiter(",");
                while (posInd.hasNext()) {
                    pos.add(posInd.next().trim());
                }
                // posInd.close();
                // Neg ind
                Scanner negInd = new Scanner(scannerQuestion.next());
                negInd.useDelimiter(",");
                while (negInd.hasNext()) {
                    neg.add(negInd.next().trim());
                }
                // negInd.close();
                interviewTips = scannerQuestion.next();
                iqRepo.insert(new Question("Software Engineer", title, pos, neg, interviewTips));
            }
            // scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "Success";

    }

    @GetMapping("dev/interview-selection")
    public ModelAndView interviewSelection() {
        ModelAndView mvc = new ModelAndView("/interview-selection");
        mvc.addObject("positions", positionRepo.findAll());
        try {
            // TODO remove tips in questions, and make front end request each time.
            mvc.addObject("questions", iqRepo.findAll());
        } catch (Exception e) {
            mvc.addObject("errors", "Could not retrive interview questions");
        }
        return mvc;
    }

    // TODO disable all access feature
    @GetMapping("dev/interview")
    public ModelAndView interviewPractice() {
        return new ModelAndView(PAGE_PATH + "/interview-practice");
    }

    @GetMapping("dev/interview/result")
    public ModelAndView interviewResult() {
        return new ModelAndView(PAGE_PATH + "/interview-result");
    }

    // @GetMapping("dev/test")
    // @ResponseBody
    // public String test() {
    // return "Hi";
    // }
}
