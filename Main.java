package com.controller;
 
 
import com.model.Person;
import com.service.PersonService;
import com.service.PersonServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Instantiate the service and controller
        PersonService personService = new PersonServiceImpl();
        PersonController personController = new PersonController(personService);
       
        // Create a new Person
        Long id = 1L; // Example ID
        Person newPerson = personController.addPerson(id, "John", "Doe", "123 Street", "City", "State", "12345", "555-1234");
        System.out.println("Added Person: " + newPerson);
        System.out.println();
        // Read and display the details of the Person
        personController.showPersonDetails(id);

        // Update the person's details
        newPerson.setCity("New City");
        Person updatedPerson = personController.updatePersonDetails(newPerson);
        System.out.println("Updated Person: " + updatedPerson);
        System.out.println();

        // Delete the person
        boolean isDeleted = personController.removePerson(id);
        if (isDeleted) {
            System.out.println("Person deleted successfully.");
        } else {
            System.out.println("Failed to delete Person.");
        }
    }
}
