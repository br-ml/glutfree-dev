package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.UserRoleEntity;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;
import eu.glutfree.glutfree.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class GlutfreeDBUserServiceTest {

    private GlutfreeDBUserService serviceToTest;

    //1 Mock za vsichki dependansita

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new GlutfreeDBUserService(mockUserRepository);
    }

    @Test
    public void testUserNotFound() {

        Assertions.assertThrows(
                UsernameNotFoundException.class, () -> {
                    serviceToTest.loadUserByUsername("user_does_not_exist");
                }
        );
    }

    @Test
    void testExistingUser() {
        //Инструктираме мокЮзърепото, когато го питат с findbyusername, da vryshta userentiti
        //1 pravim si userentit

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername("brani");
        userEntity.setPassword("brani1");
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);

        UserRoleEntity adminRoleEntity = new UserRoleEntity();
        adminRoleEntity.setRole(UserRoleEnum.ADMIN);

        userEntity.setRoles(List.of(userRoleEntity,adminRoleEntity));

        //2 Инструктираме Мока

        Mockito.when(mockUserRepository.findByUsername("brani")).thenReturn(Optional.of(userEntity));

        //testa
        UserDetails userDetails = serviceToTest.loadUserByUsername("brani");


        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        List<String> authorities = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
    }

}
