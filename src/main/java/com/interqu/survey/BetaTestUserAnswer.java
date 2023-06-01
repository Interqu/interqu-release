package com.interqu.survey;

public class BetaTestUserAnswer {
    
    private String email;
    //What is your age?    
    private String age;
    //Educational Background (School and Degree if applicable)
    private String education;
    //What field are you pursing/currently in
    private String field;
    //How many years have you’ve been working in the industry
    private String years;
    //How many interviews have you’ve completed?
    private String interviews;
    //How do you currently practice for interviews
    private String currentInterviewPractice;
    //Password
    private String password;
    public BetaTestUserAnswer(String email, String age, String education, String field, String years, String interviews,
            String currentInterviewPractice, String password) {
                this.email = email;
        this.age = age;
        this.education = education;
        this.field = field;
        this.years = years;
        this.interviews = interviews;
        this.currentInterviewPractice = currentInterviewPractice;
        this.password = password;
    }

    

    public BetaTestUserAnswer() {
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getYears() {
        return years;
    }
    public void setYears(String years) {
        this.years = years;
    }
    public String getInterviews() {
        return interviews;
    }
    public void setInterviews(String interviews) {
        this.interviews = interviews;
    }
    public String getCurrentInterviewPractice() {
        return currentInterviewPractice;
    }
    public void setCurrentInterviewPractice(String currentInterviewPractice) {
        this.currentInterviewPractice = currentInterviewPractice;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "BetaTestUserAnswer [email=" + email + ", age=" + age + ", education=" + education + ", field=" + field
                + ", years=" + years + ", interviews=" + interviews + ", currentInterviewPractice="
                + currentInterviewPractice + ", password=" + password + "]";
    }

    

}
