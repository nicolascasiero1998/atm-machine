package com.casierni.app;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("€#,##0.00");
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();

	public void getLogin() throws IOException {
		boolean end = false;
		int customerNumber = 0;
		int pinNumber = 0;
		while (!end) {
			try {
				System.out.print("\nEnter your customer number: ");
				customerNumber = menuInput.nextInt();
				System.out.print("\nEnter your PIN number: ");
				pinNumber = menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Account acc= (Account) pair.getValue();
					if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
						System.out.println("\nWelcome in you ATM personal account, your balance at moment it's :" + moneyFormat.format(acc.getCheckingBalance()));
						System.out.println("\nPlease see your current banknote denomination:");
						int[] values = {50,20,10,5};
				        int[] ammounts = {10,30,30,20};
				        List<Integer[]> results = solutions(values, ammounts, new int[4], (int) acc.getCheckingBalance(), 0);
				        String bankonotes[] = {"50€", "20€", "10€", "5€"};
				        String stringResult = null;
				        for (Integer[] result : results){
				            stringResult =  Arrays.toString(result);
				        }
				        	String[] arr = stringResult.split(",");
				        	for (int j = 0; j < arr.length; j++) {
				        		System.out.println("N° " + arr[j].replace("[","").replace("]","").replace(" ","") + " of " + bankonotes[j]);
							}
						getChecking(acc);
						end = true;
						break;
					}
				}
				if (!end) {
					System.out.println("\nWrong Customer Number or Pin Number");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Character(s). Only Numbers.");
			}
		}
	}

	public void getChecking(Account acc) throws IOException {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCheckings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Exit");
				System.out.print("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection) {
				case 1:
					System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
					break;
				case 2:
					acc.getCheckingWithdrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;
				case 4:
					end = true;
					System.out.println("Bye bye, please login again for more actions");
					getLogin();
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				menuInput.next();
			}
		}
	}

	public void mainMenu() throws IOException {
		data.put(123456789, new Account(123456789, 1234, 1500, 1500));
		data.put(987654321, new Account(987654321, 4321, 1500, 1500));
		getLogin();
	}
	
	public static List<Integer[]> solutions(int[] values, int[] ammounts, int[] variation, int price, int position){
        List<Integer[]> list = new ArrayList<>();
        int value = compute(values, variation);
        if (value < price){
            for (int i = position; i < values.length; i++) {
                if (ammounts[i] > variation[i]){
                    int[] newvariation = variation.clone();
                    newvariation[i]++;
                    List<Integer[]> newList = solutions(values, ammounts, newvariation, price, i);
                    if (newList != null){
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == price) {
            list.add(myCopy(variation));
        }
        return list;
    }    

    public static int compute(int[] values, int[] variation){
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }    

    public static Integer[] myCopy(int[] ar){
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }
}
