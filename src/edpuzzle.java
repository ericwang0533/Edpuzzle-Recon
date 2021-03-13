import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
public class edpuzzle{
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Edpuzzle URL: ");
        String assignmentUrl = in.nextLine();
        PrintWriter out = new PrintWriter(new File("recon.txt"));
        String id = assignmentUrl.substring(33, assignmentUrl.length()-6);
        System.out.println("Navigate to this link: https://edpuzzle.com/api/v3/assignments/" + id);
        System.out.println("Copy Info from the Webpage and Enter Here: ");
        String body = in.nextLine();
        in.close();
        
        String[] arr = body.split("\"questionNumber\":0,\"time\":");
        for(int i = 1; i < arr.length; i++) {
        	String[] brr = arr[i].split(",");
        	int seconds = (int) Math.round(Double.valueOf(brr[0]));
        	
        	String mins = String.valueOf(seconds/60);
        	String finalMins = "";
        	if(mins.length() == 1) {
        		finalMins = "0"+mins;
        	}
        	else {
        		finalMins = mins;
        	}
        	
        	String seconds1 = String.valueOf(seconds%60);
        	String finalSeconds;
        	if(seconds1.length() == 1) {
        		finalSeconds = "0"+seconds1;
        	}
        	else {
        		finalSeconds = seconds1;
        	}
        	
        	String time = finalMins + ":" + finalSeconds;       
//        	System.out.println(Arrays.toString(brr) + "\n " + brr.length);
        	String question = brr[4].replace("\"html\":\"<p>", "").replaceAll("&nbsp;", "").replaceAll("&#39;", "'").replaceAll("&quot;", "\"").replaceAll("</p>\"", "");
        	if((i == arr.length-1 && brr.length == 29 || brr.length == 34) || brr.length == 10 || brr.length == 11) {
        		out.println("Open-Ended at: " + time);
        		out.println(question);
        	}
        	else {
        		out.println("Multiple-Choice at: " + time);
        		out.println(question);
        		out.println("Choices:");
        		for(int j = 5; j < brr.length; j++) {
        			if(brr[j].length() > 11 && brr[j].substring(0, 11).equals("\"html\":\"<p>")) {
        				String choice = brr[j].replaceAll("\"html\":\"<p>", "").replaceAll("&nbsp;", "").replaceAll("&#39;", "'").replaceAll("&quot;", "\"").replaceAll("</p>\"", "");
        				out.println(" - " + choice);
        			}
        		}
        	}
        	out.println();
        }
        out.close();
        
        // remember to check the last arr[]
    }
}
