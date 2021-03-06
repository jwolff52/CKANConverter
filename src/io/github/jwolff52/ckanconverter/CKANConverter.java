/*
 * Converts .ckan files to .txt or .csv
    Copyright (C) 2015  James Wolff

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.jwolff52.ckanconverter;

import java.io.File;
import java.util.Scanner;

import io.github.jwolff52.ckanconverter.util.Parser;

public class CKANConverter {
	
	private static String importLocation;
	private static String exportLocation;
	
	public static Scanner scan;

	/**
	 * 
	 * @param args - file import location, file export location, optional behavior parameters
	 *  -c Convert to CSV
	 *  -t Convert to Text (default)
	 */
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		if(args.length > 3) {
			System.out.println("You passed too many parameters, be sure to surround import and export locations with \"\")");
			quit();
		}
		if(args.length < 2) {
			args = new String [3];
			System.out.println("Please provide the path to the .ckan file (without quotes): ");
			args[0] = scan.nextLine();
			System.out.println("Please provide the path to the .txt or .csv file (without quotes): ");
			args[1] = scan.nextLine();
			System.out.println("Is this going to be exported as a .csv Y/N: ");
			String temp = scan.nextLine();
			if(temp.toLowerCase().startsWith("y")) {
				args[2] = "-c";
			}else {
				args[2] = "-t";
			}
		}
		importLocation = args[0];
		exportLocation = args[1];
		boolean isText=true;
		if(args[2].equalsIgnoreCase("-c")) {
			isText = false;
		}
		Parser.parseFile(new File(importLocation), new File(exportLocation), isText);
		scan.close();
	}
	
	public static void quit() {
		scan.close();
		System.exit(0);
	}
}
