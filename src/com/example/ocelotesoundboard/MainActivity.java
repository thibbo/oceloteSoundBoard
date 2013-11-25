package com.example.ocelotesoundboard;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file;
        File soundDirectory = new File(Environment.getDataDirectory()+File.separator+"sounds");
        FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".mp3") || filename.endsWith(".ogg");
			}
		};
        
        //create a directory for custom sounds
        if(!soundDirectory.exists()){
        	soundDirectory.mkdirs();
        }
        
        AssetManager am = getAssets();
        String[] list = null;
        try {
			list = am.list("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for(int i =0; i<list.length; i++){
        	if(list[i].endsWith(".mp3") || list[i].endsWith(".ogg")){
        		Button b1 = new Button(this);
        		b1.setText("Button");
        	}
        }
//        //use audio resources
//        Resources r = getResources();
//
//        //uses files that are contained in the directory sounds.
//        for(final File fileEntry : soundDirectory.listFiles(filter)){
//        	//create button for this file
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
