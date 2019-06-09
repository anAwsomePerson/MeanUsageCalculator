# Mean Usage Calculator
This program takes any combination of Pokemon usage files from smogon.com/stats/ and finds the mean usage for each Pokemon across all the files you put into the program. I couldn't make a nice usage calculator. Sorry :(

Note that each file gets the exact same weight when calculating means, so stuff like number of battles is completely ignored. It also truncates numbers to 3 decimal places, so any numbers in the output file can be up to 0.001% off. 

Given how it's set up now, you need Eclipse to run this. After you figure out how to download this and open it in Eclipse, you need to download n usage files, name them 0.txt, 1.txt, 2.txt...<n-1>.txt, and paste them in the big folder, replacing the files 0.txt through 11.txt already there. Then find Main.java and change the fileCount and format variables if you want. Then run the program and look at the output file. 
