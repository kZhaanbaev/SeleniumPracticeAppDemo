package tests;

import com.github.javafaker.Faker;
import data.pojos.User;

public class Temp {
    public static void main(String[] args) {
        Faker faker = new Faker();
        User mentor = new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.phoneNumber().cellPhone(),
                "test@test.com",
                "mentor"
        );

        System.out.println(mentor.getEmail());
        System.out.println(mentor.getLastName());
    }
}
