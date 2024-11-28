import React, { useState } from 'react'

const App = () => {
  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);
  const [userAddress, setUserAddress] = useState("");

  const [realtimeLatitude, setRealtimeLatitude] = useState(0);
  const [realtimeLongitude, setRealtimeLongitude] = useState(0);

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
    let url = `https://api.opencagedata.com/geocode/v1/json?key=d922a9a260a74f4aa6b69aabc0dd8a33&q=${latitude}%2C+${longitude}&pretty=1&no_annotations=1`
    const loc = await fetch(url);
    const data = await loc.json();
    setUserAddress(data.results[0].formatted)
    console.log("User Address: ", userAddress);
  }

  const handleGetUserAddress = () => {
    getUserAddress();
  }

  // Get real-time user current location
  geo.watchPosition(userRealtimeCoords);
  function userRealtimeCoords(position) {
    let userRealtimeLatitude = position.coords.latitude;
    let userRealtimeLongitude = position.coords.longitude;
    setRealtimeLatitude(userRealtimeLatitude);
    setRealtimeLongitude(userRealtimeLongitude);
    console.log(realtimeLatitude);
    console.log(realtimeLongitude);

  }

  return (
    <div>
      <h1>Current Location</h1>
      <h2>latitude- {latitude}</h2>
      <h2>longitude- {longitude}</h2>
      <h2>user address- {userAddress}</h2>
      <button onClick={handleGetUserAddress}>Get Address</button>
      <hr />
      <h1>Realtime Tracking</h1>
      <h2>Latitude - {realtimeLatitude}</h2>
      <h2>Longitude - {realtimeLongitude}</h2>
    </div>
  )
}

export default App