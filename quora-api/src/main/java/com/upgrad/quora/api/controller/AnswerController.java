package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.AnswerRequest;
import com.upgrad.quora.api.model.AnswerResponse;
import com.upgrad.quora.service.business.AnswerService;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /**
     * This endpoint create answer to a question
     *
     * @param questionId    : Question ID that you want to answer
     * @param accessToken   : access-token to authenticate
     * @param answerRequest : The answer body
     * @return answer creation resopnse
     * @throws AuthorizationFailedException : Returns authorization failed exception
     * @throws InvalidQuestionException     : If question id is invalid returns invalid question response
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/question/{questionId}/answer/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerResponse> createAnswer(
            @RequestHeader("authorization") final String accessToken,
            @PathVariable("questionId") final String questionId,
            AnswerRequest answerRequest)
            throws AuthorizationFailedException, InvalidQuestionException {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswer(answerRequest.getAnswer());
        answerEntity = answerService.createAnswer(answerEntity, accessToken, questionId);
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setId(answerEntity.getUuid());
        answerResponse.setStatus("ANSWER CREATED");
        return new ResponseEntity<AnswerResponse>(answerResponse, HttpStatus.CREATED);
    }
}