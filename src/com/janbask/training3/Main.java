package com.janbask.training3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.nio.file.FileSystemException;
import java.util.Scanner;

public class Main {
    static final String QUIZ_FILE_NAME = "src/data/questions.dat";
    public static void main(String[] args) {
        String contestantName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our quiz service!\nPlease enter your name (*required): ");
        contestantName = scanner.nextLine();

	    //Code without IoC and Spring
        Service quizService = new FileQuizService(QUIZ_FILE_NAME);
        Master quizMaster = new QuizMaster(quizService);

        //code with IoC using Spring
        /*ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Master quizMaster = (Master) context.getBean("quizMaster");*/

        quizMaster.setContestantName(contestantName);
        try{
            quizMaster.initialize();
            quizMaster.start();
            System.out.println(String.format("\nThat's all we had!\nFetching results...\nYou scored %s out of %s.\nThanks for playing!",
                    quizMaster.getScore(), quizMaster.getQuestionsAsked()));
        }catch (FileSystemException exception){
            exception.printStackTrace();
            System.out.println("Fatal: could not initialize the quiz master!\nTerminating...");
            return;
        }
    }
}
