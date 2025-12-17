package com.mycompany.user;

import javax.swing.JOptionPane; 

public class HealthMonitor {

    public static void main(String[] args) {

        // get info
        String name = JOptionPane.showInputDialog(
                null,
                "Enter your name:",
                "Health Monitoring System",
                JOptionPane.QUESTION_MESSAGE
        );

        int age = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter your age:",
                "Health Monitoring System",
                JOptionPane.QUESTION_MESSAGE
        ));

        float weight = Float.parseFloat(JOptionPane.showInputDialog(
                null,
                "Enter your weight (kg, e.g. 50):",
                "Health Monitoring System",
                JOptionPane.QUESTION_MESSAGE
        ));

        int height = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter your height (cm, e.g. 175):",
                "Health Monitoring System",
                JOptionPane.QUESTION_MESSAGE
        ));

        // create object
        User user = new User(name, age, weight, height);

        // get hr
        int heartRate = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter your resting heart rate (bpm):",
                "Health Monitoring System",
                JOptionPane.QUESTION_MESSAGE
        ));

        // get weekly steos
        int[] weeklySteps = new int[7];
        int totalSteps = 0;

        for (int i = 0; i < 7; i++) {
            weeklySteps[i] = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Enter your step count for Day " + (i + 1) + ":",
                    "Weekly Steps",
                    JOptionPane.QUESTION_MESSAGE
            ));
            totalSteps += weeklySteps[i];
        }

        double avgSteps = (double) totalSteps / 7.0;

        //declare func
        bmiCalculator bmiCalc = new bmiCalculator();
        heartRateClassifier hrClassifier = new heartRateClassifier();
        recommendationEngine recEngine = new recommendationEngine();

        // calc bmi
        float bmi = bmiCalc.calculateBmi(user);
        String bmiCat = bmiCalc.bmiCategory(bmi);
        float roundBmi = (float) (Math.round(bmi * 10.0) / 10.0);  // round to 1 decimal

        // class hr
        String hrCat = hrClassifier.heartRateCategory(heartRate);

        // output
        System.out.println("=== Health Monitoring System ===");
        System.out.println("\n===== Health Summary =====\n");
        System.out.println("Hello, " + user.getName() + " !");
        System.out.println("Now you are " + user.getAge() + " years old.");
        System.out.println("Your BMI is " + roundBmi + " which is " + bmiCat + " !");
        System.out.println("Heart rate status: " + hrCat);
        System.out.println("\nTotal steps this week: " + totalSteps);
        System.out.printf("Average steps per day: %.1f\n", avgSteps);

        System.out.println("\n===== Health Recommendations =====");
        System.out.println(recEngine.bmiRecommendation(bmiCat));
        System.out.println(recEngine.heartRateRecommendation(hrCat));
        System.out.println(recEngine.activityRecommendation(avgSteps));
        System.out.println("- Keep exercising regularly");
        System.out.println("\nThank you for using the Health Monitoring System.");

        // sum
        String popupMessage =
                "Health Summary for " + user.getName() + "\n\n" +
                "Age: " + user.getAge() + " years\n" +
                "Weight: " + user.getWeight() + " kg\n" +
                "Height: " + user.getHeight() + " cm\n\n" +
                "BMI: " + roundBmi + " (" + bmiCat + ")\n" +
                "Heart rate: " + heartRate + " bpm (" + hrCat + ")\n" +
                "Total weekly steps: " + totalSteps + "\n" +
                String.format("Average daily steps: %.1f\n\n", avgSteps) +
                "Recommendations:\n" +
                recEngine.bmiRecommendation(bmiCat) + "\n" +
                recEngine.heartRateRecommendation(hrCat) + "\n" +
                recEngine.activityRecommendation(avgSteps) + "\n" +
                "- Keep exercising regularly\n";

        JOptionPane.showMessageDialog(
                null,
                popupMessage,
                "Health Monitoring Result",
                JOptionPane.INFORMATION_MESSAGE
        );
       
        JOptionPane.showMessageDialog(
                null,
                "Thank you for using this Health Monitoring System.\nStay healthy and have a great day!",
                "Goodbye",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}

// function to calc BMI
class bmiCalculator {
    // Calculate BMI based on User object (weight in kg, height in cm)
    public float calculateBmi(User users) {
        float heightMeter = (float) (users.getHeight() / 100.0); // convert cm to m
        return users.getWeight() / (heightMeter * heightMeter);
    }

    // Return BMI category string
    public String bmiCategory(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal Weight";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}

// function for the heart rate classification
class heartRateClassifier {
    // Classify resting heart rate based on simple ranges
    public String heartRateCategory(int heartRate) {
        if (heartRate < 60) {
            return "Bradycardia, (below normal)";
        } else if (heartRate <= 100) {
            return "Normal";
        } else {
            return "Tachycardia (above normal)";
        }
    }
}

// function to generate health recommendations
class recommendationEngine {

    // Recommendation based on BMI category
    public String bmiRecommendation(String bmiCat) {
        switch (bmiCat) {
            case "Underweight":
                return "- Your BMI is low. Try to increase calorie intake and do strength training.";
            case "Normal Weight":
                return "- Your BMI is normal. Maintain your current lifestyle.";
            case "Overweight":
                return "- Your BMI is high. Add more physical activity and watch your diet.";
            default: // Obese
                return "- Your BMI is in the obese range. Please seek professional medical advice.";
        }
    }

    // Recommendation based on heart rate classification
    public String heartRateRecommendation(String hrCat) {
        if (hrCat.startsWith("Bradycardia")) {
            return "- Your heart rate is below normal. If you are not athletic, consider seeing a doctor.";
        } else if (hrCat.equals("Normal")) {
            return "- Your heart rate is normal. Keep exercising regularly.";
        } else {
            return "- Your heart rate is above normal. Manage stress and consult a healthcare provider.";
        }
    }

    // Recommendation based on average daily steps
    public String activityRecommendation(double avgSteps) {
        if (avgSteps < 4000) {
            return "- Your activity is low. Aim for at least 4,000–6,000 steps per day.";
        } else if (avgSteps < 8000) {
            return "- You are moderately active. Try to reach 8,000–10,000 steps per day.";
        } else {
            return "- Great job! You are highly active. Keep it up safely.";
        }
    }
}
