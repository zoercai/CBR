package retrieval;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import similarityMetrics.DefaultMetrics;
import similarityMetrics.KNearestNeighbour;

public class MainRunner {
	public static void main(String[] args){
		Cases cases = new Cases();
		cases.read();

		DefaultMetrics defaultMetrics = new DefaultMetrics(cases);
		defaultMetrics.populateHolidayTable();
		System.out.println();
		defaultMetrics.populateTransportTable();
		System.out.println();
		
		final KNearestNeighbour similarityCalculator = new KNearestNeighbour(defaultMetrics, cases);
		
		JFrame frame = new JFrame("Travel CBR");
		frame.setLayout(new BorderLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel newCasePanel = new JPanel();
		
		String[] holidayTypes = {
				"Recreation","Bathing", "Wandering", "Active", "City", "Education", "Skiing", "Language"
		};
		final JComboBox<String> holidayType = new JComboBox<String>(holidayTypes);
		newCasePanel.add(holidayType);
		
		JLabel priceLabel = new JLabel("Price");
		final JTextField price = new JTextField(4);
		newCasePanel.add(priceLabel);
		newCasePanel.add(price);
		
		JLabel personLabel = new JLabel("Number of People");
		final JTextField numberOfPerson = new JTextField(2);
		newCasePanel.add(personLabel);
		newCasePanel.add(numberOfPerson);
		
		String[] transportations = {
				"Car", "Plane", "Train", "Coach"
		};
		final JComboBox<String> transportation = new JComboBox<String>(transportations);
		newCasePanel.add(transportation);
		
		JLabel durationLabel = new JLabel("Duration");
		final JTextField duration = new JTextField(2);
		newCasePanel.add(durationLabel);
		newCasePanel.add(duration);
		
		String[] months = {
				"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
		};
		final JComboBox<String> month = new JComboBox<String>(months);
		newCasePanel.add(month);
		
		String[] accommodations = {
				"HolidayFlat", "OneStar", "TwoStars", "ThreeStars", "FourStars", "FiveStars"
		};
		final JComboBox<String> accommodation = new JComboBox<String>(accommodations);
		newCasePanel.add(accommodation);
		
		JButton submit = new JButton("Submit");
		newCasePanel.add(submit);
		
		frame.add(newCasePanel, BorderLayout.NORTH);
		
		
		
		final JTextArea result = new JTextArea(25, 20);
		
		JScrollPane scroll = new JScrollPane (result, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll, BorderLayout.SOUTH);
		
		
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String holidayTypeInput = (String) holidayType.getSelectedItem();
				String priceInput = price.getText();
				String numberOfPersonInput = numberOfPerson.getText();
				String transportationInput = (String) transportation.getSelectedItem();
				String durationInput = duration.getText();
				String monthsInput = (String) month.getSelectedItem();
				String accommodationInput = (String) accommodation.getSelectedItem();
				
				final TravelCase newCase = new TravelCase();
				newCase.holidayType = holidayTypeInput;
				newCase.transportation = transportationInput;
				newCase.price = Integer.parseInt(priceInput);
				newCase.numOfPersons = Integer.parseInt(numberOfPersonInput);
				newCase.duration = Integer.parseInt(durationInput);
				newCase.season = monthsInput;
				newCase.accommodation = accommodationInput;
				
				SwingWorker worker = new SwingWorker<PriorityQueue<TravelCase>, Void>(){
					PriorityQueue<TravelCase> nearestNeighbours;
					@Override
					protected PriorityQueue<TravelCase> doInBackground() throws Exception {
						nearestNeighbours = similarityCalculator.getNearestNeighbours(newCase);
						return nearestNeighbours;
					}
					
					@Override
				    public void done() {
						System.out.println("Hello");
						int k = 5;
						result.setText("");
						while(!nearestNeighbours.isEmpty() && k-- > 0){
							TravelCase currentNeighbour = nearestNeighbours.poll();
							result.append(currentNeighbour.caseName+"\n");
							result.append("Holiday Type: " + currentNeighbour.holidayType+"\n");
							result.append("Price: " + currentNeighbour.price+"\n");
							result.append("Number of People: " + currentNeighbour.numOfPersons+"\n");
							result.append("Region: " + currentNeighbour.region+"\n");
							result.append("Transportation: " + currentNeighbour.transportation+"\n");
							result.append("Duration: " + currentNeighbour.duration+"\n");
							result.append("Season: " + currentNeighbour.season+"\n");
							result.append("Accommodation: " + currentNeighbour.accommodation+"\n");
							result.append("Hotel: " + currentNeighbour.hotel+"\n");
							result.append("\n");
						}
					}
				
				};
				
				worker.execute();
			}
		});
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
}