package test.model;

public class ScoreInfo {

    private String sid;
    private Float chinese;
    private Float math;
    private Float english;

    public ScoreInfo() {
    }

    public ScoreInfo(String sid, Float chinese, Float math, Float english) {
        this.sid = sid;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "sid='" + sid + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Float getChinese() {
        return chinese;
    }

    public void setChinese(Float chinese) {
        this.chinese = chinese;
    }

    public Float getMath() {
        return math;
    }

    public void setMath(Float math) {
        this.math = math;
    }

    public Float getEnglish() {
        return english;
    }

    public void setEnglish(Float english) {
        this.english = english;
    }
}
