package com.janbask.training3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.ArrayList;

public class FileQuizService implements Service {

    public static final String FILENAME_NOT_INITIALIZED = "fileName not initialized.";

    private ArrayList<Question> questions;
    private String fileName;
    private int counter = 0;
    public FileQuizService(){

    }

    public FileQuizService(String sourceFileName){
        //ToDo: add validations here
        fileName = sourceFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void initialize() throws NullPointerException, FileNotFoundException, IOException{
        questions = new ArrayList<>();
        if(fileName==null||fileName.trim().isEmpty())
            throw new NullPointerException(FILENAME_NOT_INITIALIZED);
        else{
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                Question question = new Question(line);
                questions.add(question);
            }
            System.out.println(String.format("\nSuccess: Loaded FileQuizService!\n\tTotal Questions: %s", questions.size()));
        }
    }

    @Override
    public Question getQuestion() {
        if(counter<questions.size()) {
            return questions.get(counter++);
        }
        else
            return null;
    }

    @Override
    public ArrayList<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public ArrayList<Question> getAllQuestions(boolean randomize) {
        throw new NotImplementedException();
    }
}
