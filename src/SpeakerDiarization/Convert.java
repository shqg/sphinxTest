package SpeakerDiarization;

import java.io.*;


public class Convert {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Convert cv=new Convert();
	 
		cv.convertDir("/D:/", "/D:/", "Piero_Baldini##");
	};
	public void convertDir(String in_dir, String outDir, String newName){
		File pathFile=new File(in_dir);
		File listFile[] = pathFile.listFiles();
		for(int i=0;i<listFile.length;i++){
			System.out.println(listFile[i]);
			if(listFile[i].getName().endsWith(".mp3")){
				
				convertSimple(listFile[i].getPath(), outDir, newName+listFile[i].getName().replaceAll(".mp3", ""));

			}
		}
	}
	
	public void convertSimple(String filePath, String outDir, String newName){
		 try {
             Runtime rt = Runtime.getRuntime();
             String ff=newName;
             if(newName==null){
             int li=filePath.lastIndexOf(".");
             String ff2=filePath.substring(0, li);
             li=ff2.lastIndexOf("/");
             ff=filePath.substring(li, ff2.length());
             }
             
             String command="/opt/local/bin/ffmpeg  -i "+filePath+" -acodec pcm_s16le -ac 1 -ar 16000 "+outDir+"/"+ ff+".wav";
             System.out.println(command);
             Process pr = rt.exec(command);

             BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

             String line=null;

             while((line=input.readLine()) != null) {
                 System.out.println(line);
             }

             int exitVal = pr.waitFor();
             System.out.println("Exited with error code "+exitVal);

         } catch(Exception e) {
             //System.out.println(e.toString());
             e.printStackTrace();
         }
     }
		
	}
