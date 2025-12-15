package com.mycompany.user;

import java.util.Scanner;

public class HealthMonitor {
    
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        
        System.out.println("=== Health Monitoring System ===");
        //to get the user's info
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your age: ");
        int age = input.nextInt();
        System.out.print("Enter your weight(kg, eg: 50): ");
        float weight = input.nextFloat();
        System.out.print("Enter your height(cm, eg: 175): ");
        int height = input.nextInt();
        
        User user = new User(name, age, weight, height);
        
        //2. get heart rate
        System.out.print("Enter your resting heart rate (bpm): ");
        int heartRate = input.nextInt();
        
        //declare function
        bmiCalculator bmiCalc = new bmiCalculator();
        heartRateClassifier hrClassifier = new heartRateClassifier();
        
        //call bmi function
        float bmi = bmiCalc.calculateBmi(user);
        String bmiCat = bmiCalc.bmiCategory(bmi);
        float roundBmi = (float) (Math.round(bmi * 10.0)/10.0);
        
        //call heart rate function
        String hrCat = hrClassifier.heartRateCategory(heartRate);
        
        //display
        System.out.println("\n===== Health Summary =====\n");
        System.out.println("Hello, "+ user.getName()+" !");
        System.out.println("Now you are "+ user.getAge()+" years old.");
        System.out.println("Your BMI is "+ roundBmi+" which is "+bmiCat+" !");
        System.out.println("Heart rate status: "+ hrCat);
    }
}

//function to calc bmi
class bmiCalculator{
    public float calculateBmi(User users){
        float heightMeter = (float) (users.getHeight() / 100.0);
        return users.getWeight() / (heightMeter * heightMeter);
    }
       
    public String bmiCategory(float bmi){
        if (bmi < 18.5){
            return "Underweight";
        } else if(bmi < 25.0){
            return "Normal Weight";
        } else if(bmi <30.0){
            return "Overweight";
        } else{
            return "Obese";
        }
    }
}

//func for the hr classification
class heartRateClassifier{
    public String heartRateCategory(int heartRate){
        if (heartRate < 60){
            return "Bradycardia, (below normal)";
        }else if(heartRate <= 100){
            return "Normal";
        }else{
            return "Tachycardia (above normal)";
        }
    }
}

