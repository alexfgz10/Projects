// Author: Alejandro Gonzalez
// TA Info: Josh Ning, section AA
// Program Desc: implements the class for a node in the question tree program 
// with data, left, and right 

public class QuestionNode{
   
   // fields
   public String data;
   public QuestionNode left;
   public QuestionNode right;
   
   // Pre: Requires a string as intput
   // Post creates an instance of the QuestioNode, storing given string into the state
   public QuestionNode(String data){
      this.data = data;
   }
   
   // Requires a string (data), a correctAnswe of type QuestionNode and incorrect
   // answer of type questioNode as well
   // Creates an instance of the QuestionNode, stroring each of the given pieces of information
   public QuestionNode(String data, QuestionNode answer, QuestionNode incorrect){
      this.data = data;
      this.left = answer;
      this.right = incorrect;
   }
   
   
}