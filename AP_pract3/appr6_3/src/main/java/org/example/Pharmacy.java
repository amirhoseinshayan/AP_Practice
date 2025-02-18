package org.example;


import java.security.PublicKey;

public class Pharmacy
{

    private final DrugRepository drugRepository;
    private String name;
    private Location location;

    public Pharmacy(DrugRepository drugRepositoryn, String name, Location location )
    {
        this.drugRepository = drugRepositoryn;
        this.name = name;
        this.location = location;
    }
    public int getnIventory(Drug drug){
        return drugRepository.getInventoryDr(drug);
    }

    public String getName()
    {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getDrugCount()
    {
        return drugRepository.getSize();
    }

    public void addDrug(Drug drug, int inventory)
    {
        for (int i = 0 ; i < inventory ; i++){
            drugRepository.addDrug(drug);
        }
    }
    public void removeDrug(Drug drug){
        drugRepository.removeDrug(drug);

    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrugRepository getDrugRepository() {
        return drugRepository;
    }
}

