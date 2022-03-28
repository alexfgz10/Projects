// Author: Alejandro Gonzalez
// TA Info: Quiz Section AA, TA = Josh Ning
// Program Desc: This program creates a game of assassin by constructing
// a kill ring, a graveryard (where people are transfered after being killed)

import java.util.*;

public class AssassinManager{
   private AssassinNode front;
   private AssassinNode graveyard;
   
   // Pre: requires a list of strings (names) as parameter,
   // which is not empty (throws illegalArgumentException if empty list)
   // Post: constructs the order of the kill ring (who assassinates who)
   // by the order of given list (order is preserved)
   public AssassinManager(List<String> names){
      int size = names.size();
      if (size == 0){
         throw new IllegalArgumentException();
      }
      
      front = null;
      for (int i = size - 1; i >= 0; i--){
         front = new AssassinNode(names.get(i), front);
      }
   }
   
   // Pre: no input required
   // Post: prints the names in the kill ring with who they're hunting
   // oner per line (indented), has no return and has no ouput if empty
   public void printKillRing(){
      AssassinNode current = front;
      
      while (current != null && current.next != null){
         System.out.println("    " + current.name + " is stalking " + current.next.name);
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + front.name);
   }
   
   // Pre: no input required
   // Post: prints the people inside the graveryard, ordered by most recently killed
   // no returns, and has no output if empty
   public void printGraveyard(){
      AssassinNode current = graveyard;
      while (current != null){
         System.out.println("    " + current.name + " was killed by " + current.killer);
         current = current.next;
      }
   }
   
   // Pre: requires name (string) as an input (ignores casing in comparing names)
   // Post: returns boolean saying true if name is in the kill ring, false if not
   public boolean killRingContains(String name){
      return contains(name, front);
   }
   
   // pre: requires name (string) as an input (ignores casing in comparing names)
   // post: returns boolean saying if name is in the graveryard
   public boolean graveyardContains(String name){
      return contains(name, graveyard);
   }
   
   // Pre: no input required
   // Post: returns true (boolean) if kill ring has just one person left
   // returns false other wise (including 0 people in kill ring)
   public boolean gameOver(){
      return front != null && front.next == null;
   }
   
   // Pre: no input required
   // Post: returns the name (string) of the winner, last person standing
   // in kill ring. Returns null if the game is not over (more than one person
   // in kill ring)
   public String winner(){
      if (front != null && front.next == null){ // checking for null pointer first
         return front.name;
      }
      return null;
   }
   
   // pre: requires name (string) as an input (ignores casing when comparing)
   // name must be in kill ring (throws IllegalArgumentException if not)
   // if game is overr (1 or 0 players in kill ring)
   // throws IllegalStateExcpetion. (will through either exception if both violated)
   // post: no return. Records the kill of the person by moving him/her from
   // kill ring to graveyard (does not change order of printKillRing)
   public void kill(String name){
      // EMPTY AND ONE PERSON (GAME OVER)
      if (front == null || front.next == null){
         throw new IllegalStateException();
      }
      // but it may be redundent
      if (!killRingContains(name)){
         throw new IllegalArgumentException();
      }
      
      AssassinNode frontCheck = front;
      AssassinNode current = front;
      AssassinNode temp = front;
      
      // handle every case EXCEPT THE FRONT!
      while (current.next != null){
         if (comparison(current.next.name, name)){
            temp = current.next;
            temp.killer = current.name;
            current.next = current.next.next;
         } else{
            current = current.next;
         }
      }
      
      // handles front case
      if (comparison(frontCheck.name, name)){
         front.killer = current.name;
         front = front.next;
      }
      
      // reducing redunedency since it would've been exected in both ifs
      temp.next = graveyard;
      graveyard = temp;
   }
   
   // abstracted logic to test if two string are the same
   private boolean comparison(String first, String second){
      String lowerFirst = first.toLowerCase();
      String lowerSecond = second.toLowerCase();
      return lowerFirst.equals(lowerSecond);
   }
   
   // method which checks if the person is inside, abstracted
   private boolean contains(String givenName, AssassinNode beginning){
      AssassinNode current = beginning;
      while (current != null){
         if (comparison(current.name, givenName)){
            return true;
         }
         current = current.next;
      }
      return false;
   }
}