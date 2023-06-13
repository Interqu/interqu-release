package com.interqu.controller;

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
import com.interqu.interviews.Result;
import com.interqu.interviews.questions.Question;

@Controller
@RequestMapping("/user/")
public class InterviewController {

    private static final String PAGE_PATH = "";
    private static final String URL_PATH = "";

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private PositionRepository positionRepo;

    @Autowired
    private QuestionTipsRepository qtRepo;

    // @GetMapping("insert-position")
    // @ResponseBody
    // public String insertPosition(@PathVariable String position) {
    //     try {
    //         positionRepo.insert(new Position(position));
    //         return "Success!";
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "Error";
    //     }
    // }

    @GetMapping("interview-selection")
    public ModelAndView interviewSelection() {
        ModelAndView mvc = new ModelAndView("/interview-selection");
        mvc.addObject("positions", positionRepo.findAll());
        try {
            mvc.addObject("questions", iqRepo.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            mvc.addObject("errors", "Could not retrive interview questions");
        }
        return mvc;
    }

    // TODO disable all access feature
    @GetMapping("interview/{questionId}")
    public ModelAndView interviewPractice(@PathVariable String questionId) {
        ModelAndView mvc = new ModelAndView(PAGE_PATH + "/interview-practice");
        Question question = iqRepo.findByQuestionId(questionId);
        if(question==null){
            return new ModelAndView(PAGE_PATH + "/interview-selection");
        }
        mvc.addObject("question", question);
        return mvc;
    }

    @GetMapping("interview/result")
    public ModelAndView interviewResult() {
        ModelAndView mvc = new ModelAndView(PAGE_PATH + "/interview-result");
        //TODO pull from database
        //TODO add video link
        Result result = new Result();
        result.setEmail("rejie.li@gmail.com");
        result.setPrompt("What makes you motivated?");
        result.setPosition("Software Engineer");
        result.setOverallScore(60);
        result.setTimeTaken("1m 12s");
        result.setOverallVideoEmotionTitle("Angry");
        result.setVideoFeedBack("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.");
        result.setVideoScore(30);
        result.setOverallAudioEmotionTitle("Confident");
        result.setAudioFeedback("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.");
        result.setAudioScore(90);
        result.setContextScore(60);
        result.setOverallContextTitle("Detailed");
        result.setContextFeedback("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed id semper risus in hendrerit gravida rutrum quisque. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Elit duis tristique sollicitudin nibh sit amet. Pellentesque habitant morbi tristique senectus et netus et malesuada. Consequat interdum varius sit amet mattis vulputate enim. Eget nullam non nisi est sit. Duis convallis convallis tellus id interdum velit.");
        mvc.addObject("result", result);
        return mvc;
    }
}
