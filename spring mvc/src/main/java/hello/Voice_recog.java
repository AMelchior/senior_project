package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Voice_recog {
    private long id;
    private String content;
    private String[] words;
    private Voice_recog obj;


    public Voice_recog(long id, String content) {
        this.id = id;
        this.content = content.substring(1,content.length()-2);     //[["A",x,y],["B",x,y]] -> ["A",x,y],["B",x,y]
        words = content.split(Pattern.quote("], "));             //["A",x,y   ["B",x,y]
        for( String word : words )
            word = word.replace("[","");          //"A",x,y    "B",x,y
    }

    public Voice_recog getObj() { return obj; }

    public long getId() {
        return id;
    }

    public void setContent (String content ) {this.content = content;}

    public String getCotnent() {
        return content;
    }

    public String[] getWords() { return words;}

    @Override
    public String toString() {
        return "Voice{" +
                "id= " + id + '\'' +
                "content= " + content +  '}';
    }
}
