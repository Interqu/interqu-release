package com.interqu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.InterviewVideoDataRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.db.UserRepository;
import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;
import com.interqu.interviews.questions.QuestionTips;
import com.interqu.process.FileService;
import com.interqu.survey.BetaTestUserAnswer;
import com.interqu.user.User;
import com.interqu.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/interview/")
public class InterviewAPI extends API{

    @Autowired
    private PositionRepository positionRepo;

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private InterviewVideoDataRepository ivdRepo;

    @Autowired
    private InterviewQuestionRepository questionRepo;

    @Autowired
    private QuestionTipsRepository qtRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FileService fileService;

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
        // TODO check if user has authority to check
        QuestionTips q = qtRepo.findByQuestionId(question.getQuestionId());
        if(q != null){
            return q.getTips();
        }
        return new ArrayList<String>();
    }

    @PostMapping(path="/registerBetaUser")
    public ModelAndView registerBetaUser(@ModelAttribute("BetaTestUserAnswer") BetaTestUserAnswer user){
        System.out.println(user.toString());
        ModelAndView mvc = new ModelAndView("error");
            mvc.addObject("Error", "An unexpected error has occred");
            return mvc;
    }

    @PostMapping("/uploadInterview")
    public ResponseEntity<?> processInterview(HttpServletRequest request, @RequestParam("video") MultipartFile file, @RequestParam("questionId") String questionId){
        InterviewVideoData ivd;
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
             user = userRepo.findByEmail(username);
        }
        try{
            if(user == null){
                return new ResponseEntity<>("You do not have access to this page!", HttpStatus.FORBIDDEN);
            }
            Question question = questionRepo.findByQuestionId(questionId);
            
            ivd= Utils.setInterviewVideoData(user, question);
            ivdRepo.insert(ivd);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
           fileService.uploadFile(ivd.getFileName(), file);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch(Exception e){
            ivdRepo.delete(ivd);
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //FOR DEV ONLY
    @PostMapping("/insertQuestion")
    public ResponseEntity<String> insertQuestion(@RequestBody Map<String, Object> interviewQuestions){
        try{
            //ensure question is unique
            Question questions = new Question((Map<String,Object>)interviewQuestions.get("Question"));
            if(questionRepo.findByPositionAndQuestion(questions.getPosition(), questions.getQuestion())!=null){
                return new ResponseEntity<>("Question already exists!", HttpStatus.ALREADY_REPORTED);
            }
            QuestionTips questionTips = new QuestionTips((List<String>)interviewQuestions.get("QuestionTips"));
            if(questions==null || questionTips==null){
                return new ResponseEntity<>("RequestBody would not be parsed", HttpStatus.BAD_REQUEST);
            }
            questionTips.setQuestion(questions.getQuestion());
            questionTips.setQuestionId(questions.getQuestionId());
            questionRepo.insert(questions);
            qtRepo.insert(questionTips);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
