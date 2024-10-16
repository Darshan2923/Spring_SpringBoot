import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

let stompClient = null;

export const connectWebSocket = (userId, onNotification) => {
    // Connect to your backend WebSocket endpoint via SockJS
    const socket = new SockJS('http://localhost:8080/ws');
    stompClient = new Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000, // Try to reconnect every 5 seconds if disconnected
        onConnect: () => {
            console.log('Connected to WebSocket');
            // Subscribe to the user-specific notification channel
            stompClient.subscribe(`/user/${userId}/notification`, (message) => {
                const notification = JSON.parse(message.body);
                onNotification(notification); // Call the callback with the notification
            });
        },
        onStompError: (frame) => {
            console.error('WebSocket Error:', frame);
        },
    });

    stompClient.activate();
};

export const disconnectWebSocket = () => {
    if (stompClient) {
        stompClient.deactivate();
    }
};
