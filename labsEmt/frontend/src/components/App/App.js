import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router,Routes,Route,Navigate} from 'react-router-dom';
import Authors from "../Authors/Authors";
import Categories from "../Categories/Categories";
import Countries from "../Countries/Countries";
import Books from "../Books/BooksList/Books";
import Header from "../Header/Header";
import booksRepository from "../../repository/booksRepository";
import BooksAdd from "../Books/BooksAdd/BooksAdd";
import BooksEdit from "../Books/BooksEdit/BooksEdit";
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            countries: [],
            books: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"/countries"} element={<Countries countries={this.state.countries}/>}/>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/books/add"} element={<BooksAdd categories={this.state.categories} authors={this.state.authors} onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element={<BooksEdit authors={this.state.authors} categories={this.state.categories} onEditBook={this.editBook} book={this.state.selectedBook} />}/>
                            <Route path={"/books"} element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} onMark={this.rentBook}/>}/>
                            <Route path={"/"} element={<Navigate to={"/books"}/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    loadAuthors = () => {
        booksRepository.fetchAuthors().then((data) => {this.setState({authors: data.data})});
    }

    loadCategories = () => {
        booksRepository.fetchCategories().then((data) => {this.setState({categories: data.data})})
    }

    loadCountries = () => {
        booksRepository.fetchCountries().then((data) => {this.setState({countries: data.data})});
    }

    loadBooks = () => {
        booksRepository.fetchBooks().then((data) => {this.setState({books: data.data})});
    }

    getBook = (id) => {
        booksRepository.getBook(id).then((data) => {this.setState({selectedBook: data.data})});
    }

    deleteBook = (id) => {
        booksRepository.deleteBook(id).then(() => {this.loadBooks();});
    }

    addBook = (name, category, author, availableCopies) => {
        booksRepository.addBook(name, category, author, availableCopies).then(() => {this.loadBooks();});
    }

    editBook = (id, name, category, author, availableCopies) => {
        booksRepository.editBook(id, name, category, author, availableCopies).then(() => {this.loadBooks();});
    }

    rentBook = (id) => {
        booksRepository.rentBook(id).then(() => {this.loadBooks();});
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadCategories();
        this.loadCountries();
        this.loadBooks();
    }
}

export default App;
