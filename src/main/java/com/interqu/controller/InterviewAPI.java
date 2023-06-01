package com.interqu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.PendingUserRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.db.UserRepository;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;
import com.interqu.interviews.questions.QuestionTips;
import com.interqu.survey.BetaTestUserAnswer;

@Controller
@RequestMapping("api")
public class InterviewAPI {

    @Autowired
    private PositionRepository positionRepo;

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private PendingUserRepository pendingUserRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private QuestionTipsRepository qtRepo;

    @PostMapping("/getPositions")
    @ResponseBody
    public List<Position> getPositions() {
        return positionRepo.findAll();
    }

    @PostMapping("/getQuestions")
    @ResponseBody
    public List<Question> getQuestionByPosition(@RequestBody String position) {
        return iqRepo.findByPosition(position);
    }

    @PostMapping("/getInterviewTip")
    @ResponseBody
    public List<String> getInterviewTipByQuestion(@RequestBody Question question) {
        System.out.println(question.getQuestion());
        // TODO check if user has authority to check
        QuestionTips q = qtRepo.findByQuestion(question.getQuestion());
        if(q != null){
            return q.getTips();
        }
        return new ArrayList<String>();
    }

    @PostMapping(path="/registerBetaUser")
    public ModelAndView registerBetaUser(@ModelAttribute("BetaTestUserAnswer") BetaTestUserAnswer user){
        System.out.println(user.toString());
        // PendingUser p = pendingUserRepo.findByEmail(user.getEmail());
        // try{
        //     if(p!=null){
        //         pendingUserRepo.delete(p);
        //         user.setBanned(false);
        //         user.setVerified(true);
        //         user.setDateRegistered(new Date(System.currentTimeMillis()));
        //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //         String encodedPassword = encoder.encode(user.getPassword());
        //         user.setPassword(encodedPassword);
        //         userRepo.save(user);
        //         ModelAndView mvc = new ModelAndView("login");
        //         mvc.addObject("success_message", "Successfully Registered.");
        //         return mvc;
        //     }else{
        //         ModelAndView mvc = new ModelAndView("link-expired");
        //         return mvc;
        //     }
        // }catch(Exception e){
        //     ModelAndView mvc = new ModelAndView("error");
        //     mvc.addObject("Error", "An unexpected error has occred");
        //     return mvc;
        // }
        ModelAndView mvc = new ModelAndView("error");
            mvc.addObject("Error", "An unexpected error has occred");
            return mvc;
    }
}
