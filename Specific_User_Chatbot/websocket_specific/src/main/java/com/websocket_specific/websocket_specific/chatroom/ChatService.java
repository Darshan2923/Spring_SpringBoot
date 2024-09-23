package com.websocket_specific.websocket_specific.chatroom;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

    @Autowired
    private final ChatRoomRepository chatRoomRepository;

    // public Optional<String> getChatRoomId(String senderId, String recipientId,
    // boolean createNewRoomIfNotExists) {
    // return chatRoomRepository.findBySenderIdAndRecipientId(senderId,
    // recipientId).or(()->{
    // .map(ChatRoom::getChatRoomId)
    // if(createNewRoomIfNotExists){

    // }
    // return Optional.empty();
    // });
    // }
}
