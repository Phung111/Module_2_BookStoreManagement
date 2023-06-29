package repository.inmemory;

import repository.IModel;
import repository.ISearch;

import java.util.ArrayList;
import java.util.List;

import static zStringFormat.formatStringVietNam.toKhongDau;

public abstract class MemoryContext<T> {
    protected List<T> list;
    public MemoryContext() {
        init();
    }
    public abstract void init(); //????/
    public List<T> getAll(){
        return list;
    }
    public List<T> searchByAmount(long amount) {
        List<T> searchAmountResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> iSearch = (ISearch<T>) list.get(i);
            if (iSearch.getAmount() == amount ){
                searchAmountResult.add(list.get(i));
            }
        }
        return searchAmountResult;
    }

    public List<T> searchByName(String name) {
        List<T> searchNameResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> iSearch = (ISearch<T>) list.get(i);
            if (toKhongDau(iSearch.getName().trim()).toUpperCase().contains(toKhongDau(name.trim()).toUpperCase())){
                searchNameResult.add(list.get(i));
            }
        }
//        if (searchNameResult.size() == 0) {
//            System.out.println("--Not found this name--");
//            return null;
//        }
        return searchNameResult;
    }

    public T findById(long id){
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }
    public void deleteById(long id){
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getId() == id) {
                list.remove(i);
                break;
            }
        }
    }

    public void add(T newObj){

        list.add(newObj);
    }
    public void updateById(long id, T obj){
        for (int i = 0; i < list.size(); i++) {
            IModel<T> iModel = (IModel<T>) list.get(i);
            if (iModel.getId() == id) {
                iModel.update(obj);
            }
        }
    }


}
