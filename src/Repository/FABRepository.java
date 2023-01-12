package Repository;

import Model.FAB;
import Model.User;
import Service.FileContext;

public class FABRepository extends FileContext<FAB> {
    public FABRepository(){
        filePath = "C:\\Phu Phan\\CinemaManager-FaB-\\data\\FAB.txt";
        tClass = FAB.class;
    }

}
