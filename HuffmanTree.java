// Author: Alejandro Gonzalez
// TA Info: Section AA, Josh Ning
// Program Desc: This program constructs a Huffman encoding tree to compress a file
// it will take letter frequencies


import java.util.*;
import java.io.*;

public class HuffmanTree{

   private HuffmanNode overallRoot;
   
   // Pre: Requires a count (an array of type Ints)
   // Post: Constructs a huffmanTree from given ints, where the min counts appear at top
   // no return type   
   public HuffmanTree(int[] count){

      Queue<HuffmanNode> huffmanTree = new PriorityQueue<>(); 
      HuffmanNode eof = new HuffmanNode(1, count.length);
           
      int countSize = count.length;
      
      for (int i = 0; i < countSize; i++){
         int numberCount = count[i];
         if (numberCount > 0){
            System.out.println(i + " " + numberCount);
            HuffmanNode newFreqNode = new HuffmanNode(numberCount, i);
            huffmanTree.add(newFreqNode);
         }
      } 
      huffmanTree.add(eof);
      
      while (huffmanTree.size() != 1){
         HuffmanNode firstMin = huffmanTree.remove();
         HuffmanNode secondMin = huffmanTree.remove();
         int sum = firstMin.freq + secondMin.freq;
         
         HuffmanNode sumNode = new HuffmanNode(sum, firstMin, secondMin);
         huffmanTree.add(sumNode);
      }
       overallRoot = huffmanTree.remove();
   }
   
   // Pre: Requires input of type scanner
   // Post: given an input file in standard format, creates huffmanTree, with its effiency,
   // the frequency values given to the top nodes are arbitary
   // but the leaf nodes will always contain the given information
   // return type (a constructor)
   public HuffmanTree(Scanner input){
   
       while(input.hasNextLine()){
         int asciiRep = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         
         overallRoot = scannerHelper(overallRoot, 0, asciiRep, code);
       }
   }
   
   private HuffmanNode scannerHelper(HuffmanNode root, int index, int ascii, String pattern){
         
      if (index < pattern.length()){
         char leftOrRight = pattern.charAt(index);
         
         if (root == null){
            root = new HuffmanNode(-1);
         } else if(leftOrRight == '0'){
            root.left = scannerHelper(root.left, index + 1, ascii, pattern);
         } else{
            root.right = scannerHelper(root.right, index + 1, ascii, pattern);
         } 
         
      } else{
         return new HuffmanNode(ascii);
      }
      return root;
   }
   
   // Pre: Requires an output of type PrintStream
   // Post: This program writes the given Huffeman tree in standard pre order traversal format
   // no return type
   public void write(PrintStream output){
      explorePrint(overallRoot, output, "");
   }
   
   // private method to supplant the recursive funtionality
   private void explorePrint(HuffmanNode root, PrintStream output, String encodedRepresentation){
         
      if (root != null){
         if (root.left == null && root.right == null){
            output.println(root.asciiIndex);
            output.println(encodedRepresentation);
         } else{
            // EXPLORE
            explorePrint(root.left, output, encodedRepresentation + "0");
            explorePrint(root.right, output, encodedRepresentation + "1");
         }
      
      }
   }
   
   // Pre: Requires input of type BitInputStream, output of type PrintStream, and 
   // end of character (eof) of type int
   // Post: Given a message encoded by huffman, which are stored in the bit input stream
   // this will decode the message and write into our printStream output
   // does not print the end of character.
   // no return
   public void decode(BitInputStream input, PrintStream output, int eof){
      if (overallRoot != null){
         HuffmanNode root = overallRoot;
         
         while (root.asciiIndex != eof){
            if (root.left == null && root.right == null){
               output.write(root.asciiIndex);
               root = overallRoot;
            } else {
               int nextBit = input.readBit();
               
               if (nextBit == 0){
                  root = root.left;
               } else{
                  root = root.right;
               }
            }
         }
      }
   }
}