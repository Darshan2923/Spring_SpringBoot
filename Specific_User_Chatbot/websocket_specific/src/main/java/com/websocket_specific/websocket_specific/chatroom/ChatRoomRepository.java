package com.websocket_specific.websocket_specific.chatroom;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
