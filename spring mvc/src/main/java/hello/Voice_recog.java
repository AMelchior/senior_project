package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Voice_recog {
    private String content;
    private String[] words;
    private List<Long> startTimes;
    private List<Long> endTimes;
    private Voice_recog obj;
    private FileSystemResource fsr;

    public Voice_recog( String content) {
        if( content != null && !content.equals("")) {
            fillWords();
        }
        fsr = new FileSystemResource(new File
                (System.getProperty("user.dir") + "\\"+ Properties.audio));
    }

    private void fillWords() {
        String temp = content.substring(1, content.length() - 1);     //[["A",x,y],["B",x,y]] -> ["A",x,y],["B",x,y]
        words = temp.split(Pattern.quote("],"));                   //["A",x,y   ["B",x,y]
        String[] newwords = words;
        int i = 0;
        for (String word : words) {
            String[] s = word.split(",");                       //"A"    "B"
            word = s[0].substring(2,s[0].length()-1);
            newwords[i] = word;
            //startTimes.add(Long.parseLong(s[1].replaceAll("[^\\d.]", "")));  //x
            //endTimes  .add(Long.parseLong(s[2].replaceAll("[^\\d.]", "")));  //y
            i++;
        }
        words = newwords;
    }

    public Voice_recog getObj() { return obj; }

    FileSystemResource getFileSystemResource() {
        return fsr;
    }

    void setContent (String content ) {this.content = content; fillWords();}

    String getContent() { return content; }

    List<Long> getStartTimes() { return startTimes;}

    List<Long> getEndTimes() { return endTimes;}

    String[] getWords() { return words;}

    @Override
    public String toString() {
        return "Voice{ " +
                "content= " + content +  '}';
    }
}
