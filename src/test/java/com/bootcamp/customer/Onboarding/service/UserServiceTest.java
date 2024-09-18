/*
package com.bootcamp.customer.Onboarding.service;

import com.bootcamp.customer.Onboarding.Repository.*;
import com.bootcamp.customer.Onboarding.Service.EmailService;
import com.bootcamp.customer.Onboarding.Service.OtpService;
import com.bootcamp.customer.Onboarding.Service.UserService;
import com.bootcamp.customer.Onboarding.exceptions.ValidationException;
import com.bootcamp.customer.Onboarding.model.Plans;
import com.bootcamp.customer.Onboarding.model.User;
import com.bootcamp.customer.Onboarding.model.UserDetailsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomerTypeRepository customerTypeRepository;

    @MockBean
    private UserPlansRepository userPlansRepository;

    @MockBean
    private PlansRepository plansRepository;

    @MockBean
    private EmailService emailService;

    @MockBean
    private DocumentRepository documentRepository;

    @MockBean
    private OtpService otpService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User(1L, "testuser", "encodedPassword", "test@example.com", "1234567890", 1, null, null);
    }

    @Test
    public void testRegisterUserSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.registerUser("testuser", "password", "test@example.com", "1234567890", 1);
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    public void testRegisterUserFailure() {
        // Mocking the userRepository to return a non-null user indicating the user already exists
        when(userRepository.findByEmail(anyString())).thenReturn(testUser);

        // Verify that ValidationException is thrown when trying to register a user that already exists
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            userService.registerUser("testuser", "password", "test@example.com", "1234567890", 1);
        });

        assertEquals("User already exists", thrown.getMessage());
    }


    @Test
    public void testLoginUserSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        User result = userService.loginUser("testuser", "password");
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    public void testLoginUserFailure() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        User result = userService.loginUser("testuser", "wrongpassword");
        assertNull(result);
    }

    @Test
    public void testUpdatePasswordSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        boolean result = userService.updatePassword("testuser", "oldpassword", "newpassword");
        assertTrue(result);
    }

    @Test
    public void testUpdatePasswordFailure() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        boolean result = userService.updatePassword("testuser", "wrongoldpassword", "newpassword");
        assertFalse(result);
    }

    @Test
    public void testAuthenticateSuccessWithPlans() {
        Long planId = 1L;
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(userPlansRepository.findPlanIdByUserId(anyLong())).thenReturn(planId);
        when(plansRepository.findByPlanId(anyLong())).thenReturn(new Plans(1L, "Plan Name", "Plan Description", 100.0, 30, null));
        when(documentRepository.isDocumentVerified(anyLong())).thenReturn(true);

        UserDetailsDTO result = userService.authenticate("testuser", "password");
        assertNotNull(result);
        assertEquals("Plan Name", result.getPlan_name());
    }

    @Test
    public void testAuthenticateSuccessWithoutPlans() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(userPlansRepository.findPlanIdByUserId(anyLong())).thenReturn(null);
        when(documentRepository.isDocumentVerified(anyLong())).thenReturn(true);

        UserDetailsDTO result = userService.authenticate("testuser", "password");
        assertNotNull(result);
        assertNull(result.getPlan_name());
    }

    @Test
    public void testAuthenticateFailure() {
        when(userRepository.findByUsername(anyString())).thenReturn(testUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        UserDetailsDTO result = userService.authenticate("testuser", "wrongpassword");
        assertNull(result);
    }
}
*/
