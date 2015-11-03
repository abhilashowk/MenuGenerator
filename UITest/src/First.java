import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import com.sun.org.apache.xml.internal.serialize.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.JSONException;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;


public class First {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First window = new First();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public First() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 449, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHotelMenu = new JLabel("Hotel Menu");
		lblHotelMenu.setBounds(37, 51, 82, 16);
		frame.getContentPane().add(lblHotelMenu);
		
		JLabel lblSelectCountry = new JLabel("Select Country");
		lblSelectCountry.setBounds(37, 73, 95, 16);
		frame.getContentPane().add(lblSelectCountry);
		
		JComboBox countryCombo = new JComboBox();
		countryCombo.setBounds(247, 70, 135, 22);
		frame.getContentPane().add(countryCombo);
		countryCombo.addItem("Great Britain");
		countryCombo.addItem("USA");
	
		JLabel lblSelectTimings = new JLabel("Select Restaurant Category");
		lblSelectTimings.setBounds(37, 108, 156, 16);
		frame.getContentPane().add(lblSelectTimings);
		
		JComboBox restaurantCombo = new JComboBox();
		restaurantCombo.setBounds(247, 105, 135, 22);
		frame.getContentPane().add(restaurantCombo);
		restaurantCombo.addItem("Diner");
		restaurantCombo.addItem("Evening Only");
		restaurantCombo.addItem("All Day");
			
		
		JLabel lblSelectFoodCategory = new JLabel("Select Menu Format");
		lblSelectFoodCategory.setBounds(37, 139, 135, 16);
		frame.getContentPane().add(lblSelectFoodCategory);
		
		JComboBox menuFormat = new JComboBox();
		menuFormat.setBounds(247, 140, 135, 22);
		frame.getContentPane().add(menuFormat);
		menuFormat.addItem("Plain Text");
		menuFormat.addItem("HTML");
		menuFormat.addItem("XML");
		
		JButton btnGo = new JButton("Go!");
		btnGo.setBounds(139, 185, 97, 25);
		frame.getContentPane().add(btnGo);
		btnGo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String country= countryCombo.getSelectedItem().toString();
				String restaurant= restaurantCombo.getSelectedItem().toString();
				String food= menuFormat.getSelectedItem().toString();
				//System.out.println(country);
				//System.out.println(restaurant);
				//System.out.println(food);	
				analyze(country, restaurant, food);
			}
		});
		
	}
	
	public int analyze(String country, String restaurant, String food){
		int c=0,r=0,f=0;
		if(country.equals("USA")) c=2;
		if(country.equals("Great Britain")) c=1;
		if(restaurant.equals("Diner")) r=1;
		if(restaurant.equals("Evening Only")) r=2;
		if(restaurant.equals("All Day")) r=3;
		if(food.equals("Plain Text")) f=1;
		if(food.equals("HTML")) f=2;
		if(food.equals("XML")) f=3;
		
		System.out.println(c);
		System.out.println(r);
		System.out.println(f);
		chooseMenu(c,r,f);
		return 0;
	}
	
	public int chooseMenu(int c, int r, int f){
		
		if(c==1){
			try {
				 int k=0;
				File fXmlFile = new File("C:/temp/FoodItemData.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				System.out.println("Food Item Data :" + doc.getDocumentElement().getNodeName());
				NodeList nList = doc.getElementsByTagName("FoodItem");
				System.out.println("----------------------------");
				StringBuilder output = new StringBuilder();
				output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				output.append("<FoodItemData xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='file:/Users/fcalliss/Dropbox/NB7.2_Projects/CreationalPatterns/src/main/resources/edu/asu/codesamples/cse460/creationalpatterns/dao/FoodItemDataXmlSchema.xsd'>\n");
				for (int temp = 0; temp < nList.getLength(); temp++) 
				{
					Node nNode = nList.item(temp);
				
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						//System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
						//System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
						//System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
						//System.out.println("category : " + eElement.getElementsByTagName("category").item(0).getTextContent());
						//System.out.println("price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
						if(r==1){
						if(eElement.getElementsByTagName("category").item(0).getTextContent().equals("Breakfast") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Lunch") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Snack") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Appetizer") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Lunch")){
								System.out.println("\nCurrent Element :" + nNode.getNodeName());
								System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
								System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
								System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
								System.out.println("category : " + eElement.getElementsByTagName("category").item(0).getTextContent());
								System.out.println("price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
								k++;
								System.out.println(k);
								//PrintWriter writer = new PrintWriter("C:\\temp\\textout.txt", "UTF-8");
								if(f==1)
								{
								try {
									//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
									 String filename= "C:\\temp\\GB-Diner.txt";
									    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
									    //fw.write(eElement.getElementsByTagName("id").item(0).getTextContent().toString()); fw.append("\n");
									    fw.write( eElement.getElementsByTagName("name").item(0).getTextContent().toString()+"------------------"+"GBP" + eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
									    fw.write(eElement.getElementsByTagName("description").item(0).getTextContent().toString());fw.append("\n");
									    fw.write("Category--" +eElement.getElementsByTagName("category").item(0).getTextContent().toString());fw.append("\n");
									    //fw.write(eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
									    fw.append("\n");
									    fw.close();
								}catch (IOException e) {
								    
								}
								}
								
								if(f==2)
								{
									File f1= new File("C:\\temp\\GB-Diner-HTML.html");
									String html= "<div><h1>GB Diner Menu</h1></div>";
									BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
									bw1.write(html);
									
									try{
										BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
										//bw.write("<h1>"+ eElement.getElementsByTagName("id").item(0).getTextContent() +"</h1>"); bw.append("\n");
									    bw.write("<h1><li>" + eElement.getElementsByTagName("name").item(0).getTextContent().toString() + "</li></h1>");bw.append("\n");
									    bw.write("<body><p>" +eElement.getElementsByTagName("description").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
									    bw.write("<body><p>"+"Category--" + eElement.getElementsByTagName("category").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
									    bw.write("<body><p>" +"Price is $"+ eElement.getElementsByTagName("price").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
										bw.close();
									}
									catch(IOException e)
									{
										
									}
									
								}
								
								//f=3 r=1
								if(f==3)
								{
//									
												
								output.append("<FoodItem country=\"GB\">\n");
								output.append("<id>"+eElement.getElementsByTagName("id").item(0).getTextContent()+"</id>\n");
								output.append("<name>"+eElement.getElementsByTagName("name").item(0).getTextContent()+"</name>\n");
								output.append("<description>"+eElement.getElementsByTagName("description").item(0).getTextContent()+"</description>\n");
								output.append("<category>"+eElement.getElementsByTagName("category").item(0).getTextContent()+"</category>\n");
								output.append("<price>"+eElement.getElementsByTagName("price").item(0).getTextContent()+"</price>\n");
						        output.append("</FoodItem>\n");
							}    
						}
						}
						if(r==2){
							if(eElement.getElementsByTagName("category").item(0).getTextContent().equals("Dinner") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Side Dish") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Appetizer") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Dessert")){
							System.out.println("\nCurrent Element :" + nNode.getNodeName());
							System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
							System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
							System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
							System.out.println("category : " + eElement.getElementsByTagName("category").item(0).getTextContent());
							System.out.println("price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
							k++;
							System.out.println(k);
							if(f==1)
							{
							try {
								//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
								 String filename= "C:\\temp\\GB-Evening-Only.txt";
								    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
								  //fw.write(eElement.getElementsByTagName("id").item(0).getTextContent().toString()); fw.append("\n");
								    fw.write( eElement.getElementsByTagName("name").item(0).getTextContent().toString()+"------------------"+"GBP" + eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
								    fw.write(eElement.getElementsByTagName("description").item(0).getTextContent().toString());fw.append("\n");
								    fw.write("Category--" +eElement.getElementsByTagName("category").item(0).getTextContent().toString());fw.append("\n");
								    //fw.write(eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
								    fw.append("\n");
								    fw.close();
							}catch (IOException e) {
							    
							}
							}
							
							if(f==2)
							{
								File f1= new File("C:\\temp\\GB-Evening-Only-HTML.html");
								String html= "<div><h1>GB Diner Menu</h1></div>";
								BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
								bw1.write(html);
								
								try{
									BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
									//bw.write("<h1>"+ eElement.getElementsByTagName("id").item(0).getTextContent() +"</h1>"); bw.append("\n");
								    bw.write("<h1><li>" + eElement.getElementsByTagName("name").item(0).getTextContent().toString() + "</li></h1>");bw.append("\n");
								    bw.write("<body><p>" +eElement.getElementsByTagName("description").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
								    bw.write("<body><p>"+"Category--" + eElement.getElementsByTagName("category").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
								    bw.write("<body><p>" +"Price is $"+ eElement.getElementsByTagName("price").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
									bw.close();
								}
								catch(IOException e)
								{
									
								}
								
							}
							
							if(f==3)
							{
			
							output.append("<FoodItem country=\"GB\">\n");
							output.append("<id>"+eElement.getElementsByTagName("id").item(0).getTextContent()+"</id>\n");
							output.append("<name>"+eElement.getElementsByTagName("name").item(0).getTextContent()+"</name>\n");
							output.append("<description>"+eElement.getElementsByTagName("description").item(0).getTextContent()+"</description>\n");
							output.append("<category>"+eElement.getElementsByTagName("category").item(0).getTextContent()+"</category>\n");
							output.append("<price>"+eElement.getElementsByTagName("price").item(0).getTextContent()+"</price>\n");
					        output.append("</FoodItem>\n");
						}    
						}
						}
						if(r==3){
							if(eElement.getElementsByTagName("category").item(0).getTextContent().equals("Breakfast") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Dessert") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Snack") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Appetizer") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Lunch") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Side Dish") || eElement.getElementsByTagName("category").item(0).getTextContent().equals("Dinner")){
							System.out.println("\nCurrent Element :" + nNode.getNodeName());
							System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
							System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
							System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
							System.out.println("category : " + eElement.getElementsByTagName("category").item(0).getTextContent());
							System.out.println("price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
							k++;
							System.out.println(k);
								
							if(f==1)
							{
							try {
								//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
								 String filename= "C:\\temp\\GB-Allday.txt";
								    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
								  //fw.write(eElement.getElementsByTagName("id").item(0).getTextContent().toString()); fw.append("\n");
								    fw.write( eElement.getElementsByTagName("name").item(0).getTextContent().toString()+"------------------"+"GBP" + eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
								    fw.write(eElement.getElementsByTagName("description").item(0).getTextContent().toString());fw.append("\n");
								    fw.write("Category--" +eElement.getElementsByTagName("category").item(0).getTextContent().toString());fw.append("\n");
								    //fw.write(eElement.getElementsByTagName("price").item(0).getTextContent().toString());fw.append("\n");
								    fw.append("\n");
								    fw.close();
							}catch (IOException e) {
							    
							}
							}
							
							if(f==2)
							{
								File f1= new File("C:\\temp\\GB-Allday-HTML.html");
								String html= "<div><h1>GB Diner Menu</h1></div>";
								BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
								bw1.write(html);
								
								try{
									BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
									//bw.write("<h1>"+ eElement.getElementsByTagName("id").item(0).getTextContent() +"</h1>"); bw.append("\n");
								    bw.write("<h1><li>" + eElement.getElementsByTagName("name").item(0).getTextContent().toString() + "</li></h1>");bw.append("\n");
								    bw.write("<body><p>" +eElement.getElementsByTagName("description").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
								    bw.write("<body><p>"+"Category--" + eElement.getElementsByTagName("category").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
								    bw.write("<body><p>" +"Price is $"+ eElement.getElementsByTagName("price").item(0).getTextContent().toString() + "</p></body>");bw.append("\n");
									bw.close();
								}
								catch(IOException e)
								{
									
								}
								
							}
							if(f==3)
							{
			
							output.append("<FoodItem country=\"GB\">\n");
							output.append("<id>"+eElement.getElementsByTagName("id").item(0).getTextContent()+"</id>\n");
							output.append("<name>"+eElement.getElementsByTagName("name").item(0).getTextContent()+"</name>\n");
							output.append("<description>"+eElement.getElementsByTagName("description").item(0).getTextContent()+"</description>\n");
							output.append("<category>"+eElement.getElementsByTagName("category").item(0).getTextContent()+"</category>\n");
							output.append("<price>"+eElement.getElementsByTagName("price").item(0).getTextContent()+"</price>\n");
					        output.append("</FoodItem>\n");
						}
						}
						}
					}
				}
				output.append("</FoodItemData>");
				System.out.println(output.toString());
					
				String xmlfilename=null;
				if(r==1 && f==3)
					xmlfilename = "C:\\temp\\GB-Diner.xml";
				if(r==2 && f==3)
					xmlfilename = "C:\\temp\\GB-EveningOnly.xml";
				if(r==3 && f==3)
					xmlfilename = "C:\\temp\\GB-AllDay.xml";
				FileWriter fwxml = new FileWriter(xmlfilename,true); //the true will append the new data
				fwxml.write(output.toString());
				fwxml.close();
					
			    } 
				
			catch (Exception e) {
				e.printStackTrace();
			    }
		}
		//To Read JSON file
		if(c==2){
			try {
	            FileReader reader = new FileReader("C:/temp/FoodItemData.json");
	            
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
//	            System.out.println(jsonObject);
	 
	            //String country = (String) jsonObject.get("-country");
	            //System.out.println(country);
	            
//	           long id = (long) jsonObject.get("id");
	            JSONArray items = (JSONArray) jsonObject.get("FoodItemData");
	            int k=1;
	            Iterator i = items.iterator();
	            StringBuilder output1 = new StringBuilder();
				output1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				output1.append("<FoodItemData xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='file:/Users/fcalliss/Dropbox/NB7.2_Projects/CreationalPatterns/src/main/resources/edu/asu/codesamples/cse460/creationalpatterns/dao/FoodItemDataXmlSchema.xsd'>\n");
				
	            while (i.hasNext()) 
	            {
	             JSONObject innerObj = (JSONObject) i.next(); 
	             
	             if(r==1){
	             if((innerObj.containsValue("Breakfast") || innerObj.containsValue("Lunch") || innerObj.containsValue("Snack") || innerObj.containsValue("Appetizer") || innerObj.containsValue("Dessert")) && innerObj.containsValue("US")){
	            	 System.out.println("id "+innerObj.get("id"));
		             System.out.println("name "+innerObj.get("name"));
		             System.out.println("description "+innerObj.get("description"));
		             System.out.println("category "+innerObj.get("category"));
	            	 System.out.println("price "+innerObj.get("price"));
	             k++;
	             
	             if(f==1)
					{
					try {
						//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
						 String filename= "C:\\temp\\US-Diner.txt";
						    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
						    //fw.write(innerObj.get("id").toString()); fw.append("\n");
						    fw.write( innerObj.get("name").toString()+"------------------"+"$"+innerObj.get("price").toString());fw.append("\n");
						    fw.write(innerObj.get("description").toString());fw.append("\n");
						    fw.write("Category--" + innerObj.get("category").toString());fw.append("\n");
						    //fw.write(innerObj.get("price").toString());fw.append("\n");
						    fw.append("\n");
						    fw.close();
					}catch (IOException e) {
					    
					}
					}
					
					if(f==2)
					{
						File f1= new File("C:\\temp\\US-Diner-HTML.html");
						String html= "<div><h1>GB Diner Menu</h1></div>";
						BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
						bw1.write(html);
						
						try{
							BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
							//bw.write("<body>"+ innerObj.get("id").toString() +"</body>"); bw.append("\n");
						    bw.write("<h1><li>" + innerObj.get("name").toString() + "</li></h1>");bw.append("\n");
						    bw.write("<body><p>" + innerObj.get("description").toString() + "</p></body>");bw.append("\n");
						    bw.write("<body><p>" +"Category--"+ innerObj.get("category").toString() + "</p></body>");bw.append("\n");
						    bw.write("<body><p>" +"Price is $"+innerObj.get("price").toString() + "</p></body>");bw.append("\n");
							bw.close();
						}
						catch(IOException e)
						{
							
						}
						
					}
					if(f==3)
					{
	
					output1.append("<FoodItem country=\"US\">\n");
					output1.append("<id>"+innerObj.get("id").toString()+"</id>\n");
					output1.append("<name>"+innerObj.get("name").toString()+"</name>\n");
					output1.append("<description>"+innerObj.get("description").toString()+"</description>\n");
					output1.append("<category>"+innerObj.get("category").toString()+"</category>\n");
					output1.append("<price>"+innerObj.get("price").toString()+"</price>\n");
			        output1.append("</FoodItem>\n");
				}
	             }
	            }
	            
	             if(r==2){
		             if((innerObj.containsValue("Dinner") || innerObj.containsValue("Side Dish") || innerObj.containsValue("Appetizer") || innerObj.containsValue("Dessert")) && innerObj.containsValue("US")){	 
		            	 System.out.println("id "+innerObj.get("id"));
			             System.out.println("name "+innerObj.get("name"));
			             System.out.println("description "+innerObj.get("description"));
			             System.out.println("category "+innerObj.get("category"));
		            	 System.out.println("price "+innerObj.get("price"));
		             k++;
		             
		             if(f==1)
						{
						try {
							//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
							 String filename= "C:\\temp\\US-EveningOnly.txt";
							    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
							  //fw.write(innerObj.get("id").toString()); fw.append("\n");
							    fw.write( innerObj.get("name").toString()+"------------------"+"$"+innerObj.get("price").toString());fw.append("\n");
							    fw.write(innerObj.get("description").toString());fw.append("\n");
							    fw.write("Category--" + innerObj.get("category").toString());fw.append("\n");
							    //fw.write(innerObj.get("price").toString());fw.append("\n");
							    fw.append("\n");
							    fw.close();
						}catch (IOException e) {
						    
						}
						}
						
						if(f==2)
						{
							File f1= new File("C:\\temp\\US-EveningOnly-HTML.html");
							String html= "<div><h1>GB Diner Menu</h1></div>";
							BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
							bw1.write(html);
							
							try{
								BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
								//bw.write("<body>"+ innerObj.get("id").toString() +"</body>"); bw.append("\n");
							    bw.write("<h1><li>" + innerObj.get("name").toString() + "</li></h1>");bw.append("\n");
							    bw.write("<body><p>" + innerObj.get("description").toString() + "</p></body>");bw.append("\n");
							    bw.write("<body><p>" +"Category--"+ innerObj.get("category").toString() + "</p></body>");bw.append("\n");
							    bw.write("<body><p>" +"Price is $"+innerObj.get("price").toString() + "</p></body>");bw.append("\n");
								bw.close();
							}
							catch(IOException e)
							{
								
							}
							
						}
						if(f==3)
						{
		
						output1.append("<FoodItem country=\"US\">\n");
						output1.append("<id>"+innerObj.get("id").toString()+"</id>\n");
						output1.append("<name>"+innerObj.get("name").toString()+"</name>\n");
						output1.append("<description>"+innerObj.get("description").toString()+"</description>\n");
						output1.append("<category>"+innerObj.get("category").toString()+"</category>\n");
						output1.append("<price>"+innerObj.get("price").toString()+"</price>\n");
				        output1.append("</FoodItem>\n");
					}
		             }
		            }
	             
	             if(r==3){
		             if((innerObj.containsValue("Breakfast") || innerObj.containsValue("Lunch") || innerObj.containsValue("Snack") || innerObj.containsValue("Side Dish") || innerObj.containsValue("Appetizer") || innerObj.containsValue("Dinner") || innerObj.containsValue("Dessert")) && innerObj.containsValue("US")){	 
		            	 System.out.println("id "+innerObj.get("id"));
			             System.out.println("name "+innerObj.get("name"));
			             System.out.println("description "+innerObj.get("description"));
			             System.out.println("category "+innerObj.get("category"));
		            	 System.out.println("price "+innerObj.get("price"));
		             k++;
		             
		             if(f==1)
						{
						try {
							//writer = (PrintWriter) new FileWriter("C:\\temp\textout.txt", true);
							 String filename= "C:\\temp\\US-Allday.txt";
							    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
							  //fw.write(innerObj.get("id").toString()); fw.append("\n");
							    fw.write( innerObj.get("name").toString()+"------------------"+"$"+innerObj.get("price").toString());fw.append("\n");
							    fw.write(innerObj.get("description").toString());fw.append("\n");
							    fw.write("Category--" + innerObj.get("category").toString());fw.append("\n");
							    //fw.write(innerObj.get("price").toString());fw.append("\n");
							    fw.append("\n");
							    fw.close();
						}catch (IOException e) {
						    
						}
						}
						
						if(f==2)
						{
							File f1= new File("C:\\temp\\US-Allday-HTML.html");
							String html= "<div><h1>GB Diner Menu</h1></div>";
							BufferedWriter bw1= new BufferedWriter(new FileWriter(f1, true));
							bw1.write(html);
							
							try{
								BufferedWriter bw= new BufferedWriter(new FileWriter(f1, true));
								//bw.write("<body>"+ innerObj.get("id").toString() +"</body>"); bw.append("\n");
							    bw.write("<h1><li>" + innerObj.get("name").toString() + "</li></h1>");bw.append("\n");
							    bw.write("<body><p>" + innerObj.get("description").toString() + "</p></body>");bw.append("\n");
							    bw.write("<body><p>" +"Category--"+ innerObj.get("category").toString() + "</p></body>");bw.append("\n");
							    bw.write("<body><p>" +"Price is $"+innerObj.get("price").toString() + "</p></body>");bw.append("\n");
								bw.close();
							}
							catch(IOException e)
							{
								
							}
							
						}
						if(f==3)
						{
		
						output1.append("<FoodItem country=\"US\">\n");
						output1.append("<id>"+innerObj.get("id").toString()+"</id>\n");
						output1.append("<name>"+innerObj.get("name").toString()+"</name>\n");
						output1.append("<description>"+innerObj.get("description").toString()+"</description>\n");
						output1.append("<category>"+innerObj.get("category").toString()+"</category>\n");
						output1.append("<price>"+innerObj.get("price").toString()+"</price>\n");
				        output1.append("</FoodItem>\n");
					}
		             }
		            }
	           
	            }
	            output1.append("</FoodItemData>");
				System.out.println(output1.toString());
				String jsonfilename=null;
				if(r==1 && f==3)
					jsonfilename = "C:\\temp\\US-Diner.xml";
				if(r==2 && f==3)
					jsonfilename = "C:\\temp\\US-EveningOnly.xml";
				if(r==3 && f==3)
					jsonfilename = "C:\\temp\\US-AllDay.xml";
				FileWriter jsonw = new FileWriter(jsonfilename,true); //the true will append the new data
				jsonw.write(output1.toString());
	            //System.out.println(k);
	            //End of while loop
				jsonw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		return 0;
	}
	
}
