package com.interqu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.QuestionTips;

@Controller
@RequestMapping("/dev/")
public class InterviewController {

    private static final String PAGE_PATH = "";
    private static final String URL_PATH = "";

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private PositionRepository positionRepo;

    @Autowired
    private QuestionTipsRepository qtRepo;

    @GetMapping("insert-position")
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

    @GetMapping("insert-questions")
    @ResponseBody
    public String insertQuestions() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("interqu-release\\Prototype I Questions - Consultant.csv"));

            scanner.useDelimiter(";");
            // iterate through each question
            while (scanner.hasNext()) {
                String title = "";
                List<String> pos = new ArrayList<String>();
                List<String> neg = new ArrayList<String>();
                List<String> tips = new ArrayList<String>();
                Scanner scannerQuestion = new Scanner(scanner.next());
                scannerQuestion.useDelimiter("#");
                // Title
                title = scannerQuestion.next().trim();
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
                Scanner tipsScanner = new Scanner(scannerQuestion.next());
                tipsScanner.useDelimiter("\n");
                while(tipsScanner.hasNext()){
                    String tip = tipsScanner.next().trim();
                    if(!tip.equals("")){
                        tips.add(tip);
                    }
                }
                if(qtRepo.findByQuestion(title)!=null){
                    qtRepo.insert(new QuestionTips(title, tips));
                }
                
                // iqRepo.insert(new Question("Software Engineer", title, pos, neg));
            }
            // scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "Success";

    }

    @GetMapping("interview-selection")
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
    @GetMapping("interview")
    public ModelAndView interviewPractice() {
        return new ModelAndView(PAGE_PATH + "/interview-practice");
    }

    @GetMapping("interview/result")
    public ModelAndView interviewResult() {
        return new ModelAndView(PAGE_PATH + "/interview-result");
    }
}
