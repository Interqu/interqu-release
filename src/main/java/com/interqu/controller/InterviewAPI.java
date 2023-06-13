package com.interqu.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.interqu.db.PendingUserRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.db.UserRepository;
import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;
import com.interqu.interviews.questions.QuestionTips;
import com.interqu.survey.BetaTestUserAnswer;
import com.interqu.user.PendingUser;
import com.interqu.user.User;
import com.interqu.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.S3Client;

@Controller
@RequestMapping("api")
public class InterviewAPI {

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

    // @PostMapping("/user/createPendingUser")
    // @PreAuthorize("hasRole('DEV') or hasRole('ADMIN')")
    // public ModelAndView createPendingUser(PendingUser pendingUser) {
        
    //     QuestionTips q = qtRepo.findByQuestion(question.getQuestion());
    //     if(q != null){
    //         return q.getTips();
    //     }
    //     return new ArrayList<String>();
    // }

    @PostMapping("/processInterview")
    public ResponseEntity<?> processInterview(HttpServletRequest request, @RequestParam("video") MultipartFile file, @RequestParam("questionId") String questionId){
        InterviewVideoData ivd;
        User user = null;
        Principal principal = request.getUserPrincipal();        
        // System.out.println(((UserDetails)principal).getUsername());
        if(principal instanceof UserDetails){
            user = userRepo.findByEmail(((UserDetails)principal).getUsername());
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
           Utils.uploadFile(ivd.getFileName(), file.getInputStream());
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
