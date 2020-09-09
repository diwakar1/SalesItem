package com.saleitem;


/*by Diwakar Sharma
 * mykanchha@gmail.com
*/
import java.util.Scanner;
import java.util.ArrayList;

public class ItemSale {
	
	//this is the arrayList which takes Object
	public static ArrayList<Record> items = new ArrayList<>();
	
	//declaration of Object through instantiation of class Record
	static Record record;

	//main method which runs one method 
	public static void main(String[] args) {

		printAll();
	}
	
	
	// this method  prints  getAll method until condition met.  otherwise  it gets the arrayList and prints all required items and loop ends.

	public static void printAll() {
		Scanner scanner = new Scanner(System.in);
		int number = 0;
		String ans = "yes";

		while (ans.equalsIgnoreCase("yes")) {
			String itemName = null;
			double price = 0;
			getAll(itemName, price);

			System.out.println("do you want to buy other item ? 'yes' or 'no' ");
			ans = scanner.nextLine();
		}
		
		System.out.println(" ");
		System.out.println("---------------- ----------Receipt-----------------------------------------");
		
		for (Record thisRecord : items) {
			
			System.out.println(
					"============================================================================================"
							+ "===================================");
			System.out.println(
					"Itemwise Name  \t price  \t    basic tax amount \t   imported tax amount \t\t   total    ");
			System.out
					.println(thisRecord.itemWiseName + "\t\t " + thisRecord.price + "\t\t\t" + thisRecord.basicTaxAmount
							+ "\t\t\t " + thisRecord.importedTaxAmount + "\t\t\t " + thisRecord.total

					);
			System.out.println(
					"============================================================================================"
							+ "===================================");
			System.out.println(" ");

		}

	}
      // this method asks the user input through Scanner class. it also calls the methods getBasicsSalesTax(), get importTax().
	 // it also calls the getTotal() different ways. 
	public static void getAll(String itemName, double price) {
        //declared variables,initialization of variables, userInput
		String itemWiseName;
		double  basicSalesTaxPercent = 10;
		int importTaxRatePercent = 5;
		String imported;
		Scanner scan = new Scanner(System.in);
		System.out.println("what is the item type you want to buy? 'book' or 'medicine' or 'food' or 'anythingElse' ");
		itemName = scan.nextLine();
		System.out.println("what is the itemwise name");
		itemWiseName = scan.nextLine();
		System.out.println("is item is imported or not? please enter'yes' or 'no' ");
		imported = scan.next();
		System.out.println("what is the price of Item");
		price = scan.nextDouble();
		if(price<0) {
			System.out.println("please enter price as positive amount");
			price = scan.nextDouble();

		}

		double basicSalesTaxAmount = getBasicSalesTax(price, itemName, basicSalesTaxPercent);
		double importTaxAmount = getImportTax(price, itemName, importTaxRatePercent, imported);
		double totalAmount = 0;
		if ((!itemName.equalsIgnoreCase("book") || !itemName.equalsIgnoreCase("medicine")
				|| !itemName.equalsIgnoreCase("food")) && (imported.equalsIgnoreCase("yes"))) {
			totalAmount = getTotal(price, basicSalesTaxAmount, importTaxAmount);

		} else if ((itemName.equalsIgnoreCase("book") || itemName.equalsIgnoreCase("medicine")
				|| itemName.equalsIgnoreCase("food")) && !(imported.equalsIgnoreCase("yes"))) {
			totalAmount = price;
		} else if ((itemName.equalsIgnoreCase("book") || itemName.equalsIgnoreCase("medicine")
				|| itemName.equalsIgnoreCase("food")) && (imported.equalsIgnoreCase("yes"))) {
			totalAmount = getTotal(price, importTaxAmount);
		} else {
			totalAmount = getTotal(price, basicSalesTaxAmount);
		}
        //instantiation of Object 
		record = new Record(itemWiseName, price, basicSalesTaxAmount, importTaxAmount, totalAmount);

		items.add(record);

	}

	// it finds out basic tax with calculation.
	public static double getBasicSalesTax(double price, String name, double basicSalesTaxPercent) {
		double basicSalesTax = 0;
		if ((name.equalsIgnoreCase("book")) || (name.equalsIgnoreCase("medicine")) || (name.equalsIgnoreCase("food"))) {
			basicSalesTax = 0;
		} else {
			basicSalesTax = (price * basicSalesTaxPercent) / 100;
		}

		return basicSalesTax;

	}

	// it finds out import tax with simple calculation.
	public static double getImportTax(double price, String name, int importTaxRatePercent, String imported) {
		double importTax = 0;
		if (!imported.equalsIgnoreCase("yes")) {
			importTax = 0;
		} else {
			importTax = (price * importTaxRatePercent) / 100;
		}
		return importTax;

	}

	// it finds out total with 3 arguments
	public static double getTotal(double price, double basicSalesTaxAmount, double importTaxAmount) {

		double total = 0;

		total += Math.ceil((price + basicSalesTaxAmount + importTaxAmount) * 10) / 10;

		return total;

	}

	// it finds out total with 2 arguments
	public static double getTotal(double price, double taxAmount) {

		double total = 0;

		total += Math.ceil((price + taxAmount) * 10) / 10;

		return total;

	}

}



// this is the other class which have declared variables with loaded constructor.
class Record {
	String itemWiseName;
	double price;
	double basicTaxAmount;
	double importedTaxAmount;
	double total;

	Record(String itemWiseName, double price, double basicTaxAmount, double importedTaxAmount, double total) {
		this.itemWiseName = itemWiseName;
		this.price = price;
		this.basicTaxAmount = basicTaxAmount;
		this.importedTaxAmount = importedTaxAmount;
		this.total = total;
	}

}

