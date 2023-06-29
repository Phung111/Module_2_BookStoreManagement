package view;

import model.Book;
import model.ERole;
import service.BookService;
import utils.DateUtils;

import java.util.*;

import static zStringFormat.centerString.centerString;
import static zStringFormat.formatLongInput.inputDate;
import static zStringFormat.formatLongInput.inputLong;
import static zStringFormat.isContinue.isContinue;

public class BookView extends GenericView {
    private BookService bookService;
    public BookView(){
        bookService = new BookService();
    }
    @Override
    public void launcher() {
        if (account.getErole() == ERole.ADMIN){
            boolean isContinue = false;
            do {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
                System.out.println("                                                                ┃      Menu Book For Admin      ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("                                                                ┃[1]Xem danh sách               ┃");
                System.out.println("                                                                ┃[2]Thêm sách                   ┃");
                System.out.println("                                                                ┃[3]Sửa thông tin sách          ┃");
                System.out.println("                                                                ┃[4]Xoá sách                    ┃");
                System.out.println("                                                                ┃[5]Sắp xếp sách                ┃");
                System.out.println("                                                                ┃[6]Tìm kiếm sách               ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("--Chọn chức năng--");
                String choiceStr = inputLong();
                if (choiceStr.equals(".")){
                    break;
                }
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        showBookListAndPage();
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
                isContinue = isContinue("tiếp tục Quản lý sách","(quay lại Menu Admin)");
            } while (isContinue);
        } else {
            boolean isContinue = false;
            do {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
                System.out.println("                                                                ┃       Menu Book For User      ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("                                                                ┃[1]Xem danh sách               ┃");
                System.out.println("                                                                ┃[2]Sắp xếp sách                ┃");
                System.out.println("                                                                ┃[3]Tìm kiếm sách               ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("--Chọn chức năng--");
                String choiceStr = inputLong();
                if (choiceStr.equals(".")){
                    break;
                }
                int choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        showBookListAndPage();
                        break;
                    case 2:
                        sortBookView();
                        break;
                    case 3:
                        searchBookView();
                        break;
                }
                isContinue = isContinue("tiếp tục Quản lý sách","(quay lại Menu User)");
            } while (isContinue);
        }
    }

    public void showBookListAndPage() {
        showBookListAndPage1();
        boolean isContinue = true;
        do {
                isContinue = isContinue("Xem page khác", "");
                if(isContinue == false){
                    break;
                }
                System.out.println("--Nhập page bạn muốn xem--");
                String pageStr = inputLong();
                if (pageStr.equals(".")){
                    isContinue = false;
                    return;
                }
                long indexPage = Long.parseLong(pageStr);
                long numberOfPage = 5;
                List<Book> books = bookService.getAllBooks();
                long pageSize = (long) Math.ceil(((double) books.size()/numberOfPage));
                long fromIndex = (indexPage-1)*numberOfPage;
                long toIndex = fromIndex + numberOfPage;
                if (indexPage == pageSize){
                    toIndex = books.size();
                }
                showBookList(books.subList((int) fromIndex, (int) toIndex));
                System.out.println("Số trang " + pageSize +" ------------------------------------------------------------------------------------------------------------------ Page "+ indexPage);

        } while (isContinue);
    }
    public void showBookListAndPage1() {
        int indexPage = 1;
        long numberOfPage = 5;
        List<Book> books = bookService.getAllBooks();
        long pageSize = (long) Math.ceil(((double) books.size()/numberOfPage));
        long fromIndex = (indexPage-1)*numberOfPage;
        long toIndex = fromIndex + numberOfPage;
        if (indexPage == pageSize){
            toIndex = books.size();
        }
        showBookList(books.subList((int) fromIndex, (int) toIndex));
        System.out.println("Số trang " + pageSize +" ------------------------------------------------------------------------------------------------------------------ Page "+ indexPage);
    }

    public void searchBookView() {
        boolean isContinue = false;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃      Menu Tìm kiếm sách       ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Tìm kiếm theo Id            ┃");
            System.out.println("                                                                ┃[2]Tìm kiếm theo Name          ┃");
            System.out.println("                                                                ┃[3]Tìm kiếm theo Author        ┃");
            System.out.println("                                                                ┃[4]Tìm kiếm theo Amount        ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    searchBookViewById();
                    break;
                case 2:
                    searchBookViewByName();
                    break;
                case 3:
                    searchBookViewByAuthor();
                    break;
                case 4:
                    searchBookViewByAmount();
                    break;
            }
            isContinue = isContinue("tiếp tục Menu Tìm kiếm sách", "(quay lại Menu Book)");
        } while (isContinue);
    }

    private void searchBookViewById() {
        System.out.println("--Nhập id bạn muốn tìm kiếm--");
        String idStr = inputLong();
        if (idStr.equals(".")){
            return;
        }
        long idBookSearch = Long.parseLong(idStr);
        Book book = bookService.findBookById(idBookSearch);
        List<Book> listBook = new ArrayList<>();
        listBook.add(book);
        if (book != null){
            System.out.println();
            System.out.println("--Danh sách books tìm kiếm được--");
            showBookList(listBook);
        } else {
            System.out.println("--Không tìm thấy book--");
        }
    }

    public boolean checkIfCanFind(List<Book> book){
        boolean checkIfCanFind = false;
        if (book.size() > 0){
            System.out.println("--Danh sách books tìm kiếm được--");
            showBookList(book);
            checkIfCanFind = true;
        } else {
            System.out.println("--Không tìm thấy book--");
        }
        return checkIfCanFind;
    }

    private boolean searchBookViewByAuthor() {
        boolean checkIfCanFind = false;
        System.out.println("--Nhập Author bạn muốn tìm kiếm--");
        String authorSearch = scanner.nextLine();
        List<Book> resultSearchBooksByAuthor = bookService.searchBooksByAuthor(authorSearch);
        checkIfCanFind = checkIfCanFind(resultSearchBooksByAuthor);
        return checkIfCanFind;
    }

    public boolean searchBookViewByAmount(){
        boolean checkIfCanFind = false;
        System.out.println("--Nhập AmountBook bạn muốn tìm kiếm--");
        long amountBookSearch = Long.parseLong(scanner.nextLine());
        List<Book> resultsSearchBooksByAmount = bookService.searchBooksByAmount(amountBookSearch);
        checkIfCanFind = checkIfCanFind(resultsSearchBooksByAmount);
        return checkIfCanFind;
    }

    private boolean searchBookViewByName() {
        boolean checkIfCanFind = false;
        System.out.println("--Nhập NameBook bạn muốn tìm kiếm--");
        String nameSearch = scanner.nextLine();
        List<Book> resultsSearchBooksByName = bookService.searchBooksByName(nameSearch);
        checkIfCanFind = checkIfCanFind(resultsSearchBooksByName);
        return checkIfCanFind;
    }

    private boolean searchBookViewByName(String nameSearch) {
        boolean checkIfCanFind = false;
        List<Book> resultsSearchBooksByName = bookService.searchBooksByName(nameSearch);
        checkIfCanFind = checkIfCanFind(resultsSearchBooksByName);
        return checkIfCanFind;
    }

    public void sortBookView() {
        boolean isContinue = false;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃       Menu sắp xếp sách       ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Sắp xếp theo Id             ┃");
            System.out.println("                                                                ┃[2]Sắp xếp theo tên            ┃");
            System.out.println("                                                                ┃[3]Sắp xếp theo số lượng       ┃");
            System.out.println("                                                                ┃[4]Sắp xếp theo giá            ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            List<Book> books = bookService.getAllBooks();
            switch (choice){
                case 1:
                    books = bookService.sortBookById();
                    break;
                case 2:
                    books = bookService.sortBookByName();
                    break;
                case 3:
                    books = bookService.sortBookByAmount();
                    break;
                case 4:
                    books = bookService.sortBookByPrice();
                    break;
                default:
                    System.out.println("--Chọn sai chức năng, nhập lại--");
                    isContinue = true;
                    break;
            }
            if(isContinue == false){
                showBookList(books);
            }
            isContinue = isContinue("tiếp tục Menu Sắp xếp sách", "(quay lại Menu quản lý sách)");
        } while (isContinue);
    }

    public void editBookView(){
        boolean isContinue = false;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃    Menu sửa thông tin sách    ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Sửa theo Id                 ┃");
            System.out.println("                                                                ┃[2]Sửa theo tên                ┃");
            System.out.println("                                                                ┃[3]Sửa theo số lượng           ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
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
            isContinue = isContinue("tiếp tục Menu Sửa thông tin sách", "(quay lại Menu Book)");
        } while (isContinue);
    }

    public void editBookViewByAmount(){
        boolean checkIfCanFind = searchBookViewByAmount();
        if(checkIfCanFind){
            editBookViewById();
        }
    }
    public void editBookViewByName(){
        boolean checkIfCanFind = searchBookViewByName();
        if(checkIfCanFind){
            editBookViewById();
        }
    }
    public void editBookViewById(){
        System.out.println("Nhập Id bạn muốn sửa");
        long idBook = Long.parseLong(scanner.nextLine());
        Book book = bookService.findBookById(idBook);
        if (book != null) {
            System.out.println("Book tìm được theo Id bạn vừa nhập");
            showBook(book);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃  Menu thông tin bạn muốn sửa  ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Name                        ┃");
            System.out.println("                                                                ┃[2]Author                      ┃");
            System.out.println("                                                                ┃[3]Price                       ┃");
            System.out.println("                                                                ┃[4]Avaible                     ┃");
            System.out.println("                                                                ┃[5]Amount                      ┃");
            System.out.println("                                                                ┃[6]DateAdd                     ┃");
            System.out.println("                                                                ┃[7]Sửa tất cả thông tin        ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            boolean isContinue = true;
            do {
                System.out.println("--Chọn chức năng--");
                String choiceStr = inputLong();
                if (choiceStr.equals(".")){
                    break;
                }
                int choice = Integer.parseInt(choiceStr);
                switch (choice){
                    case 1:
                        System.out.println("--Nhập Name mới cho sách--");
                        String newNameBook = scanner.nextLine();
                        if (newNameBook.equals(".")){
                            break;
                        }
                        book.setName(newNameBook);
                        break;
                    case 2:
                        System.out.println("--Nhập Author mới cho sách--");
                        String newAuthorBook = scanner.nextLine();
                        if (newAuthorBook.equals("#")){
                            break;
                        }
                        book.setName(newAuthorBook);
                        break;
                    case 3:
                        System.out.println("--Nhập Price mới cho sách--");
                        String newPriceBookStr = inputLong();
                        if (newPriceBookStr.equals(".")){
                            break;
                        }
                        long newPriceBook = Long.parseLong(newPriceBookStr);
                        book.setPrice(newPriceBook);
                        break;
                    case 4:
                        System.out.println("--Nhập Avaiable mới cho sách--");
                        String newAvaiableBookStr = inputLong();
                        if (newAvaiableBookStr.equals(".")){
                            break;
                        }
                        long newAvaiableBook = Long.parseLong(newAvaiableBookStr);
                        book.setAvaiable(newAvaiableBook);
                        break;
                    case 5:
                        editAmountBookViewById(book);
                        break;
                    case 6:
                        System.out.println("--Nhập DateAdd mới cho sách--");
                        System.out.println("--(ngày-tháng-năm)--");
                        String newDateAddBookStr = inputDate();
                        if (newDateAddBookStr.equals(".")){
                            break;
                        }
                        Date newDateAddBook = DateUtils.parseDate(newDateAddBookStr);
                        book.setDateAdd(newDateAddBook);
                        break;
                    case 7:
                        System.out.println("--Nhập Name mới cho sách--");
                        String name = scanner.nextLine();
                        System.out.println("--Nhập Author mới cho sách--");
                        String author = scanner.nextLine();
                        System.out.println("--Nhập Price mới cho sách--");
                        String priceStr = inputLong();
                        long price = Long.parseLong(priceStr);
                        System.out.println("--Nhập Avaiable mới cho sách--");
                        String avaiableStr = inputLong();
                        long avaiable = Long.parseLong(avaiableStr);
                        System.out.println("Nhập Amount mới cho sách--");
                        String amountStr = inputLong();
                        long amount = Long.parseLong(amountStr);
                        System.out.println("--Nhập DateAdd mới cho sách--");
                        System.out.println("--(ngày-tháng-năm)--");
                        String dateStr = inputDate();
                        Date date = DateUtils.parseDate(dateStr);
                        book.setName(name);
                        book.setAuthor(author);
                        book.setPrice(price);
                        book.setAvaiable(avaiable);
                        book.setAmount(amount);
                        book.setDateAdd(date);
                        break;
                    default:
                        System.out.println("--Nhập sai chức năng--");
                        System.out.println("--Nhập lại--");
                        isContinue = false;
                        break;
                }
            } while (isContinue == false);
            System.out.println("--Kiểm tra lại thông tin muốn thêm--");
            showBook(book);
            System.out.println("--Bạn có chắc muốn cập nhật? [1]Có/[2]Không");
            String str = inputLong();
            if(str.equals(".")){
                return;
            }
            long answer = Long.parseLong(str);
            if(answer == 1){
                bookService.updateBooktById(idBook, book);
                showBookList(bookService.getAllBooks());
                System.out.println("--Done--");
            }
        }else{
            System.out.println("--ID không tồn tại--");
        }
    }

    private void editAmountBookViewById(Book book) {
            System.out.println("--Nhập Amount mới cho sách--");
            String newAmountBookStr = inputLong();
            if (newAmountBookStr.equals(".")){
                return;
            }
            long newAmountBook = Long.parseLong(newAmountBookStr);
            long notAvaiable = book.getAmount()-book.getAvaiable();
            long setAmount = keepAmountEditNotOverStorage(newAmountBook);
            long setAvaiAble = setAmount - notAvaiable;
            book.setAmount(setAmount);
            book.setAvaiable(setAvaiAble);
    }

    private long keepAmountEditNotOverStorage(long newAmount){
        long storage = 100;
        long setAmount = newAmount;
        if (newAmount > storage) {
            setAmount = storage;
        }
        return setAmount;
    }

    public void deleteBookView() {
        showBookList();
        System.out.println("Nhập ID bạn muốn xoá");
        String str = inputLong();
        if (str.equals(".")){
            return;
        }
        long idBook = Long.parseLong(str);
        System.out.println("--Kiểm tra lại book bạn muốn xoá--");
        showBook(bookService.findBookById(idBook));
        System.out.println("--Bạn có chắc muốn xoá? [1]Có/[2]Không");
        String str2 = inputLong();
        if(str2.equals(".")){
            return;
        }
        long answer = Long.parseLong(str2);
        if(answer == 1){
            bookService.deleteBookById(idBook);
            showBookList(bookService.getAllBooks());
        }
    }

    public void addBookView(){
        //kiểm tra trùng(tên, tác giả): ++số lượng, ++ avaiable.
        boolean checkActionContinueAddBook = false;
        do {
            long id = getMaxId() + 1;
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("◤━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("┃      Menu Thêm Sách       ┃");
            System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("┃Nhập tên:                  ┃");
            String nameBookadd = scanner.nextLine();
            if (nameBookadd.equals(".")){
                break;
            }
            boolean checkIfCanFindBookByName = searchBookViewByName(nameBookadd);
            if (checkIfCanFindBookByName == true){
                System.out.println("--Sách bạn thêm đã tồn tại--");
                System.out.println("--Chuyển đến edit amount book--");
                System.out.println("--Vui lòng nhập Id--");
                String idBookAddStr = inputLong();
                if (idBookAddStr.equals(".")){
                    break;
                }
                long idBook = Long.parseLong(idBookAddStr);
                Book book = bookService.findBookById(idBook);
                if (book != null){
                    editAmountBookViewById(book);
                } else {
                    System.out.println("--ID không tồn tại--");
                }
            } else {
                System.out.println("┃Nhập tác giả:              ┃");
                String authorBookadd = scanner.nextLine();
                if (authorBookadd.equals(".")){
                    break;
                }
                System.out.println("┃Nhập giá:                  ┃");
                String priceBookaddStr = inputLong();
                if (priceBookaddStr.equals(".")){
                    break;
                }
                long priceBookadd = Long.parseLong(priceBookaddStr);
                System.out.println("┃Nhập số lượng:             ┃");
                String amountBookAddStr = inputLong();
                if (amountBookAddStr.equals(".")){
                    break;
                }
                long amountBookadd = Long.parseLong(amountBookAddStr);
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                long checkAmountStorage = keepAmountEditNotOverStorage(amountBookadd);
                long avaiableBookadd = checkAmountStorage;
                Date dateBookAdd = new Date();

                Book book = new Book(id, nameBookadd, authorBookadd, priceBookadd, avaiableBookadd, checkAmountStorage, dateBookAdd);
                System.out.println("Kiểm tra lại thông tin sách vừa nhập");
                showBook(book);
                System.out.println("Bạn có chắc là muốn thêm? [1]Có/[2]Không");
                String str2 = inputLong();
                if(str2.equals(".")){
                    return;
                }
                long answer = Long.parseLong(str2);
                if(answer == 1){
                    bookService.addBook(book);
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            showBookList(bookService.getAllBooks());
            System.out.println("---------Done---------");
            checkActionContinueAddBook = isContinue("tiếp tục Menu Thêm Sách","(quay lại Menu Book");
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
    public void showBookListHeader(){
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
        System.out.printf(
                "┃%-5s┃%-50s┃%-20s┃%12s┃%10s┃%10s┃%-16s┃\n",
                centerString(5, "."),
                centerString(50, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount"),
                centerString(16, " Date Add"));
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }

    public void showBook(Book book){
        showBookListHeader();
        System.out.println(book.toViewer());
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public void showBookList(){
        showBookListHeader();
        for (Book book : bookService.getAllBooks()){
            System.out.println(book.toViewer());
        }
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void showBookList(List<Book> books){
        showBookListHeader();
        for (Book book : books){
            System.out.println(book.toViewer());
        }
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
