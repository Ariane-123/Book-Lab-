import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.io.FileWriter;

public class Book
{
  private String book = "";

  public Book(String url){
    readBook(url);
  }

  private void readBook(String link){
    try{
      URL url = new URL(link);
      Scanner s = new Scanner(url.openStream());
      while(s.hasNext()){
        String text = s.nextLine();
       // System.out.println(text);
        book += text;
      }
      s.close();
    }
    catch(IOException ex){
      ex.printStackTrace();
    }
    book = translateSentence(book);

    String fileName = "TranslatedFileArianeDesdier.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(book);
            System.out.println("Successfully wrote to the file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        /* try (PrintWriter out = new PrintWriter("translated_book.txt")) {
          out.println(book);   // "book" already contains your translated text
          } catch (Exception e) {
            e.printStackTrace();
          }
            */
  }

  public String pigLatin(String word)
  {
    String digits = "01234567";
    String vowels = "aeiouyAEIOUY";
    if (word.length()==0){
      return word;
    }
    else if (digits.indexOf(word.substring(0,1))>=0){
      return word;
    }
    else if (vowels.indexOf(word.substring(0,1))>=0){
      return (word+"yay");
    }
    else if (word.length()==1){
      return word + "ay";
    }
    else{
      for(int i=0; i<word.length();i++){
      if (vowels.indexOf(word.substring(i,i+1))>=0){
        String a = word.substring(0,i);
        String b = word.substring(i,word.length());
        return (b+a+"ay");
      }
    }
  }
  return word;
  }
  
  public int endPunctuation(String word)  //return the index of where the punctuation is at the end of a String. If it is all punctuation return 0, if there is no punctuation return -1
  {
    String newWord = "";
    char a = word.charAt(0);
    if (Character.isUpperCase(word.charAt(0))){
      boolean uppercase = true;
      char b = Character.toLowerCase(a);
      newWord = Character.toString(b) + word.substring(1,word.length());
    }

    String punctuation = ".,;!:'";
    for(int i=0; i<word.length();i++){
      if (punctuation.indexOf(word.substring(i,i+1))>=0){
        boolean punct = true;
        return (word.length()-i);
      }
    }
    return -1;
  }

  public String fixPunct(String word)  //return the index of where the punctuation is at the end of a String. If it is all punctuation return 0, if there is no punctuation return -1
  {
    if (word == null || word.length() == 0) {
      return word;
    }
    String halfDone = "";
    String newWord = word;
    boolean uppercase = false;
    boolean punct = false;
    char c = word.charAt(0);
    String endPunct = "";
    char firstLetter;

    if (Character.isUpperCase(c)){
      uppercase = true;
      char d = Character.toLowerCase(c);
      newWord = (Character.toString(d) + word.substring(1,word.length()));
    }

    String punctuation = ".,;!:?'";
    for(int i=0; i<newWord.length();i++){
      if (punctuation.indexOf(newWord.substring(i,i+1))>=0){
        punct = true;
        endPunct = newWord.substring(i,newWord.length());
        newWord = newWord.substring(0,i);
        break;
      }
    }
    
    halfDone = pigLatin(newWord);
    
if (uppercase && punct){
    firstLetter = halfDone.charAt(0);
    firstLetter = Character.toUpperCase(firstLetter);
    halfDone = (Character.toString(firstLetter) + halfDone.substring(1,halfDone.length()) + endPunct);
}
else if (uppercase){
    firstLetter = halfDone.charAt(0);
    firstLetter = Character.toUpperCase(firstLetter);
    halfDone = (Character.toString(firstLetter) + halfDone.substring(1,halfDone.length()));
  }
else if (punct){
    halfDone = halfDone + endPunct;
  }
else{
    return halfDone;
  }

  return halfDone;
  }

  public String translateWord(String word)    //to share with class
  {
    String convertedWord = "";
    convertedWord += fixPunct(word);
    return convertedWord;
  }

  public String translateSentence(String sentence)
  {
    String retSentence = "";
    int index = 0;
    int wordCount = 1;
    for (int i =0; i<sentence.length(); i++){
      if (sentence.substring(i, i+1).equals(" ")){
        retSentence += (fixPunct(sentence.substring(index,i)) + " ");
        index = i+1;
        wordCount +=1;
      }
      else{
      }
    }
    retSentence += fixPunct(sentence.substring(index+1, sentence.length()));

    System.out.println(wordCount);
    return retSentence;
  }
}  
