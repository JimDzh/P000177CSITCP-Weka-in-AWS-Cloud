import React from 'react';
import ReactDOM from 'react-dom';
import { CookiesProvider } from "react-cookie";

import App from './App';

// injecting the entire react app inside the file 
// and triggering the div with id 'root'
ReactDOM.render(
    <CookiesProvider>
        <App />
    </CookiesProvider>,
    document.getElementById('root')
);
