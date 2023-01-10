package com.softcode.employeemanagement.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.softcode.employeemanagement.model.Note;

public interface FirebaseMessagingService {
    String sendNotification(Note note, String token) throws FirebaseMessagingException;
}
