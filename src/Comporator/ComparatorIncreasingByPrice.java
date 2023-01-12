package Comporator;

import Model.FAB;

import java.util.Comparator;

public class ComparatorIncreasingByPrice implements Comparator<FAB> {
    @Override
    public int compare(FAB o1, FAB o2) {
        if (o1.getPrice()>o2.getPrice()){
            return 1;
        }
        else if (o1.getPrice()==o2.getPrice()){
            return 0;
        }
        else return -1;
    }
}