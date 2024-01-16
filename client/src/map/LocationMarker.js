import React, { useEffect, useState } from "react";
import { Marker, Popup, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import "leaflet-routing-machine";

import icon from "./constants";

function LocationMarker({ selectedRestaurant }) {
  const [position, setPosition] = useState(null);
  const [bbox, setBbox] = useState([]);
  const [routeControl, setRouteControl] = useState(null);

  const map = useMap();

  useEffect(() => {
    map.locate().on("locationfound", function (e) {
      setPosition(e.latlng);
      map.flyTo(e.latlng, map.getZoom());
      const radius = e.accuracy;
      const circle = L.circle(e.latlng, radius);
      circle.addTo(map);
      setBbox(e.bounds.toBBoxString().split(","));

      // Create a routing control when the user's location is found
      if (selectedRestaurant && routeControl === null) {
        const waypoints = [
          L.latLng(e.latlng.lat, e.latlng.lng),
          L.latLng(selectedRestaurant.lat, selectedRestaurant.lng),
        ];

        const control = L.Routing.control({
          waypoints,
          routeWhileDragging: true,
        });

        control.addTo(map);
        setRouteControl(control);
      }
    });

    // Remove the routing control when the component is unmounted
    return () => {
      if (routeControl) {
        map.removeControl(routeControl);
      }
    };
  }, [map, selectedRestaurant, routeControl]);

  return position === null ? null : (
    <Marker position={position} icon={icon} eventHandlers={{
      mouseover: (event) => event.target.openPopup(),
    }}>
      <Popup>
        You are here. <br />
        Map bbox: <br />
        <b>Southwest lng</b>: {bbox[0]} <br />
        <b>Southwest lat</b>: {bbox[1]} <br />
        <b>Northeast lng</b>: {bbox[2]} <br />
        <b>Northeast lat</b>: {bbox[3]}
      </Popup>
    </Marker>
  );
}

export default LocationMarker;
