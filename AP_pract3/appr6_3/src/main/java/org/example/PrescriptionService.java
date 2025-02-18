package org.example;

import java.util.*;

public class PrescriptionService
{

    private LocationService locationService;
    private Set<Pharmacy> pharmacies = new HashSet<>();
    private DrugRepository drugRepository = DrugRepository.getInstance();

    public PrescriptionService(LocationService locationService, Set<Pharmacy> pharmacies)
    {
        this.locationService = locationService;
        this.pharmacies = pharmacies;
    }

    public Pharmacy primaryRegistration(Prescription prescription) {
        int best = 1000000000;
        int dis = 0;
        int i = 0 ;
        Pharmacy nearPharmacy = null;
        if (prescription.getDate()> 30){
            System.out.println("Prescription is expired.");
            return null;
        }
        for (Pharmacy ph : pharmacies) {
            for (PrescriptionItem item  : prescription.getItems()){
                DrugRepository phdrugripo = ph.getDrugRepository();
                if (phdrugripo.findDrugByExactNameStr(item.getName())){
                    i++;
                }

            }
            if (i == prescription.getItems().size()){
                dis = locationService.distance(ph.getLocation() ,prescription.getLocation());
                if (best > dis){
                    best = dis;
                    nearPharmacy = ph;
                }
            }
        }
        if (nearPharmacy == null){
            System.out.println("No Pharmacy Found. Try Later...");
            return null;
        }else {
            return nearPharmacy;
        }
    }

    public double finalRegisteration(Prescription prescription, Pharmacy pharmacy)
    {
        boolean findDrugInPharmacy = false ;
        double priceOfAllPrescription = 0;
        for (PrescriptionItem item  : prescription.getItems()){
            for (Drug ph : pharmacy.getDrugRepository().anyDrugWithCount.keySet()) {
                if (ph.getName().equals(item.getName())) {
                    pharmacy.getDrugRepository().removeDrug(item);
                    findDrugInPharmacy = true;
                    priceOfAllPrescription += ph.getBasePrice();
                }
            }
            if (findDrugInPharmacy){
                System.out.println(" this item ( drugs name : " + item.getName() + " ) not found in this pharmacy :( ");
            }
            findDrugInPharmacy = false;
        }
        return priceOfAllPrescription;

    }

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setDrugRepository(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public DrugRepository getDrugRepository() {
        return drugRepository;
    }
}
