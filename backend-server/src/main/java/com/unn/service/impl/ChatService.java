package com.unn.service.impl;

import com.unn.model.Chat;
import com.unn.model.Message;
import com.unn.repository.ChatRepo;
import com.unn.service.IChatService;
import java.util.HashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final ChatRepo chatRepo;

    @Override
    public Optional<Chat> createChat(Chat chat) {
        chatRepo.save(chat);
        return Optional.of(chat);
    }

    @Override
    public Optional<Chat> findChat(Long doctorId, Long patientId) {
        return chatRepo.findByDoctorIdAndPatientId(doctorId, patientId);
    }

    @Override
    public Optional<Chat> findChat(Long chatId) {
        return chatRepo.findById(chatId);
    }

    @Override
    public Optional<Chat> deleteChat(Long chatId) {
        Optional<Chat> chat = chatRepo.findById(chatId);
        if (chat.isPresent()) chatRepo.deleteById(chatId);
        return chat;
    }

    @Override
    public Optional<Chat> newMessage(Long chatId, Message message) {
        Optional<Chat> chat = chatRepo.findById(chatId);
        if (chat.isPresent() && message != null) {
            if (chat.get().getMessageHistory() == null) {
                chat.get().setMessageHistory(new HashMap<Long, Message>());
            }
            chat.get().getMessageHistory().put(message.getId(), message);
            chatRepo.save(chat.get());
        }
        return chat;
    }
}
