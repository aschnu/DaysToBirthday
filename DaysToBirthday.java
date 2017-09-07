/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author AGRZEGOR
 */
public class DaysToBirthday {

    /**
     * Application count how many days are left to your next birthday.
     * You should give day and month your birthday in format: d.m or dd.mm
     * Where: 
     * d - day 
     * m - month
     * 
     * example: For 12 June is 12.06 or 12.6
     */
    public static void main(String[] args) {
        try {
            //Print today's date
            Date nowDate = new GregorianCalendar().getTime();
            System.out.println("Today is: " + new SimpleDateFormat("dd.MM.yyyy").format(nowDate));
            Calendar cal = Calendar.getInstance();

            //take data from user
            Scanner sc = new Scanner(System.in);
            String userMD = sc.nextLine();

            //convert string on date
            Date userDate = nowDate;
            cal.setTime(userDate);
            int month = new Integer(userMD.substring(userMD.indexOf('.') + 1, userMD.length()));
            int day = new Integer(userMD.substring(0, userMD.indexOf('.')));

            //check that months and days are correct
            if(month > 12 || month < 1){
                throw new NullPointerException();
            }else if(month == 2 && day > 29){
                throw new NullPointerException();
            }else if(((month < 8 && month%2 != 0) || (month >= 8 && month%2 == 0)) && day >31){
                throw new NullPointerException();
            }else if(((month < 7 && month%2 == 0) || (month >= 9 && month%2 != 0)) && day >30){
                throw new NullPointerException();
            }
            
            //set next birthday's date
            Calendar calNow = Calendar.getInstance();
            calNow.setTime(nowDate);
            if (month < calNow.get(Calendar.MONTH) + 1 || (month == calNow.get(Calendar.MONTH) + 1 && day < calNow.get(Calendar.DAY_OF_MONTH))) {
                cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
            }
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);

            //count how many days left to next birthday
            long daysLeft = (cal.getTime().getTime() - nowDate.getTime()) / 1000 / 60 / 60 / 24;

            //print appropriate communicate about birthday
            if (daysLeft == 0) {
                System.out.println("Your birthday are TODAY! Happy birthday!");
            } else if (daysLeft == 1) {
                System.out.println("Your next birthday will be in: " + new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime()) + "\n" + daysLeft + " day is to your birthday!");
            } else {
                System.out.println("Your next bitrhday will be in: " + new SimpleDateFormat("dd.MM.yyyy").format(cal.getTime()) + "\n" + daysLeft + " days are left to your birthday!");

            }
        }catch(NullPointerException npe){
            System.out.println("Bad date: Your date does not exist!");
        } catch (Exception ex) {
            System.out.println("Bad date format, please use correct fromat: dd.mm or d.m \n"
                    + "for example 1 March is 1.3 or 01.03.");
        }
    }
}

