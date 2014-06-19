package sounds;
import javax.sound.sampled.*;
public class Musics {
	
	private Clip bgm;
	
	public Musics(String musicWhere){
		
		try{
			
			AudioInputStream stream = 
					AudioSystem.getAudioInputStream(
							getClass().getResourceAsStream(musicWhere)
								);
			
			AudioFormat soundType = stream.getFormat();
			AudioFormat findSoundType = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, soundType.getSampleRate(),16,soundType.getChannels(),soundType.getChannels()*2,soundType.getSampleRate(),false);
			
			
			AudioInputStream goodStream = AudioSystem.getAudioInputStream(findSoundType, stream );
			bgm = AudioSystem.getClip();
			bgm.open(goodStream);
			System.out.println("Opened Music");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(bgm ==null) return;
		bgm.setFramePosition(0);
		bgm.start();
		stop();
	}
	
	public void stop(){
		if(bgm.isRunning()) bgm.stop();
	}
	
	
	public void close(){
		stop();
		bgm.close();
	}

}
