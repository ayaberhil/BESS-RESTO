import React, { Suspense, lazy, Component } from "react";
import { Route, Routes, BrowserRouter, Router } from "react-router-dom";

import './App.css';
import './assets/style/theme.css'
import './assets/style/style.css'
import './assets/style/custom.css'
import './assets/style/main.css'
import "bootstrap/dist/css/bootstrap.min.css";
import Acceuil from "./components/Acceuil";
import RestaurantsList from "./components/RestaurantsList";
import Navbar from "./components/sidebar";


class App extends Component {
  render() {
    return (
      <div className="page-wrapper overflow-auto chiller-theme toggled">
        <a id="show-sidebar" className="btn btn-sm btn-dark" href="#"> <i className="fas fa-bars"></i></a>
        <Navbar></Navbar>
        <main className="container mt-3">
            <div className=" container-fluid">
              <Routes>
                <Route path="/projet" element={<Acceuil />} />
                <Route path="/projet/list" element={<RestaurantsList />} />
              </Routes>
            </div>
        </main>
      </div>
    );
  }
}

export default App;
