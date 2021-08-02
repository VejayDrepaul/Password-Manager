import os
import getpass
from dotenv import load_dotenv
from database_manager import *
from account_notes import *

def menu():
    print("------------------------------");
    print("-------------Menu-------------");
    print("\n");
    print("1. Enter an account to be stored in the database")
    print("2. Find account information for a specific site or app")
    print("3. List all accounts")
    print("4. Change account information in database")
    print("5. Delete an account from the database")
    print("6. Export account information as file")
    print("7. Create notes for accounts")
    print("8. Read all notes")
    print("9. Exit");
    option = int(input("\nWhat do you want to do: "))


    if option == 1:
        website_name = input("What is the name of the website or app: ")
        link = input("What is the link to the website (if applicable): ")
        username = input("What is the username of the account: ")
        email = input("What is the email used for the account: ")
        password = getpass.getpass("What is the password for your account: ")
        store_new_account(website_name, link, username, email, password)
    elif option == 2:
        find_accounts()
    elif option == 3:
        list_accounts()
    elif option == 5:
        appname = input("What is the name of the account you want to delete: ")
        delete_account(appname)
    elif option == 6:
        file_type = input("Do you want the passwords in a (c)sv or (e)xcel file: ")
        if file_type == 'c':
            csv()
        elif file_type == 'e':
            xlsx()
    elif option == 7:
        take = input("Type what note you want to add: \n")
        create_notes(take)
    elif option == 8:
        read_notes()
    if option == 9:
        exit()


def login():
    load_dotenv()
    MASTER_PASSWORD = os.getenv('MASTER_PASSWORD')

    password = getpass.getpass("Please provide the master password: ")

    if password == MASTER_PASSWORD:
        menu()
