package Service;

import Model.User;
import Repository.IModel;

import java.util.ArrayList;
import java.util.List;

public abstract class FileContext <T> {
    protected Class<T> tClass;
    protected String filePath;
    private FileService fileService;
    public FileContext() {
        fileService = new FileService();
    }

    public  T findById(long id){
        List<T> list = getAll();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public T findByName(String name){
        List<T> list = getAll();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getName().toUpperCase().equals(name.toUpperCase())) {
                return item;
            }
        }
        return null;
    }
    public void add(T obj){
        List<T> list = getAll();
        list.add(obj);
        fileService.writeDate(list, filePath);
    }
    public void updateById(long id, T obj){
        List<T> list = getAll();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getId() == id) {
                iModel.update(obj);
            }
        }
        fileService.writeDate(list, filePath);
    }
    public List<T> getAll(){
        return fileService.readData(filePath, tClass);
    }
//    public List<T> saveAll(list){
//        return fileService.writeDate((T) list, tClass);
//    }
    public List<T> getUser(){
        List<T> list = getAll();
        List <T> listUser = new ArrayList<>();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getEPosition().getValue().equals("USER") ) {
                listUser.add((T) iModel);
            }
        }
        return listUser;
    }
    public List<T> getStaff(){
        List<T> list = getAll();
        List <T> listUser = new ArrayList<>();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getEPosition().getValue().equals("STAFF") ) {
                listUser.add((T) iModel);
            }
        }
        return listUser;
    }

    public  void deleteById(long id){
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getId() == id) {
                list.remove(i);
                break;
            }
        }
        fileService.writeDate(list, filePath);
    }
}
