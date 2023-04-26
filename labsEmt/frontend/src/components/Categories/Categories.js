import React from 'react';

const categories = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <table className={"table table-striped"}>
                    <thead>
                        <tr>
                            <th scope={"col"}>Category</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.categories.map((category) => {
                            return(
                                <tr>
                                    <td>{category}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default categories;