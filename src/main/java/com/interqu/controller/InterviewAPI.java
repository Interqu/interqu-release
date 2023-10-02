package com.interqu.controller;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import com.amazonaws.Response;
import com.interqu.db.InterviewMetadataRepository;
import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.InterviewVideoDataRepository;
import com.interqu.db.PendingUserRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.db.UserRepository;
import com.interqu.interviews.InterviewMetadata;
import com.interqu.interviews.InterviewVideoData;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;
import com.interqu.interviews.questions.QuestionTips;
import com.interqu.process.FileService;
import com.interqu.process.S3Service;
import com.interqu.process.model.AWSPresignedURLModel;
import com.interqu.survey.BetaTestUserAnswer;
import com.interqu.user.CustomUserDetails;
import com.interqu.user.PendingUser;
import com.interqu.user.User;
import com.interqu.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api")
public class InterviewAPI {

    @Value("${aws.bucket.name}")
    private String bucketName;

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

    @Autowired
    private S3Service s3Service;

    @Autowired
    private InterviewMetadataRepository interviewMetadataRepo;

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
            //Call an async pub/sub method here.
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

    @GetMapping("/user/getAllInterviews")
    public ResponseEntity<?> retriveAllInterviews(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok().body(interviewMetadataRepo.findByUser(userDetails.getUsername()));   
    }

    @PostMapping("/questionDetails")
    public ResponseEntity<?> retriveQuestionDetails(@RequestBody List<String> questionIds){
        return ResponseEntity.ok().body(questionRepo.findByQuestionIdIn(questionIds));
    }

    //TODO move to /secured endpoint
    @GetMapping("/presigned-url")
    public ResponseEntity<?> generatePresignedURL(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam(name = "questionId")String questionId,  HttpServletRequest request, HttpServletResponse response){
        //Ensure user is not null
        // if(userDetails==null){
        //     return ResponseEntity.badRequest().body("user could not be identified.");
        // }
        //Find Question in Database
        Question question = questionRepo.findByQuestionId(questionId);
        if(question==null){
            return ResponseEntity.badRequest().body("question could not be retrived.");
        }
        //Generate file name TODO store in mongodb
        String fileName = Utils.setInterviewVideoData(userDetails.getUser(), question).getFileName();
        //Generate video metadata
        Map<String, String> metadata = Utils.generateInterviewMetadata(userDetails.getUser(), question);
        //Generate code
        String url = s3Service.generatePresignedUrl(userDetails.getUser(), question , metadata, fileName);
        
        //Store in mongodb
        InterviewMetadata im = new InterviewMetadata(userDetails.getUsername(), fileName, new Date(), null, "Uploading", question.getQuestionId());
        interviewMetadataRepo.save(im);
        return ResponseEntity.ok().body(new AWSPresignedURLModel(url, metadata, fileName));
    }
}
