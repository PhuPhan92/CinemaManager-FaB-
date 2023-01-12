package Comporator;

import Model.FAB;

import java.util.Comparator;

public class ComparatorIncreasingByName implements Comparator<FAB> {
    @Override
    public int compare(FAB o1, FAB o2) {
        if ((o1.getName()).compareTo(o2.getName())>=1){
            return -1;
        }
        else if ((o1.getName()).compareTo(o2.getName())==0){
            return 0;
        }
        else return 1;
    }
}
