package BookStoreManagerment.view;


import BookStoreManagerment.ComparatorByName;
import BookStoreManagerment.model.Book;
import BookStoreManagerment.service.BookService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static StringFormat.centerString.centerString;
import static StringFormat.checkNumber.checkNumberIntoLongAndPosity;

public class BookStoreView {
    private Scanner scanner = new Scanner(System.in);
    private BookService bookService;
    public BookStoreView(){
        bookService = new BookService();
    }

    public void laucher(){
        boolean checkActionMenuBook = false;
        do{
            System.out.println("-------------------------");
            System.out.println("Menu quản lý sách       |");
            System.out.println("-------------------------");
            System.out.println("[1]Xem danh sách        |");
            System.out.println("[2]Thêm sách            |");
            System.out.println("[3]Sửa thông tin sách   |");
            System.out.println("[4]Xoá sách             |");
            System.out.println("[5]Sắp xếp sách         |");
            System.out.println("[6]Tìm kiếm sách        |");
            System.out.println("-------------------------");
            int actionMenuBook = Integer.parseInt(scanner.nextLine());
            switch (actionMenuBook) {
                case 1:
                    showBooksView();
                    break;
                case 2:
                    addBookView();
                    break;
                case 3:
                    editBookView();
                    break;
                case 4:
                    deleteBookView();
                    break;
                case 5:
                    sortBookView();
                    break;
                case 6:
                    searchBookView();
                    break;
            }
            checkActionMenuBook = checkActionContinue("Quản lý sách","Hoàn toàn");
        } while (checkActionMenuBook);
    }

    private void searchBookView() {
        boolean checkActionMenuSearchBook = false;
        do {
            System.out.println("-----------------------------");
            System.out.println("Menu tìm kiếm sách:         |");
            System.out.println("-----------------------------");
            System.out.println("[1]Tìm kiếm theo Id         |");
            System.out.println("[2]Tìm kiếm theo Name       |");
            System.out.println("[3]Tìm kiếm theo Author     |");
            System.out.println("[4]Tìm kiếm theo Amount     |");
            System.out.println("-----------------------------");
            int actionMenuEditBook = Integer.parseInt(scanner.nextLine());
            switch (actionMenuEditBook){
                case 1:
//                    searchBookViewById();
                    break;
                case 2:
                    searchBookViewByName();
                    break;
                case 3:

                    break;
                case 4:
                    searchBookViewByAmount();
                    break;

            }
            checkActionMenuSearchBook = checkActionContinue("Tìm kiếm sách", "Đến Menu quản lý sách");
        } while (checkActionMenuSearchBook == true);
    }

    public void checkIfCanFind(List<Book> book){
        if (book.size() > 0){
            System.out.println("Danh sách books tìm kiếm được");
            showBooksView(book);
        } else {
            System.out.println("--Không tìm thấy sách theo yêu cầu--");
        }
    }

    public void searchBookViewByAmount(){
        System.out.println("Nhập AmountBook bạn muốn tìm kiếm");
        long amountBookSearch = Long.parseLong(scanner.nextLine());
        List<Book> resultsSearchBooksByAmount = bookService.searchBooksByAmount(amountBookSearch);
        checkIfCanFind(resultsSearchBooksByAmount);
    }

    private void searchBookViewByName() {
        System.out.println("Nhập NameBook bạn muốn tìm kiếm");
        String nameSearch = scanner.nextLine();
        List<Book> resultsSearchBooksByName = bookService.searchBooksByName(nameSearch);
        checkIfCanFind(resultsSearchBooksByName);
    }

    private void searchBookViewByName(String nameSearch) {
        List<Book> resultsSearchBooksByName = bookService.searchBooksByName(nameSearch);
        checkIfCanFind(resultsSearchBooksByName);
    }

    private void sortBookView() {
        boolean checkActionMenuSortBook = false;
        do {
            System.out.println("-----------------------------");
            System.out.println("Menu sắp xếp sách:          |");
            System.out.println("-----------------------------");
            System.out.println("[1]Sắp xếp theo Id          |");
            System.out.println("[2]Sắp xếp theo tên         |");
            System.out.println("[3]Sắp xếp theo số lượng    |");
            System.out.println("-----------------------------");
            int actionMenuEditBook = Integer.parseInt(scanner.nextLine());
            switch (actionMenuEditBook){
                case 1:
//                    sortBookViewById();
                    break;
                case 2:
                    sortBookViewByName();
                    break;
                case 3:

                    break;

            }
            checkActionMenuSortBook = checkActionContinue("Sắp xếp sách", "Đến Menu quản lý sách");
        } while (checkActionMenuSortBook == true);
    }

    private void sortBookViewByName() {
        List<Book> books = bookService.getAllBooks();
        Comparator<Book> comparator = new ComparatorByName();
        books.sort(comparator);
        showBooksView(books);
    }

    public void editBookView(){
        boolean checkActionMenuEditBook = false;
        do {
            System.out.println("-----------------------------");
            System.out.println("Menu sửa thông tin sách:    |");
            System.out.println("-----------------------------");
            System.out.println("[1]Sửa theo Id              |");
            System.out.println("[2]Sửa theo tên             |");
            System.out.println("[3]Sửa theo số lượng        |");
            System.out.println("-----------------------------");
            int actionMenuEditBook = Integer.parseInt(scanner.nextLine());
            switch (actionMenuEditBook){
                case 1:
                    editBookViewById();
                    break;
                case 2:
                    editBookViewByName();
                    break;
                case 3:
                    editBookViewByAmount();
                    break;

            }
            checkActionMenuEditBook = checkActionContinue("Sửa thông tin sách", "Đến Menu quản lý sách");
        } while (checkActionMenuEditBook == true);
    }

    public void editBookViewByAmount(){
        System.out.println("Nhập Amount Book bạn muốn sửa");
        long amountBookEdit = Long.parseLong(scanner.nextLine());

    }
    public void editBookViewByName(){
        System.out.println("Nhập Name Book bạn muốn sửa");
        String nameBookEdit = scanner.nextLine();
        searchBookViewByName(nameBookEdit);
        editBookViewById();
    }
    public void editBookViewById(){
        System.out.println("Nhập Id bạn muốn sửa");
        long idBook = Long.parseLong(scanner.nextLine());
        Book book = bookService.findBookById(idBook);
        if (book != null) {
            System.out.println("Book tìm được theo Id bạn vừa nhập");
            showBooks(book);

            boolean checkActionBookViewById = false;
            do {
                System.out.println("-----------------------------");
                System.out.println("Menu thông tin bạn muốn sửa |");
                System.out.println("-----------------------------");
                System.out.println("[1]Name                     |");
                System.out.println("[2]Author                   |");
                System.out.println("[3]Price                    |");
                System.out.println("[4]Avaible                  |");
                System.out.println("[5]Amount                   |");
                System.out.println("[6]DateAdd                  |");
                System.out.println("[7]Sửa tất cả thông tin     |");
                System.out.println("-----------------------------");
                int actionBookViewById = Integer.parseInt(scanner.nextLine());
                switch (actionBookViewById ){
                    case 1:
                        System.out.println("Nhập Name mới cho sách");
                        String newNameBook = scanner.nextLine();
                        book.setName(newNameBook);
                        break;
                    case 5:
                        System.out.println("Nhập Amount mới cho sách");
                        long newAmountBook = Long.parseLong(scanner.nextLine());
//                        updateAmountAndAvaiable(book, newAmountBook);
//                        checkNumberIntoLongAndPosity(newAmountBook);
                        book.setAvaiable(getNewAvaiableEdit(book, newAmountBook));
                        book.setAmount(getNewAmountEdit(newAmountBook));
                }
                bookService.updateBooktById(idBook, book);
                showBooksView(bookService.getAllBooks());
                checkActionBookViewById = checkActionContinue("Sửa book theo Id?" , "Đến Nhập Id bạn muốn sửa");
            } while (checkActionBookViewById == true);
        }else{
            System.out.println("--ID không tồn tại--");
        }
    }

    private long getNewAmountEdit(long newAmount){
        long setAmount = newAmount;
        if (newAmount > 100) {
            setAmount = 100;
        }
        return setAmount;
    }

    private long getNewAvaiableEdit(Book book, long newAmount){
        long setAmount = book.getAmount();
        long setAvaiable = book.getAvaiable();
        if (newAmount >= setAmount){
            setAvaiable = book.getAvaiable() + (newAmount - book.getAmount());
        } else if (newAmount > setAvaiable){
            setAvaiable = book.getAvaiable();
        } else {
            setAvaiable = newAmount;
        }
        return setAvaiable;
    }

//    private void updateAmountAndAvaiable(Book book, long newAmount){
//        long setAmount = book.getAmount();
//        long setAvaiable = book.getAvaiable();
//        if (newAmount >= setAmount){
//            setAmount = newAmount;
//            setAvaiable = book.getAvaiable() + (newAmount - book.getAmount());
//        } else {
//            setAmount = setAvaiable;
//        }
//        book.setAmount(setAmount);
//        book.setAvaiable(setAvaiable);
//    }

    private void deleteBookView() {
        System.out.println("Nhập ID bạn muốn xoá");
        long idBook = Long.parseLong(scanner.nextLine());
        bookService.deleteBookById(idBook);
        showBooksView(bookService.getAllBooks());
    }

    public boolean checkActionContinue(String string,String string2){
        boolean checkActionContinue = false;
        do {
            System.out.println("Bạn có muốn tiếp tục " + string + "?");
            System.out.println("[y] Đồng ý");
            System.out.println("[n] Thoát (" + string2 + ")");
            String actionContinue = scanner.nextLine();
            switch (actionContinue){
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    checkActionContinue = true;
            }
        } while (checkActionContinue = true);
        return false;
    }

    public void addBookView(){
        //kiểm tra trùng(tên, tác giả): ++số lượng, ++ avaiable.
        boolean checkActionContinueAddBook = false;
        do {
            long id = getMaxId() + 1;
            System.out.println("-----------------------------");
            System.out.println("Menu thêm sách              |");
            System.out.println("-----------------------------");
            System.out.println("Nhập tên:                   |");
            String name = scanner.nextLine();

            System.out.println("Nhập tác giả:               |");
            String author = scanner.nextLine();
            System.out.println("Nhập giá:                   |");
            long price = Long.parseLong(scanner.nextLine());
            System.out.println("Nhập số lượng:              |");
            long amount = Long.parseLong(scanner.nextLine());
            System.out.println("-----------------------------");
            long avaiable = amount;
            Date dateAdd = new Date();

            Book book = new Book(id, name, author, price, avaiable, amount, dateAdd);
            long newAmount = getNewAmountEdit(amount);
            book.setAvaiable(newAmount);
            book.setAmount(newAmount);


            System.out.println("Kiểm tra lại thông tin sách vừa nhập");
            showBooks(book);
            System.out.println("Bạn có chắc là muốn lưu? y/n");
            String choiceSaveAddBookInfomation = scanner.nextLine().trim().toLowerCase();
            switch (choiceSaveAddBookInfomation) {
                case "y":
                    bookService.addBook(book);
                    System.out.println("---------Done---------");
                    checkActionContinueAddBook = false;
                    showBooksView(bookService.getAllBooks());
                    break;
                case "n":
                    System.out.println("[1]Quay lại Menu thêm sách");
                    System.out.println("[2]Về Menu quản lý sách");
                    int choiceEdit = Integer.parseInt(scanner.nextLine());
                    if (choiceEdit == 1) {
                        checkActionContinueAddBook = true;
                    } else if (choiceEdit == 2) {
                        checkActionContinueAddBook = false;
                    } else {
                        checkActionContinueAddBook = true;
                    }
                default:
                    break;
            }

        } while(checkActionContinueAddBook == true);

    }

    public long getMaxId(){
        long maxId = 0;
        for(int i = 0; i < bookService.getAllBooks().size(); i++){
            if (bookService.getAllBooks().get(i).getId() > maxId){
                maxId = bookService.getAllBooks().get(i).getId();
            }
        }
        return maxId;
    }

    public boolean cancel(String string){
        boolean checkCancel = false;
        if(string.equals(0)){
            checkCancel = true;
        } else {
            checkCancel = false;
        }
        return checkCancel;
    }

    public static void main(String[] args) {
        BookStoreView bookStore = new BookStoreView();
        bookStore.laucher();
    }

    public void showBooks(Book book){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s|%-50s|%-20s|%-12s|%-10s|%-10s|%-16s|\n",
                centerString(5, "#"),
                centerString(51, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount"),
                centerString(16, " Date Add"));
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(book.toViewer());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void showBooksView(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s|%-50s|%-20s|%-12s|%-10s|%-10s|%-16s|\n",
                centerString(5, "#"),
                centerString(51, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount" + ""),
                centerString(16, " Date Add")
        );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Book book : bookService.getAllBooks()){
            System.out.println(book.toViewer());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void showBooksView(List<Book> books){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s|%-50s|%-20s|%-12s|%-10s|%-10s|%-16s|\n",
                centerString(5, "#"),
                centerString(51, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount"),
                centerString(16, " Date Add")
        );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Book book : books){
            System.out.println(book.toViewer());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

}
