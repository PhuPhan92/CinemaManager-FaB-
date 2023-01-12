package Service;

import Comporator.ComparatorDecreasingByPrice;
import Comporator.ComparatorIncreasingByPrice;
import Model.FAB;
import Model.User;
import Repository.FABRepository;
import Repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FABService {
    private FABRepository fabRepository;
    public FABService(){fabRepository = new FABRepository();}
    public List<FAB> getAllFAB(){return fabRepository.getAll();}
    public FAB findFABById (long idFab){
        return fabRepository.findById(idFab);
    }
    public FAB findFABByName(String name){return fabRepository.findByName(name);}
    public void updateFABById (long id, FAB fab){
        fabRepository.updateById(id, fab);
    }
    public void addFAB (FAB fab){
        fabRepository.add(fab);
    }
    public void deleteFABById (long id){
        fabRepository.deleteById(id);
    }
    public boolean existById(Long id) {
        if (findFABById(id) != null) {
            return true;
        } else {
            return false;
        }
    }

}
