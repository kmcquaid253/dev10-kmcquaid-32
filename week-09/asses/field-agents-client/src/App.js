import React from 'react';
import Agents from './Agents/Agents';
import Home from './Home/Home';
import NavBar from './NavBar/NavBar';
import EditAgent from './EditAgent/EditAgent';
import AddAgent from './AddAgent/AddAgent';
import NotFound from './NotFound/NotFound';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import DeleteAgent from './DeleteAgent/DeleteAgent';

function App() {
  return (
    <div className='App container'>
      <BrowserRouter>
      <NavBar/>
        <Switch>
          <Route exact path="/">
            <Home/>
          </Route>
          <Route path="/agents">
            <Agents/>
          </Route>
          <Route path="/agent/edit/:id">
            <EditAgent/>
          </Route>
          <Route path="/agent/delete/:id">
            <DeleteAgent/>
          </Route>
          <Route path="/agent/add">
            <AddAgent/>
          </Route>
          <Route>
            <NotFound />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
