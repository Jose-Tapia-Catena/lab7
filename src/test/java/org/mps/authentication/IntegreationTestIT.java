package org.mps.authentication;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegreationTestIT {
    @Test
    public void userRegistrationTop(){
        Date date = Mockito.mock(Date.class);
        PasswordString passwordString = Mockito.mock(PasswordString.class);
        CredentialValidator credentialValidator = Mockito.mock(CredentialValidator.class);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);

        Mockito.when(date.validate()).thenReturn(true);
        Mockito.when(passwordString.validate()).thenReturn(true);
        Mockito.when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        Mockito.when(credentialStore.credentialExists(date,passwordString)).thenReturn(false);

        UserRegistration ur = new UserRegistration();
        ur.register(date,passwordString,credentialStore,credentialValidator);
        Mockito.verify(credentialStore, Mockito.times(1)).register(date, passwordString);
    }

    @Test
    public void userRegistrationTopWithDate(){
        Date date = new Date(5,10,2012);
        PasswordString passwordString = Mockito.mock(PasswordString.class);
        CredentialValidator credentialValidator = Mockito.mock(CredentialValidator.class);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);

        Mockito.when(passwordString.validate()).thenReturn(true);
        Mockito.when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        Mockito.when(credentialStore.credentialExists(date,passwordString)).thenReturn(false);

        UserRegistration ur = new UserRegistration();
        ur.register(date,passwordString,credentialStore,credentialValidator);
        Mockito.verify(credentialStore, Mockito.times(1)).register(date, passwordString);
    }

    @Test
    public void userRegistrationTopWithDateAndPassword(){
        Date date = new Date(5,10,2012);
        PasswordString passwordString = new PasswordString("?HolaHola2");
        CredentialValidator credentialValidator = Mockito.mock(CredentialValidator.class);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);

        Mockito.when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        Mockito.when(credentialStore.credentialExists(date,passwordString)).thenReturn(false);

        UserRegistration ur = new UserRegistration();
        ur.register(date,passwordString,credentialStore,credentialValidator);
        Mockito.verify(credentialStore, Mockito.times(1)).register(date, passwordString);
    }

    @Test
    public void userRegistrationTopWithDateAndPasswordAndCredentialValidator(){
        Date date = new Date(5,10,2012);
        PasswordString passwordString = new PasswordString("?HolaHola2");
        CredentialValidator credentialValidator = null;

        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
        Mockito.when(credentialStore.credentialExists(date,passwordString)).thenReturn(false);

        UserRegistration ur = new UserRegistration();
        ur.register(date,passwordString,credentialStore,credentialValidator);
        Mockito.verify(credentialStore, Mockito.times(1)).register(date, passwordString);
    }

    @Test
    public void userRegistrationTopWithDateAndPasswordAndCredentialValidatorAndCredentialStore(){
        Date date = new Date(5,10,2012);
        PasswordString passwordString = new PasswordString("?HolaHola2");
        CredentialValidator credentialValidator = null;

        CredentialStore credentialStore = new CredentialStoreSet();

        UserRegistration ur = new UserRegistration();
        ur.register(date,passwordString,credentialStore,credentialValidator);

       assertThat(credentialStore.credentialExists(date,passwordString)).isTrue();
    }
}
