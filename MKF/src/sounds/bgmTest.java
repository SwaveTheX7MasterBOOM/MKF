package sounds;

import java.io.File;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.media.bean.playerbean.*;

public class bgmTest {

	
	public bgmTest(String sounds){
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
	    Format input2 = new AudioFormat(AudioFormat.MPEG);
	    Format output = new AudioFormat(AudioFormat.LINEAR);
	    PlugInManager.addPlugIn(
	        "com.sun.media.codec.audio.mp3.JavaDecoder",
	        new Format[]{input1, input2},
	        new Format[]{output},
	        PlugInManager.CODEC);
	    
	    
	    
	    try{ MediaPlayer MediaPlayer1 = new javax.media.bean.playerbean.MediaPlayer();
	    MediaPlayer1.setMediaLocator(new MediaLocator(new File(sounds).toURI().toURL()));
	    MediaPlayer1.setPlaybackLoop(true);
	    MediaPlayer1.start();}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
}
