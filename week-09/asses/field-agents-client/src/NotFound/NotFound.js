import "./NotFound.css";

import React, { Component } from 'react';

// image import
import CartIcon from './mikke.png';

function NotFound() {


    return (
       
    

      <main>
        <h1 id='titleOne'>404</h1>
        <h2 id='titleTwo'>PAGE NOT FOUND</h2>
        <img src={CartIcon} id='mikePic'/>
        <h4 id='titleThree'>Oops! It seems like you may have selected the wrong door.</h4>
      </main>

    );
  }
  
  export default NotFound;