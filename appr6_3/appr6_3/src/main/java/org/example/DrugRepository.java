package org.example;

import java.util.*;
import java.util.regex.Pattern;

public class DrugRepository
{
    private Set<Drug> drugs = new HashSet<>();
    Map<Drug, Integer> anyDrugWithCount = new HashMap<>();

    public static DrugRepository getInstance()
    {
        return new DrugRepository();
    }

    public Set <Drug> getDrugs()
    {
        return drugs;
    }

    public void addDrug(Drug drug)
    {
        this.drugs.add(drug);
        boolean thisDrugNotFound = true;
        for (Map.Entry<Drug, Integer> entry : anyDrugWithCount.entrySet()) {
            if (entry.getKey().getName().equals(drug.getName())) {
                anyDrugWithCount.put(entry.getKey(), entry.getValue() + 1);
                thisDrugNotFound = false;
            }
        }
        if (thisDrugNotFound){
            anyDrugWithCount.put(drug , 1);
        }
    }

    public void removeDrug(Drug drug)
    {
        this.drugs.remove(drug);
        if(anyDrugWithCount.get(drug) > 0){
            this.anyDrugWithCount.computeIfPresent(drug, (key, value) -> value - 1);
        }else{
            this.anyDrugWithCount.remove(drug);
        }
    }

    public Drug findDrugByExactName(String name)
    {
        for (Drug drug : drugs)
            if (drug.getName().equals(name))
                return drug;
        return null;//there is no drug with exact name so return null
    }

    public List<String> search(String query)
    {
        List<String> listOfDrugs = new ArrayList<>();
        for (Drug drug : drugs)
            if (Pattern.matches(query.toLowerCase().replaceAll("%", "."), drug.getName().toLowerCase()))
                listOfDrugs.add(drug.getName());
        return listOfDrugs;
    }
    public boolean findDrugByExactNameStr(String name)
    {
        for (Drug drug : drugs)
            if (drug.getName().equals(name))
                return true;
        return false;//there is no drug with exact name so return null
    }
    public int getSize (){
        return drugs.size();
    }
    public  int getInventoryDr(Drug drug){
        int howMany = 0;
        for (Map.Entry<Drug, Integer> entry : anyDrugWithCount.entrySet()) {
            if (entry.getKey().getName().equals(drug.getName())) {
                howMany =entry.getValue();
            }
        }
        return howMany;
    }

}
