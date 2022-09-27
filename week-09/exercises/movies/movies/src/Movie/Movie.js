function Movie({id, title, releaseYear}){

    return(
    <tr>
        <td>{id}</td>
        <td>{title}</td>
        <td>{releaseYear}</td>
    </tr>
    );
}

export default Movie;