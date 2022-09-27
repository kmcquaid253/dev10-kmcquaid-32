import React from 'react';
import Heading from './Heading/Heading';
import Movies from './Movies/Movies';
import Numbers from './Numbers/Numbers';

function App() {
  let movies =[];

    movies[0] = {id: 1, title: "Endgame", releaseYear: 2019};
    movies[1] = {id: 2, title: "Legally Blonde", releaseYear: 2003};
    movies[2] = {id: 3, title: "Blonde", releaseYear: 2015};
    movies[3] = {id: 4, title: "Clueless", releaseYear: 1997};

  return (
  <div className="container">
    <h2>Ramping Up On React</h2>
    <Heading message={"Bye, world!"}/>
    <Heading message={"Hello, world!"}/>
    <Heading message={"Adios, world!"}/>
    <Heading message={"Hola, world!"}/>
    <Heading message={"Cheerio, world!"}/>

    <Numbers min={10} max={20}/>

    <Movies movies={movies}/>
  </div>
  );
}

export default App;
