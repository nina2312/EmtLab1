import React from "react";
import {Link} from "react-router-dom";

const booksTerm = (props) => {
    return(
        <tr>
            <td>{props.book.name}</td>
            <td>{props.book.category}</td>
            <td>{props.book.author.name}</td>
            <td>{props.book.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"} onClick={() => props.onDelete(props.book.id)}>Delete</a>
                <Link className={"btn btn-info ms-1 me-1"} onClick={() => props.onEdit(props.book.id)} to={`/books/edit/${props.book.id}`}>Edit</Link>
                <a title={"Mark as Taken"} className={"btn btn-secondary"} onClick={() => props.onMark(props.book.id)}>Mark as Taken</a>
            </td>
        </tr>
    );
}

export default booksTerm;