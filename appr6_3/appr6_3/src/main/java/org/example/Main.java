package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int price = 5;
        String name1 = "novaphen";
        Drug drug = new InsuredDrug(4, name1 , "InsuredDrug" );
        DrugRepository drugRepository = new DrugRepository();
        drugRepository.addDrug(drug);
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        //Pharmacy pharmacy = new Pharmacy(drugRepository,"sarah shayan " , )
        //pharmacies.add(pharmacy);
        Location location = new Location(10 , 10 );
        //PrescriptionService prescriptionService = new PrescriptionService(location , )
        //Prescription prescription = new Prescription()
        //Pharmacy pharmacy = new Pharmacy(drugRepository , "shayan" , location);
        //Prescription prescription = new Prescription();
        System.out.println(drug.getBasePrice());
    }
}