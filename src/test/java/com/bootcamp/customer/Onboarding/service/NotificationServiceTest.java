/*package com.bootcamp.customer.Onboarding.service;

import com.bootcamp.customer.Onboarding.Repository.NotificationRepository;
import com.bootcamp.customer.Onboarding.Repository.PlansRepository;
import com.bootcamp.customer.Onboarding.Repository.UserPlansRepository;
import com.bootcamp.customer.Onboarding.Repository.UserRepository;
import com.bootcamp.customer.Onboarding.Service.EmailService;
import com.bootcamp.customer.Onboarding.Service.NotificationService;
import com.bootcamp.customer.Onboarding.exceptions.ResourceNotFoundException;
import com.bootcamp.customer.Onboarding.model.Notification;
import com.bootcamp.customer.Onboarding.model.NotificationId;
import com.bootcamp.customer.Onboarding.model.Plans;
import com.bootcamp.customer.Onboarding.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlansRepository plansRepository;

    @Mock
    private UserPlansRepository userPlansRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNotification_Success() {
        Long userId = 1L;
        Long planId = 1L;

        User user = new User();
        user.setUserId(userId);

        Plans plan = new Plans();
        plan.setPlanId(planId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(plansRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(notificationRepository.save(any(Notification.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Notification notification = notificationService.createNotification(userId, planId);

        assertNotNull(notification);
        assertEquals(userId, notification.getId().getUserId());
        assertEquals(planId, notification.getId().getPlanId());
        verify(notificationRepository).save(any(Notification.class));
    }

    @Test
    public void createNotification_UserNotFound() {
        Long userId = 1L;
        Long planId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            notificationService.createNotification(userId, planId);
        });

        assertEquals("User not found for userId :" + userId, thrown.getMessage());
    }

    @Test
    public void notificationSentForUser_Success() {
        Long userId = 1L;
        Long planId = 1L;

        User user = new User();
        user.setUserId(userId);
        user.setEmail("test@example.com");

        Plans plan = new Plans();
        plan.setPlanId(planId);
        plan.setPlan_name("Basic Plan");

        Notification notification = new Notification(new NotificationId(userId, planId), user, plan, true);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userPlansRepository.findPlanIdsByUserId(userId)).thenReturn(Collections.singletonList(planId));
        when(plansRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(notificationRepository.findById(new NotificationId(userId, planId))).thenReturn(Optional.of(notification));

        notificationService.notificationSentForUser(userId);

        verify(emailService).sendEmailNotification(user.getEmail(), plan);
        verify(notificationRepository).save(notification);
    }

    @Test
    public void notificationSentForUser_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            notificationService.notificationSentForUser(userId);
        });

        assertEquals("User not found for userId :" + userId, thrown.getMessage());
    }

    @Test
    public void documentVerificationSuccess_Success() {
        Long userId = 1L;

        User user = new User();
        user.setUserId(userId);
        user.setEmail("test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        notificationService.documentVerificationSuccess(userId);

        verify(emailService).sendDocumentNotification(user.getEmail());
    }

    @Test
    public void documentVerificationSuccess_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            notificationService.documentVerificationSuccess(userId);
        });

        assertEquals("User not found for document verification userId: " + userId, thrown.getMessage());
    }
}
*/