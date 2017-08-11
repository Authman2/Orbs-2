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




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	/** Initializes the database by grabbing the API key. */
	public static void initDatabase() {
		InputStream firebaseURLLink = Networking.class.getResourceAsStream("/resources/firebaseURL.txt");
		BufferedReader reader = null;

		// Load the text file.
		try {
			reader = new BufferedReader(new InputStreamReader(firebaseURLLink));
			String str = reader.readLine();
			DATABASE_URL = str;
			System.out.println("\nDATABSE: \n"+ str);
			reader.close();

			try {
	            FileInputStream serviceAccount = new FileInputStream("../google-services.json");
	            FirebaseOptions options = new FirebaseOptions.Builder()
	                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
	                    .setDatabaseUrl(DATABASE_URL)
	                    .build();
	            FirebaseApp.initializeApp(options);

	            // Shared Database reference
	        	fireRef = FirebaseDatabase.getInstance().getReference();

	        } catch (Exception e) {
	            System.out.println("ERROR: invalid service account credentials. See README.");
	            System.out.println(e.getMessage());

	            System.exit(1);
	        }

		} catch(Exception err) {
			return;
		}
	}






	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Trys to load a game from the given game ID. */
	public static void loadGame(String gameID) {
		DatabaseReference ref = fireRef.child(gameID);
		if(ref == null) {
			System.out.println("There is no data for that save ID");
			return;
		}
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
		        Map<String, Object> data = dataSnapshot.getValue(Map.class);
		        System.out.println(data);
		    }

		    @Override
		    public void onCancelled(DatabaseError databaseError) {
		        System.out.println("The read failed: " + databaseError.getCode());
		    }
		});
	}




	/** Saves the game to the database. */
	public static void saveGame(Map<String, Object> saveData, Function<?,?> success, Function<String,String> failure) {
		/* If the game save id is not null, that means that you have loaded the game 
		already and are currently running that save file. */
		if(gameSaveID != null) {

			// Assuming you are already working with a game save ID, just save the new data.
			DatabaseReference ref = fireRef.child(gameSaveID);
			ref.setValue(saveData, new DatabaseReference.CompletionListener() {
			    @Override
			    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
			        if (databaseError != null) {
			            failure.apply(databaseError.getMessage().toString());
			        } else {
			            success.apply(null);
			        }
			    }
			});

			// Don't forget to set the variable of gameSaveID.
			gameSaveID = ref.getKey();

		} else {
			/* If the save id IS null, that means you either have not saved yet or you
			have just started a new game. */

			// Create a new, random save ID and upload the save data to it.
			DatabaseReference ref = fireRef.push();

			// Make sure to get the key from the reference point.
			saveData.put("key", ref.getKey());

			// Finally, save the data.
			ref.setValue(saveData, new DatabaseReference.CompletionListener() {
			    @Override
			    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
			        if (databaseError != null) {
			            failure.apply(databaseError.getMessage().toString());
			        } else {
			            success.apply(null);
			        }
			    }
			});

			// Don't forget to set the variable of gameSaveID.
			gameSaveID = ref.getKey();
		}
	}





}