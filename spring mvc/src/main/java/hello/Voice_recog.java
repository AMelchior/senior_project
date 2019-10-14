package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Voice_recog {
    private long id;
    private String content;
    private long[] times;

    public Voice_recog(long id, String content, long... times) {
        this.id = id;
        this.content = content;
        this.times = times;
    }

    public long getId() {
        return id;
    }

    public void setContent (String content ) {this.content = content;}

    public String getCotnent() {
        return content;
    }

    @Override
    public String toString() {
        String s = "Voice{" +
                "id= " + id + '\'' +
                "content= " + content + '\'' +
                ", times= " ;
        for (long t: times)
            s+=(t + " ");
        return s + '}';
    }
}
