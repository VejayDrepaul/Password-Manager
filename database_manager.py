import psycopg2
from config import config


def connect():
    try:
        params = config()
        print("\nConnecting to PostgreSQL database...")
        connection = psycopg2.connect(**params)
        return connection
    except (Exception, psycopg2.DatabaseError) as error:
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
