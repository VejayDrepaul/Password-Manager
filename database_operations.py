import psycopg2
from config import config


def connect():
    try:
        params = config()
        connection = psycopg2.connect(**params)
        return connection
    except(Exception, psycopg2.DatabaseError) as error:
        print(error)


def store_new_account(app_name, url, username, email, password):
    try:
        connection = connect()
        cursor = connection.cursor()
        account_query = """INSERT INTO AccountLog (AppName, URL, Username, Email, Password) VALUES (%s, %s, %s, %s, %s)"""
        account_records = (app_name, url, username, email, password)
        cursor.execute(account_query, account_records)
        connection.commit()
    except(Exception, psycopg2.Error) as error:
        print(error)


def find_accounts():
    try:
        connection = connect()
        cursor = connection.cursor()
        website_name = input("What is the name of the website or app for the account: ")
        print("\n")
        print("Account Information:")
        find = """ SELECT * FROM accountlog WHERE appname = '""" + website_name + "'"
        cursor.execute(find, website_name)
        connection.commit()
        result = cursor.fetchone()
        print(result)
    except(Exception, psycopg2.Error) as error:
        print(error)


def list_accounts():
    try:
        connection = connect()
        cursor = connection.cursor()
        cursor.execute("SELECT * FROM accountlog")
        connection.commit()
        result = cursor.fetchall()
        print(result)
    except(Exception, psycopg2.Error) as error:
        print(error)


def delete_account(account_name):
    try:
        connection = connect()
        cursor = connection.cursor()
        delete = f"DELETE FROM accountlog WHERE appname = '{account_name}'"
        cursor.execute(delete)
        connection.commit()
    except(Exception, psycopg2.Error) as error:
        print(error)


def csv():
    try:
        connection = connect()
        cursor = connection.cursor()
        f_output = open('passwords.csv', 'w')
        b = "COPY (SELECT * FROM accountlog) TO STDOUT WITH CSV HEADER"
        t_path = "passwords.csv"
        with open(t_path, 'w') as f_output:
            cursor.copy_expert(b, f_output)
    except(Exception, psycopg2.Error) as error:
        print(error)


def xlsx():
    try:
        connection = connect()
        cursor = connection.cursor()
        f_output = open('passwords.xlsx', 'w')
        b = "COPY (SELECT * FROM accountlog) TO STDOUT WITH CSV HEADER"
        t_path = "passwords.xlsx"
        with open(t_path, 'w') as f_output:
            cursor.copy_expert(b, f_output)
    except(Exception, psycopg2.Error) as error:
        print(error)
