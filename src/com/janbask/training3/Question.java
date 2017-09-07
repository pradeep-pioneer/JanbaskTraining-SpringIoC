package com.janbask.training3;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private ArrayList<Answer> answers;
    private char answerOption;
    private String questionText;

    public Question(){
        answers = new ArrayList<>();
        answerOption='-';
        questionText="Empty";
    }

    public Question(String questionText, ArrayList<Answer> answers, char answerOption){
        this.answers = answers;
        this.answerOption=answerOption;
        this.questionText=questionText;
    }

    public Question(String encodedData){
        if(encodedData==null||encodedData.trim().isEmpty()){
            this.answers=new ArrayList<>();
            answerOption='-';
            questionText="Empty";
        }else{
            String[] items = encodedData.split("\\?");
            this.answers=new ArrayList<>();
            if(items.length>2){
                //ToDo: Add validations
                this.questionText = items[0];
                this.answerOption = items[2].charAt(0);
                String[] answerStrings = items[1].split("`");
                for (String item:answerStrings) {
                    try {
                        Answer answer = new Answer(item);
                        answers.add(answer);
                    }catch (InstantiationException ex){
                        ex.printStackTrace();
                    }
                }
            }else{
                answerOption = '-';
                questionText = "Invalid Markup";
            }
        }
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public char getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(char answerOption) {
        this.answerOption = answerOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
