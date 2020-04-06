package noLizards;


import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Crypting {

	public static void main(String[] args) throws IOException {
		int choice = 0;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		while(choice == 0) {
			try {
				System.out.println("Would you like to:\n\n1: Encrypt a file.\n2: Decrypt a file.\n3: Find the decryption key.\n");
				choice = scan.nextInt();
			}
			catch(InputMismatchException ex) {
				System.out.println("\nInputMismatchException: You did not enter a numeric value.\n");
			}
			scan.nextLine();
		}
		
		cryption(choice);
	}
	
	public static void cryption(int choice) throws IOException {
		String filename = "";
		byte cryptionKey = 0;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		switch(choice) {
		
			case 1:
				while(cryptionKey == 0) {
					try {
						System.out.print("\nEnter the numeric key to encrypt file(byte range (-128, 127]): ");
						cryptionKey = scan.nextByte();
					}
					catch(InputMismatchException ex) {
						System.out.println("\nInputMismatchException: You did not enter a proper byte value or exceeded the variables range.\n");
						cryptionKey = 0;
					}
					scan.nextLine();
				}
			
				System.out.println("\n\nEnter the file you would like to Encrypt(inlcude file extension): ");
				filename = scan.next(); 
			
				keyTesting(filename, choice, cryptionKey);

				break;
		
			case 2:
				while(cryptionKey == 0) {
					try {
						System.out.print("\nEnter the opposite numeric value of encryption key to decrypt file(byte range (-128, 127]): ");
						cryptionKey = scan.nextByte();
					}
					catch(InputMismatchException ex) {
						System.out.println("\nInputMismatchException: You did not enter a proper byte value or exceeded the variables range.\n");
						cryptionKey = 0;
					}
					scan.nextLine();
				}	
			
				System.out.println("\n\nEnter the file you would like to Encrypt(inlcude .encryped file extension): ");
				filename = scan.next();
			
				keyTesting(filename, choice, cryptionKey);
			
				break;
				
			case 3:
				System.out.println("\n\nEnter the file you would like to Encrypt(inlcude .encryped file extension): ");
				filename = scan.next();
				
				keyBreaker(filename, choice);
				
				break;
			
			default:
				System.out.println("Get out of here lizardmen!");
				break;
		}
	}
	
	/*Method will start the process of testing each byte value for possible key
	 * 
	 */
	public static void keyTesting(String filename, int choice, byte cryptionKey) throws IOException {
		String extension = "";
		
		if(choice == 1)
			extension = ".encrypted";
		else
			extension = ".decrypted";
	
		File inFile = new File(filename);
	
		try{
			FileInputStream input = new FileInputStream(inFile);
			
			//byte array size of the file
			byte fileContent[] = new byte[(int)inFile.length()];
			
			//reading the array and storing in the byte array	
			input.read(fileContent);
			
			//adding the key to each byte value in array
			for(int j = 0; j < fileContent.length; j++) 
				fileContent[j] = (byte)(fileContent[j] + cryptionKey);
				
			try {
				FileOutputStream output = new FileOutputStream(filename + extension);
				
				//writing byte array with key added to new file
				for(int j = 0; j < fileContent.length; j++) 
					output.write(fileContent[j]);
				
				System.out.println();
				System.out.println(new String(fileContent).toCharArray());
				
				output.close();
			}
			
			catch(IOException ex) {
				System.out.println("IOException: Something happened when trying to WRITE the encrypted/decrypted file.");
			}
			input.close();
		}
		
		catch(FileNotFoundException ex) {
			System.out.println("\nFileNotFoundException: The file '" + filename + "' does not exist. Lets try encrypting/decrypting again.\n");
			cryption(choice);
		}
	}
	
	
	/**
	 * Following methods are for breaking a basic key
	 */
	
		public static void keyBreaker(String filename, int choice) throws IOException{
			byte testKey = -128;
			//int key = -128;
			String extension = ".broken";
			
			/*byte array of some of the most common letter sequences in English language
			 * to be passed to the algorithm below
			 */
			byte[][] pattern = {
					{116, 104, 101}, //the
					{97, 110, 100},	//and
					{102, 111, 114}, //for
					{105, 102},		//if
					{116, 111},		//to
					{105, 115},		//is
					{105, 116},		//it
					{97, 115},		//as
					{97, 116}, 		//at
					{97, 110},		//an
					{105, 110},		//in
					{100, 111},		//do
					{115, 116},		//st
			};
			
			//array list to hold the possible keys
			ArrayList<Byte> possibleKey = new ArrayList<>();
			
			File inFile = new File(filename);
			
			
			//Writing the date that the key was attempted to be broken
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename + extension, true));
				
				output.writeObject((new java.util.Date()).toString());
				output.write("\n\n".getBytes());

				output.close();
			}
		
			catch(IOException ex) {
				System.out.println("IOException: Something happened when trying to WRITE the encrypted/decrypted file.");
				System.exit(0);
			}
			
			//looping through all possible byte values
			while(testKey < 127) {
				testKey++;
				//key++;
				try{
					FileInputStream input = new FileInputStream(inFile);
					
					byte fileContent[] = new byte[(int)inFile.length()];
					byte[] tempArray = null;
					
					input.read(fileContent);
					
					for(int j = 0; j < fileContent.length; j++) 
						fileContent[j] = (byte)(fileContent[j] + testKey);
					
				
					for(int i = 0; i < pattern.length; i++) {
						tempArray = pattern[i];
						
						/*the knuth-morris-pratt algorithm returns -1 any time it doesnt
						 * find the byte pattern
						 */
						if(indexOf(fileContent, tempArray) != -1) {
							
							if(possibleKey.contains(testKey)) 
								continue;
							
							else {
								//storing possible key in arraylist
								possibleKey.add(testKey);
								
								//writing the current possible key to a file
								try {
									ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename + extension, true));
									
									for(int j = 0; j < fileContent.length; j++)
										output.write(fileContent[j] + testKey);
									output.write("\n\n".getBytes());
									
									System.out.println();
									System.out.println(new String(fileContent).toCharArray());
								
									output.close();
								}
							
								catch(IOException ex) {
									System.out.println("IOException: Something happened when trying to WRITE the encrypted/decrypted file.");
									System.exit(0);
								}
							}
						}
					}	
					
					input.close();
				}
				
				catch(FileNotFoundException ex) {
					System.out.println("\nFileNotFoundException: The file '" + filename + "' does not exist.  Lets try encrypting/decrypting again.\n");
					cryption(choice);
				}
			}
			System.out.print("\n\nThe possible keys are: ");
			for(int i = possibleKey.size() - 1; i >= 0; i--) 
				System.out.print(possibleKey.get(i) + " ");
			System.out.println();
			
		}
	
	    /**
	     * Knuth-Morris-Pratt Algorithm for Pattern Matching
	     * Finds the first occurrence of the pattern in the text.
	     */
	    public static int indexOf(byte[] data, byte[] pattern) {
	        int[] failure = computeFailure(pattern);

	        int j = 0;
	        if (data.length == 0) return -1;

	        for (int i = 0; i < data.length; i++) {
	            while (j > 0 && pattern[j] != data[i]) {
	                j = failure[j - 1];
	            }
	            if (pattern[j] == data[i]) { j++; }
	            if (j == pattern.length) {
	                return i - pattern.length + 1;
	            }
	        }
	        return -1;
	    }

	    /**
	     * Computes the failure function using a boot-strapping process,
	     * where the pattern is matched against itself.
	     */
	    private static int[] computeFailure(byte[] pattern) {
	        int[] failure = new int[pattern.length];

	        int j = 0;
	        for (int i = 1; i < pattern.length; i++) {
	            while (j > 0 && pattern[j] != pattern[i]) {
	                j = failure[j - 1];
	            }
	            if (pattern[j] == pattern[i]) {
	                j++;
	            }
	            failure[i] = j;
	        }

	        return failure;
	    }
	
}
