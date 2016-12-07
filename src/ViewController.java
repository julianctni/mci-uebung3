import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

public class ViewController {

	private TlxMainMenue mainMenueFrame;
	private JFrame analysisFrame;
	private JFrame testFrame;
	private JFrame firstTestFrame;
	int windowWidth = 800;
	int windowHeight = 650;
	
	public List<int[]> weights = new ArrayList<int[]>();
	public List<int[]> values = new ArrayList<int[]>();
	
	private static ViewController controller;	
	
	private ViewController(){
		mainMenueFrame = new TlxMainMenue(windowWidth, windowHeight);
		testFrame = new TlxTestView(windowWidth, windowHeight);
		firstTestFrame = new TlxFirstTestView(windowWidth, windowHeight);
	}
	
	public static ViewController getInstance (){
		if (ViewController.controller == null) {
			ViewController.controller = new ViewController();
		}
		return ViewController.controller;
	}
	
	public void showMainMenue (JFrame hideFrame){
		if (hideFrame != null){
			hideFrame.setVisible(false);
			mainMenueFrame.setBounds(hideFrame.getBounds().x, hideFrame.getBounds().y, hideFrame.getBounds().width, hideFrame.getBounds().height);
		}
		mainMenueFrame.enableExportAndAnalysis(!weights.isEmpty() && !values.isEmpty());
		mainMenueFrame.setVisible(true);
		
	}
	
	public void showAnalysis (JFrame hideFrame){
		if (analysisFrame == null)
			analysisFrame = new TlxAnalysisView(windowWidth, windowHeight);
		if (hideFrame != null){
			hideFrame.setVisible(false);
			analysisFrame.setBounds(hideFrame.getBounds().x, hideFrame.getBounds().y, hideFrame.getBounds().width, hideFrame.getBounds().height);
		}
		
		analysisFrame.setVisible(true);
	}
	
	public void showFirstTest(JFrame hideFrame){
		if (hideFrame != null){
			hideFrame.setVisible(false);
			firstTestFrame.setBounds(hideFrame.getBounds().x, hideFrame.getBounds().y, hideFrame.getBounds().width, hideFrame.getBounds().height);
		}
		firstTestFrame.setVisible(true);
	}
	
	public void showTest (JFrame hideFrame){
		if (hideFrame != null) {
			hideFrame.setVisible(false);
			testFrame.setBounds(hideFrame.getBounds().x, hideFrame.getBounds().y, hideFrame.getBounds().width, hideFrame.getBounds().height);
		}
		testFrame.setVisible(true);
	}
	
	public String createJson(){
		String result = "[";
		int count = 0;
		for (int[] weight : weights){
			String tempResult = "{\"result"+count+"\":{";
			int[] value = values.get(count);
			tempResult += "\"mental-demand\":{\"weight\":" + weight[0] + ", \"result\":"+value[0]+"},";
			tempResult += "\"physical-demand\":{\"weight\":" + weight[1] + ", \"result\":"+value[1]+"},";
			tempResult += "\"temporal-demand\":{\"weight\":" + weight[2] + ", \"result\":"+value[2]+"},";
			tempResult += "\"performance\":{\"weight\":" + weight[3] + ", \"result\":"+value[3]+"},";
			tempResult += "\"frustration\":{\"weight\":" + weight[4] + ", \"result\":"+value[4]+"},";
			tempResult += "\"effort\":{\"weight\":" + weight[5] + ", \"result\":"+value[5]+"}}}";
			tempResult += count+1 < weights.size() ? "," : "";
			result += tempResult;
			count++;
		}
		return result+"]";
	}
}
