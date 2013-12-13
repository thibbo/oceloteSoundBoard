package com.example.ocelotesoundboard;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	private static final String TAG = "MyActivity";
	private Button button;
	private String[] list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        for(int i=0; i<10;i++){
//        	Button b = new Button(this);
//        	b.setText(Integer.toString(i));
//        }
//        button = new Button(this);
//        button.setText("heelo");
//        ((ViewGroup)findViewById(R.id.layout)).addView(button);
        
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
        try {
			list = am.list("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for(int i =0; i<list.length; i++){
        	if(list[i].endsWith(".mp3") || list[i].endsWith(".ogg")){
        		Button button = new Button(this);
        		button.setId(i);
        		String bLabel = list[i].replaceAll("_", " ");
        		bLabel = bLabel.substring(0, bLabel.lastIndexOf('.'));
        		button.setText(bLabel);
        		button.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						playSound((Button) v);
					}
				});
                ((ViewGroup)findViewById(R.id.layout)).addView(button);
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
    
    public void playSound(Button button){
        try {
            AssetFileDescriptor afd = getAssets().openFd(list[button.getId()]);
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            player.prepare();
            player.start();
            } 
        catch (IllegalArgumentException e) {    } 
        catch (IllegalStateException e) { } 
        catch (IOException e) { } 	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
