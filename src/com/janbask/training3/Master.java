package com.janbask.training3;

import java.nio.file.FileSystemException;

public interface Master {
    void initialize() throws FileSystemException;
    void start();
    int getScore();
    String getContestantName();
    void setContestantName(String contestantName);
    Service getQuizService();
    void setQuizService(Service quizService);
    int getQuestionsAsked();
}
