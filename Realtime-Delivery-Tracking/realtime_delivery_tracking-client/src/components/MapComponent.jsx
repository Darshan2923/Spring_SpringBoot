import React, { useEffect, useState } from 'react'
import { GoogleMap, Marker, LoadScript } from "@react-google-maps/api";
import vehicleIcon from "../assets/vehicle_icon.jpg"

const containerStyle = {
    width: "100%",
    height: "400px",
};


// Center of the map, somewhere between New York and New Jersey
const center = {
    lat: 40.71836,
    lng: -74.1040,
};

const MapComponent = () => {

    const [currentLocation, setCurrentLocation] = useState(null);

    // Function to fetch current location from the backend
    const fetchLocation = () => {
        fetch("http://locahost:8082/location")
            .then((response) => response.json())
            .then((data) => {
                const [lat, lon] = data.location.split(",").map(Number);
                setCurrentLocation({ lat, lon });
            })
            .catch((e) => console.error("Error fetching location: ", e));
    };

    useEffect(() => {
        // Fetch the location every second to simulate real-time movement
        const interval = setInterval(fetchLocation, 1000);
        return () => clearInterval(interval);
    }, []);

    return (
        <LoadScript googleMapsApiKey='YOUR_GOOGLE_MAPS_API_KEY'>
            <GoogleMap mapContainerStyle={containerStyle} center={center} zoom={10}>
                {
                    currentLocation && (
                        <Marker
                            position={currentLocation}
                            icon={{
                                url: vehicleIcon,
                                scaledSize: new window.google.maps.Size(50, 50),
                            }}
                        />
                    )
                }
            </GoogleMap>
        </LoadScript>
    )
}

export default MapComponent