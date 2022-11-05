package com.example.project.simulation;

public class AverageCalculator {
    public static double calculate(ConnectionMatrix connectionMatrix, RelationMatrix relationMatrix){
        double sum = 0;
        double L = 0;
        for (int i=0;i< connectionMatrix.getNumberOfActors();i++){
            for (int j=0;j<i;j++){
                sum+= connectionMatrix.get(i,j)* relationMatrix.get(i,j);
                L+= connectionMatrix.get(i,j);
            }
        }
        return sum/L;
    }
}
