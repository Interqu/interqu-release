package com.interqu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interqu.db.InterviewQuestionRepository;
import com.interqu.db.PositionRepository;
import com.interqu.db.QuestionTipsRepository;
import com.interqu.interviews.Position;
import com.interqu.interviews.questions.Question;

@RestController("api")
public class InterviewAPI {

    @Autowired
    private PositionRepository positionRepo;

    @Autowired
    private InterviewQuestionRepository iqRepo;

    @Autowired
    private QuestionTipsRepository qtRepo;

    @PostMapping("getPositions")
    public List<Position> getPositions() {
        return positionRepo.findAll();
    }

    @PostMapping("getQuestions")
    public List<Question> getQuestionByPosition(@RequestBody String position) {
        return iqRepo.findByPosition(position);
    }

    @PostMapping("getInterviewTip")
    public String getInterviewTipByQuestion(@RequestBody String question) {
        // TODO check if user has authority to check
        return qtRepo.findByQuestion(question).getTips();
    }

}
