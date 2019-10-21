package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AvailableRidesPageController {
	
	@FXML
	private Label status;
	
	@FXML
	private AnchorPane firstRecommendation;
	
	@FXML
	private Label cost1;
	
	@FXML
	private Label start1;
	
	@FXML
	private Label destination1;
	
	@FXML
	private Label time1;
	
	@FXML
	private Label seat1;
	
	@FXML
	private AnchorPane secondRecommendation;
	
	@FXML
	private Label cost2;
	
	@FXML
	private Label start2;
	
	@FXML
	private Label destination2;
	
	@FXML
	private Label time2;
	
	@FXML
	private Label seat2;
	
	//need to fix code
	public void initData(ArrayList<Ride> rides) {
		if (rides.size() == 0) {
			status.setText("Sorry, there are no rides available.");
			firstRecommendation.setVisible(false);
			secondRecommendation.setVisible(false);
		} else if (rides.size() == 1) {
			secondRecommendation.setVisible(false);
			status.setText("One ride is recommended for your travel details...");
			Ride ride = rides.get(0);
			start1.setText(ride.getStart());
			destination1.setText(ride.getDestination());
			time1.setText(ride.getTime());
		} else {
			status.setText("Two rides are recommended for your travel details...");
			Ride ride1 = rides.get(0);
			start1.setText(ride1.getStart());
			destination1.setText(ride1.getDestination());
			time1.setText(ride1.getTime());
			
			Ride ride2 = rides.get(0);
			start2.setText(ride2.getStart());
			destination2.setText(ride2.getDestination());
			time2.setText(ride2.getTime());
		}
		
	}
	
	public void selectButtonPressed(ActionEvent event) throws IOException {
		
	}

}
