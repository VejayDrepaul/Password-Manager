import os
from dotenv import load_dotenv
from database_manager import *

def menu():
    print("------------------------------");
    print("-------------Menu-------------");
    print("\n");
    print("1. Enter an account to be stored in the database");
    print("2. Find a account information for a site or app");
    print("3. Change account information in database");
    print("4. Delete an account from the database");
    print("5. Generate a password for an account");
    print("6. Exit");
    option = int(input("\nWhat do you want to do: "))

    if option == 1:
        website_name = input("What is the name of the website or app: ")
        link = input("What is the link to the website or app: ")
        username = input("What is the username of the account: ")
        email = input("What is the email used for the account: ")
        password = input("What is the password for your account: ")

        store_new_account(website_name, link, username, email, password)


def login():
    load_dotenv()
    MASTER_PASSWORD = os.getenv('MASTER_PASSWORD')

    password = input("Please provide the master password: ")

    if password == MASTER_PASSWORD:
        menu()
