import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Book {
    public int Id;
    public String Title;
    public String Author;
    public String Publisher;
    public int Quantity;
    public static List<Book> BookList = new ArrayList<Book>();
    public Book(int id, String title, String author, String publisher, int quantity) {
        Id = id;
        Title = title;
        Author = author;
        Publisher = publisher;
        Quantity = quantity;
    }
    public Book() {
    }
    public void addBook(String title, String author, String publisher, int quantity) {
        Book book = new Book(BookList.size() + 1, title, author, publisher, quantity);
        BookList.add(book);
    }

    
    public int getId(){
        return Id;
    }

    public static void editBook(int id, Book book) {
        for (Book b : BookList) {
            if (b.Id == id) {
                b.Title = book.Title;
                b.Author = book.Author;
                b.Publisher = book.Publisher;
                b.Quantity = book.Quantity;
                break;
            }
        }
    }
    public static void deleteBook(int id) {
        for (Book book : BookList) {
            if (book.Id == id) {
                BookList.remove(book);
                break;
            }
        }
    }

    // Method to get a specific student by ID
    public static Book getBookById(int id) {
        for (Book book : BookList) {
            if (book.Id == id) {
                return book;
            }
        }
        return null;
    }

    public static List<Book> getList() {
        return BookList;
    }

    public static void ListToCsv() {
        try {
            FileWriter csvWriter = new FileWriter("Book.csv");
            csvWriter.append("Id,Title,Author,Publisher,Quantity\n");
            for (Book book : BookList) {
                csvWriter.append(String.join(",", String.valueOf(book.Id), book.Title, book.Author, book.Publisher, String.valueOf(book.Quantity)));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CsvToList() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("Book.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Book book = new Book(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]));
                BookList.add(book);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
