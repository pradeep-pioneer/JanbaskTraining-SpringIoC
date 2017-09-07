package com.janbask.training3;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Scanner;

public class QuizMaster implements Master {
    private Service quizService;
    private String contestantName;
    private int score = 0;
    private int questionsAsked = 0;
    public QuizMaster(){

    }

    public QuizMaster(Service quizService){
        this.quizService = quizService;
    }

    public void initialize() throws FileSystemException{
        try {
            quizService.initialize();
        }catch (IOException exception){
            //ToDo: Add a logging component and register it with Spring and use it to log exceptions in all classes(hint use dependency injection).
            exception.printStackTrace();
            FileSystemException throwable = new FileSystemException("There was an error initializing the quiz service!");
            throwable.addSuppressed(exception);
            throw throwable;
        }
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        if(contestantName==null||contestantName.trim().isEmpty())
            throw new NullPointerException("Contestant name is required!");
        else{
            Question question;
            while ((question = quizService.getQuestion())!=null){
                System.out.println(String.format("\nHere is your question:\n%s?\nOptions are:", question.getQuestionText()));
                for (Answer item:question.getAnswers()) {
                    System.out.println(String.format("\t%s: %s", item.getOption(), item.getOptionText()));
                }
                System.out.println("Your answer is: ");
                String answer = scanner.nextLine();
                boolean result = answer.toLowerCase().equals(Character.toString(question.getAnswerOption()).toLowerCase());
                System.out.println(String.format("Your answer is %s", result?"Correct":"Incorrect"));
                if(result)
                    score+=1;
                questionsAsked+=1;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getQuestionsAsked() {
        return questionsAsked;
    }

    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    public Service getQuizService() {
        return quizService;
    }

    public void setQuizService(Service quizService) {
        this.quizService = quizService;
    }
}
