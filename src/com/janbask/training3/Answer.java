package com.janbask.training3;

public class Answer {

    public static final String INSTANTIATION_EXCEPTION_MESSAGE = "The data is in invalid format, cannot create instance of object <Answer>.";
    private char option;
    private String optionText;

    public Answer(){
        option = 'A';
        optionText = "Empty";
    }

    public Answer(char option, String optionText){
        this.option = option;
        this.optionText = optionText;
    }

    public Answer(String encodedString)
            throws InstantiationException{
        //You might add validations for additional cases like zero length string for option (the string for option should have at leat 1 char.
        String[] items = encodedString.split("~");
        if(items.length<1)
            throw new InstantiationException(INSTANTIATION_EXCEPTION_MESSAGE);
        else{
            option = items[0].charAt(0);
            optionText=items[1];
        }
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
