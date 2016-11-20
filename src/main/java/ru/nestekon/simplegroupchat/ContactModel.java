package ru.nestekon.simplegroupchat;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {

	private static ContactModel sContactModel;
    private List<Contact> mContacts;
    
    public static ContactModel get()
    {
        if(sContactModel == null)
        {
            sContactModel = new ContactModel();
        }
        return  sContactModel;
    }
    
    private ContactModel()
    {
        mContacts = new ArrayList<>();
        populateWithInitialContacts();

    }
    
    private void populateWithInitialContacts()
    {
        Contact contact1 = new Contact("zoza@izuba.tech");
        mContacts.add(contact1);
        Contact contact2 = new Contact("User2@server.com");
        mContacts.add(contact2);
        Contact contact3 = new Contact("User3@server.com");
        mContacts.add(contact3);
        Contact contact4 = new Contact("User4@server.com");
        mContacts.add(contact4);
        Contact contact5 = new Contact("User5@server.com");
        mContacts.add(contact5);
        Contact contact6 = new Contact("User6@server.com");
        mContacts.add(contact6);
        Contact contact7 = new Contact("User7@server.com");
        mContacts.add(contact7);
    }
    
    public List<Contact> getContacts()
    {
        return mContacts;
    }
}
