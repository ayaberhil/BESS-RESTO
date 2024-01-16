import React, { Component } from "react";
import "bootstrap";
import RestaurantService from "../services/restaurant.service";
import SpecialiteService from "../services/specialite.service";
import VilleService from "../services/ville.service";
import SerieService from "../services/serie.service";
import ZoneService from "../services/zone.service";

class RestaurantsList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchZone = this.onChangeSearchZone.bind(this);
    this.onChangeSearchVille = this.onChangeSearchVille.bind(this);
    this.retrieveRestaurants = this.retrieveRestaurants.bind(this);
    this.retrieveVilles = this.retrieveVilles.bind(this);
    this.retrieveZones = this.retrieveZones.bind(this);
    this.setActiveRestaurant = this.setActiveRestaurant.bind(this);
    this.handleSearch = this.handleSearch.bind(this);
    this.updateNoRestaurantsMessage = this.updateNoRestaurantsMessage.bind(this);

    this.state = {
      originalRestaurants: [], // Store the original unfiltered restaurants
      restaurants: [],
      specialities: [],
      series: [],
      zonesByVille: [],
      villes: [],
      currentRestau: null,
      currentIndex: -1,
      isDisabled: true,
      searchVille: "",
      searchZone: "",
      searchSerie: "",
      searchSpecialite: "",
      noRestaurantsMessage: "No restaurants available.", // Default message
    };
  }

  componentDidMount() {
    this.retrieveRestaurants();
    this.retrieveVilles();
    this.retrieveSpecialities();
    this.retrieveSeries();
  }

  // Add this method to update the originalRestaurants state
  updateOriginalRestaurants(restaurants) {
    this.setState({
      originalRestaurants: restaurants,
    });
  }

  onChangeSearchSpecialite(e) {
    const searchSpecialite = e.target.value;
    this.setState({
      searchSpecialite: searchSpecialite,
    });
  }

  onChangeSearchVille(e) {
    const searchVille = e.target.value;
    this.setState({
      searchVille: searchVille,
      isDisabled: false,
    });

    this.retrieveZones(e);
  }

  onChangeSearchZone(e) {
    const searchZone = e.target.value;
    this.setState({
      searchZone: searchZone,
    });
  }

  onChangeSearchSerie(e) {
    const searchSerie = e.target.value;
    this.setState({
      searchSerie: searchSerie,
    });
  }

  handleSearch(e) {
    e.preventDefault();

    const { searchVille, searchZone } = this.state;

    if (searchVille && searchZone) {
      // If both ville and zone are specified, fetch restaurants by both
      RestaurantService.getAllByVilleAndZone(searchVille, searchZone)
        .then((response) => {
          const restaurants = Array.isArray(response.data) ? response.data : [];
          this.setState({
            restaurants: restaurants,
          });
          this.updateNoRestaurantsMessage(restaurants);
        })
        .catch((error) => {
          console.log(error);
          this.updateNoRestaurantsMessage([]);
        });
    } else if (searchVille) {
      // If only ville is specified, fetch restaurants by ville only
      RestaurantService.getAllByVille(searchVille)
        .then((response) => {
          const restaurants = Array.isArray(response.data) ? response.data : [];
          this.setState({
            restaurants: restaurants,
          });
          this.updateNoRestaurantsMessage(restaurants);
        })
        .catch((error) => {
          console.log(error);
          this.updateNoRestaurantsMessage([]);
        });
    }
    // Add additional conditions for other search criteria if needed
  }

  // Add this method to update the message when there are no restaurants
  updateNoRestaurantsMessage(restaurants) {
    if (restaurants.length === 0) {
      this.setState({
        noRestaurantsMessage: "No restaurants available.",
      });
    } else {
      this.setState({
        noRestaurantsMessage: "",
      });
    }
  }

  retrieveRestaurants() {
    RestaurantService.getAll()
      .then((response) => {
        // Update both the originalRestaurants and the current restaurants state
        this.setState({
          originalRestaurants: response.data,
          restaurants: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  retrieveSpecialities() {
    SpecialiteService.getAll()
      .then((response) => {
        this.setState({
          specialities: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  retrieveSeries() {
    SerieService.getAll()
      .then((response) => {
        this.setState({
          series: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  retrieveVilles() {
    VilleService.getAll()
      .then((response) => {
        this.setState({
          villes: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  retrieveZones(e) {
    const searchVille = e.target.value;
    ZoneService.getAllByVille(parseInt(searchVille))
      .then((response) => {
        this.setState({
          zonesByVille: response.data,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  setActiveRestaurant(restaurant, index) {
    this.setState({
      currentRestau: restaurant,
      currentIndex: index,
    });
  }

  render() {
    const { restaurants, villes, zonesByVille } = this.state;

    return (
      <div className="row">
        <div className="card">
          <form className="row gy-2 gx-3" onSubmit={this.handleSearch}>
            {/* filter by villes */}
            <div className="col">
              <label htmlFor="searchVille" className="col col-form-label">
                Villes
              </label>
              <div className="col">
                <select
                  id="searchVille"
                  className="form-select"
                  value={this.state.searchVille}
                  onChange={this.onChangeSearchVille}
                >
                  <option value="">Choisir ville</option>
                  {villes.map((ville) => (
                    <option value={ville.id} key={ville.id}>
                      {ville.nom}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* filter by zones */}
            <div className="col">
              <label htmlFor="searchZone" className="col col-form-label">
                Zones
              </label>
              <div className="col">
                <select
                  id="searchZone"
                  className="form-select"
                  value={this.state.searchZone}
                  onChange={this.onChangeSearchZone}
                  disabled={this.state.isDisabled}
                >
                  <option value="">Choisir zone</option>
                  {(Array.isArray(zonesByVille) ? zonesByVille : []).map((zone) => (
                    <option value={zone.id} key={zone.id}>
                      {zone.nom}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* ... (other filters) */}

            <div className="d-grid gap-2 d-md-block">
              <button className="btn btn-secondary" type="submit">
                Rechercher
              </button>
            </div>
          </form>

          {restaurants.length === 0 ? (
            <p className="mt-3">{this.state.noRestaurantsMessage}</p>
          ) : (
            <table className="table mt-3">
           <thead style={{ backgroundColor:"darkcyan", border: "1px solid #666" }}>
                <tr>
             
                  <th scope="col" style={{backgroundColor:"#5C659A", color:"white"}}>Nom</th>
                  <th scope="col" style={{backgroundColor:"#5C659A", color:"white"}}>Adresse</th>
                  <th scope="col" style={{backgroundColor:"#5C659A", color:"white"}}>Rank</th>
                  <th scope="col" style={{backgroundColor:"#5C659A", color:"white"}}>Ouvre</th>
                  <th scope="col" style={{backgroundColor:"#5C659A", color:"white"}}>Ferme</th>
                </tr>
              </thead>
              <tbody>
                {restaurants.map((restaurant) => (
                  <tr key={restaurant.id}>
                    <td>{restaurant.nom}</td>
                    <td>{restaurant.adresse}</td>
                    <td>{restaurant.rank}</td>
                    <td>{restaurant.hourop}</td>
                    <td>{restaurant.hourcl}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
        <footer style={{ backgroundColor: "#f7f7f7", padding: "10px", textAlign: "center", border: "1px solid #666" }}>
        <p>Copyright © 2024 BESS . All rights reserved.</p>
      </footer>
      </div>
    );
  }
}

export default RestaurantsList;
