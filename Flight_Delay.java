
package termproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class TermProject<T> {

    static ArrayList<String[]> List = new ArrayList<String[]>();
    static LinkedList<String[]> linkedList = new LinkedList<>();
    static Map<String, List<String>> sortedMap = new TreeMap<String, List<String>>();

    
    private static HashMap<String, List<String> > map = new HashMap<>(); 
    private static HashMap<String, List<String> > map2 = new HashMap<>(); 
    private static HashMap<String, List<String> > map3 = new HashMap<>(); 
    private static HashMap<String, Integer > mapWithCount = new HashMap<>(); 
    private static HashMap<String, Integer > mapWithCount2 = new HashMap<>(); 
    
    static String arrayLine[];
    static String array[];
    static int count=0;
    static int count2 = 0;
    static int counter3 = 0;
    static int counter4=0;
    static double sum = 0;
    static double sum2 = 0;
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        
        System.out.println("---------");
        System.out.println("Question1");
        Question1();
        
        System.out.println("\n---------");
        System.out.println("Question2");
        Question2();
        
        System.out.println("\n---------");
        System.out.println("Question3");
        Question3();
        
        System.out.println("\n---------");
        System.out.println("Question4");
        Question4();
        
        
    }
        //6 saniye
        public static void Question1() {
            
            String fileName = "flights.csv";
            long startTime = System.currentTimeMillis();
             try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                br.readLine();
                String data;
                while ((data = br.readLine()) != null) {
                    array = data.split(",");
                    count ++;
                    for(int i=0; i<15; i++) {
                        if(array[i].equals("")) {
                            count2++;
                            break;
                        }
                    }

                }
             } catch(Exception e) {
                 System.out.println("Error");
             }
            int good = count-count2;
            System.out.println("Number of total: " + count  );
            System.out.println("Number of Eliminated: " +count2);
            System.out.println("Number of Good : " + good);
        
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime-startTime;
            System.out.println("\nTotal Time : "+totalTime/1000 + " seconds");
            
    }
        
        //16 saniye
        public static void Question2() throws ParseException {
            String file = "flights.csv";
            Scanner s= new Scanner(System.in);
            System.out.println("Enter Fist Date: (Format: MM/dd/yyyy) ");
            String first = s.next();
            System.out.println("Enter Second Date: (Format: MM/dd/yyyy) ");
            String second = s.next();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date firstDate = sdf.parse(first);
            Date secondDate = sdf.parse(second);
            long startProcessingTime = System.currentTimeMillis();
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                br.readLine();
                String data;
                while((data = br.readLine()) != null) {
                    count++;
                    array = data.split(",");
                    String temp = array[1] + "/" +array[2] + "/" + array[0];
                    Date tempF = sdf.parse(temp);
                    if(tempF.compareTo(firstDate) >= 0 && secondDate.compareTo(tempF) >=0) {
                        if(!array[4].equals("") && !array[5].equals("") && !array[11].equals("")) {
                        String flightNo = array[4] + array[5];
                        if (!map.containsKey(flightNo)) {
                            map.put(flightNo, new LinkedList<String>());
                            map.get(flightNo).add(array[11]);
                            counter3++;
                        }   
                        else if(map.containsKey(flightNo)) {
                            map.get(flightNo).add(array[11]);

                        }
                        }
                    }
                }
            }
            catch(Exception e){
                System.out.println("Error");
            }
            for(Map.Entry<String, List<String>> entry : map.entrySet()){
                String flight = entry.getKey();
                int entries = entry.getValue().size();
                mapWithCount.put(flight, entries);
            }
            Map<String,Integer> sortedFlightMap = sortByValues(mapWithCount);
            long endProcessingTime  = System.currentTimeMillis();
            System.out.println("Flight Number:\t\tEntries:\t\tMax Delay:\t\tMin Delay:\t\tAverage Delay:");
            for(String flight: sortedFlightMap.keySet()){
                List<String> v = map.get(flight);
                int maxValue = Integer.parseInt(v.get(0));
                int minValue = Integer.parseInt(v.get(0));
                sum = Double.parseDouble(v.get(0));
                for(int j = 1; j<v.size(); j++) {
                    if(maxValue<Integer.parseInt(v.get(j))) {
                        maxValue = Integer.parseInt(v.get(j));
                    }
                    if(Integer.parseInt(v.get(j)) < minValue) {
                        minValue = Integer.parseInt(v.get(j));
                    }
                    sum += Double.parseDouble(v.get(j));
                }
                System.out.println(flight + "\t\t\t" + v.size() + "\t\t\t" + maxValue + "\t\t\t" + minValue + "\t\t\t" + String.format("%.2f", sum/v.size()) );
            }
            System.out.println("Airline Flight Count without Repitition: "+counter3);
            System.out.println("Airline Flight Count with Repitition: "+count);
            long endTotalTime   = System.currentTimeMillis();
            long totalProcessingTime = endProcessingTime - startProcessingTime;
            long totalTime = endTotalTime-startProcessingTime;
            System.out.println("\nTime with printing: "+totalTime/1000 + "seconds");
            System.out.println("Time without printing: "+totalProcessingTime/1000+ "seconds");
        }
        
        //8saniye
        public static void Question3() throws ParseException {
            
            String file = "flights.csv";
            Scanner s= new Scanner(System.in);
            System.out.println("Enter Fist Date: (Format: MM/dd/yyyy) ");
            String first = s.next();
            System.out.println("Enter Second Date: (Format: MM/dd/yyyy) ");
            String second = s.next();
            System.out.println("Enter Airlane Name: ");
            String airline = s.next();
            long startProcessingTime = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date firstDate = sdf.parse(first);
            Date secondDate = sdf.parse(second);
             try (BufferedReader br = new BufferedReader(new FileReader(file))){
                br.readLine();
                String data;
                while((data = br.readLine()) != null) {
                    count++;
                    array = data.split(",");
                    String temp = array[1] + "/" +array[2] + "/" + array[0];
                    Date tempF = sdf.parse(temp);
                    if(array[4].equals(airline)) {
                    if(tempF.compareTo(firstDate) >= 0 && secondDate.compareTo(tempF) >=0) {
                        if(!array[5].equals("") && !array[11].equals("") 
                                && !array[8].equals("") && !array[7].equals("")){
                        String flightNo = airline + array[5] + " "  + array[7] + " " +array[8];
                        if (!map2.containsKey(flightNo)) {
                            map2.put(flightNo, new LinkedList<String>());
                            map2.get(flightNo).add(array[11]);
                            counter4++;
                        }
                        else if(map2.containsKey(flightNo)) {
                            map2.get(flightNo).add(array[11]);
                        }
                    }
                }
            }
        }
            } catch(Exception e) {
                 System.out.println("error");
            }
              for(Map.Entry<String, List<String>> entry : map2.entrySet()){
                String flight = entry.getKey();
                int entries = entry.getValue().size();
                mapWithCount2.put(flight, entries);
            }
            Map<String,Integer> sortedFlightMap = sortByValues(mapWithCount2);
            long endProcessingTime  = System.currentTimeMillis();
            System.out.println("Original & Dest & Flight:\t\tEntries:\t\tMax Delay:\t\tMin Delay:\t\tAverage Delay:");
             for(String flight: sortedFlightMap.keySet()){
		List<String> v = map2.get(flight);
                int maxValue = Integer.parseInt(v.get(0));
                int minValue = Integer.parseInt(v.get(0));
                sum2 = Double.parseDouble(v.get(0));
                for(int j = 1; j<v.size(); j++) {
                    if(maxValue<Integer.parseInt(v.get(j))) {
                        maxValue = Integer.parseInt(v.get(j));
                    }
                    if(Integer.parseInt(v.get(j)) < minValue) {
                        minValue = Integer.parseInt(v.get(j));
                    }
                    sum2 += Double.parseDouble(v.get(j));
                }
		System.out.println(flight + "\t\t\t\t" + v.size() + "\t\t\t" + maxValue + "\t\t\t" + minValue + "\t\t\t" + String.format("%.2f", sum2/v.size()) );
	}
            System.out.println("Airlines & Flight Count without Repitition: "+counter4);
            System.out.println("Airline Flight Count with Repitition: "+count);
            long endTotalTime   = System.currentTimeMillis();
            long totalProcessingTime = endProcessingTime - startProcessingTime;
            long totalTime = endTotalTime-startProcessingTime;
            System.out.println("\nTime with printing: "+totalTime/1000 + "seconds");
            System.out.println("Time without printing: "+totalProcessingTime/1000+ "seconds");
        }
        
        //2 min 14 second //134 seconds
        public static void Question4() throws FileNotFoundException {
            
            String fileName = "flights.csv";
            long startTime = System.currentTimeMillis();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                br.readLine();
                String data;
                while((data = br.readLine()) != null) {
                     array = data.split(",");
                     if(!array[0].equals("YEAR") && !array[4].equals("") && !array[5].equals("")
                             && !array[6].equals("") && !array[7].equals("") && !array[8].equals("")
                             && !array[9].equals("") && !array[10].equals("")) {
                         String name = array[4] + "-" + array[5];
                         if (!map3.containsKey(name)) {
                            map3.put(name, new LinkedList<String>());
                            map3.get(name).add(array[6]);
                            map3.get(name).add(array[7]);
                            map3.get(name).add(array[8]);
                            map3.get(name).add(array[9]);
                            map3.get(name).add(array[10]);
                        }
                        else if(map3.containsKey(name)) {
                            map3.get(name).add(array[6]);
                            map3.get(name).add(array[7]);
                            map3.get(name).add(array[8]);
                            map3.get(name).add(array[9]);
                            map3.get(name).add(array[10]);
                        }
                     }
                }
            }catch(Exception e) {
                 System.out.println("Error");
             }
              for (Map.Entry<String, List<String>> entry : map3.entrySet()) {
                String k = entry.getKey();
                List<String> v = entry.getValue();
                PrintWriter pw = new PrintWriter(new File("csv\\" +k +".csv"));
                StringBuilder sb = new StringBuilder();
                sb.append(v.get(0));
                sb.append(",");
                sb.append(v.get(1));
                sb.append(",");
                sb.append(v.get(2));
                sb.append(",");
                sb.append(v.get(3));
                sb.append(",");
                sb.append(v.get(4));
                pw.write(sb.toString());
                pw.close();
              }
            System.out.println("Finished");
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime-startTime;
            System.out.println("\nTotal Time : "+totalTime/1000 + " seconds");
        }
        public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = (K k1, K k2) -> {
            int compare =
                    map.get(k2).compareTo(map.get(k1));
            if (compare == 0)
                return 1;
            else
                return compare;
        };
        Map<K, V> sortedByValues = new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
      }

    
}
