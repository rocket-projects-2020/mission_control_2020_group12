import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import Landing from "./components/Landing";
import Instructions from "./components/Instructions";
import Simulation from "./components/Simulation";
import Weather from "./components/Weather";

/**
 *  This page house the single page application (SPA).
 *  We use the DOM to control routing between pages.
 */
function App() {
  return (
    <Router>
      <Switch>
        <Route path="/Dashboard">
          <Dashboard></Dashboard>
        </Route>
        <Route path="/Instructions">
          <Instructions></Instructions>
        </Route>
        <Route path="/Weather">
          <Weather></Weather>
        </Route>
        <Route path="/Simulation">
          <Simulation></Simulation>
        </Route>
        <Route path="/">
          <Landing></Landing>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
