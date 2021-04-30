package ru.kpfu.itis.daniyar.idrisov.datamining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CalculateEstimatingMoments {

    private Integer amountOfNumbers;

    private Integer rangeOfNumbers;

    private Integer numberOfVariables;

    private Integer[] arrayNumbers;

    private Map<Integer, Integer> mapNumbers;
    
    private Map<Integer, Integer> mapVariables;

    public CalculateEstimatingMoments(Integer amountOfNumbers, Integer rangeOfNumbers, Integer numberOfVariables) {
        this.amountOfNumbers = amountOfNumbers;
        this.rangeOfNumbers = rangeOfNumbers;
        this.numberOfVariables = numberOfVariables;
        this.arrayNumbers = new Integer[amountOfNumbers];
        this.mapNumbers = new HashMap<>();
        this.mapVariables = new HashMap<>();
        this.generateValues();
        this.distributionToMapNumbers();
        this.generateVariablesValuesAndDistributionToMapVariables();
    }

    private void generateValues() {
        for (int i = 0; i < this.amountOfNumbers; i++) {
            this.arrayNumbers[i] = (int) (Math.random() * (this.rangeOfNumbers + 1));
        }
    }

    private void distributionToMapNumbers() {
        for (int i = 0; i < this.amountOfNumbers; i++) {
            if (this.mapNumbers.containsKey(this.arrayNumbers[i])) {
                int value = this.mapNumbers.get(this.arrayNumbers[i]) + 1;
                this.mapNumbers.put(this.arrayNumbers[i], value);
            }
            else {
                this.mapNumbers.put(this.arrayNumbers[i], 1);
            }
        }
    }

    private void generateVariablesValuesAndDistributionToMapVariables() {
        for (int i = 0; i < numberOfVariables; i++) {
            int randomI = (int) (Math.random() * this.amountOfNumbers);
            while (this.mapVariables.containsKey(this.arrayNumbers[randomI])) {
                randomI = (int) (Math.random() * this.amountOfNumbers);
            }
            this.mapVariables.put(this.arrayNumbers[randomI], 1);
            if (randomI < (amountOfNumbers - 1)) {
                for (int j = randomI + 1; j < this.amountOfNumbers; j++) {
                    if (this.mapVariables.containsKey(this.arrayNumbers[j])) {
                        int value = this.mapVariables.get(this.arrayNumbers[j]) + 1;
                        this.mapVariables.put(this.arrayNumbers[j], value);
                    }
                }  
            }
        }
    }

    public Integer getZeroMoment() {
        Iterator<Integer> iterator = this.mapNumbers.values().iterator();
        int zeroMoment = 0;
        while (iterator.hasNext()) {
            zeroMoment = (int) (zeroMoment + Math.pow(iterator.next(), 0));
        }
        return zeroMoment;
    }

    public Integer getFirstMoment() {
        Iterator<Integer> iterator = this.mapNumbers.values().iterator();
        int firstMoment = 0;
        while (iterator.hasNext()) {
            firstMoment = firstMoment + iterator.next();
        }
        return firstMoment;
    }

    public Long getSecondMoment() {
        Iterator<Integer> iterator = this.mapVariables.values().iterator();
        Long secondMoment = 0L;
        while (iterator.hasNext()) {
            int pow = (int) Math.pow(iterator.next(), 2);
            secondMoment = secondMoment + pow;
        }
        return secondMoment;
    }

    public static void main(String[] args) {
        CalculateEstimatingMoments calculateEstimatingMoments = new CalculateEstimatingMoments(1000000, 1000, 100);
        System.out.println("Zero moment: " + calculateEstimatingMoments.getZeroMoment());
        System.out.println("First moment: " + calculateEstimatingMoments.getFirstMoment());
        System.out.println("Second moment 100 variables :" + calculateEstimatingMoments.getSecondMoment());
        System.out.println("---------------------------------------------------------------------------------");
        CalculateEstimatingMoments calculateEstimatingMoments2 = new CalculateEstimatingMoments(1000000, 1000, 500);
        System.out.println("Second moment 500 variables :" + calculateEstimatingMoments2.getSecondMoment());
    }

}
