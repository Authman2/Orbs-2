package controllers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.stream.Stream;
import java.util.function.Function;

import javax.imageio.ImageIO;
import java.io.*;
import java.io.File;
import java.net.URL;

import java.util.Map;
import java.util.HashMap;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/** The class that handles all of the calls to the database. */
public class Networking {

	/********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/

	// The url to the firebase database.
	private static String DATABASE_URL;

	// The reference to the firebase database.
	private static DatabaseReference fireRef;


	// The id of the save that the current user is using.
	public static String gameSaveID;

	// The map that represents the game save data.
	public static HashMap<String,Object> saveData;



	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	/** Loads the firebase url into a string. */
	public static Pair<String, String> loadFirebaseURL() {
		ArrayList<String> lines = read("firebaseURL.txt");
		
		if(lines.size() > 1) {
			return new Pair<String, String>(lines.get(0), lines.get(1));
		} else {
			return new Pair<String, String>("","");
		}
	}



	/** Initializes the database by grabbing the API key. */
	public static void initDatabase() {
		Pair<String, String> pair = loadFirebaseURL();
		DATABASE_URL = pair.getKey().trim().replace("\n","");

		try {
            FileInputStream fis = new FileInputStream(pair.getValue().trim());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(fis))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);

            // Shared Database reference
        	fireRef = FirebaseDatabase.getInstance().getReference();

        	fis.close();
        	return;
        } catch (Exception e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            System.out.println(e.getMessage());

            System.exit(1);
        }
	}



	/********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/

	/** Reads a text file and returns an array list of the lines. */
	public static ArrayList<String> read(String path) {
		ArrayList<String> lines = new ArrayList<String>();

		try {
	    	InputStream inp = Networking.class.getResourceAsStream("/" + path);
			BufferedReader reader = reader = new BufferedReader(new InputStreamReader(inp));

			String str = reader.readLine();
			while(str != null) {
				lines.add(str);

				// Restart the loop.
				try { str = reader.readLine(); } catch(Exception err) { err.printStackTrace(); break; }
			}
			reader.close();
	    } catch(Exception er) {
	    	er.printStackTrace();
	    }

	    return lines;
	}


	/** Loads a buffered image from the resources. */
	public static BufferedImage loadBufferedImage(String path) {
		BufferedImage img = null;
		try {
			URL res = Networking.class.getResource("/" + path);
			img = ImageIO.read( res );
		} catch(Exception err) {
			err.printStackTrace();
		}
		return img;
	}



	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Clears all the game save data. This is used when returning to the home screen. */
	public static void clear() {
		if(saveData != null)
			saveData.clear();
		gameSaveID = null;
	}


	/** Trys to load a game from the given game ID. */
	//-KrNID9XL09S_VNhYAAN
	public static void loadGame(String gameID, Function<?,?> then) {
		DatabaseReference ref = fireRef.child(gameID.trim().replace("\n",""));

		if(ref == null) {
			return;
		}
		gameSaveID = gameID;
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {

		    	//HashMap<String, Object> data = dataSnapshot.getValue(genericType);
		        GenericTypeIndicator<HashMap<String,Object>> t = new GenericTypeIndicator<HashMap<String,Object>>() {};
     			saveData = dataSnapshot.getValue(t);

     			then.apply(null);
		    }

		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		    	System.out.println("ERROR: " + databaseError.getCode());
		    }
		});
	}




	/** Saves the game to the database. */
	public static void saveGame() {
		/* If the game save id is not null, that means that you have loaded the game 
		already and are currently running that save file. */
		if(gameSaveID != null) {

			// Assuming you are already working with a game save ID, just save the new data.
			DatabaseReference ref = fireRef.child(gameSaveID);

			// Don't forget to set the variable of gameSaveID.
			gameSaveID = ref.getKey();

			ref.setValue(saveData, new DatabaseReference.CompletionListener() {
			    @Override
			    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
			        
			    }
			});

		} else {
			/* If the save id IS null, that means you either have not saved yet or you
			have just started a new game. */

			// Create a new, random save ID and upload the save data to it.
			DatabaseReference ref = fireRef.push();

			// Make sure to get the key from the reference point.
			saveData.put("key", ref.getKey());

			// Don't forget to set the variable of gameSaveID.
			gameSaveID = ref.getKey();

			// Finally, save the data.
			ref.setValue(saveData, new DatabaseReference.CompletionListener() {
			    @Override
			    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
			       	
			    }
			});
		}
	}





}