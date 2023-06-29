package repository.file;

import repository.IModel;
import repository.ISearch;
import repository.ISearchByKeyWord;

import java.util.ArrayList;
import java.util.List;

import static zStringFormat.formatStringVietNam.toKhongDau;

public abstract class FileContext<T> {
    protected Class<T> tClass;
    protected String filePath;
    private FileService fileService;

    public FileContext(FileService fileService) {
        this.fileService = fileService;
    }

    public FileContext() {
        fileService = new FileService();
    }

    public List<T> getAll(){
        return fileService.readData(filePath, tClass);
    }
    public List<T> searchByKeyWord(String keyword, ISearchByKeyWord<T> iSearchByKeyWord) {
        List<T> list = getAll();
        List<T> searchResult = new ArrayList<>() ;
            for (int i = 0; i < list.size(); i++) {
                if(iSearchByKeyWord.searchByKeyWord(keyword, list.get(i))){
                    searchResult.add(list.get(i));
                }
        }
            return searchResult;
    }

    public List<T> searchByAmount(long amount) {
        List<T> list = getAll();
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
        List<T> list = getAll();
        List<T> searchNameResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> iSearch = (ISearch<T>) list.get(i);
            if (toKhongDau(iSearch.getName().trim()).toUpperCase().contains(toKhongDau(name.trim()).toUpperCase())){
                searchNameResult.add(list.get(i));
            }

        }
        return searchNameResult;
    }
    public List<T> searchByAuthor(String author) {
        List<T> list = getAll();
        List<T> searchAuthorResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> iSearch = (ISearch<T>) list.get(i);
            if (toKhongDau(iSearch.getAuthor().trim()).toUpperCase().contains(toKhongDau(author.trim()).toUpperCase())){
                searchAuthorResult.add(list.get(i));
            }
        }
        return searchAuthorResult;
    }

    public T findById(long id){
        List<T> list = getAll();
        for (T item : list) {
            IModel<T> iModel = (IModel<T>) item;
            if (iModel.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public void deleteById(long id){
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
}
