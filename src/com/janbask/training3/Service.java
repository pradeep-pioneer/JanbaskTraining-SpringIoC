package com.janbask.training3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface Service {
    void initialize() throws NullPointerException, FileNotFoundException, IOException;
    Question getQuestion();
    ArrayList<Question> getAllQuestions();
    ArrayList<Question> getAllQuestions(boolean randomize);
}
