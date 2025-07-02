package org.example.lmsbackend.model;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {
    @Test
    void testSetAndGetProperties() {
        User user = new User();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        user.setUserId(1);
        user.setUsername("john_doe");
        user.setPassword("securePassword");
        user.setEmail("john@example.com");
        user.setFullName("John Doe");
        user.setRole(User.Role.student);
        user.setVerificationToken("abc123");
        user.setVerified(true);
        user.setVerifiedAt(now);

        assertEquals(1, user.getUserId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("securePassword", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("John Doe", user.getFullName());
        assertEquals(User.Role.student, user.getRole());
        assertEquals("abc123", user.getVerificationToken());
        assertTrue(user.isVerified());
        assertEquals(now, user.getVerifiedAt());
    }

    @Test
    void testPrePersistSetsTimestamps() {
        User user = new User();
        user.onCreate(); // giả lập tạo mới

        assertNotNull(user.getCreatedAt(), "createdAt should be set");
        assertNotNull(user.getUpdatedAt(), "updatedAt should be set");
        assertEquals(user.getCreatedAt(), user.getUpdatedAt(), "createdAt và updatedAt nên bằng nhau lúc mới");
    }

    @Test
    void testPreUpdateSetsUpdatedAt() throws InterruptedException {
        User user = new User();
        user.onCreate();
        Timestamp beforeUpdate = user.getUpdatedAt();

        Thread.sleep(10); // đợi chút để updatedAt có giá trị khác
        user.onUpdate();
        Timestamp afterUpdate = user.getUpdatedAt();

        assertTrue(afterUpdate.after(beforeUpdate), "updatedAt phải sau khi update");
    }

    @Test
    void testRoleEnumValues() {
        assertEquals(User.Role.valueOf("admin"), User.Role.admin);
        assertEquals(User.Role.valueOf("instructor"), User.Role.instructor);
        assertEquals(User.Role.valueOf("student"), User.Role.student);
    }
}
