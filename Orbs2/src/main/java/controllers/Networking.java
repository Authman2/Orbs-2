package controllers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.stream.Stream;

import javax.imageio.ImageIO;
import java.io.*;
import java.io.File;
import java.net.URL;


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




	/********************
	*					*
	*   INITIALIZATION	*
	*					*
	*********************/

	/** Initializes the database by grabbing the API key. */
	public static void initDatabase() {
		InputStream tileLocs = Networking.class.getResourceAsStream("/google-services.txt");
		BufferedReader reader = null;

		// Load the text file.
		try {
			reader = new BufferedReader(new InputStreamReader(tileLocs));
			String str = reader.readLine();
			DATABASE_URL = str;
		} catch(Exception err) {
			return;
		}

        try {
            FileInputStream serviceAccount = new FileInputStream("google-services.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            System.out.println(e.getMessage());

            System.exit(1);
        }

        // Shared Database reference
        fireRef = FirebaseDatabase.getInstance().getReference();
	}




	/********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

	/** Trys to load a game from the given game ID. */
	public static void loadGame(String gameID) {
		System.out.println("Game ID: " + gameID);
	}







}