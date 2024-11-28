import React, { useState } from 'react'

const App = () => {
  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);

  const geo = navigator.geolocation;

  // Get user current location
  geo.getCurrentPosition(userCoords);
  function userCoords(position) {
    let userLatitude = position.coords.latitude;
    let userLongitude = position.coords.longitude;
    setLatitude(userLatitude);
    setLongitude(userLongitude);
  }

  const getUserAddress = async () => {
    let url = `https://api.opencagedata.com/geocode/v1/json?key=${process.env.REACT_APP_API_KEY}&q=${latitude}%2C+${longitude}&pretty=1&no_annotations=1`
    const loc = await fetch(url);
    const data = await loc.json();
    console.log("User Address: ", data);
  }

  const handleGetUserAddress = () => {
    getUserAddress();
  }

  return (
    <div>
      <h1>Current Location</h1>
      <h2>latitude- {latitude}</h2>
      <h2>longitude- {longitude}</h2>
      <button onClick={handleGetUserAddress}>Get Address</button>
    </div>
  )
}

export default App