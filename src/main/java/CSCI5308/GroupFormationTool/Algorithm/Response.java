package CSCI5308.GroupFormationTool.Algorithm;

public class Response {
    private long id;
    private String questionID;
    private String userID;
    private String response;
    private String surveyID;

    public long getId() {
        return id;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getResponse() {
        return response;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }
}
