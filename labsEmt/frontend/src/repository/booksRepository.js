import axios from "../custom-axios/axios";

const booksRepository = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    fetchBooks: () => {
        return axios.get("/books")
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name,category,author,availableCopies) => {
        return axios.post("/books/add",{
            "name": name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    },
    editBook: (id,name,category,author,availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },
    rentBook: (id) => {
        return axios.put(`/books/rent/${id}`);
    }
}

export default booksRepository;