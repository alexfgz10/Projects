// Author: Alejandro Gonzalez
// TA info: Section AA, Josh Ning
// Program Desc: this program implements a single node to be used in creating 
// huffman trees with encryption. It comes fields left right and freq to store and point info

public class HuffmanNode implements Comparable<HuffmanNode>{

   // FIELDS
   public int freq; // freq
   public int asciiIndex;
   public HuffmanNode left;
   public HuffmanNode right;
   
   // CONSTRUCTOR
   // Pre: Requires an in for frequency of occurance
   // Post: Constructs an instance of the huffmanNode
   public HuffmanNode(int freq){
      this.freq = freq;
      
   }
   
   // Pre: Requires a int frequency, int ascii value and boolean asci
   // Post: Constructs a huffmanNode specifically for ascii value insertion
   public HuffmanNode(int freq, int ascii, boolean isAscciConstruct){
      this.freq = freq;
      this.asciiIndex = ascii;
   }


   // Pre: requires int frequence and int index as input
   // Post: construcuts huffmanNode with these two saved in teh structure
   public HuffmanNode(int freq, int index){
      this.asciiIndex = index;
      this.freq = freq;
   }
   
   // second constructor
   // Pre: requies two HuffmaNO, left and right, as well as frequency Int
   // Post: Constructs huffmanNode with the fiven left right and freq
   // storing then in the stucture
   public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
      this.freq = freq;
      this.left = left;
      this.right = right;

   }
   
   // compareTo
   // Pre: requires a HuffmanNode as input
   // Post: will return a (type int) negative number if our current HuffmanNode's freq is less then the input's
   // Returns 0 if both HuffmanNodes have same freq int
   // returns positive int if the input HuffmanNode has a greater freq value than our current HuffmanNode
   public int compareTo(HuffmanNode other){
      // DOES THIS COMPARISON STYLE COME TO WORK?
      return this.freq - other.freq;
   }
}