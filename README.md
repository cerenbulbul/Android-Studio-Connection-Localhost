# FlightDelay

I wrote a programs for processing text (csv) files. You can download it the following link.
"https://drive.google.com/open?id=1RPlfZKW83M3FQbylulXxN6DPiF8daJ7z" The flight delays zipped file downloaded from Kaggle contains three files; airlines.csv, airports.csv
and flights.csv.
Works done in this project: 
 
  1. Sanitization
Eliminate repeating entries in Flights.csv, remove entries with missing values and errors in the file. Program should report the total processing time, # of eliminated entries and # of good entries.
To solve this part, I've separated the file line by line, or I've separated each line followed by column by column, and I've determined that any column is empty or full.

   <img src="https://user-images.githubusercontent.com/36292743/72291542-70e44a80-3604-11ea-9778-1d319e67651b.png" width="600" height="200">


  2. User will pick a date interval (start and end dates are included) and program will show a summary report for the entries between these dates, listing each flight (Airline and Flight Number), total number of entries, minimum departure delay, maksimum departure delay, average departure delay ordered by number of entries descending.
In this part, I first determined the day intervals. I then put the desired values that contain these ranges into HashMap. The key value in HashMap is Airline and Flight Number. The value value saves the departure delays (This section is made as a list). 

   <img src="https://user-images.githubusercontent.com/36292743/72291671-b143c880-3604-11ea-9826-5fd8da4727db.png" width="600" height="200">

This list is required to get the desired values. Before this, HashMap was assigned to TreeMap and sorted according to decreasing. Benchmarking method was used in order of decreasing order.

   <img src="https://user-images.githubusercontent.com/36292743/72291748-d89a9580-3604-11ea-90ea-3f8c9736cc1e.png" width="600" height="200">


  3. User will pick an airline and a date interval (start and end dates are included), and program will show a report for the entries between these dates listing, origin airport name, destination airport name, flight number and statistics such as total number of entries, minimum departure delay, maksimum departure delay, average departure delay ordered by departure delay descending.
This part is made in a similar way to the second question. HashMap was used and a comparison method was used to rank. Two ranges and an airline were determined and the desired values within these ranges were drawn. Then the desired values are put in the key and value. Here, unlike the second question, the original, destination airport and flight number are placed in the key. Other procedures were performed in a similar way to the second part.



