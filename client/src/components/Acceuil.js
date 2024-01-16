import React, { useState, useEffect, useRef } from "react";
import { Popup, Marker, MapContainer, TileLayer, Polyline } from 'react-leaflet';
import osm from "../map/osm-providers";
import "leaflet/dist/leaflet.css";
import LocationMarker from "../map/LocationMarker";
import icon from "../map/constants";
import RestaurantService from "../services/restaurant.service";
import axios from 'axios';

const Acceuil = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [currentLocation, setCurrentLocation] = useState([]);
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [path, setPath] = useState([]);
  const mapRef = useRef(null);

  useEffect(() => {
    retrieveRestaurants();
    getCurrentLocation();
  }, []);

  const retrieveRestaurants = () => {
    RestaurantService.getAll()
      .then(response => {
        setRestaurants(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  };

  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          setCurrentLocation([latitude, longitude]);
        },
        (error) => {
          console.error('Error getting current location:', error);
        }
      );
    } else {
      console.error('Geolocation is not supported by your browser');
    }
  };

  const fetchRoute = async (waypoints) => {
    const apiKey = '5b3ce3597851110001cf624831834c563bfe4b128cf21b3cc7d0811f'; // Replace with your API key
    const apiUrl = `/api/v2/directions/driving-car?api_key=${apiKey}`;


    try {
      const response = await axios.post(apiUrl, {
        coordinates: waypoints,
        format: 'geojson',
      });

      if (response.data && response.data.features && response.data.features.length > 0) {
        const route = response.data.features[0].geometry.coordinates;
        setPath(route);
      } else {
        console.error('Invalid response from OpenRouteService:', response);
      }
    } catch (error) {
      console.error('Error fetching route:', error);
    }
  };

  const setActiveRestaurant = (restaurant) => {
    setSelectedRestaurant(restaurant);

    if (mapRef.current && currentLocation.length === 2) {
      const waypoints = [
        [currentLocation[1], currentLocation[0]],
        [parseFloat(restaurant.longi), parseFloat(restaurant.lati)],
      ];

      fetchRoute(waypoints);
    }
  };

  return (
    <div>
      <div>
        <MapContainer
          ref={mapRef}
          center={currentLocation.length === 2 ? [currentLocation[0], currentLocation[1]] : [49.1951, 16.6068]}
          zoom={13}
          scrollWheelZoom
          style={{ height: "100vh" }}
        >
          <TileLayer url={osm.maptiler.url} attribution={osm.maptiler.attribution} />
          {restaurants.map((restaurant) => (
            <Marker
              key={restaurant.id}
              position={[parseFloat(restaurant.lati), parseFloat(restaurant.longi)]}
              icon={icon}
              riseOnHover
            >
              <Popup>
                {restaurant.nom}<br />
                <button onClick={() => setActiveRestaurant(restaurant)}>
                  Show Itinerary
                </button>
              </Popup>
            </Marker>
          ))}
          {path.length > 0 && <Polyline positions={path} color="blue" />}
          <LocationMarker />
        </MapContainer>
      </div>
      <div>
        {/* Add your Carousel component or other UI elements here */}
      </div>
      <footer style={{ backgroundColor: "#f7f7f7", padding: "10px", textAlign: "center", border: "1px solid #666" }}>
        <p>Copyright © 2024 BESS . All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Acceuil;
