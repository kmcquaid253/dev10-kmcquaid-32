import Movie from "../Movie/Movie";

function Movies({movies}){

    return(

    <table className="table table-striped">
        <thead className="thead-dark">
            <tr>
                <th>Movie Id</th>
                <th>Title</th>
                <th>Release Year</th>
            </tr>
        </thead>
        <tbody>
            {movies.map(m =>
            <Movie id={m.id} title={m.title} releaseYear={m.releaseYear}/>
            )}
        </tbody>
    </table>
)
}

export default Movies;