package com.thibbo.ocelotesoundboard;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.example.ocelotesoundboard.R;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "MyActivity";
	private String[] list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        File soundDirectory = new File(Environment.getDataDirectory()+File.separator+"sounds");
        
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
        		button.setTextColor(Color.WHITE);
        		button.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						playSound((Button) v);
					}
				});
                ((ViewGroup)findViewById(R.id.layout)).addView(button);
        	}
        }
    }
    
    public void playSound(Button button){
        try {
            AssetFileDescriptor afd = getAssets().openFd(list[button.getId()]);
            final MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            player.prepare();
            player.start();
            player.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					player.release();					
				}
			});
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
