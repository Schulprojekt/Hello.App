package com.Schulprojekt.helloprojekt.GUILogik;


public class readwrite {

	public void writeread(){

		// TODO Wegen FEHLERN AUSKOMMENTIERT!
		
//		try { 
// 	       // catches IOException below
// 	       final String TESTSTRING = new String("Hello Android");
//
// 	       /* We have to use the openFileOutput()-method
// 	       * the ActivityContext provides, to
// 	       * protect your file from others and
// 	       * This is done for security-reasons.
// 	       * We chose MODE_WORLD_READABLE, because
// 	       *  we have nothing to hide in our file */             
// 	       FileOutputStream fOut = openFileOutput("samplefile2.txt",
// 	                                                            MODE_WORLD_WRITEABLE);
// 	       OutputStreamWriter osw = new OutputStreamWriter(fOut); 
//
// 	       // Write the string to the file
// 	       osw.write(TESTSTRING);
//
// 	       /* ensure that everything is
// 	        * really written out and close */
// 	       osw.flush();
// 	       osw.close();
//
// 	//Reading the file back...
//
// 	       /* We have to use the openFileInput()-method
// 	        * the ActivityContext provides.
// 	        * Again for security reasons with
// 	        * openFileInput(...) */
//
// 	        FileInputStream fIn = openFileInput("samplefile.txt");
// 	        InputStreamReader isr = new InputStreamReader(fIn);
//
// 	        /* Prepare a char-Array that will
// 	         * hold the chars we read back in. */
// 	        char[] inputBuffer = new char[TESTSTRING.length()];
//
// 	        // Fill the Buffer with data from the file
// 	        isr.read(inputBuffer);
//
// 	        // Transform the chars to a String
// 	        String readString = new String(inputBuffer);
//
// 	        // Check if we read back the same chars that we had written out
// 	        boolean isTheSame = TESTSTRING.equals(readString);
//
// 	        Log.i("File Reading stuff", "success = " + isTheSame);
//
// 	    } catch (IOException ioe) 
// 	      {ioe.printStackTrace();}
	}

}
