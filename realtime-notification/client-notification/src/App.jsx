// App.js
import React, { useEffect, useState } from 'react';
import { connectWebSocket, disconnectWebSocket } from './services/WebSocketService';

function App() {
  const [notifications, setNotifications] = useState([]);
  const userId = '123'; // Replace this with the actual user ID

  // Handle incoming notifications
  const handleNotification = (notification) => {
    setNotifications((prev) => [...prev, notification]);
  };

  useEffect(() => {
    // Connect to WebSocket when component mounts
    connectWebSocket(userId, handleNotification);

    // Disconnect when component unmounts
    return () => disconnectWebSocket();
  }, [userId]);

  return (
    <div className="App">
      <h1>Real-time Notifications</h1>
      <ul>
        {notifications.map((notification, index) => (
          <li key={index}>
            <strong>{notification.status}</strong>: {notification.message} ({notification.notesTitle})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
