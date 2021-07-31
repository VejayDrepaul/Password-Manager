import psycopg2
from config import config


def store_new_account(app_name, url, username, email, password):
    conn = None
    try:
        params = config()
        print("\nConnecting to PostgreSQL database...")
        conn = psycopg2.connect(**params)
        print("\nSuccessfully connected to PostgreSQL database!")
        print("\nPostgreSQL Database Version: ")
        curr.execute('SELECT version()')
        db_version = curr.fetchone()
        print(db_version)

        curr = conn.cursor()

        account_query = """INSERT INTO AccountLog (AppName, URL, Username, Email, Password) VALUES (%s, %s, %s, %s, %s)"""
        account_records = (app_name, url, username, email, password)

        curr.execute(account_query, account_records)

        conn.commit()
        curr.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print("Connection to database closed")
